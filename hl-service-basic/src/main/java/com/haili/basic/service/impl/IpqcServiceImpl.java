package com.haili.basic.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.IpqcMapper;
import com.haili.basic.mapper.OutboundOrderRawItemMapper;
import com.haili.basic.service.IIpqcService;
import com.haili.framework.domain.basic.Ipqc;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
import com.haili.framework.domain.basic.QcDefect;
import com.haili.framework.domain.basic.response.IpqcCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.utils.HlOauth2Util;
import com.haili.framework.utils.WorkflowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-04-02
 */
@Service
@Transactional
public class IpqcServiceImpl extends ServiceImpl<IpqcMapper, Ipqc> implements IIpqcService {
    @Autowired
    OutboundOrderRawItemMapper outboundOrderRawItemMapper;
    @Autowired
    QcDefectServiceImpl qcDefectServiceImpl;

    @Override
    public IPage<Ipqc> page(IPage<Ipqc> page, Wrapper<Ipqc> queryWrapper) {
        IPage<Ipqc> page1 = this.baseMapper.getPage(page, queryWrapper);
        return page1;
    }

    @Override
    public List<Ipqc> list(Wrapper<Ipqc> queryWrapper) {
        List<Ipqc> list = this.baseMapper.getList(queryWrapper);
        return list;
    }

    @Override
    public boolean saveOrUpdate(Ipqc entity) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HlOauth2Util hlOauth2Util = new HlOauth2Util();
        HlOauth2Util.UserJwt userJwt = hlOauth2Util.getUserJwtFromHeader(request);
        if (userJwt == null) {
            ExceptionCast.cast(CommonCode.UNAUTHENTICATED);
        }
        List<String> roles = userJwt.getRoles();
//        String id = userJwt.getId();
        String staffId = userJwt.getStaffId();
        String name = userJwt.getName();
        boolean isQcInspector = roles.contains("qcInspector");
        boolean isQcChecker = roles.contains("qcChecker");
        boolean isAdmin = roles.contains("admin");
        if (!isQcInspector && !isQcChecker && !isAdmin) {
            ExceptionCast.cast(CommonCode.UNAUTHORISE);
        }
        Integer status = entity.getStatus();
        if (status != null && status != 0) {
            if (isQcInspector && status == 1) {
                entity.setInspector(staffId);
                entity.setInspectorName(name);
                entity.setStatus(1);
            }
            if (isQcChecker && status == 2) {
                entity.setChecker(staffId);
                entity.setCheckerName(name);
                entity.setStatus(2);
            }
        } else {
            if (isQcInspector) {
                entity.setInspector(null);
                entity.setInspectorName(null);
            }
            if (isQcChecker) {
                entity.setChecker(null);
                entity.setCheckerName(null);
            }
            entity.setStatus(null);
        }

        String operation = entity.getOperation();
        String productNumber = entity.getProductNumber();
        String id = entity.getId();
        if (!StringUtils.isEmpty(id)) {
            Ipqc ipqc = this.baseMapper.selectById(id);
            if (ipqc == null) {
                ExceptionCast.cast(IpqcCode.IPQC_NOT_EXIST);
            }
            String operation1 = ipqc.getOperation();
            String productNumber1 = ipqc.getProductNumber();
            if ((StringUtils.isEmpty(operation) && operation.equals(operation1))
                    || (StringUtils.isEmpty(productNumber) && !productNumber.equals(productNumber1))) {
                ExceptionCast.cast(IpqcCode.COIL_CURRENT_PROCESS_CANNOT_BE_MODIFIED);
            }
        }

        String inspectorResult = entity.getInspectorResult();
        if (!StringUtils.isEmpty(inspectorResult) && status != null && status != 0) {
            QueryWrapper<OutboundOrderRawItem> wrapper = new QueryWrapper<>();
            wrapper.eq("product_number", productNumber);
            wrapper.eq("current_operation_label", operation);
            OutboundOrderRawItem outboundOrderRawItem = outboundOrderRawItemMapper.selectOne(wrapper);
            if (outboundOrderRawItem != null) {
                String jsonTextWorkflow = outboundOrderRawItem.getJsonTextWorkflow();
                Map nextWorkflowContext = WorkflowUtil.getNextWorkflowContext(jsonTextWorkflow, operation, inspectorResult);
                if (nextWorkflowContext != null) {
                    String label = (String) nextWorkflowContext.get("label");
                    outboundOrderRawItem.setNextOperationLabel(label);
                    outboundOrderRawItemMapper.updateById(outboundOrderRawItem);
                }

            }

        }
        super.saveOrUpdate(entity);
        String ipqcId = entity.getId();
        List<QcDefect> defectList = JSON.parseArray(entity.getDefectList(), QcDefect.class);
        defectList = defectList.stream().filter(defect -> !StringUtils.isEmpty(defect.getDefectCode())).map(defect -> {
            return defect.setId(null).setIpqcId(ipqcId);
        }).collect(Collectors.toList());
        LambdaQueryWrapper<QcDefect> lambdaQueryWrapper = Wrappers.<QcDefect>lambdaQuery();
        lambdaQueryWrapper.eq(QcDefect::getIpqcId, ipqcId);
        qcDefectServiceImpl.remove(lambdaQueryWrapper);
        qcDefectServiceImpl.saveBatch(defectList);
        return true;
    }

}

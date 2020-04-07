package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.IpqcMapper;
import com.haili.basic.mapper.OutboundOrderRawItemMapper;
import com.haili.basic.service.IIpqcService;
import com.haili.framework.domain.basic.Ipqc;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
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
        if (isQcInspector || isAdmin) {
            entity.setInspector(staffId);
            entity.setInspectorName(name);
        }
        if (isQcChecker || isAdmin) {
            entity.setChecker(staffId);
            entity.setCheckerName(name);
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
        if (!StringUtils.isEmpty(inspectorResult)) {
            QueryWrapper<OutboundOrderRawItem> wrapper = new QueryWrapper<>();
            wrapper.eq("product_number", entity.getProductNumber());
            wrapper.eq("current_operation_label", operation);
            OutboundOrderRawItem outboundOrderRawItem = outboundOrderRawItemMapper.selectOne(wrapper);
            if (outboundOrderRawItem == null) {
                ExceptionCast.cast(IpqcCode.IPQC_INSPECTOR_RESULT_CANNOT_BE_MODIFIED);
            }
            String jsonTextWorkflow = outboundOrderRawItem.getJsonTextWorkflow();
            Map nextWorkflowContext = WorkflowUtil.getNextWorkflowContext(jsonTextWorkflow, operation, inspectorResult);
            if (nextWorkflowContext == null) {
                ExceptionCast.cast(CommonCode.INVALID_PARAM);
            }
            String label = (String) nextWorkflowContext.get("label");
/*
            Integer index = (Integer) nextWorkflowContext.get("index");
            outboundOrderRawItem.setCurrentOperationLabel(label);
            outboundOrderRawItem.setCurrentOperationIndex(index);
*/

            outboundOrderRawItem.setNextOperationLabel(label);
            outboundOrderRawItem.setNextOperationStatus(0);
            outboundOrderRawItemMapper.updateById(outboundOrderRawItem);
        }
        return super.saveOrUpdate(entity);
    }

}

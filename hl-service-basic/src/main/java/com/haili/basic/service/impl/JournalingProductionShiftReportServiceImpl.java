package com.haili.basic.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.*;
import com.haili.basic.service.IJournalingProductionShiftReportService;
import com.haili.framework.domain.basic.*;
import com.haili.framework.domain.basic.response.JournalingProductionShiftReportCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.utils.HlOauth2Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-20
 */
@Service
@Transactional
public class JournalingProductionShiftReportServiceImpl extends ServiceImpl<JournalingProductionShiftReportMapper, JournalingProductionShiftReport> implements IJournalingProductionShiftReportService {

    @Autowired
    OutboundOrderRawItemMapper outboundOrderRawItemMapper;
    @Autowired
    JournalingRewindItemMapper journalingRewindItemMapper;
    @Autowired
    JournalingRollingMillItemMapper journalingRollingMillItemMapper;
    @Autowired
    JournalingAnnealItemMapper journalingAnnealItemMapper;
    @Autowired
    JournalingFinishingTensionLevelerItemMapper journalingFinishingTensionLevelerItemMapper;

    @Override
    public IPage<JournalingProductionShiftReport> page(IPage<JournalingProductionShiftReport> page, Wrapper<JournalingProductionShiftReport> queryWrapper) {
        IPage<JournalingProductionShiftReport> page1 = this.baseMapper.getPage(page, queryWrapper, queryWrapper.getEntity());
        return page1;
    }

    @Override
    public List<JournalingProductionShiftReport> list(Wrapper<JournalingProductionShiftReport> queryWrapper) {
        List<JournalingProductionShiftReport> list = this.baseMapper.getList(queryWrapper, queryWrapper.getEntity());
        return list;
    }

    @Override
    public boolean save(JournalingProductionShiftReport entity) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HlOauth2Util hlOauth2Util = new HlOauth2Util();
        HlOauth2Util.UserJwt userJwt = hlOauth2Util.getUserJwtFromHeader(request);
        if (userJwt == null) {
            ExceptionCast.cast(CommonCode.UNAUTHENTICATED);
        }
        Integer type = entity.getType();
        if (type == null || type > 3 || type < 0) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        List<String> roles = userJwt.getRoles();
        Boolean[] bArr = {roles.contains("rewindShiftLeader"),
                roles.contains("rollingMillShiftLeader"),
                roles.contains("annealShiftLeader"),
                roles.contains("finishingTensionLevelerShiftLeader")
        };
        if (!bArr[type]) {
            ExceptionCast.cast(CommonCode.UNAUTHORISE);
        }
        entity.setStatus(0);
        return super.save(entity);
    }

    @Override
    public boolean updateById(JournalingProductionShiftReport entity) {
        JournalingProductionShiftReport journalingProductionShiftReport = this.baseMapper.selectById(entity.getId());
        if (journalingProductionShiftReport == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HlOauth2Util hlOauth2Util = new HlOauth2Util();
        HlOauth2Util.UserJwt userJwt = hlOauth2Util.getUserJwtFromHeader(request);
        if (userJwt == null) {
            ExceptionCast.cast(CommonCode.UNAUTHENTICATED);
        }
        Integer type = entity.getType();
        if (type == null || type > 3 || type < 0 || type != journalingProductionShiftReport.getType()) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        List<String> roles = userJwt.getRoles();
        Boolean[] isShiftLeaderArr = {roles.contains("rewindShiftLeader"),
                roles.contains("rollingMillShiftLeader"),
                roles.contains("annealShiftLeader"),
                roles.contains("finishingTensionLevelerShiftLeader")
        };
        boolean isSupervisor = roles.contains("supervisor");
        boolean isInspector = roles.contains("inspector");
        if (!isShiftLeaderArr[type] && !isSupervisor && !isInspector) {
            ExceptionCast.cast(CommonCode.UNAUTHORISE);
        }
        String shiftLeader = entity.getShiftLeader();
        String supervisor = entity.getSupervisor();
        String inspector = entity.getInspector();
        String shiftLeader1 = journalingProductionShiftReport.getShiftLeader();
        String supervisor1 = journalingProductionShiftReport.getSupervisor();
        String inspector1 = journalingProductionShiftReport.getInspector();
        Integer status = journalingProductionShiftReport.getStatus();
        Boolean[] bArr1 = {isShiftLeaderArr[type], isSupervisor, isInspector};
        Boolean[] bArr2 = {!StringUtils.isEmpty(shiftLeader), !StringUtils.isEmpty(supervisor), !StringUtils.isEmpty(inspector)};
        Boolean[] bArr3 = {!StrUtil.equals(shiftLeader, shiftLeader1),
                !StrUtil.equals(supervisor, supervisor1),
                !StrUtil.equals(inspector, inspector1)};
        String id = userJwt.getId();
        String name = userJwt.getName();
        for (int i = 0; i < status - 1; i++) {
            if (bArr2[i]) {
                ExceptionCast.cast(JournalingProductionShiftReportCode.PRODUCTION_SHIFT_REPORT_ALREADY_APPROVED);
            }
        }
        if (status == 0) {
            if (bArr1[0] && bArr2[0]) {
                entity.setShiftLeader(id);
                entity.setShiftLeaderName(name);
                entity.setStatus(1);
                updateOutboundOrderRawItemCurrentLabel(journalingProductionShiftReport, type);
            } else if (!bArr1[0] && bArr2[0]) {
                ExceptionCast.cast(CommonCode.UNAUTHORISE);
            }
        } else if (status == 1) {
            entity = new JournalingProductionShiftReport().setId(entity.getId());
            if (bArr1[1] && bArr2[1]) {
                entity.setSupervisor(id);
                entity.setSupervisorName(name);
                entity.setStatus(2);
            } else if (!bArr1[1] && bArr2[1]) {
                ExceptionCast.cast(CommonCode.UNAUTHORISE);
            } else if (bArr1[0] && bArr2[0] && !bArr3[0]) {
                entity.setShiftLeader(id);
                entity.setShiftLeaderName(name);
                entity.setStatus(1);
            } else {
                ExceptionCast.cast(JournalingProductionShiftReportCode.PRODUCTION_SHIFT_REPORT_ALREADY_APPROVED);
            }
        } else if (status == 2) {
            entity = new JournalingProductionShiftReport().setId(entity.getId());
            if (bArr1[2] && bArr2[2]) {
                entity.setInspector(id);
                entity.setInspectorName(name);
                entity.setStatus(3);
            } else if (!bArr1[2] && bArr2[2]) {
                ExceptionCast.cast(CommonCode.UNAUTHORISE);
            } else if (bArr1[1] && bArr2[1] && !bArr3[1]) {
                entity.setSupervisor(id);
                entity.setSupervisorName(name);
                entity.setStatus(2);
            } else {
                ExceptionCast.cast(JournalingProductionShiftReportCode.PRODUCTION_SHIFT_REPORT_ALREADY_APPROVED);
            }
        } else if (status == 3) {
            if (bArr1[2] && bArr2[2] && !bArr3[2]) {
                entity = new JournalingProductionShiftReport().setId(entity.getId());
                entity.setInspector(id);
                entity.setInspectorName(name);
                entity.setStatus(3);
            } else {
                ExceptionCast.cast(JournalingProductionShiftReportCode.PRODUCTION_SHIFT_REPORT_ALREADY_APPROVED);
            }
        }
        return super.updateById(entity);
    }

    private void updateOutboundOrderRawItemCurrentLabel(JournalingProductionShiftReport journalingProductionShiftReport, Integer type) {
        List<String> productNumbers = new ArrayList<>();
        String journalingItemType = "";
        if (type == 0) {
            journalingItemType = "重卷";
            LambdaQueryWrapper<JournalingRewindItem> lambdaQueryWrapper = Wrappers.lambdaQuery();
            lambdaQueryWrapper.eq(JournalingRewindItem::getDate, journalingProductionShiftReport.getDate())
                    .eq(JournalingRewindItem::getShiftId, journalingProductionShiftReport.getShiftId());
            List<JournalingRewindItem> journalingRewindItems = journalingRewindItemMapper.selectList(lambdaQueryWrapper);
            productNumbers = journalingRewindItems.stream().map(item -> item.getProductNumber()).collect(Collectors.toList());
        } else if (type == 1) {
            journalingItemType = "轧机";
            LambdaQueryWrapper<JournalingRollingMillItem> lambdaQueryWrapper = Wrappers.lambdaQuery();
            lambdaQueryWrapper.eq(JournalingRollingMillItem::getDate, journalingProductionShiftReport.getDate())
                    .eq(JournalingRollingMillItem::getShiftId, journalingProductionShiftReport.getShiftId());
            List<JournalingRollingMillItem> journalingRollingMillItems = journalingRollingMillItemMapper.selectList(lambdaQueryWrapper);
            productNumbers = journalingRollingMillItems.stream().map(item -> item.getProductNumber()).collect(Collectors.toList());
        } else if (type == 2) {
            journalingItemType = "退火炉";
            LambdaQueryWrapper<JournalingAnnealItem> lambdaQueryWrapper = Wrappers.lambdaQuery();
            lambdaQueryWrapper.eq(JournalingAnnealItem::getDate, journalingProductionShiftReport.getDate())
                    .eq(JournalingAnnealItem::getShiftId, journalingProductionShiftReport.getShiftId());
            List<JournalingAnnealItem> journalingAnnealItems = journalingAnnealItemMapper.selectList(lambdaQueryWrapper);
            productNumbers = journalingAnnealItems.stream().map(item -> item.getProductNumber()).collect(Collectors.toList());
        } else if (type == 3) {
            journalingItemType = "精整拉矫";
            LambdaQueryWrapper<JournalingFinishingTensionLevelerItem> lambdaQueryWrapper = Wrappers.lambdaQuery();
            lambdaQueryWrapper.eq(JournalingFinishingTensionLevelerItem::getDate, journalingProductionShiftReport.getDate())
                    .eq(JournalingFinishingTensionLevelerItem::getShiftId, journalingProductionShiftReport.getShiftId());
            List<JournalingFinishingTensionLevelerItem> journalingFinishingTensionLevelerItems = journalingFinishingTensionLevelerItemMapper.selectList(lambdaQueryWrapper);
            productNumbers = journalingFinishingTensionLevelerItems.stream().map(item -> item.getProductNumber()).collect(Collectors.toList());
        }
        LambdaUpdateWrapper<OutboundOrderRawItem> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        lambdaUpdateWrapper.in(OutboundOrderRawItem::getProductNumber, productNumbers)
                .eq(OutboundOrderRawItem::getNextOperationLabel, journalingItemType)
                .set(OutboundOrderRawItem::getCurrentOperationLabel, journalingItemType);
        outboundOrderRawItemMapper.update(null, lambdaUpdateWrapper);
    }

    @Override
    public boolean removeById(Serializable id) {
        JournalingProductionShiftReport journalingProductionShiftReport = this.baseMapper.selectById(id);
        if (journalingProductionShiftReport == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HlOauth2Util hlOauth2Util = new HlOauth2Util();
        HlOauth2Util.UserJwt userJwt = hlOauth2Util.getUserJwtFromHeader(request);
        if (userJwt == null) {
            ExceptionCast.cast(CommonCode.UNAUTHENTICATED);
        }
        Integer status = journalingProductionShiftReport.getStatus();
        if (status > 1) {
            ExceptionCast.cast(JournalingProductionShiftReportCode.PRODUCTION_SHIFT_REPORT_ALREADY_APPROVED);
        }
        List<String> roles = userJwt.getRoles();
        Boolean[] isShiftLeaderArr = {roles.contains("rewindShiftLeader"),
                roles.contains("rollingMillShiftLeader"),
                roles.contains("annealShiftLeader"),
                roles.contains("finishingTensionLevelerShiftLeader")
        };
        Integer type = journalingProductionShiftReport.getType();
        if (!isShiftLeaderArr[type]) {
            ExceptionCast.cast(CommonCode.UNAUTHORISE);
        }
        return super.removeById(id);
    }
}

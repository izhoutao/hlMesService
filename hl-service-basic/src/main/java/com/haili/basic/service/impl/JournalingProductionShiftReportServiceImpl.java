package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.JournalingProductionShiftReportMapper;
import com.haili.basic.service.IJournalingProductionShiftReportService;
import com.haili.framework.domain.basic.JournalingProductionShiftReport;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.utils.HlOauth2Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Override
    public IPage<JournalingProductionShiftReport> page(IPage<JournalingProductionShiftReport> page, Wrapper<JournalingProductionShiftReport> queryWrapper) {
        IPage<JournalingProductionShiftReport> page1 = this.baseMapper.getPage(page, queryWrapper);
        return page1;
    }

    @Override
    public List<JournalingProductionShiftReport> list(Wrapper<JournalingProductionShiftReport> queryWrapper) {
        List<JournalingProductionShiftReport> list = this.baseMapper.getList(queryWrapper);
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
        Boolean isUnauthorised = true;
        Boolean[] bArr1 = {isShiftLeaderArr[type], isSupervisor, isInspector};
        Boolean[] bArr2 = {!StringUtils.isEmpty(shiftLeader), !StringUtils.isEmpty(supervisor), !StringUtils.isEmpty(inspector)};
        for (int i = 2; i >= 0; i--) {
            if (bArr1[i]) {
                isUnauthorised = false;
                break;
            } else if (bArr2[i]) {
                break;
            }
        }
        if (isUnauthorised) {
            ExceptionCast.cast(CommonCode.UNAUTHORISE);
        }
        String id = userJwt.getId();
        if (bArr1[0] && bArr2[0] && StringUtils.isEmpty(journalingProductionShiftReport.getShiftLeader())) {
            entity.setShiftLeader(id);
            entity.setStatus(1);
        }
        if (bArr1[1] && bArr2[1] && StringUtils.isEmpty(journalingProductionShiftReport.getSupervisor())) {
            entity.setSupervisor(id);
            entity.setStatus(2);
        }
        if (bArr1[2] && bArr2[2] && StringUtils.isEmpty(journalingProductionShiftReport.getInspector())) {
            entity.setInspector(id);
            entity.setStatus(3);
        }
        return super.updateById(entity);
    }
}

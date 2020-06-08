package com.haili.basic.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.JournalingGrindItemMapper;
import com.haili.basic.mapper.JournalingGrindShiftReportMapper;
import com.haili.basic.service.IJournalingGrindShiftReportService;
import com.haili.framework.domain.basic.JournalingGrindShiftReport;
import com.haili.framework.domain.basic.response.JournalingShiftReportCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.utils.HlOauth2Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-06-08
 */
@Service
public class JournalingGrindShiftReportServiceImpl extends ServiceImpl<JournalingGrindShiftReportMapper, JournalingGrindShiftReport> implements IJournalingGrindShiftReportService {
    @Autowired
    JournalingGrindItemMapper journalingGrindItemMapper;

    @Override
    public List<JournalingGrindShiftReport> list(Wrapper<JournalingGrindShiftReport> queryWrapper) {
        List<JournalingGrindShiftReport> list = this.baseMapper.getList(queryWrapper, queryWrapper.getEntity());
        return list;
    }

    @Override
    public IPage<JournalingGrindShiftReport> page(IPage<JournalingGrindShiftReport> page, Wrapper<JournalingGrindShiftReport> queryWrapper) {
        IPage<JournalingGrindShiftReport> page1 = this.baseMapper.getPage(page, queryWrapper, queryWrapper.getEntity());
        return page1;
    }


    @Override
    public boolean save(JournalingGrindShiftReport entity) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HlOauth2Util hlOauth2Util = new HlOauth2Util();
        HlOauth2Util.UserJwt userJwt = hlOauth2Util.getUserJwtFromHeader(request);
        if (userJwt == null) {
            ExceptionCast.cast(CommonCode.UNAUTHENTICATED);
        }
        List<String> roles = userJwt.getRoles();
        if (!roles.contains("grindShiftLeader")) {
            ExceptionCast.cast(CommonCode.UNAUTHORISE);
        }
        entity.setStatus(0);
        return super.save(entity);
    }

    @Override
    public boolean updateById(JournalingGrindShiftReport entity) {
        JournalingGrindShiftReport journalingGrindShiftReport = this.baseMapper.selectById(entity.getId());
        String shiftId = journalingGrindShiftReport.getShiftId();
        LocalDate date = journalingGrindShiftReport.getDate();
        if (journalingGrindShiftReport == null
                || !shiftId.equals(entity.getShiftId())
                || !date.equals(entity.getDate())
        ) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HlOauth2Util hlOauth2Util = new HlOauth2Util();
        HlOauth2Util.UserJwt userJwt = hlOauth2Util.getUserJwtFromHeader(request);
        if (userJwt == null) {
            ExceptionCast.cast(CommonCode.UNAUTHENTICATED);
        }

        List<String> roles = userJwt.getRoles();
        Boolean isShiftLeader = roles.contains("grindShiftLeader");
        boolean isSupervisor = roles.contains("supervisor");
        boolean isInspector = roles.contains("inspector");
        if (!isShiftLeader && !isSupervisor && !isInspector) {
            ExceptionCast.cast(CommonCode.UNAUTHORISE);
        }
        String shiftLeader = entity.getShiftLeader();
        String supervisor = entity.getSupervisor();
        String inspector = entity.getInspector();
        String shiftLeader1 = journalingGrindShiftReport.getShiftLeader();
        String supervisor1 = journalingGrindShiftReport.getSupervisor();
        String inspector1 = journalingGrindShiftReport.getInspector();
        Integer status = journalingGrindShiftReport.getStatus();
        Boolean[] bArr1 = {isShiftLeader, isSupervisor, isInspector};
        Boolean[] bArr2 = {!StringUtils.isEmpty(shiftLeader), !StringUtils.isEmpty(supervisor), !StringUtils.isEmpty(inspector)};
        Boolean[] bArr3 = {!StrUtil.equals(shiftLeader, shiftLeader1),
                !StrUtil.equals(supervisor, supervisor1),
                !StrUtil.equals(inspector, inspector1)};
        String id = userJwt.getId();
        String name = userJwt.getName();
        for (int i = 0; i < status - 1; i++) {
            if (bArr2[i]) {
                ExceptionCast.cast(JournalingShiftReportCode.PRODUCTION_SHIFT_REPORT_ALREADY_APPROVED);
            }
        }
        if (status == 0) {
            if (bArr1[0] && bArr2[0]) {
                entity.setShiftLeader(id);
                entity.setShiftLeaderName(name);
                entity.setStatus(1);
            } else if (!bArr1[0] && bArr2[0]) {
                ExceptionCast.cast(CommonCode.UNAUTHORISE);
            }
        } else if (status == 1) {
            entity = new JournalingGrindShiftReport().setId(entity.getId());
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
                ExceptionCast.cast(JournalingShiftReportCode.PRODUCTION_SHIFT_REPORT_ALREADY_APPROVED);
            }
        } else if (status == 2) {
            entity = new JournalingGrindShiftReport().setId(entity.getId());
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
                ExceptionCast.cast(JournalingShiftReportCode.PRODUCTION_SHIFT_REPORT_ALREADY_APPROVED);
            }
        } else if (status == 3) {
            if (bArr1[2] && bArr2[2] && !bArr3[2]) {
                entity = new JournalingGrindShiftReport().setId(entity.getId());
                entity.setInspector(id);
                entity.setInspectorName(name);
                entity.setStatus(3);
            } else {
                ExceptionCast.cast(JournalingShiftReportCode.PRODUCTION_SHIFT_REPORT_ALREADY_APPROVED);
            }
        }
        status = entity.getStatus();
        if (status != null && status != 0) {
            updateJournalingItemStatus(date, shiftId, status, journalingGrindItemMapper);
        }
        return super.updateById(entity);
    }

    private <T> void updateJournalingItemStatus(LocalDate date, String shiftId, Integer status, BaseMapper<T> mapper) {
        UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("date", date)
                .eq("shift_id", shiftId)
                .set("status", status);
        mapper.update(null, updateWrapper);
    }

    @Override
    public boolean removeById(Serializable id) {
        JournalingGrindShiftReport journalingGrindShiftReport = this.baseMapper.selectById(id);
        if (journalingGrindShiftReport == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HlOauth2Util hlOauth2Util = new HlOauth2Util();
        HlOauth2Util.UserJwt userJwt = hlOauth2Util.getUserJwtFromHeader(request);
        if (userJwt == null) {
            ExceptionCast.cast(CommonCode.UNAUTHENTICATED);
        }
        Integer status = journalingGrindShiftReport.getStatus();
        if (status > 1) {
            ExceptionCast.cast(JournalingShiftReportCode.PRODUCTION_SHIFT_REPORT_ALREADY_APPROVED);
        }
        List<String> roles = userJwt.getRoles();
        if (!roles.contains("grindShiftLeader")) {
            ExceptionCast.cast(CommonCode.UNAUTHORISE);
        }
        updateJournalingItemStatus(journalingGrindShiftReport.getDate(),
                journalingGrindShiftReport.getShiftId(),
                0,
                journalingGrindItemMapper);
        return super.removeById(id);
    }


}

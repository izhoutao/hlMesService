package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.JournalingRewindReportMapper;
import com.haili.basic.service.IJournalingRewindReportService;
import com.haili.framework.domain.basic.JournalingRewindReport;
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
public class JournalingRewindReportServiceImpl extends ServiceImpl<JournalingRewindReportMapper, JournalingRewindReport> implements IJournalingRewindReportService {

    @Override
    public IPage<JournalingRewindReport> page(IPage<JournalingRewindReport> page, Wrapper<JournalingRewindReport> queryWrapper) {
        IPage<JournalingRewindReport> page1 = this.baseMapper.getPage(page, queryWrapper);
        return page1;
    }

    @Override
    public List<JournalingRewindReport> list(Wrapper<JournalingRewindReport> queryWrapper) {
        List<JournalingRewindReport> list = this.baseMapper.getList(queryWrapper);
        return list;
    }

    @Override
    public boolean save(JournalingRewindReport entity) {
        entity.setStatus(0);
        return super.save(entity);
    }

    @Override
    public boolean updateById(JournalingRewindReport entity) {
        JournalingRewindReport journalingRewindReport = this.baseMapper.selectById(entity.getId());
        if (journalingRewindReport == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HlOauth2Util hlOauth2Util = new HlOauth2Util();
        HlOauth2Util.UserJwt userJwt = hlOauth2Util.getUserJwtFromHeader(request);
        if (userJwt == null) {
            ExceptionCast.cast(CommonCode.UNAUTHENTICATED);
        }
        String shiftLeader = entity.getShiftLeader();
        String supervisor = entity.getSupervisor();
        String inspector = entity.getInspector();
        List<String> roles = userJwt.getRoles();
        Boolean isUnauthorised = true;
        Boolean[] bArr1 = {roles.contains("rewindShiftLeader"), roles.contains("supervisor"), roles.contains("inspector")};
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
        if (bArr1[0] && bArr2[0] && StringUtils.isEmpty(journalingRewindReport.getShiftLeader())) {
            entity.setShiftLeader(id);
            entity.setStatus(1);
        }
        if (bArr1[1] && bArr2[1] && StringUtils.isEmpty(journalingRewindReport.getSupervisor())) {
            entity.setSupervisor(id);
            entity.setStatus(2);

        }
        if (bArr1[2] && bArr2[2] && StringUtils.isEmpty(journalingRewindReport.getInspector())) {
            entity.setInspector(id);
            entity.setStatus(3);

        }
        return super.updateById(entity);
    }
}

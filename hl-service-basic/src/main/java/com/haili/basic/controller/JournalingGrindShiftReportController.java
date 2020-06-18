package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.JournalingGrindShiftReport;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.utils.HlOauth2Util;
import com.haili.framework.web.CrudController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-06-08
*/
@RestController
@RequestMapping("/basic/journalinggrindshiftreport")
public class JournalingGrindShiftReportController extends CrudController<JournalingGrindShiftReport> {
    @Override
    protected QueryWrapper<JournalingGrindShiftReport> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<JournalingGrindShiftReport> queryWrapper = super.extractWrapperFromRequestMap(map);
        queryWrapper.eq(!StringUtils.isEmpty(map.get("date")), "date", map.get("date"));
        queryWrapper.eq(!StringUtils.isEmpty(map.get("shiftId")), "shift_id", map.get("shiftId"));
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HlOauth2Util hlOauth2Util = new HlOauth2Util();
        HlOauth2Util.UserJwt userJwt = hlOauth2Util.getUserJwtFromHeader(request);
        if (userJwt == null) {
            ExceptionCast.cast(CommonCode.UNAUTHENTICATED);
        }
        List<String> roles = userJwt.getRoles();
        String role = (String) map.get("role");
        if (!roles.contains(role)) {
            ExceptionCast.cast(CommonCode.UNAUTHORISE);
        }
        Integer status = 0;
        if ("YMKZ".equals(role)) {
            status = 1;
        } else if ("CZ".equals(role)) {
            status = 2;
        }
        if (StringUtils.isEmpty(map.get("status"))) {
            queryWrapper.ge(StringUtils.isEmpty(map.get("status")), "status", status);
        } else {
            Integer status1 = (Integer) map.get("status");
            if (status1 < status) {
                ExceptionCast.cast(CommonCode.UNAUTHORISE);
            }
            queryWrapper.eq("status", status1);
        }
        return queryWrapper;
    }

    @Override
//    @PreAuthorize("hasAuthority('journaling_grind_shift_report_list')")
    public QueryResponseResult<JournalingGrindShiftReport> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('journaling_grind_shift_report_save')")
    public ModelResponseResult<JournalingGrindShiftReport> save(@RequestBody JournalingGrindShiftReport entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('journaling_grind_shift_report_update')")
    public ResponseResult updateById(@RequestBody JournalingGrindShiftReport entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('journaling_grind_shift_report_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}

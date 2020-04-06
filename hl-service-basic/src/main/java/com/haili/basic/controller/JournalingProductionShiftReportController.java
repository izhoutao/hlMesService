package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.JournalingProductionShiftReport;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.utils.HlOauth2Util;
import com.haili.framework.web.CrudController;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-20
 */
@RestController
@RequestMapping("/basic/journalingproductionshiftreport")
public class JournalingProductionShiftReportController extends CrudController<JournalingProductionShiftReport> {
    @Override
    public ModelResponseResult<JournalingProductionShiftReport> save(@RequestBody JournalingProductionShiftReport entity) {
        ModelResponseResult<JournalingProductionShiftReport> result = super.save(entity);
        HashMap<String, Object> map = new HashMap();
        map.put("id", entity.getId());
        map.put("type", entity.getType());
        QueryWrapper<JournalingProductionShiftReport> queryWrapper = super.extractWrapperFromRequestMap(map);
        queryWrapper.eq("id", entity.getId());
        List<JournalingProductionShiftReport> list = service.list(queryWrapper);
        if (list != null && list.size() > 0) {
            return new ModelResponseResult<JournalingProductionShiftReport>(CommonCode.SUCCESS, list.get(0));
        } else {
            return result;
        }
    }

    @Override
    protected QueryWrapper<JournalingProductionShiftReport> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<JournalingProductionShiftReport> queryWrapper = super.extractWrapperFromRequestMap(map);
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
        if ("supervisor".equals(role)) {
            status = 1;
        } else if ("inspector".equals(role)) {
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
        Integer type = (Integer) map.get("type");
        if (type == null || type > 3 || type < 0) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        switch (role) {
            case "rewindShiftLeader":
                queryWrapper.eq("type", 0);
                break;
            case "rollingMillShiftLeader":
                queryWrapper.eq("type", 1);
                break;
            case "annealShiftLeader":
                queryWrapper.eq("type", 2);
                break;
            case "finishingTensionLevelerShiftLeader":
                queryWrapper.eq("type", 3);
                break;
            default:
                queryWrapper.eq("type", type);
                break;
        }
        return queryWrapper;
    }
}

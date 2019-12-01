package com.haili.basic.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.haili.framework.utils.HlOauth2Util;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author dyz
 * @program boot-use
 * @create 2019-09-11 14:09
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        boolean hasSetterCreateTime = metaObject.hasSetter("createTime");
        if (hasSetterCreateTime) {
            setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
        boolean hasSetterUpdateTime = metaObject.hasSetter("updateTime");
        if (hasSetterUpdateTime) {
            setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
        boolean hasSetterCreatePerson = metaObject.hasSetter("createPerson");
        boolean hasSetterUpdatePerson = metaObject.hasSetter("updatePerson");
        if (hasSetterCreatePerson || hasSetterUpdatePerson) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HlOauth2Util.UserJwt userJwt = HlOauth2Util.getUserJwtFromHeader(request);
            if (hasSetterCreatePerson) {
                setInsertFieldValByName("createPerson", userJwt.getId(), metaObject);
            }
            if (hasSetterUpdatePerson) {
                setUpdateFieldValByName("updatePerson", userJwt.getId(), metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        boolean hasSetterUpdateTime = metaObject.hasSetter("updateTime");
        if (hasSetterUpdateTime) {
            setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
        boolean hasSetterUpdatePerson = metaObject.hasSetter("updatePerson");
        if (hasSetterUpdatePerson) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HlOauth2Util.UserJwt userJwt = HlOauth2Util.getUserJwtFromHeader(request);
            if (hasSetterUpdatePerson) {
                setUpdateFieldValByName("updatePerson", userJwt.getId(), metaObject);
            }
        }
    }
}

package com.haili.ucenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.ucenter.Menu;
import com.haili.framework.model.response.*;
import com.haili.framework.web.CrudController;
import com.haili.ucenter.service.impl.MenuServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-24
 */
@RestController
@RequestMapping("/ucenter/menu")
public class MenuController extends CrudController<Menu> {
    @Override
//    @PreAuthorize("hasAuthority('menu_list')")
    public QueryResponseResult<Menu> list(@RequestBody Map<String, Object> map) {
        Boolean bool = (Boolean) map.get("lazyLoad");
        List<Menu> list;
        QueryWrapper<Menu> queryWrapper = extractWrapperFromRequestMap(map);
        setOrderFromRequestMap(map, queryWrapper);
        if (bool != null && !bool) {
            list = ((MenuServiceImpl) service).listPreload(queryWrapper);
        } else {
            list = service.list(queryWrapper);
        }
        QueryResult<Menu> queryResult = new QueryResult<>();
        queryResult.setList(list);
        queryResult.setTotal(list.size());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    protected QueryWrapper<Menu> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(map.get("name")), "name", map.get("name"));
        return queryWrapper;
    }


    @Override
    @PreAuthorize("hasAuthority('menu_save')")
    public ModelResponseResult<Menu> save(@RequestBody Menu entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('menu_update')")
    public ResponseResult updateById(@RequestBody Menu entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('menu_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        Map<String, Object> map = new HashMap<>();
        map.put("pid", id);
        List<Menu> menuList = (List<Menu>) this.getService().listByMap(map);
        List<Serializable> idList = new ArrayList<>();
        idList.add(id);
        idList = ((MenuServiceImpl) this.getService()).getDeleteMenus(menuList, idList);
        this.getService().removeByIds(idList);
        return new ResponseResult(CommonCode.SUCCESS);

    }
}

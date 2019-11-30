package com.haili.ucenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.ucenter.Menu;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.QueryResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.web.CrudController;
import com.haili.ucenter.service.impl.MenuServiceImpl;
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
    public QueryResponseResult<Menu> list(@RequestBody Map<String, Object> map) {
        Boolean bool = (Boolean)map.get("lazyLoad");
        if (bool != null && !bool) {
            return super.list(map);
        } else {
            QueryResult<Menu> queryResult = new QueryResult<>();
            List<Menu> list = (List<Menu>)service.listByMap(new HashMap<>());
            queryResult.setList(list);
            queryResult.setTotal(list.size());
            return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        }
    }
    @Override
    protected QueryWrapper<Menu> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(map.get("name")), "name", map.get("name"));
        return queryWrapper;
    }
    @Override
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

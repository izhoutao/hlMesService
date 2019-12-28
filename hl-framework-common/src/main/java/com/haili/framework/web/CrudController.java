package com.haili.framework.web;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.TypeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haili.framework.model.response.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author Cenyol mail: 826025820@qq.com
 * @date 2019-11-17 11:21
 * <p>
 * provide the basic crud method
 */
@Slf4j
@Data
public class CrudController<T> {
    @Autowired
    protected IService<T> service;
    /**
     * 在搜索的时候，去除这几个 map 参数
     */
    public static String[] pageParams = {"size", "current", "orders"};

    /**
     * example:
     * {
     * "size": 15,            默认：10
     * "current": 1,          当前页码
     * "id": 1162,            SQL查询条件
     * "orders": ["id desc"]  排序条件，可以设置多个
     * }
     *
     * @param map
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public QueryResponseResult<T> list(@RequestBody Map<String, Object> map) {
        Page<T> page = extractPageFromRequestMap(map);
        QueryWrapper<T> queryWrapper = extractWrapperFromRequestMap(map);
        setOrderFromRequestMap(map, queryWrapper);
        QueryResult<T> queryResult = new QueryResult<>();
        if (page != null) {
            IPage<T> iPage = service.page(page, queryWrapper);
            queryResult.setList(iPage.getRecords());
            queryResult.setTotal(iPage.getTotal());
        } else {
            List<T> list = service.list(queryWrapper);
            queryResult.setList(list);
            queryResult.setTotal(list.size());
        }
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @PostMapping
    @ResponseBody
    public ModelResponseResult<T> save(@RequestBody T entity) {
        service.save(entity);
        return new ModelResponseResult<T>(CommonCode.SUCCESS, entity);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ModelResponseResult<T> getById(@PathVariable("id") Long id) {
        T obj = service.getById(id);
        return new ModelResponseResult<T>(CommonCode.SUCCESS, obj);
    }

    @PutMapping
    @ResponseBody
    public ResponseResult updateById(@RequestBody T entity) {
        service.updateById(entity);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        service.removeById(id);
        return new ResponseResult(CommonCode.SUCCESS);
    }


    /**
     * 从请求体中提取查询参数
     *
     * @param map
     * @return
     */
    protected QueryWrapper<T> extractWrapperFromRequestMap(Map<String, Object> map) {
        map.values().removeIf(value -> StringUtils.isEmpty(value));
        Type type = TypeUtil.getTypeArgument(this.getClass());
        Object bean = BeanUtil.fillBeanWithMap(map,
                ReflectUtil.newInstance(TypeUtil.getClass(type), new Object[0]),
                true, false);
        QueryWrapper<T> queryWrapper = new QueryWrapper<>((T) bean);
        return queryWrapper;

/*        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        for (String pageParam : pageParams) {
            map.remove(pageParam);
        }
        HashMap<String, Object> underlineMap = new HashMap<>();
        map.forEach((k, v) -> underlineMap.put(StringUtils.camelToUnderline(k), v));
        queryWrapper.allEq(underlineMap, false);
        return queryWrapper;*/

    }

    protected QueryWrapper<T> setOrderFromRequestMap(Map<String, Object> map, QueryWrapper<T> queryWrapper) {
        // 排序
        String key = pageParams[2];
        if (map.containsKey(key) && map.get(key) instanceof List) {
            for (String orderArrStr : (List<String>) map.get(key)) {
                if (StringUtils.isEmpty(orderArrStr) || !orderArrStr.contains(" ")) {
                    continue;
                }
                String[] orderArr = orderArrStr.split(" ");
                if ("desc".equals(orderArr[1])) {
                    queryWrapper.orderByDesc(orderArr[0]);
                } else {
                    queryWrapper.orderByAsc(orderArr[0]);
                }
            }
        }
        return queryWrapper;
    }

    /**
     * 从请求体中提取分页参数
     *
     * @param map
     * @return
     */
    protected Page<T> extractPageFromRequestMap(Map<String, Object> map) {

        Page<T> page = new Page<>();
        Boolean pageFlag = false;
        String key = pageParams[0];
        if (map.containsKey(key) && map.get(key) instanceof Integer) {
            page.setSize((Integer) map.get(key));
            pageFlag = true;
        }

        key = pageParams[1];
        if (map.containsKey(key) && map.get(key) instanceof Integer) {
            page.setCurrent((Integer) map.get(key));
            pageFlag = true;
        }
        return pageFlag ? page : null;
    }
}

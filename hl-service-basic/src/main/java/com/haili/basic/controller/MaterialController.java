package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haili.basic.service.impl.MaterialServiceImpl;
import com.haili.framework.domain.basic.Material;
import com.haili.framework.domain.basic.dto.MaterialDto;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.QueryResult;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-21
 */
@RestController
@RequestMapping("/basic/material")
public class MaterialController extends CrudController<Material> {
/*    @PostMapping("/list1")
    @ResponseBody
    public QueryResponseResult<MaterialDto> listByPage1(@RequestBody Map<String, Object> map) {
        IPage<MaterialDto> iPage = ((MaterialServiceImpl)getService()).pageMaterialDto(
                extractPageFromRequestMap(map),
                extractWrapperFromRequestMap(map));
        QueryResult<MaterialDto> queryResult = new QueryResult<>();
        queryResult.setList(iPage.getRecords());
        queryResult.setTotal(iPage.getTotal());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }*/

    @PostMapping("/list1")
    @ResponseBody
    public QueryResponseResult<MaterialDto> listByPage1(@RequestBody Map<String, Object> map) {
        IPage<MaterialDto> iPage = ((MaterialServiceImpl)getService()).getMaterialDtoList(
                extractPageFromRequestMap(map),
                extractWrapperFromRequestMap(map));
        QueryResult<MaterialDto> queryResult = new QueryResult<>();
        queryResult.setList(iPage.getRecords());
        queryResult.setTotal(iPage.getTotal());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }
}

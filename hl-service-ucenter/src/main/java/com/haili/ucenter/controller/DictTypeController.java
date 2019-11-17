package com.haili.ucenter.controller;

import com.haili.api.system.DictTypeControllerApi;
import com.haili.framework.domain.system.DictType;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-17
*/
@RestController
@RequestMapping("/ucenter/dicttype")
public class DictTypeController extends CrudController<DictType> implements DictTypeControllerApi {

}

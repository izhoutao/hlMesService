package com.haili.ucenter.controller;

import com.haili.api.system.DictInfoControllerApi;
import com.haili.framework.domain.system.DictInfo;
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
@RequestMapping("/ucenter/dictinfo")
public class DictInfoController extends CrudController<DictInfo> implements DictInfoControllerApi {

}

package com.haili.basic.controller;

import com.haili.framework.domain.basic.JournalingGrindItem;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-06-08
*/
@RestController
@RequestMapping("/basic/journalinggrinditem")
public class JournalingGrindItemController extends CrudController<JournalingGrindItem> {

}

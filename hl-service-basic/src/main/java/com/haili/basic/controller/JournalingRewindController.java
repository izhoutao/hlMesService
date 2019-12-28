package com.haili.basic.controller;

import com.haili.framework.domain.basic.JournalingRewind;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-20
*/
@RestController
@RequestMapping("/basic/journalingrewind")
public class JournalingRewindController extends CrudController<JournalingRewind> {

}

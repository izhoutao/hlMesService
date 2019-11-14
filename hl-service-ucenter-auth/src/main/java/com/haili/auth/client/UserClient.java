package com.haili.auth.client;

import com.haili.framework.client.HlServiceList;
import com.haili.framework.domain.ucenter.ext.MenuDTO;
import com.haili.framework.domain.ucenter.response.UserResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = HlServiceList.HL_SERVICE_UCENTER)
public interface UserClient {
    @GetMapping("/ucenter/user/{userName}")
    public UserResult findUserExtByUserName(@PathVariable("userName") String userName);
    @GetMapping("/ucenter/menu/list")
    public List<MenuDTO> findMenuList();
}

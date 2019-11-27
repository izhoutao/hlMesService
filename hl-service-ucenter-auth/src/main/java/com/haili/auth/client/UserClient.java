package com.haili.auth.client;

import com.haili.framework.client.HlServiceList;
import com.haili.framework.domain.ucenter.Menu;
import com.haili.framework.domain.ucenter.response.UserResult;
import com.haili.framework.model.response.QueryResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = HlServiceList.HL_SERVICE_UCENTER)
public interface UserClient {
    @GetMapping("/ucenter/user")
    public UserResult getUserByUserName(@RequestParam("userName") String userName);
    @PostMapping("/ucenter/menu/list")
    public QueryResponseResult<Menu> getMenuList(@RequestBody Map<String, Object> map);
}

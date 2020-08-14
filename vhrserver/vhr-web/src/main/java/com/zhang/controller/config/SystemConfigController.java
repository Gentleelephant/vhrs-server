package com.zhang.controller.config;

import com.zhang.model.Menu;
import com.zhang.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:Zpg
 * @Date:2020/4/2 18:56
 * @Version:1.0
 * @Description:
 */
@RestController
@RequestMapping("/sys/config")
public class SystemConfigController {

    @Autowired
    MenuService menuService;


    /**前端数据不可信，因此不能用前端传过来的数据，根据当前登录用户获取该用户的系统配置信息*/
    @GetMapping("/menu")
    public List<Menu> getMenusById(){
        return menuService.getMenusByHrId();
    }

}

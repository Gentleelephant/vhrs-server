package com.zhang.controller;

import com.zhang.model.RespBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Zpg
 * @Date:2020/3/27 11:54
 * @Version:1.0
 * @Description:
 */
@RestController
public class LoginController {

    @GetMapping("/login")
//    @CrossOrigin("*")   // 允许登录跨域请求(不推荐)
    public RespBean login(){
        return RespBean.error("尚未登陆请登录!");
    }

}

package com.zhang.utils;

import com.zhang.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author:Zpg
 * @Date:2020/4/14 12:34
 * @Version:1.0
 * @Description:
 */
public class HrUtils {
    public static Hr getCurrentHr(){
        // 获取当前登录对象
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

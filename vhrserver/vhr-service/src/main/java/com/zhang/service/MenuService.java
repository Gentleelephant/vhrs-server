package com.zhang.service;

import com.zhang.mapper.MenuMapper;
import com.zhang.model.Hr;
import com.zhang.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Zpg
 * @Date:2020/4/6 13:33
 * @Version:1.0
 * @Description:
 */
@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getMenusByHrId(){
        return menuMapper.getMenusByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    /**
     * @Cacheable
     */
    public List<Menu> getAllMenusWithRole(){
        return menuMapper.getAllMenusWithRole();
    }


    public List<Menu> getAllMenus(){
        return menuMapper.getAllMenus();
    }

    public List<Integer> getMidsByRid(Integer rid){
        return menuMapper.getMidsByRid(rid);
    }
}

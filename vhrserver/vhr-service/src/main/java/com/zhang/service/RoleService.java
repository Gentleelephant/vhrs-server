package com.zhang.service;

import com.zhang.mapper.RoleMapper;
import com.zhang.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Zpg
 * @Date:2020/4/12 11:56
 * @Version:1.0
 * @Description:
 */
@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    public List<Role> getAllRoles(){
        return roleMapper.getAllRoles();
    }
}

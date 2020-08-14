package com.zhang.service;

import com.zhang.mapper.HrMapper;
import com.zhang.mapper.HrRoleMapper;
import com.zhang.model.Hr;
import com.zhang.utils.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:Zpg
 * @Date:2020/3/23 11:45
 * @Version:1.0
 * @Description:
 */
@Service
public class HrService implements UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    @Autowired
    HrRoleMapper hrRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if (hr == null){
            System.out.println("没有查询到用户");
            throw new UsernameNotFoundException("用户不存在!");
        }
        hr.setRoles(hrMapper.getHrRolesById(hr.getId()));
        return hr;

    }

//    public List<Hr> getAllHrsExceptCurrentHr(Integer hrid) {
//        return hrMapper.getAllHrsExceptCurrentHr(HrUtils.getCurrentHr().getId());
//    }

    public Integer updateHr(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    public List<Hr> getAllHrs(Integer hrid, String keywords) {
        return hrMapper.getAllHrs(hrid, keywords);

    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateHrRole(Integer hrid, Integer[] rids) {
        hrRoleMapper.deleteByHrid(hrid);
        return hrRoleMapper.addRole(hrid, rids) == rids.length;
    }


    public Integer deleteByHrId(Integer id) {
        return hrMapper.deleteByPrimaryKey(id);
    }

    public List<Hr> getAllHrsExceptCurrentHr() {
        return hrMapper.getAllHrsExceptCurrentHr(HrUtils.getCurrentHr().getId());
    }
}

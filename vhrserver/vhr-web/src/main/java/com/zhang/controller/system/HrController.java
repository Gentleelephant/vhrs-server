package com.zhang.controller.system;

import com.zhang.model.Hr;
import com.zhang.model.RespBean;
import com.zhang.model.Role;
import com.zhang.service.HrService;
import com.zhang.service.RoleService;
import com.zhang.utils.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:Zpg
 * @Date:2020/4/14 12:31
 * @Version:1.0
 * @Description: 查询除了自己的所有Hr
 */
@RestController
@RequestMapping("/system/hr")
public class HrController {

    @Autowired
    HrService hrService;

    @Autowired
    RoleService roleService;

//    @GetMapping("/")
//    public List<Hr> getAllHrsExceptCurrentHr(){
//        return hrService.getAllHrsExceptCurrentHr(HrUtils.getCurrentHr().getId());
//    }

    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr){
        if (hrService.updateHr(hr) == 1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @GetMapping("/")
    public List<Hr> getAllHrs(String keywords){
        return  hrService.getAllHrs(HrUtils.getCurrentHr().getId(),keywords);
    }


    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @PutMapping("/role")
    public RespBean updateHrRole(Integer hrid,Integer[] rids){
        if (hrService.updateHrRole(hrid,rids)){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteByHrId(@PathVariable Integer id){
        if (hrService.deleteByHrId(id) == 1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

}

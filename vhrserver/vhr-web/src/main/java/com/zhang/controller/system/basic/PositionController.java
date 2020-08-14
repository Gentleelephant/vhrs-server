package com.zhang.controller.system.basic;

import com.zhang.model.Department;
import com.zhang.model.Position;
import com.zhang.model.RespBean;
import com.zhang.service.DepartmentService;
import com.zhang.service.MenuService;
import com.zhang.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:Zpg
 * @Date:2020/4/8 13:54
 * @Version:1.0
 * @Description: 职位信息
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    PositionService positionService;

    @GetMapping("/")
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        if (positionService.addPosition(position) == 1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }


    @PutMapping("/")
    public RespBean updatePositions(@RequestBody Position position){
        if (positionService.updatePositions(position) == 1){
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }
    @DeleteMapping("/")
    public RespBean deletePositionById(Integer id){
        if (positionService.deleteByPrimaryKey(id) == 1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @DeleteMapping("/many")
    public RespBean deletePositionsByIds(Integer[] ids){
        if (positionService.deletePositionsByIds(ids) == ids.length){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }




}

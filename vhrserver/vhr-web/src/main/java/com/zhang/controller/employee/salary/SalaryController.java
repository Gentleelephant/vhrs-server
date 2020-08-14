package com.zhang.controller.employee.salary;

import com.zhang.model.RespBean;
import com.zhang.model.Salary;
import com.zhang.service.SalaryService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Zpg
 * @Date:2020/7/13 13:58
 * @Version:1.0
 * @Description:
 */
@RestController
@RequestMapping("/sal/sob")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @GetMapping("/")
    public List<Salary> getAllSalary(){
        return salaryService.getAllSalary();
    }


    @PostMapping("/")
    public RespBean addSalary(@RequestBody Salary salary){
        if (salaryService.addSalary(salary) == 1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteSalary(@PathVariable Integer id){
        if (salaryService.deleteSalary(id) == 1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @PutMapping("/")
    public RespBean updateSalaryById(@RequestBody Salary salary){
        if (salaryService.updateSalaryById(salary) == 1){
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }

}

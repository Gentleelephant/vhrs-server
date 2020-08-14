package com.zhang.controller.employee.salary;

import com.zhang.model.Employee;
import com.zhang.model.RespBean;
import com.zhang.model.RespPageBean;
import com.zhang.model.Salary;
import com.zhang.service.EmployeeService;
import com.zhang.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:Zpg
 * @Date:2020/7/13 16:34
 * @Version:1.0
 * @Description:
 */
@RestController
@RequestMapping("/sal/sobcfg")
public class SobConfigController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    SalaryService salaryService;

    @GetMapping("/")
    public RespPageBean getEmployeeByPageWithSalary(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        return employeeService.getEmployeeByPageWithSalary(page,size);
    }

    @GetMapping("/salaries")
    public List<Salary> getAllSalaries(){
        return salaryService.getAllSalary();
    }

    @PutMapping("/")
    public RespBean updataEmployeeSalaryById(Integer eid,Integer sid){
        int i = employeeService.updateEmployeeSalaryById(eid, sid);
        if (i == 1 || i == 2){
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }

}

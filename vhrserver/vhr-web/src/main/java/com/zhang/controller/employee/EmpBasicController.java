package com.zhang.controller.employee;

import com.zhang.model.*;
import com.zhang.service.*;
import com.zhang.utils.POIUtils;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

//import com.zhang.service.EmployeeService;

/**
 * @Author:Zpg
 * @Date:2020/4/17 13:32
 * @Version:1.0
 * @Description: 员工基本资料分页查询
 */
@RestController
@RequestMapping("/emp/basic")
public class EmpBasicController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    NationService nationService;

    @Autowired
    PoliticsstatusService politicsstatusService;

    @Autowired
    JobLevelService jobLevelService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    PositionService positionService;

    /**
     * 默认查询第一页，查询10个数据
     */
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        return employeeService.getEmployeeByPage(page, size);
    }

    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee) {
        if (employeeService.addEmp(employee) == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.getAllNations();
    }

    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getAllPoliticsstatus() {
        return politicsstatusService.getAllPoliticsstatus();
    }

    @GetMapping("/joblevels")
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }

    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    @GetMapping("/maxWorkID")
    public RespBean maxWorkID() {
        RespBean respBean = RespBean.build().setStatus(200)
                .setObj(String.format("%08d", employeeService.maxWorkID() + 1));
        return respBean;
    }

    @GetMapping("/deps")
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @DeleteMapping("/{id}")
    public  RespBean deleteEmpByEid(@PathVariable Integer id){
        if (employeeService.deleteEmpByEid(id) == 1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    /**
     *
     * @param employee
     * @return
     *
     */
    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee){
        if (employeeService.updateEmp(employee) == 1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }


    /**
     *
     * @param file
     * @return
     * @throws IOException
     * @descripe 文件上传
     */
    @PostMapping("/import")
    public RespBean importData(MultipartFile file) throws IOException {
        List<Employee> list = POIUtils.excel2Employee(file,nationService.getAllNations(),
                politicsstatusService.getAllPoliticsstatus(),departmentService.getAllDepartmentWithOutChildren(),
                positionService.getAllPositions(),jobLevelService.getAllJobLevels());
//        file.transferTo(new File("E:\\javaboy.xls"));
        if (employeeService.addEmps(list) == list.size()){
            return RespBean.ok("上传成功");
        }
        return RespBean.error("上传失败");
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> export() throws IOException {
        List<Employee> list = (List<Employee>) employeeService.getEmployeeByPage(null, null).getData();
        return POIUtils.employee2Excel(list);
    }
}

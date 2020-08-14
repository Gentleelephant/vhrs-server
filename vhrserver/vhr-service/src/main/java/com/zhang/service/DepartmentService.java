package com.zhang.service;

import com.zhang.mapper.DepartmentMapper;
import com.zhang.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Zpg
 * @Date:2020/4/26 21:04
 * @Version:1.0
 * @Description:
 */
@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartmentsWithOutChildren();
    }

    public List<Department> getAllDepartmentWithOutChildren() {
        return departmentMapper.getAllDepartmentsWithOutChildren();
    }
}

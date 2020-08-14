package com.zhang.service;

import com.zhang.mapper.SalaryMapper;
import com.zhang.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author:Zpg
 * @Date:2020/7/13 14:00
 * @Version:1.0
 * @Description:
 */
@Service
public class SalaryService {


    @Autowired
    SalaryMapper salaryMapper;

    public List<Salary> getAllSalary() {
        return salaryMapper.getAllSalaries();
    }

    public int addSalary(Salary salary) {
        salary.setCreateDate(new Date());
        return salaryMapper.insertSelective(salary);
    }

    public int deleteSalary(Integer id) {
        return salaryMapper.deleteByPrimaryKey(id);
    }

    public int updateSalaryById(Salary salary) {
        return salaryMapper.updateByPrimaryKeySelective(salary);
    }
}

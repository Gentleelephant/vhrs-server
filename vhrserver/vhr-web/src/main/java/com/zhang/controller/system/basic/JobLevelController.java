package com.zhang.controller.system.basic;

import com.zhang.model.JobLevel;
import com.zhang.model.RespBean;
import com.zhang.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author:Zpg
 * @Date:2020/4/9 14:48
 * @Version:1.0
 * @Description:
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {

    @Autowired
    JobLevelService jobLevelService;

    @GetMapping("/")
    public List<JobLevel> getAllJobLevels(){
        return jobLevelService.getAllJobLevels();
    }


    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel){
        if (jobLevelService.addJobLevel(jobLevel) == 1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody JobLevel jobLevel){
        if (jobLevelService.updatteJobLevel(jobLevel) == 1){
            return RespBean.ok("修改成功");
        }
        return RespBean.error("添加失败");
    }

    @DeleteMapping("/")
    public RespBean deleteJobLevelById(Integer id){
        if (jobLevelService.deleteJobLevelById(id) == 1){
            return RespBean.ok("删除成功");
        }
        return RespBean.ok("删除失败");
    }

    @DeleteMapping("/many")
    public RespBean deleteJobLevelByIds(Integer[] ids){
        if (jobLevelService.deleteJobLevelByIds(ids) == ids.length) {
            return  RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }


}

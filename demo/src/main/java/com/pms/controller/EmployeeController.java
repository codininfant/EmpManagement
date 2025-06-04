package com.pms.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pms.pojo.EmployeeInfo;
import com.pms.pojo.PageBean;
import com.pms.pojo.Result;
import com.pms.pojo.EmployeeInfo.JobType;
import com.pms.server.EmployeeServer;

@RestController
@RequestMapping("/employee")
public class EmployeeController {



    @Autowired
    public EmployeeServer employeeServer;

    @GetMapping("/list")
    public Result page(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize,
    String name,Boolean gender,int startAge,int endAge,Long startJoindate,Long endJoindate,
    String protitle,Interger marstatus,String edubg,Interger dep,Interger empstatus,Interger startSalary,Interger endSalary,
    String[] sortField,Boolean[] sortOrder)
    {
        PageBean pageBean = employeeServer.page(page, pageSize,name,gender,startAge,endAge,startJoindate,endJoindate,pro_title,mar_status,edu_bg,dep,empstatus,startSalary,endSalary,sortField,sortOrder);
        
        return Result.success(pageBean);
    }

    @PostMapping()
    public Result add(@RequestBody EmployeeInfo emp)
    {
        employeeServer.add(emp);
        return Result.success();
    }
}

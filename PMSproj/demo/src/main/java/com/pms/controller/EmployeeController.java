package com.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pms.pojo.EmployeeInfo;
import com.pms.pojo.PageBean;
import com.pms.pojo.Result;
import com.pms.server.EmployeeServer;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    public EmployeeServer employeeServer;

    @GetMapping("/list")
    public Result page(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize)
    {
        PageBean pageBean = employeeServer.page(page, pageSize);
        
        return Result.success(pageBean);
    }
}

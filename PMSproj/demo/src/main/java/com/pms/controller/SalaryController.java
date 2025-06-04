package com.pms.controller;

import com.pms.pojo.PageBean;
import com.pms.pojo.Result;
import com.pms.server.SalaryServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salary")
public class SalaryController {
    @Autowired
    private SalaryServer salaryServer;

    @GetMapping("/list")
    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize,
                       Long startSalary, Long endSalary,String name,Long userid,
                       String[] sortField, Boolean[] sortOrder)
    {
        PageBean pageBean = salaryServer.page(page, pageSize,startSalary,endSalary,name,userid,sortField,sortOrder);

        return Result.success(pageBean);
    }
}

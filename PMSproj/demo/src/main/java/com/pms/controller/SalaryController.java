package com.pms.controller;

import com.pms.pojo.PageBean;
import com.pms.pojo.Result;
import com.pms.pojo.SalaryInfo;
import com.pms.server.SalaryServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add")
    public Result add(@RequestBody SalaryInfo salary) {
        salaryServer.add(salary);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(List<Long> ids) {
        salaryServer.delete(ids);
        return Result.success();
    }


    //------------------below are the operations for salary details-------------------

    @GetMapping("/details")
    public Result details(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize,
                          Integer id) {
        PageBean pageBean = salaryServer.details(page, pageSize, id);

        return Result.success(pageBean);
    }

    @GetMapping("/addDetails")
    public Result addDetails(Integer id, Integer penalty, String reason, String date) {
        salaryServer.addDetails(id, penalty, reason, date);
        return Result.success();
    }

    @DeleteMapping("/DeleteDetails")
    public Result DeleteDetails(List<Integer> ids) {
        salaryServer.DeleteDetails(ids);
        return Result.success();
    }

    //------------------below are the operations only for employees-------------------
    @GetMapping("/list/{userid}")
    public Result pageForEmployee(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize,
                       Long startSalary, Long endSalary,String name,@PathVariable Long userid,
                       String[] sortField, Boolean[] sortOrder)
    {
        PageBean pageBean = salaryServer.page(page, pageSize,startSalary,endSalary,name,userid,sortField,sortOrder);

        return Result.success(pageBean);
    }

    @GetMapping("/details/{id}")
    public Result detailsForEmployee(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize,
                          @PathVariable Integer id) {
        PageBean pageBean = salaryServer.details(page, pageSize, id);

        return Result.success(pageBean);
    }
}

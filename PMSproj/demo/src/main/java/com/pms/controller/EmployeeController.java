package com.pms.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.pms.pojo.*;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.pms.pojo.EmployeeInfo.JobType;
import com.pms.server.EmployeeServer;
import com.pms.utils.JwtUtils;
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    public EmployeeServer employeeServer;

    @GetMapping("/list")
    public Result page(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize,
    Long id,String name,@RequestParam(required = false) Integer gender,@RequestParam(required = false) Integer startAge,@RequestParam(required = false) Integer endAge,Long startJoindate,Long endJoindate,
    String proTitle,Long marStatus,String eduBg,Long dep,Long empStatus,Long startSalary,Long endSalary,
    String[] sortField,Boolean[] sortOrder)
    {
        PageBean pageBean = employeeServer.page(page, pageSize,id,name,gender,startAge,endAge,startJoindate,endJoindate,proTitle,marStatus,eduBg,dep,empStatus,startSalary,endSalary,sortField,sortOrder);
        
        return Result.success(pageBean);
    }

    @PostMapping()
    public Result add(@RequestBody EmployeeInfoFromWeb emp)
    {
        employeeServer.add(emp);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids)
    {
        employeeServer.del(ids);
        return Result.success();
    }

    @PutMapping()
    public Result update(@RequestBody EmployeeInfoFromWeb emp)
    {
        employeeServer.update(emp);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody EmployeeInfoFromWeb emp)
    {
        AccountInfo actInfo = employeeServer.login(emp);
        if(actInfo==null)
        {
            return Result.error("用户名或密码错误");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("id", actInfo.getId());
        map.put("identification", actInfo.getIdentification());
        return Result.success(JwtUtils.generateJwtToken(map));
    }

    @GetMapping("/export/{ids}")
    public void export(@PathVariable List<Long> ids, HttpServletResponse response) throws IOException {
        log.info("ids:{}", ids);
        List<EmployeeInfo> emps = employeeServer.getByids(ids);
        log.info("emps:{}", emps);
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(emps, true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.spreadsheetml.sheet;charset=UTF-8");
        String fileName = "employee.xlsx";
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
        //return Result.success();
    }

    //-----------------below is the opreation only for employees-------------------//
    @GetMapping("/list/{id}")
    public Result pageForEmployee(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize,
                       @PathVariable Long id,String name,@RequestParam(required = false) Integer gender,@RequestParam(required = false) Integer startAge,@RequestParam(required = false) Integer endAge,Long startJoindate,Long endJoindate,
                       String proTitle,Long marStatus,String eduBg,Long dep,Long empStatus,Long startSalary,Long endSalary,
                       String[] sortField,Boolean[] sortOrder)
    {
        PageBean pageBean = employeeServer.page(page, pageSize,id,name,gender,startAge,endAge,startJoindate,endJoindate,proTitle,marStatus,eduBg,dep,empStatus,startSalary,endSalary,sortField,sortOrder);

        return Result.success(pageBean);
    }
}

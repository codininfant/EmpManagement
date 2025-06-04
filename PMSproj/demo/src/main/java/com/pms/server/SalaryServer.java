package com.pms.server;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pms.mapper.SalaryMapper;
import com.pms.pojo.EmployeeInfo;
import com.pms.pojo.PageBean;
import com.pms.pojo.SalaryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryServer {
    @Autowired
    private SalaryMapper salaryMapper;

    public PageBean page(Integer page,Integer pageSize,Long startSalary, Long endSalary, String name, Long userid, String[] sortField, Boolean[] sortOrder) {

        List<SalaryInfo> list = salaryMapper.list(startSalary, endSalary, userid, name, sortField, sortOrder);
        PageHelper.startPage(page, pageSize);

        Page<SalaryInfo> p = (Page<SalaryInfo>)salaryMapper.list(startSalary,endSalary,userid,name,sortField,sortOrder);

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }
}

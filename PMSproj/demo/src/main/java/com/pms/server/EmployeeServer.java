package com.pms.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pms.mapper.EmployeeMapper;
import com.pms.pojo.EmployeeInfo;
import com.pms.pojo.PageBean;

@Service
public class EmployeeServer {

    @Autowired
    public EmployeeMapper employeeMapper;

    public PageBean page(int page,int pageSize)
    {
        PageHelper.startPage(page, pageSize);

        Page<EmployeeInfo> p = (Page<EmployeeInfo>)employeeMapper.list();

        PageBean PageBean = new PageBean(p.getTotal(), p.getResult());
        return PageBean;
    }
}

package com.pms.server;

import java.time.LocalDateTime;
import java.util.List;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pms.mapper.EmployeeMapper;
import com.pms.pojo.EmployeeInfo;
import com.pms.pojo.EmployeeInfo.pro_titleType;
import com.pms.pojo.PageBean;

@Service
public class EmployeeServer {

    @Autowired
    public EmployeeMapper employeeMapper;

    public PageBean page(int page,int pageSize,long id,String name,Boolean gender,int startAge,int endAge,Long LstartJoindate,Long LendJoindate,
    String pro_title,Interger mar_status,String edu_bg,Interger dep,Interger empstatus,Interger startSalary,Interger endSalary,
    String[] sortField,Boolean[] sortOrder)
    {
        LocalDate localDate = LocalDate.parse(
            String.format("%08d", LstartJoindate), 
            INPUT_FORMATTER
        );
        Date startJoindate = Date.valueOf(localDate);

        localDate = LocalDate.parse(
            String.format("%08d", LstartJoindate), 
            INPUT_FORMATTER
        );
        Date endJoindate = Date.valueOf(localDate);

        PageHelper.startPage(page, pageSize);

        Page<EmployeeInfo> p = (Page<EmployeeInfo>)employeeMapper.list(id,name,gender,startAge,endAge,startJoindate,endJoindate,pro_title,mar_status,edu_bg,dep,emp_status,startSalary,endSalary,sortField,sortOrder);

        PageBean PageBean = new PageBean(p.getTotal(), p.getResult());
        return PageBean;
    }

    public void add(EmployeeInfo emp)
    {
        emp.setjoindate(LocalDateTime.now());
        employeeMapper.insert(emp);
    }
}

package com.pms.server;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.sql.Date;

import com.pms.pojo.AccountInfo;
import com.pms.pojo.EmployeeInfoFromWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pms.mapper.EmployeeMapper;
import com.pms.pojo.EmployeeInfo;
import com.pms.pojo.PageBean;

@Service
public class EmployeeServer {

    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd"); // 无分隔符格式

    @Autowired
    public EmployeeMapper employeeMapper;

    public PageBean page(Integer page,Integer pageSize,Long id,String name,Integer gender,Integer startAge,Integer endAge,Long LstartJoindate,Long LendJoindate,
    String pro_title,Long mar_status,String edu_bg,Long dep,Long emp_status,Long startSalary,Long endSalary,
    String[] sortField,Boolean[] sortOrder)
    {
        Date startJoindate,endJoindate;
        if(LstartJoindate!=null)
        {
            LocalDate localDate = LocalDate.parse(
                    String.format("%08d", LstartJoindate),
                    INPUT_FORMATTER
            );
            startJoindate = Date.valueOf(localDate);
        }
        else startJoindate = null;
        if(LstartJoindate!=null)
        {
            LocalDate localDate = LocalDate.parse(
                    String.format("%08d", LstartJoindate),
                    INPUT_FORMATTER
            );
            endJoindate = Date.valueOf(localDate);
        }
        else endJoindate = null;

        PageHelper.startPage(page, pageSize);

        Page<EmployeeInfo> p = (Page<EmployeeInfo>)employeeMapper.list(id,name,gender,startAge,endAge,startJoindate,endJoindate,pro_title, mar_status,edu_bg,dep,emp_status,startSalary,endSalary,sortField,sortOrder);

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    public void add(EmployeeInfoFromWeb empFromWeb)
    {
        EmployeeInfo emp = new EmployeeInfo();
        emp.setId(empFromWeb.getId());
        emp.setAge(empFromWeb.getAge());
        emp.setDep(empFromWeb.getDep());
        emp.setEduBg(empFromWeb.getEduBg());
        emp.setEmpStatus(empFromWeb.getEmpStatus());
        if(empFromWeb.getEntryDate()!=null) {
            LocalDate localDate = LocalDate.parse(
                    String.format("%08d", empFromWeb.getEntryDate()),
                    INPUT_FORMATTER
            );
            emp.setEntryDate(Date.valueOf(localDate));
        }
        emp.setGender(empFromWeb.getGender());
        emp.setMarStatus(empFromWeb.getMarStatus());
        emp.setName(empFromWeb.getName());
        emp.setProTitle(empFromWeb.getProTitle());
        if(empFromWeb.getResDate()!=null) {
            LocalDate localDate = LocalDate.parse(
                    String.format("%08d", empFromWeb.getResDate()),
                    INPUT_FORMATTER
            );
            emp.setResDate(Date.valueOf(localDate));
        }
        employeeMapper.insert(emp);
    }

    public void del(List<Integer> ids)
    {
        employeeMapper.delete(ids);
    }

    public EmployeeInfo getById(Long id) {
        return employeeMapper.getById(id);
    }

    public void update(EmployeeInfoFromWeb emp) {

        EmployeeInfo empInfo = employeeMapper.getById(emp.getId());
        empInfo.setAge(emp.getAge());
        empInfo.setDep(emp.getDep());
        empInfo.setEduBg(emp.getEduBg());
        empInfo.setEmpStatus(emp.getEmpStatus());
        if(emp.getEntryDate()!=null) {
            LocalDate localDate = LocalDate.parse(
                    String.format("%08d", emp.getEntryDate()),
                    INPUT_FORMATTER
            );
            empInfo.setEntryDate(Date.valueOf(localDate));
        }
        empInfo.setGender(emp.getGender());
        empInfo.setMarStatus(emp.getMarStatus());
        empInfo.setName(emp.getName());
        empInfo.setProTitle(emp.getProTitle());
        if(emp.getResDate()!=null) {
            LocalDate localDate = LocalDate.parse(
                    String.format("%08d", emp.getResDate()),
                    INPUT_FORMATTER
            );
            empInfo.setResDate(Date.valueOf(localDate));
        }
        employeeMapper.update(empInfo);
    }

    public AccountInfo login(EmployeeInfoFromWeb emp) {
        return employeeMapper.getByLogin(emp);
    }

    public List<EmployeeInfo> getByids(List<Long> ids) {
        return employeeMapper.getByids(ids);
    }
}

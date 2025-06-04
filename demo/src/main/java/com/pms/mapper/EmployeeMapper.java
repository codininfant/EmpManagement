package com.pms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.format.annotation.DateTimeFormat;

import com.pms.pojo.EmployeeInfo;
import com.pms.pojo.EmployeeInfo.JobType;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EmployeeMapper {

    public List<EmployeeInfo> list(String name,Boolean gender,int startAge,int endAge,@DateTimeFormat(pattern = "yyyy-MM-dd") Date startJoindate,@DateTimeFormat(pattern = "yyyy-MM-dd") Date endJoindate,
    String pro_title,Interger mar_status,String edu_bg,Interger dep,Interger emp_status,Interger startSalary,Interger endSalary,
    String[] sortField,Boolean[] sortOrder);

    @Insert("insert into employeeInfo(name,gender,age,edu_bg,mar_status,joindate,pro_title)"+
    " values(#{name},#{gender},#{age},#{edu_bg},#{mar_status},#{joindate},#{pro_title})")
    public void insert(EmployeeInfo emp);
}

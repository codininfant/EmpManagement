package com.pms.mapper;

import com.pms.pojo.AccountInfo;
import com.pms.pojo.EmployeeInfoFromWeb;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.format.annotation.DateTimeFormat;

import com.pms.pojo.EmployeeInfo;
import com.pms.pojo.EmployeeInfo.JobType;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EmployeeMapper {

    public List<EmployeeInfo> list(Long id,String name, Integer gender, Integer startAge, Integer endAge, @DateTimeFormat(pattern = "yyyy-MM-dd") Date startJoindate, @DateTimeFormat(pattern = "yyyy-MM-dd") Date endJoindate,
                                   String pro_title, Long mar_status, String edu_bg, Long dep, Long emp_status, Long startSalary, Long endSalary,
                                   String[] sortField, Boolean[] sortOrder);

    public void insert(EmployeeInfo emp);

    public void delete(List<Integer> ids);

    @Select("SELECT * FROM employeeInfo WHERE id = #{id}")
    public EmployeeInfo getById(Long id);

    void update(EmployeeInfo emp);

    @Select("SELECT * FROM accountInfo WHERE id = #{username} AND password = #{password}")
    AccountInfo getByLogin(EmployeeInfoFromWeb emp);

    public List<EmployeeInfo> getByids(List<Long> ids);
}

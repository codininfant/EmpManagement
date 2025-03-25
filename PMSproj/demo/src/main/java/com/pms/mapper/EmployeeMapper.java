package com.pms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.pms.pojo.EmployeeInfo;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employeeInfo")
    public List<EmployeeInfo> list();

}

package com.pms.mapper;

import com.pms.pojo.SalaryInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SalaryMapper {

    public List<SalaryInfo> list(Long startSalary, Long endSalary, Long userid, String name,String[] sortField, Boolean[] sortOrder);

    public void insert(SalaryInfo salaryInfo);

    public void update(SalaryInfo salaryInfo);

    public void delete(List<Long> ids);
}

package com.pms.mapper;

import com.pms.pojo.SalaryInfo;
import com.pms.pojo.penaltyDetails;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SalaryMapper {

    public List<SalaryInfo> list(Long startSalary, Long endSalary, Long userid, String name,String[] sortField, Boolean[] sortOrder);

    public void insert(SalaryInfo salaryInfo);

    public void update(SalaryInfo salaryInfo);

    public void delete(List<Long> ids);

    public List<penaltyDetails> details(Integer id);

    @Insert("INSERT INTO penaltyDetails (id, penaltyCount, pinfo, date) VALUES (#{id}, #{penalty}, #{reason}, #{date})")
    public void addDetails(Integer id, Integer penalty, String reason, String date);

    public void DeleteDetails(List<Integer> ids);

    @Select("SELECT * FROM salaryInfo WHERE id = #{id}")
    public SalaryInfo getSalaryById(Integer id);

    public List<penaltyDetails> getDetailsById(List<Integer> ids);
}

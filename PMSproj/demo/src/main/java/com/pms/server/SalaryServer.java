package com.pms.server;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pms.mapper.SalaryMapper;
import com.pms.pojo.EmployeeInfo;
import com.pms.pojo.PageBean;
import com.pms.pojo.SalaryInfo;
import com.pms.pojo.penaltyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryServer {
    @Autowired
    private SalaryMapper salaryMapper;

    public PageBean page(Integer page,Integer pageSize,Long startSalary, Long endSalary, String name, Long userid, String[] sortField, Boolean[] sortOrder) {
        PageHelper.startPage(page, pageSize);
        Page<SalaryInfo> p = (Page<SalaryInfo>)salaryMapper.list(startSalary,endSalary,userid,name,sortField,sortOrder);
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    public PageBean details(Integer page, Integer pageSize, Integer id) {
        PageHelper.startPage(page, pageSize);
        Page<penaltyDetails> p = (Page<penaltyDetails>)salaryMapper.details(id);
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    public void add(SalaryInfo salary) {
        salaryMapper.insert(salary);
    }

    public void delete(List<Long> ids) {
        salaryMapper.delete(ids);
    }

    public void addDetails(Integer id, Integer penalty, String reason, String date) {
        SalaryInfo salary = salaryMapper.getSalaryById(id);
        salary.setPenalty(salary.getPenalty() + penalty);
        salaryMapper.update(salary);
        salaryMapper.addDetails(id, penalty, reason, date);

    }

    public void DeleteDetails(List<Integer> ids) {
        List<penaltyDetails> details = salaryMapper.getDetailsById(ids);
        for(penaltyDetails detail : details) {
            SalaryInfo salary = salaryMapper.getSalaryById(detail.getUserId());
            salary.setPenalty(salary.getPenalty() - detail.getPenaltyCount());
            salaryMapper.update(salary);
        }
        salaryMapper.DeleteDetails(ids);
    }


}

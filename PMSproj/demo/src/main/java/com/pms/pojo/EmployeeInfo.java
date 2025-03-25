package com.pms.pojo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeInfo {
    public enum JobType {
        MANAGER,      
        ENGINEER,     
        INTERN;        
    }

    private String name;
    private Boolean gender;
    private Integer age;
    private String degree;
    private Boolean married;
    private Date joindate;
    private JobType job;

    // public EmployeeInfo() {
    // }

    // public EmployeeInfo(String name, Boolean gender, Integer age, String degree, Boolean married, Date joindate,
    //         JobType job) {
    //     this.name = name;
    //     this.gender = gender;
    //     this.age = age;
    //     this.degree = degree;
    //     this.married = married;
    //     this.joindate = joindate;
    //     this.job = job;
    // }

    // public String getName() {
    //     return name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }

    // public Boolean isGender() {
    //     return gender;
    // }

    // public void setGender(Boolean gender) {
    //     this.gender = gender;
    // }

    // public Integer getAge() {
    //     return age;
    // }

    // public void setAge(Integer age) {
    //     this.age = age;
    // }

    // public String getDegree() {
    //     return degree;
    // }

    // public void setDegree(String degree) {
    //     this.degree = degree;
    // }

    // public Boolean isMarried() {
    //     return married;
    // }

    // public void setMarried(Boolean married) {
    //     this.married = married;
    // }

    // public Date getJoindate() {
    //     return joindate;
    // }

    // public void setJoindate(Date joindate) {
    //     this.joindate = joindate;
    // }

    // public JobType getJob() {
    //     return job;
    // }

    // public void setJob(JobType job) {
    //     this.job = job;
    // }

}



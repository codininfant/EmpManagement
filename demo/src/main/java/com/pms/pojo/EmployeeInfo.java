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

    private long age;
    /**
     * 枚举，1市场，2人事，3产品
     */
    private long dep;
    /**
     * 非枚举值，学历
     */
    private String eduBg;
    /**
     * 枚举，1代表在职，2辞退，3辞职，4退休
     */
    private long empStatus;
    /**
     * 入职时间，八位数字（后端处理
     */
    private long entryDate;
    /**
     * 枚举值，1代表男，2代表女，其余无效
     */
    private long gender;
    /**
     * uid，唯一标识一个人的信息
     */
    private long id;
    /**
     * 枚举值，1代表未婚，2代表已婚
     */
    private long marStatus;
    /**
     * 名称
     */
    private String name;
    /**
     * 职称，非枚举值，
     */
    private String proTitle;
    /**
     * 允许为空，如果emp_status不等于1，则存在
     */
    private Long resDate;

    // public EmployeeInfo() {
    // }

    // public EmployeeInfo(String name, Boolean gender, Integer age, String edu_bg, Interger mar_status, Date joindate,
    //         String pro_title) {
    //     this.name = name;
    //     this.gender = gender;
    //     this.age = age;
    //     this.edu_bg = edu_bg;
    //     this.mar_status = mar_status;
    //     this.joindate = joindate;
    //     this.pro_title = pro_title;
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

    // public String getedu_bg() {
    //     return edu_bg;
    // }

    // public void setedu_bg(String edu_bg) {
    //     this.edu_bg = edu_bg;
    // }

    // public Boolean ismar_status() {
    //     return mar_status;
    // }

    // public void setmar_status(Interger mar_status) {
    //     this.mar_status = mar_status;
    // }

    // public Date getJoindate() {
    //     return joindate;
    // }

    // public void setJoindate(Date joindate) {
    //     this.joindate = joindate;
    // }

    // public JobType getJob() {
    //     return pro_title;
    // }

    // public void setJob(String pro_title) {
    //     this.pro_title = pro_title;
    // }

}



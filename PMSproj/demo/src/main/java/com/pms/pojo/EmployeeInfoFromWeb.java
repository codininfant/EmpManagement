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
public class EmployeeInfoFromWeb {
    private Integer username;
    private String password;
    private Long age;
    /**
     * 枚举，1市场，2人事，3产品
     */
    private Long dep;
    /**
     * 非枚举值，学历
     */
    private String eduBg;
    /**
     * 枚举，1代表在职，2辞退，3辞职，4退休
     */
    private Long empStatus;
    /**
     * 入职时间，八位数字（后端处理
     */
    private Long entryDate;
    /**
     * 枚举值，1代表男，2代表女，其余无效
     */
    private Long gender;
    /**
     * uid，唯一标识一个人的信息
     */
    private Long id;
    /**
     * 枚举值，1代表未婚，2代表已婚
     */
    private Long marStatus;
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
}

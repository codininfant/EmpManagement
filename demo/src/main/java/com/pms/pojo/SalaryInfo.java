package com.pms.pojo;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaryInfo {
    /**
     * 奖金
     */
    private Long bonus;
    /**
     * 与大表对应
     */
    private String name;
    /**
     * 扣款，可增加
     */
    private Long penalty;
    /**
     * 年薪
     */
    private Long salary;
    /**
     * 与大表对应
     */
    private long userid;
}

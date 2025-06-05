package com.pms.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class penaltyDetails {
    public Integer penaltyId;
    public Integer userId;
    public Integer penaltyCount;
    public String pinfo;
    public Date date;
}

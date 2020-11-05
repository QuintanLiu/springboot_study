package com.kunkun.springboot.model;

import lombok.Data;

@Data
public class DM {
    private Integer id;
    private Integer surveyRuleId;
    private Long driverNo;
    private Integer delFlag;
    private String createTime;
    private String updateTime;

}

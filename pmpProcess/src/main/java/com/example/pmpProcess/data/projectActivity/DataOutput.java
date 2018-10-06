package com.example.pmpProcess.data.projectActivity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class DataOutput {
    /**
     * 输出名称描述
     */
    String description;
    /**
     * 输出唯一标识ID信息
     */
    String ID;
    /**
     * 所属活动描述
     */
    String activityDescription;
    /**
     * 所属活动ID
     */
    String activityID;
    /**
     * 本输出是否为必要输出，true表示必要，false表示不必要
     */
    boolean isNeed;
    /**
     * 输出说明
     */
    String textDesc;
    /**
     * 输出对应的项目活动
     */
    ArrayList<DataActivity> activityArrayList;
}

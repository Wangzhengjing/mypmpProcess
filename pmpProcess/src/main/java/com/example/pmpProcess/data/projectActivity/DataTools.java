package com.example.pmpProcess.data.projectActivity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class DataTools {
    /**
     * 工具名称描述
     */
    String Description;
    /**
     * 工具唯一标识ID信息
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
     * 工具说明
     */
    String textDesc;
    /**
     * 项目工具对应的项目活动
     */
    ArrayList<DataActivity> activityArrayList;
}

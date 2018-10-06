package com.example.pmpProcess.data.projectActivity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class DataTools {
    /**
     * 工具名称描述
     */
    String toolsDescription;
    /**
     * 工具唯一标识ID信息
     */
    String ID;
    /**
     * 所属活动ID
     */
    String parentActivityID;
    /**
     * 工具说明
     */
    String textDesc;
    /**
     * 项目工具对应的项目活动
     */
    static ArrayList<DataActivity> activityArrayList = new ArrayList<>();

    /**
     * 向工具中添加项目活动
     *
     * @param dataActivity 活动对象
     */
    public static void addActivity(DataActivity dataActivity) {
        activityArrayList.add(dataActivity);

        return;
    }

    public static ArrayList getActivityArrayList(){
        return activityArrayList;
    }
}

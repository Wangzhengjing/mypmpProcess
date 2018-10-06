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
    String parentAct;
    /**
     * 所属活动ID
     */
    String parentActivityID;
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
    static ArrayList<DataActivity> activityArrayList = new ArrayList<>();

    /**
     * 向输出中添加项目活动
     *
     * @param dataActivity 项目活动
     */
    public static void addActivity(DataActivity dataActivity) {
        activityArrayList.add(dataActivity);

        return;
    }

    public static ArrayList getActivityArrayList(){
        return activityArrayList;
    }
}

package com.example.pmpProcess.data.projectActivity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class DataInput {
    /**
     * 输入名称描述
     */
    String description;
    /**
     * 输入唯一标识ID信息
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
     * 本输入是否为必要输出，true表示必要，false表示不必要
     */
    boolean isNeed;
    /**
     * 输入说明
     */
    String textDesc;
    /**
     * 输入对应的项目活动
     */
    static ArrayList<DataActivity> activityArrayList = new ArrayList<>();

    /**
     * 记录输入Id的游标
     */
    private Integer tmpIdCursor = 0;

    public Integer getInputID() {
        return tmpIdCursor++;
    }

    /**
     * 向输入中添加项目活动
     *
     * @param dataActivity 活动对象
     */
    public static void addActivity(DataActivity dataActivity) {
        activityArrayList.add(dataActivity);

        return;
    }

}

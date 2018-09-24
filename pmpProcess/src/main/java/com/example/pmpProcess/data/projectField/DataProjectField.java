package com.example.pmpProcess.data.projectField;

import com.example.pmpProcess.data.projectActivity.DataActivity;
import lombok.Data;

import java.util.LinkedList;

@Data
public class DataProjectField {
    /**
     * 项目知识领域名称描述
     */
    String projectField;
    /**
     * 项目知识领域唯一标识ID信息
     */
    Integer ID;
    /**
     * 项目知识领域中所涉及到的所有活动
     */
    static LinkedList<DataActivity> fieldActivity = new LinkedList<>();
    /**
     * 知识域说明
     */
    String textDesc;

    public static void addActivity(DataActivity activity) {
        fieldActivity.add(activity);

        return;
    }
}

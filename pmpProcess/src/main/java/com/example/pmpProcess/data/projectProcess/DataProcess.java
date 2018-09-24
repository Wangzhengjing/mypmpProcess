package com.example.pmpProcess.data.projectProcess;

import com.example.pmpProcess.data.projectActivity.DataActivity;
import lombok.Data;

import java.util.LinkedList;

@Data
public class DataProcess {
    /**
     * 项目过程组名称描述
     */
    String description;
    /**
     * 项目过程组唯一标识ID信息
     */
    Integer ID;
    /**
     * 过程组所涉及到的所有活动
     */
    static LinkedList<DataActivity> processActivity = new LinkedList<>();
    /**
     * 过程组说明
     */
    String textDesc;

    public static void addActivity(DataActivity activity) {
        processActivity.add(activity);

        return;
    }
}

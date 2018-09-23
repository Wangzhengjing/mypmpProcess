package com.example.pmpProcess.config;

import com.example.pmpProcess.data.projectActivity.DataActivity;
import com.example.pmpProcess.dto.projActivity.ActivityManager;
import lombok.Data;

import java.util.LinkedList;

@Data
public class configActivity {
    /**
     * 活动链表
     */
    LinkedList<DataActivity> activityList;
    /**
     * 活动数量
     */
    Integer actCount;


    public void addActivity(String actDesc, String filedDesc, String processDesc) {
        ActivityManager activityProcess = new ActivityManager();

        DataActivity dataActivity = activityProcess.setActivity(actDesc, filedDesc, processDesc);

        /**
         * 添加活动至活动链表中
         */
        activityList.add(dataActivity);
    }
}

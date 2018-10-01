package com.example.pmpProcess.dto.projActivity;

import com.example.pmpProcess.data.projectActivity.DataActivity;
import com.example.pmpProcess.data.projectActivity.DataInput;
import com.example.pmpProcess.data.projectActivity.DataOutput;
import com.example.pmpProcess.data.projectActivity.DataTools;
import com.example.pmpProcess.data.projectField.DataProjectField;
import com.example.pmpProcess.data.projectProcess.DataProcess;
import com.example.pmpProcess.dto.projField.FieldManager;
import com.example.pmpProcess.dto.projProcess.ProcessManager;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.LinkedList;

@Slf4j
public class ActivityManager {
    /**
     * 活动链表
     */
    static LinkedList<DataActivity> activityLinkedList = new LinkedList<>();

    /**
     * 通过活动ID查找活动
     *
     * @param activityID 活动ID
     * @return 活动详情
     */
    private static DataActivity getActivityByID(Integer activityID) {
        int activityLinkedListSize = activityLinkedList.size();
        for (int i = 0; i < activityLinkedListSize; i++) {
            DataActivity element = activityLinkedList.get(i);
            if (element.getID() == activityID) {
                //TODO 增加日志信息
                return element;
            }
        }

        return null;
    }

    /**
     * 设置活动名称等信息
     *
     * @param actDesc     活动描述
     * @param fieldDesc   知识域描述
     * @param processDesc 过程组描述
     * @return 返回活动信息
     */
    public DataActivity addActivityToList(String actDesc, Integer actID, String fieldDesc,
                                          Integer fieldID, String processDesc, Integer processID) {
        DataActivity dataActivity = new DataActivity();

        dataActivity.setDescription(actDesc);
        dataActivity.setID(actID);
        dataActivity.setParentField(fieldDesc);
        dataActivity.setParentFieldID(fieldID);
        dataActivity.setProcess(processDesc);
        dataActivity.setProcessID(processID);

        //将活动加入活动链表
        DataActivity activityEle = getActivityByID(dataActivity.getID());
        if (activityEle == null) {
            activityLinkedList.add(dataActivity);
        } else {
            //TODO 增加交互性信息
        }

        //将活动加入知识域链表
        DataProjectField dataProjectField = FieldManager.getProjFieldByID(dataActivity.getParentFieldID());
        if (dataProjectField == null) {
            //TODO 增加日志信息
        } else {
            //添加活动到知识域链表
            FieldManager.addActivity(dataActivity);
        }

        //将活动加入过程组链表
        DataProcess dataProcess = ProcessManager.getProcessByID(dataActivity.getProcessID());
        if (dataProcess == null) {
            //TODO 增加日志信息
        } else {
            ProcessManager.addActivity(dataActivity);
        }

        return dataActivity;
    }

    /**
     * 将活动的配置落入文件中
     *
     * @return
     * @throws IOException
     */
    public boolean appendActivityConfig() throws IOException {
        String fileName = "D:\\rmt_code_server\\pmpProcessor\\pmpProcess\\config\\Activity.txt";
        FileWriter fileWriter = new FileWriter(fileName, true);
        int activityCount = activityLinkedList.size();
        for (int i = 0; i < activityCount; i++) {
            DataActivity dataActivity = activityLinkedList.get(i);

            //x.x.x="描述"；过程组ID.知识域ID.活动ID=活动描述
            String destString = dataActivity.getProcessID().toString() + "." + dataActivity.getParentFieldID().toString() +
                    "." + dataActivity.getID() + "=" + dataActivity.getDescription();
            fileWriter.append(destString, 0, destString.length());
        }

        fileWriter.flush();
        fileWriter.close();

        return true;
    }

    /**
     * 读取活动的配置文件
     *
     * @return
     * @throws IOException
     */
    public boolean getActivityConfig() throws IOException {
        String fileName = "D:\\rmt_code_server\\pmpProcessor\\pmpProcess\\config\\Activity.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String bufferData = null;

        while ((bufferData = bufferedReader.readLine()) != null) {
            log.info(bufferData);
        }

        bufferedReader.close();

        return true;
    }
}

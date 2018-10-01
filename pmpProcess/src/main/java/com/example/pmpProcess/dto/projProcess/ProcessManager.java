package com.example.pmpProcess.dto.projProcess;

import com.example.pmpProcess.data.projectActivity.DataActivity;
import com.example.pmpProcess.data.projectProcess.DataProcess;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.LinkedList;

@Slf4j
public class ProcessManager {
    /**
     * 过程组链表
     */
    static LinkedList<DataProcess> processLinkedList = new LinkedList<>();

    public static DataProcess getProcessByID(Integer processID) {
        int processLinkedListSize = processLinkedList.size();
        for (int i = 0; i < processLinkedListSize; i++) {
            DataProcess element = processLinkedList.get(i);
            if (element.getID() == processID) {
                //TODO 增加日志信息
                return element;
            }
        }

        return null;
    }

    /**
     * 处理过程组的信息
     *
     * @param processDesc 过程组名称描述信息
     * @param processID   过程组ID
     * @param textDesc    过程组说明信息
     */
    public static void addProcessToList(String processDesc, Integer processID, String textDesc) {


        DataProcess dataProcess = getProcessByID(processID);
        if (dataProcess != null) {
            return;
        }
        dataProcess = new DataProcess();

        dataProcess.setDescription(processDesc);
        dataProcess.setID(processID);
        dataProcess.setTextDesc(textDesc);

        //将过程组元素添加到过程组链表中
        DataProcess dataProcessEle = getProcessByID(dataProcess.getID());
        if (dataProcessEle == null) {
            processLinkedList.add(dataProcess);
        } else {
            //TODO 增加交互性信息
        }

        return;
    }

    /**
     * 将过程组配置落入文件中
     *
     * @return
     * @throws IOException
     */
    public boolean appendProcessConfig() throws IOException {
        String fileName = "D:\\rmt_code_server\\pmpProcessor\\pmpProcess\\config\\Process.txt";
        FileWriter fileWriter = new FileWriter(fileName, true);
        int activityCount = processLinkedList.size();
        for (int i = 0; i < activityCount; i++) {
            DataProcess dataProcess = processLinkedList.get(i);

            //x="描述"；过程组ID=活动描述
            String destString = dataProcess.getID().toString() + "=" + dataProcess.getDescription();
            fileWriter.append(destString, 0, destString.length());
        }

        fileWriter.flush();
        fileWriter.close();

        return true;
    }

    /**
     * 获取知识域配置文件
     *
     * @return
     * @throws IOException
     */
    public boolean getProcessConfig() throws IOException {
        String fileName = "D:\\rmt_code_server\\pmpProcessor\\pmpProcess\\config\\Process.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String bufferData = null;

        while ((bufferData = bufferedReader.readLine()) != null) {
            log.info(bufferData);
        }
        return true;
    }

    /**
     * 向过程组中添加活动
     *
     * @param activity 活动详情
     */
    public static void addActivity(DataActivity activity) {
        DataProcess dataProcess = getProcessByID(activity.getID());
        if (dataProcess == null) {
            //TODO 增加用户交互性信息
            return;
        }

        dataProcess.addActivity(activity);

        return;
    }
}

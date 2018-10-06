package com.example.pmpProcess.dto.projProcess;

import com.example.pmpProcess.data.projectActivity.DataActivity;
import com.example.pmpProcess.data.projectProcess.DataProcess;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

@Slf4j
public class ProcessManager {
    /**
     * 过程组链表
     */
    static LinkedList<DataProcess> processLinkedList = new LinkedList<>();

    /**
     * 根据过程组ID，从过程组链表获取制定过程组的详细信息
     *
     * @param processID
     * @return
     */
    public static DataProcess getProcessByID(String processID) {
        int LinkedListSize = processLinkedList.size();
        for (int i = 0; i < LinkedListSize; i++) {
            DataProcess element = processLinkedList.get(i);
            if (element.getID().equalsIgnoreCase(processID)) {
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
    private static void loadProcessToList(String processDesc, String processID, String textDesc) {


        DataProcess dataProcess = getProcessByID(processID);
        if (dataProcess != null) {
            //TODO 增加交互性信息
            log.info("Process is already in list.");

            return;
        }
        dataProcess = new DataProcess();

        dataProcess.setDescription(processDesc);
        dataProcess.setID(processID);
        dataProcess.setTextDesc(textDesc);

        //将过程组元素添加到过程组链表中
        processLinkedList.add(dataProcess);

        return;
    }

    /**
     * 将过程组配置落入文件中
     *
     * @return
     * @throws IOException
     */
    public boolean appendProcessConfig() throws IOException {
        String fileName = ".\\config\\Process.txt";
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
    public boolean parseProcessConfig() throws IOException {
        String fileName = ".\\config\\Process.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "gbk"));
        String bufferData = null;

        String processDesc = "";
        String processID = "";
        String textDesc = "";

        while ((bufferData = bufferedReader.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferData);
            int numTokenizer = stringTokenizer.countTokens();
            String[] processInfoList = new String[numTokenizer];
            int i = 0;

            //按照空格进行解析
            while (stringTokenizer.hasMoreElements()) {
                processInfoList[i] = stringTokenizer.nextToken();
                i++;
            }
            //将解析结果进行处理
            processDesc = processInfoList[1];
            processID = processInfoList[0];

            loadProcessToList(processDesc, processID, textDesc);
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

    public static void displayActivities(String processID) {
        DataProcess process = getProcessByID(processID);
        ArrayList activityList = process.getActivityArrayList();

        int activityCount = activityList.size();
        for (int i = 0; i < activityCount; i++) {
            DataActivity dataActivity = (DataActivity) activityList.get(i);
            log.info("xxxxxxxxxxxxxx");
            log.info(dataActivity.getDescription());
            log.info(dataActivity.getID());
        }
    }

    public static void displayProcess() {
        int processCount = processLinkedList.size();

        for (int i = 0; i < processCount; i++) {
            DataProcess dataProcess = processLinkedList.get(i);
            log.info(dataProcess.getID() + ":" + dataProcess.getDescription());
        }
    }
}

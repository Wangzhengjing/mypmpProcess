package com.example.pmpProcess.dto.projField;

import com.example.pmpProcess.data.projectActivity.DataActivity;
import com.example.pmpProcess.data.projectField.DataProjectField;
import com.example.pmpProcess.data.projectProcess.DataProcess;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

@Data
@Slf4j
public class FieldManager {
    /**
     * 知识域链表
     */
    static LinkedList<DataProjectField> projectFieldLinkedList = new LinkedList<>();

    /**
     * 通过知识域ID信息，获取对应知识域详情
     *
     * @param projectFieldID 知识域ID
     * @return 返回知识域详细信息
     */
    public static DataProjectField getProjFieldByID(String projectFieldID) {
        int LinkedListSize = projectFieldLinkedList.size();
        for (int i = 0; i < LinkedListSize; i++) {
            DataProjectField element = projectFieldLinkedList.get(i);
            if (element.getID().equalsIgnoreCase(projectFieldID)) {
                //TODO 增加日志信息
                return element;
            }
        }

        return null;
    }

    /**
     * 设置项目知识领域详细信息
     *
     * @param fieldDesc 知识域名称描述
     * @return 返回知识域详细信息
     */
    public DataProjectField loadProjectFieldToList(String fieldDesc, String fieldID) {
        DataProjectField dataProjectField = new DataProjectField();

        //初始化知识域对象
        dataProjectField.setActivityArrayList(new ArrayList<>());
        dataProjectField.setDescription(fieldDesc);
        dataProjectField.setID(fieldID);

        DataProjectField projectField = getProjFieldByID(dataProjectField.getID());
        if (projectField == null) {
            projectFieldLinkedList.add(dataProjectField);
        } else {
            //TODO 增加用户交互性信息
        }

        return dataProjectField;
    }

    /**
     * 将知识域配置落入文件中
     *
     * @return
     * @throws IOException
     */
    public boolean appendProjectFieldConfig() throws IOException {
        String fileName = ".\\config\\ProjField.txt";
        FileWriter fileWriter = new FileWriter(fileName, true);
        int activityCount = projectFieldLinkedList.size();
        for (int i = 0; i < activityCount; i++) {
            DataProjectField dataProjectField = projectFieldLinkedList.get(i);

            //x="描述"；知识域ID=活动描述
            String destString = dataProjectField.getID().toString() + "=" + dataProjectField.getDescription();
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
    public boolean parseProjFieldConfig() throws IOException {
        String fileName = ".\\config\\ProjField.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"gbk"));
        String bufferData = null;

        String fieldDesc = "";
        String fieldID = "";

        while ((bufferData = bufferedReader.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferData);
            int numTokenizer = stringTokenizer.countTokens();
            String[] projectFieldInfoList = new String[numTokenizer];
            int i = 0;

            //按照空格进行解析
            while (stringTokenizer.hasMoreElements()) {
                projectFieldInfoList[i] = stringTokenizer.nextToken();
                i++;
            }
            //将解析结果进行处理
            fieldDesc = projectFieldInfoList[1];
            fieldID = projectFieldInfoList[0];

            loadProjectFieldToList(fieldDesc, fieldID);
        }
        return true;
    }


    /**
     * 向知识域中添加活动
     *
     * @param activity 活动详情
     */
    public static void addActivity(DataActivity activity) {
        DataProjectField projectField = getProjFieldByID(activity.getID());
        if (projectField == null) {
            //TODO 增加用户交互性信息
            return;
        }

        log.info("add activity "+activity.getID()+" to ProjField "+projectField.getID());

        projectField.getActivityArrayList().add(activity);

        return;
    }

    public static void displayActivities(String projFieldID) {
        DataProjectField projField = getProjFieldByID(projFieldID);
        if (projField == null) {
            //TODO 增加用户交互信息
            log.info("No projectFieldID "+projFieldID+" is found.");
            return;
        }
        ArrayList activityList = projField.getActivityArrayList();

        int activityCount = activityList.size();
        for (int i = 0; i < activityCount; i++) {
            DataActivity dataActivity = (DataActivity) activityList.get(i);
            log.info(projField.getID()+" ："+dataActivity.getID()+" : "+dataActivity.getDescription());
        }
        log.info("一共"+activityCount+" 个活动");

        return;
    }


    public static void displayProjField() {
        int processCount = projectFieldLinkedList.size();

        for (int i = 0; i < processCount; i++) {
            DataProjectField dataProjectField = projectFieldLinkedList.get(i);
            log.info(dataProjectField.getID() + ":" + dataProjectField.getDescription());
        }
    }

}

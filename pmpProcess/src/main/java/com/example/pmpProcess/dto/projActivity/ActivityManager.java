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
import java.util.StringTokenizer;

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
    public static DataActivity getActivityByID(String activityID) {
        int LinkedListSize = activityLinkedList.size();
        for (int i = 0; i < LinkedListSize; i++) {
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
    public DataActivity loadActivityToList(String actDesc, String actID, String fieldDesc,
                                           String fieldID, String processDesc, String processID) {
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
    public boolean parseActivityConfig() throws IOException {
        String fileName = "D:\\rmt_code_server\\pmpProcessor\\pmpProcess\\config\\Activity.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "gbk"));
        String bufferData = null;

        String actDesc = "";
        String actID = "";
        String fieldDesc = "";
        String fieldID = "";
        String processDesc = "";
        String processID = "";

        while ((bufferData = bufferedReader.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferData);
            int numTokenizer = stringTokenizer.countTokens();
            String[] activityInfoList = new String[numTokenizer];
            int i = 0;

            //按照空格进行解析
            while (stringTokenizer.hasMoreElements()) {
                activityInfoList[i] = stringTokenizer.nextToken();
                i++;
            }
            //将解析结果进行处理
            actDesc = activityInfoList[1];
            actID = activityInfoList[0];

            loadActivityToList(actDesc, actID, fieldDesc,
                    fieldID, processDesc, processID);
        }

        bufferedReader.close();

        return true;
    }

    /**
     * 向项目活动中添加活动输入，并同时进行反向关联
     *
     * @param dataInput 活动输入对象
     * @return true/false
     */
    public static boolean addInput(DataInput dataInput) {
        DataActivity dataActivity = getActivityByID(dataInput.getParentActivityID());
        if (dataActivity == null) {
            //TODO 增加用户交互信息
            return false;
        }

        //将输入关联至项目活动中
        dataActivity.addInput(dataInput);

        //将项目活动反向关联至活动输入中
        dataInput.addActivity(dataActivity);

        return true;
    }

    /**
     * 向项目活动中添加活动输出，并同时进行反向关联
     *
     * @param dataOutput 活动输出对象
     * @return true/false
     */
    public static boolean addOutput(DataOutput dataOutput) {
        DataActivity dataActivity = getActivityByID(dataOutput.getParentActivityID());
        if (dataActivity == null) {
            //TODO 增加用户交互信息
            return false;
        }

        //将活动输出关联至项目活动中
        dataActivity.addOutput(dataOutput);

        //将项目活动反向关联到活动输出中
        dataOutput.addActivity(dataActivity);

        return true;
    }

    /**
     * 向项目活动中添加项目工具，并同时进行反向关联
     *
     * @param dataTools 项目工具对象
     * @return true/false
     */
    public static boolean addTools(DataTools dataTools) {
        DataActivity dataActivity = getActivityByID(dataTools.getParentActivityID());
        if (dataActivity == null) {
            //TODO 增加用户交互信息
            return false;
        }

        //将项目工具关联到项目活动中
        dataActivity.addTools(dataTools);

        //将项目活动反向关联到项目工具中
        dataTools.addActivity(dataActivity);

        return true;
    }
}

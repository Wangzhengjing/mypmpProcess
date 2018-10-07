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
import java.util.ArrayList;
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
            if (element.getID().equalsIgnoreCase(activityID)) {
                //TODO 增加日志信息
                return element;
            }
        }

        return null;
    }

    /**
     * 设置活动名称等信息，并将活动关联至过程组，知识域中
     *
     * @param actDesc     活动描述
     * @param fieldDesc   知识域描述
     * @param processDesc 过程组描述
     * @return 返回活动信息
     */
    public DataActivity loadActivityToList(String actDesc, String actID, String fieldDesc,
                                           String fieldID, String processDesc, String processID) {
        DataActivity dataActivity = new DataActivity();

        dataActivity.setInputList(new ArrayList<>());
        dataActivity.setOutputList(new ArrayList<>());
        dataActivity.setToolsList(new ArrayList<>());
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
            dataProjectField.getActivityArrayList().add(dataActivity);
        }

        //将活动加入过程组链表
        DataProcess dataProcess = ProcessManager.getProcessByID(dataActivity.getProcessID());
        if (dataProcess == null) {
            //TODO 增加日志信息
        } else {
            log.info(dataProcess.getID());
            dataProcess.getActivityArrayList().add(dataActivity);
        }

        return dataActivity;
    }

    /**
     * 将活动的配置落入文件中
     *
     * @return
     * @throws IOException
     */
    public boolean insertActivityConfig(DataActivity dataActivity) throws IOException {
        String fileName = ".\\config\\Activity.txt";
        FileWriter fileWriter = new FileWriter(fileName, true);

        //x.x.x="描述"；过程组ID.知识域ID.活动ID=活动描述
        String destString = dataActivity.getProcessID().toString() + "."
                + dataActivity.getParentFieldID().toString() + "."
                + dataActivity.getID() + "="
                + dataActivity.getDescription() + "\r\n";
        fileWriter.append(destString, 0, destString.length());

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
        String fileName = ".\\config\\Activity.txt";
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

            //解析项目活动的ID信息
            StringTokenizer activityIDStrTokenizer = new StringTokenizer(actID, ".");
            numTokenizer = activityIDStrTokenizer.countTokens();
            String[] activityIDInfoList = new String[numTokenizer];
            i = 0;

            while (activityIDStrTokenizer.hasMoreElements()) {
                activityIDInfoList[i] = activityIDStrTokenizer.nextToken();
                i++;
            }
            //处理活动ID的信息
            processID = activityIDInfoList[0];
            fieldID = activityIDInfoList[1];

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
        DataActivity dataActivity = getActivityByID(dataInput.getActivityID());
        if (dataActivity == null) {
            //TODO 增加用户交互信息
            return false;
        }

        //将输入关联至项目活动中
        log.info("add input " + dataInput.getID() + " to activity " + dataActivity.getID());
        dataActivity.getInputList().add(dataInput);

        //将项目活动反向关联至活动输入中
        log.info("add activity " + dataActivity.getID() + " to input " + dataInput.getID());
        dataInput.getActivityArrayList().add(dataActivity);

        return true;
    }

    /**
     * 向项目活动中添加活动输出，并同时进行反向关联
     *
     * @param dataOutput 活动输出对象
     * @return true/false
     */
    public static boolean addOutput(DataOutput dataOutput) {
        DataActivity dataActivity = getActivityByID(dataOutput.getActivityID());
        if (dataActivity == null) {
            //TODO 增加用户交互信息
            return false;
        }

        //将活动输出关联至项目活动中
        log.info("add output " + dataOutput.getID() + " to activity " + dataActivity.getID());
        dataActivity.getOutputList().add(dataOutput);

        //将项目活动反向关联到活动输出中
        log.info("add activity " + dataActivity.getID() + " to output " + dataOutput.getID());
        dataOutput.getActivityArrayList().add(dataActivity);

        return true;
    }

    /**
     * 向项目活动中添加项目工具，并同时进行反向关联
     *
     * @param dataTools 项目工具对象
     * @return true/false
     */
    public static boolean addTools(DataTools dataTools) {
        DataActivity dataActivity = getActivityByID(dataTools.getActivityID());
        if (dataActivity == null) {
            //TODO 增加用户交互信息
            return false;
        }

        //将项目工具关联到项目活动中
        log.info("add tool " + dataTools.getID() + " to activity " + dataActivity.getID());
        dataActivity.getToolsList().add(dataTools);

        //将项目活动反向关联到项目工具中
        log.info("add activity " + dataActivity.getID() + " to tools " + dataTools.getID());
        dataTools.getActivityArrayList().add(dataActivity);

        return true;
    }

    /**
     * 展示所有活动
     */
    public static void displayActivities() {
        int processCount = activityLinkedList.size();

        for (int i = 0; i < processCount; i++) {
            DataActivity dataActivity = activityLinkedList.get(i);
            log.info(dataActivity.getID() + ":" + dataActivity.getDescription());
        }
    }

    /**
     * 根据指定项目活动ID，展示活动下所有输入
     *
     * @param activityID 指定项目活动ID
     */
    public static void displayInput(String activityID) {
        DataActivity dataActivity = getActivityByID(activityID);
        if (dataActivity == null) {
            //TODO 增加交互信息
            log.info("No activity with ID " + activityID + " is found.");
            return;
        }

        ArrayList inputArrayList = dataActivity.getInputList();
        int inputCount = inputArrayList.size();
        for (int i = 0; i < inputCount; i++) {
            DataInput dataInput = (DataInput) inputArrayList.get(i);
            log.info(dataInput.getActivityID() + ":" + dataInput.getID() + ":" + dataInput.getDescription());
        }
        log.info("一共" + inputCount + " 个输入");

        return;
    }

    /**
     * 根据指定项目活动ID，展示活动下所有输出
     *
     * @param activityID 指定项目活动ID
     */
    public static void displayOutput(String activityID) {
        DataActivity dataActivity = getActivityByID(activityID);
        if (dataActivity == null) {
            //TODO 增加交互信息
            log.info("No activity with ID " + activityID + " is found.");
            return;
        }

        ArrayList outputArrayList = dataActivity.getOutputList();
        int outputCount = outputArrayList.size();
        for (int i = 0; i < outputCount; i++) {
            DataOutput dataOutput = (DataOutput) outputArrayList.get(i);
            log.info(dataOutput.getActivityID() + ":" + dataOutput.getID() + ":" + dataOutput.getDescription());
        }
        log.info("一共" + outputCount + " 个输出");

        return;
    }

    /**
     * 根据指定项目活动ID，展示活动下所有工具
     *
     * @param activityID 指定项目活动
     */
    public static void displayTools(String activityID) {
        DataActivity dataActivity = getActivityByID(activityID);
        if (dataActivity == null) {
            //TODO 增加交互信息
            log.info("No activity with ID " + activityID + " is found.");
            return;
        }

        ArrayList toolsArrayList = dataActivity.getToolsList();
        int toolsCount = toolsArrayList.size();
        for (int i = 0; i < toolsCount; i++) {
            DataTools dataTools = (DataTools) toolsArrayList.get(i);
            log.info(dataTools.getActivityID() + ":" + dataTools.getID() + ":" + dataTools.getDescription());
        }
        log.info("一共" + toolsCount + " 个工具");

        return;
    }

}

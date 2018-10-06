package com.example.pmpProcess.dto.projActivity;

import com.example.pmpProcess.data.projectActivity.DataActivity;
import com.example.pmpProcess.data.projectActivity.DataInput;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

@Slf4j
public class InputManager {
    /**
     * 活动链表
     */
    static LinkedList<DataInput> inputLinkedList = new LinkedList<>();

    /**
     * 根据输入ID，获取输入的详细信息
     *
     * @param inputID 输入ID
     * @return 输入对象
     */
    private static DataInput getInputByID(String inputID) {
        int LinkedListSize = inputLinkedList.size();
        for (int i = 0; i < LinkedListSize; i++) {
            DataInput element = inputLinkedList.get(i);
            if (element.getID().equalsIgnoreCase(inputID)) {
                //TODO 增加日志信息
                return element;
            }
        }

        return null;
    }

    /**
     * 加载输入信息到内存中
     *
     * @param description 输入说明
     * @param inputID     输入ID
     * @return 返回输入对象
     */
    private DataInput loadInputToList(String description, String inputID, String activityID) {
        DataInput dataInput = new DataInput();

        dataInput.setActivityArrayList(new ArrayList<>());
        dataInput.setDescription(description);
        dataInput.setID(inputID);
        dataInput.setActivityID(activityID);

        //向输入链表中添加输入对象
        DataInput inputEle = getInputByID(inputID);
        if (inputEle == null) {
            inputLinkedList.add(dataInput);
        } else {
            //TODO 增加交互性信息
        }

        //将输入关联到项目活动中
        ActivityManager.addInput(dataInput);

        return dataInput;
    }

    /**
     * 将配置信息落入配置文件中
     *
     * @return ture/false
     * @throws IOException
     */
    public boolean insertInputConfig(DataInput dataInput) throws IOException {
        String fileName = ".\\config\\Input.txt";
        FileWriter fileWriter = new FileWriter(fileName, true);

        //x.x.x.x=活动ID.输入ID "描述"
        String destString = dataInput.getActivityID().toString() + "."
                + dataInput.getID() + "\t"
                + dataInput.getDescription();
        fileWriter.append(destString, 0, destString.length());

        fileWriter.flush();
        fileWriter.close();

        return true;
    }

    /**
     * 读取输入配置文件信息
     *
     * @return
     * @throws IOException
     */
    public boolean parseInputConfig() throws IOException {
        String fileName = ".\\config\\Input.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String bufferData = null;

        String description = "";
        String id = "";
        String activityID = "";
        String inputID = "";

        while ((bufferData = bufferedReader.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferData);
            int numTokenizer = stringTokenizer.countTokens();
            String[] inputDefsList = new String[numTokenizer];
            int i = 0;

            //按照空格进行解析
            while (stringTokenizer.hasMoreElements()) {
                inputDefsList[i] = stringTokenizer.nextToken();
                i++;
            }
            //将解析结果进行处理
            description = inputDefsList[1];
            id = inputDefsList[0];

            //解析项目活动的ID信息
            StringTokenizer activityIDStrTokenizer = new StringTokenizer(id, ".");
            numTokenizer = activityIDStrTokenizer.countTokens();
            String[] activityIDInfoList = new String[numTokenizer];
            i = 0;

            while (activityIDStrTokenizer.hasMoreElements()) {
                activityIDInfoList[i] = activityIDStrTokenizer.nextToken();
                i++;
            }
            //处理活动ID的信息
            activityID = activityIDInfoList[0].toString() + "."
                    + activityIDInfoList[1].toString() + "."
                    + activityIDInfoList[2];
            inputID = activityIDInfoList[3];

            loadInputToList(description, inputID, activityID);
        }

        bufferedReader.close();

        return true;
    }

    /**
     * 从配置中获取输入的定义
     *
     * @return
     * @throws IOException
     */
    public boolean parseInputDefsConfig() throws IOException {
        String fileName = ".\\config\\InputDefs.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "gbk"));
        String bufferData = null;

        String description = "";
        String id = "";
        String activityID = "";

        while ((bufferData = bufferedReader.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferData);
            int numTokenizer = stringTokenizer.countTokens();
            String[] inputDefsList = new String[numTokenizer];
            int i = 0;

            //按照空格进行解析
            while (stringTokenizer.hasMoreElements()) {
                inputDefsList[i] = stringTokenizer.nextToken();
                i++;
            }
            //将解析结果进行处理
            description = inputDefsList[1];
            id = inputDefsList[0];

            loadInputToList(description, id, activityID);
        }

        bufferedReader.close();

        return true;
    }

    public static boolean addActivity(DataActivity dataActivity) {
        return true;
    }
}

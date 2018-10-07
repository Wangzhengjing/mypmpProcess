package com.example.pmpProcess.dto.projActivity;

import com.example.pmpProcess.data.projectActivity.DataActivity;
import com.example.pmpProcess.data.projectActivity.DataInput;
import com.example.pmpProcess.data.projectActivity.DataOutput;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

@Slf4j
public class OutputManager {
    /**
     * 活动链表
     */
    static LinkedList<DataOutput> outputLinkedList = new LinkedList<>();

    /**
     * 根据输出的唯一标识ID，获取输出对象
     *
     * @param outputID 输出标识ID
     * @return 输出对象
     */
    private static DataOutput getOutputByID(String outputID) {
        int LinkedListSize = outputLinkedList.size();
        for (int i = 0; i < LinkedListSize; i++) {
            DataOutput element = outputLinkedList.get(i);
            if (element.getID().equalsIgnoreCase(outputID)) {
                //TODO 增加日志信息
                return element;
            }
        }

        return null;
    }

    /**
     * 加载输出信息到内存中
     *
     * @param description 输出描述
     * @param outputID    输出全局唯一标识
     * @return 输出对象
     */
    private DataOutput loadOutputToList(String description, String outputID, String activityID) {
        DataOutput dataOutput = new DataOutput();

        dataOutput.setActivityArrayList(new ArrayList<>());
        dataOutput.setDescription(description);
        dataOutput.setID(outputID);
        dataOutput.setActivityID(activityID);

        //向输入链表中添加输入对象
        DataOutput inputEle = getOutputByID(outputID);
        if (inputEle == null) {
            outputLinkedList.add(dataOutput);
        } else {
            //TODO 增加交互性信息
        }

        //将输出关联到项目活动中
        ActivityManager.addOutput(dataOutput);

        return dataOutput;
    }


    /**
     * 将输出信息落入配置文件中
     *
     * @return
     * @throws IOException
     */
    public boolean insertOutputConfig(DataOutput dataOutput) throws IOException {
        String fileName = ".\\config\\Output.txt";
        FileWriter fileWriter = new FileWriter(fileName, true);

        //x.x.x.x=活动ID.输出ID "描述"
        String destString = dataOutput.getActivityID().toString() + "."
                + dataOutput.getID() + "\t"
                + dataOutput.getDescription() + "\r\n";
        //fileWriter.append(destString, 0, destString.length());
        fileWriter.write(destString);

        fileWriter.flush();
        fileWriter.close();

        return true;

    }

    /**
     * 获取输出配置文件信息
     *
     * @return
     * @throws IOException
     */
    public boolean parseOutputConfig() throws IOException {
        String fileName = ".\\config\\Output.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String bufferData = null;

        String description = "";
        String id = "";
        String activityID = "";
        String outputID = "";

        while ((bufferData = bufferedReader.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferData);
            int numTokenizer = stringTokenizer.countTokens();
            String[] outputDefsList = new String[numTokenizer];
            int i = 0;

            //按照空格进行解析
            while (stringTokenizer.hasMoreElements()) {
                outputDefsList[i] = stringTokenizer.nextToken();
                i++;
            }
            //将解析结果进行处理
            description = outputDefsList[1];
            id = outputDefsList[0];

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
            outputID = activityIDInfoList[3];

            loadOutputToList(description, outputID, activityID);
        }

        bufferedReader.close();

        return true;
    }

    /**
     * 从配置中获取输出的定义
     *
     * @return
     * @throws IOException
     */
    public boolean parseOutputDefsConfig() throws IOException {
        String fileName = ".\\config\\OutputDefs.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "gbk"));
        String bufferData = null;

        String description = "";
        String id = "";
        String activityID = "";

        while ((bufferData = bufferedReader.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferData);
            int numTokenizer = stringTokenizer.countTokens();
            String[] outputDefsList = new String[numTokenizer];
            int i = 0;

            //按照空格进行解析
            while (stringTokenizer.hasMoreElements()) {
                outputDefsList[i] = stringTokenizer.nextToken();
                i++;
            }
            //将解析结果进行处理
            description = outputDefsList[1];
            id = outputDefsList[0];

            loadOutputToList(description, id, activityID);
        }

        bufferedReader.close();

        return true;
    }

    /**
     * 设置活动的输出
     *
     * @param outputDesc 输出描述
     * @param actDesc    活动描述
     * @return 返回输出信息
     */
    public DataOutput setActOutput(String outputDesc, String actDesc) {
        DataOutput dataOutput = new DataOutput();

        dataOutput.setDescription(outputDesc);
        dataOutput.setActivityDescription(actDesc);

        return dataOutput;
    }

    /**
     * 展示所有输出
     */
    public static void displayOutputs() {
        int outputCount = outputLinkedList.size();

        for (int i = 0; i < outputCount; i++) {
            DataOutput dataOutput = outputLinkedList.get(i);
            log.info(dataOutput.getID() + ":" + dataOutput.getDescription());
        }
    }

    /**
     * 根据指定输出ID，展示包含此输出的所有项目活动
     *
     * @param outputID 指定输出ID
     */
    public static void displayActivities(String outputID) {
        DataOutput dataOutput = getOutputByID(outputID);
        if (dataOutput == null) {
            //TODO 增加交互信息
            log.info("No output with ID " + outputID + " is found.");
            return;
        }

        ArrayList activityArrayList = dataOutput.getActivityArrayList();
        int activityCount = activityArrayList.size();
        for (int i = 0; i < activityCount; i++) {
            DataActivity dataActivity = (DataActivity) activityArrayList.get(i);
            log.info(dataActivity.getID() + ":" + dataActivity.getDescription());
        }
        log.info("一共" + activityCount + " 个活动");

        return;
    }
}

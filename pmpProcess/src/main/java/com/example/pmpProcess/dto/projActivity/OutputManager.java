package com.example.pmpProcess.dto.projActivity;

import com.example.pmpProcess.data.projectActivity.DataOutput;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

@Slf4j
public class OutputManager {
    /**
     * 活动链表
     */
    static LinkedList<DataOutput> OutputLinkedList = new LinkedList<>();

    /**
     * 根据输出的唯一标识ID，获取输出对象
     *
     * @param outputID 输出标识ID
     * @return 输出对象
     */
    private static DataOutput getOutputByID(String outputID) {
        int LinkedListSize = OutputLinkedList.size();
        for (int i = 0; i < LinkedListSize; i++) {
            DataOutput element = OutputLinkedList.get(i);
            if (element.getID() == outputID) {
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
     * @param id          输出全局唯一标识
     * @return 输出对象
     */
    private DataOutput loadOutputToList(String description, String id) {
        DataOutput dataOutput = new DataOutput();

        dataOutput.setDescription(description);
        dataOutput.setID(id);

        //向输入链表中添加输入对象
        DataOutput inputEle = getOutputByID(id);
        if (inputEle == null) {
            OutputLinkedList.add(dataOutput);
        } else {
            //TODO 增加交互性信息
        }

        return dataOutput;
    }


    /**
     * 将输出信息落入配置文件中
     *
     * @return
     * @throws IOException
     */
    public boolean appendOutputConfig() throws IOException {
        String fileName = "D:\\rmt_code_server\\pmpProcessor\\pmpProcess\\config\\Output.txt";
        FileWriter fileWriter = new FileWriter(fileName, true);
        int activityCount = OutputLinkedList.size();
        for (int i = 0; i < activityCount; i++) {
            DataOutput dataOutput = OutputLinkedList.get(i);

            //x="描述"；ID=输入描述
            String destString = dataOutput.getParentActivityID().toString() + dataOutput.getDescription();
            fileWriter.append(destString, 0, destString.length());
        }

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
        String fileName = "D:\\rmt_code_server\\pmpProcessor\\pmpProcess\\config\\Output.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String bufferData = null;

        String description = "";
        String id = "";

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

            loadOutputToList(description, id);
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
        String fileName = "D:\\rmt_code_server\\pmpProcessor\\pmpProcess\\config\\OutputDefs.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "gbk"));
        String bufferData = null;

        String description = "";
        String id = "";

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

            loadOutputToList(description, id);
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
        dataOutput.setParentAct(actDesc);

        return dataOutput;
    }

}

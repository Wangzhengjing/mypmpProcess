package com.example.pmpProcess.dto.projActivity;

import com.example.pmpProcess.data.projectActivity.DataInput;
import com.example.pmpProcess.data.projectActivity.DataOutput;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.LinkedList;

@Slf4j
public class OutputManager {
    /**
     * 活动链表
     */
    static LinkedList<DataOutput> OutputLinkedList = new LinkedList<>();

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
            String destString = dataOutput.getParentID().toString() + dataOutput.getDescription();
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
    public boolean getOutputConfig() throws IOException {
        String fileName = "D:\\rmt_code_server\\pmpProcessor\\pmpProcess\\config\\Output.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String bufferData = null;

        while ((bufferData = bufferedReader.readLine()) != null) {
            log.info(bufferData);
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

    /**
     * 从配置中获取输出的定义
     *
     * @return
     * @throws IOException
     */
    public boolean getOutputDefsConfig() throws IOException {
        String fileName = "D:\\rmt_code_server\\pmpProcessor\\pmpProcess\\config\\OutputDefs.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String bufferData = null;

        while ((bufferData = bufferedReader.readLine()) != null) {
            log.info(bufferData);
        }

        bufferedReader.close();

        return true;
    }

}

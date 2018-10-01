package com.example.pmpProcess.dto.projActivity;

import com.example.pmpProcess.data.projectActivity.DataActivity;
import com.example.pmpProcess.data.projectActivity.DataInput;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.LinkedList;

@Slf4j
public class InputManager {
    /**
     * 活动链表
     */
    static LinkedList<DataInput> inputLinkedList = new LinkedList<>();

    /**
     * 将配置信息落入配置文件中
     *
     * @return
     * @throws IOException
     */
    public boolean appendInputConfig() throws IOException {
        String fileName = "D:\\rmt_code_server\\pmpProcessor\\pmpProcess\\config\\Input.txt";
        FileWriter fileWriter = new FileWriter(fileName, true);
        int activityCount = inputLinkedList.size();
        for (int i = 0; i < activityCount; i++) {
            DataInput dataInput = inputLinkedList.get(i);

            //x="描述"；ID=输入描述
            String destString = dataInput.getParentID().toString() + dataInput.getDescription();
            fileWriter.append(destString, 0, destString.length());
        }

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
    public boolean getInputConfig() throws IOException {
        String fileName = "D:\\rmt_code_server\\pmpProcessor\\pmpProcess\\config\\Input.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String bufferData = null;

        while ((bufferData = bufferedReader.readLine()) != null) {
            log.info(bufferData);
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
    public boolean getInputDefsConfig() throws IOException {
        String fileName = "D:\\rmt_code_server\\pmpProcessor\\pmpProcess\\config\\InputDefs.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String bufferData = null;

        while ((bufferData = bufferedReader.readLine()) != null) {
            log.info(bufferData);
        }

        bufferedReader.close();

        return true;
    }

}

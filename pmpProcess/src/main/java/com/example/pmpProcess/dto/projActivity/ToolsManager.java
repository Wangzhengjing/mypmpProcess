package com.example.pmpProcess.dto.projActivity;

import com.example.pmpProcess.data.projectActivity.DataInput;
import com.example.pmpProcess.data.projectActivity.DataTools;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.LinkedList;

@Slf4j
public class ToolsManager {
    /**
     * 活动链表
     */
    static LinkedList<DataTools> ToolsLinkedList = new LinkedList<>();

    /**
     * 设置活动的工具信息
     *
     * @param toolsDesc 工具描述
     * @return 返回工具信息
     */
    public DataTools setActTools(String toolsDesc) {
        DataTools dataTools = new DataTools();

        dataTools.setToolsDescription(toolsDesc);

        return dataTools;
    }

    /**
     * 将工具信息落入配置文件
     *
     * @return
     * @throws IOException
     */
    public boolean appendToolsConfig() throws IOException {
        String fileName = "D:\\rmt_code_server\\pmpProcessor\\pmpProcess\\config\\Input.txt";
        FileWriter fileWriter = new FileWriter(fileName, true);
        int activityCount = ToolsLinkedList.size();
        for (int i = 0; i < activityCount; i++) {
            DataTools dataTools = ToolsLinkedList.get(i);

            //x="描述"；ID=输入描述
            String destString = dataTools.getParentID().toString() + dataTools.getToolsDescription();
            fileWriter.append(destString, 0, destString.length());
        }

        fileWriter.flush();
        fileWriter.close();

        return true;
    }

    /**
     * 获取工具配置文件信息
     *
     * @return
     * @throws IOException
     */
    public boolean getToolsConfig() throws IOException {
        String fileName = "D:\\rmt_code_server\\pmpProcessor\\pmpProcess\\config\\Tools.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String bufferData = null;

        while ((bufferData = bufferedReader.readLine()) != null) {
            log.info(bufferData);
        }

        bufferedReader.close();

        return true;
    }

    /**
     * 从配置中获取工具定义
     *
     * @return
     * @throws IOException
     */
    public boolean getToolsDefsConfig() throws IOException {
        String fileName = "D:\\rmt_code_server\\pmpProcessor\\pmpProcess\\config\\ToolsDefs.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String bufferData = null;

        while ((bufferData = bufferedReader.readLine()) != null) {
            log.info(bufferData);
        }

        bufferedReader.close();

        return true;
    }
}

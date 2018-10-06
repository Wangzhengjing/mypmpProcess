package com.example.pmpProcess.dto.projActivity;

import com.example.pmpProcess.data.projectActivity.DataTools;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

@Slf4j
public class ToolsManager {
    /**
     * 活动链表
     */
    static LinkedList<DataTools> ToolsLinkedList = new LinkedList<>();

    /**
     * 根据工具ID，获取工具的详细信息
     *
     * @param toolsID 工具ID
     * @return 工具对象
     */
    private static DataTools getToolsByID(String toolsID) {
        int LinkedListSize = ToolsLinkedList.size();
        for (int i = 0; i < LinkedListSize; i++) {
            DataTools element = ToolsLinkedList.get(i);
            if (element.getID().equalsIgnoreCase(toolsID)) {
                //TODO 增加日志信息
                return element;
            }
        }

        return null;
    }

    /**
     * 加载项目工具信息到内存中
     *
     * @param description 工具描述
     * @param id          工具全局唯一标识ID
     * @return 工具对象
     */
    private DataTools loadToolsToList(String description, String id, String activityID) {
        DataTools dataTools = new DataTools();

        dataTools.setActivityArrayList(new ArrayList<>());
        dataTools.setToolsDescription(description);
        dataTools.setID(id);
        dataTools.setActivityID(activityID);

        //向输入链表中添加输入对象
        DataTools toolsEle = getToolsByID(id);
        if (toolsEle == null) {
            ToolsLinkedList.add(dataTools);
        } else {
            //TODO 增加交互性信息
        }

        //将项目工具关联到项目活动
        ActivityManager.addTools(dataTools);

        return dataTools;
    }


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
    public boolean insertToolsConfig(DataTools dataTools) throws IOException {
        String fileName = ".\\config\\Input.txt";
        FileWriter fileWriter = new FileWriter(fileName, true);

        //x.x.x.x=活动ID.工具ID "描述"
        String destString = dataTools.getActivityID().toString() + "."
                + dataTools.getID() + "\t"
                + dataTools.getToolsDescription();
        fileWriter.append(destString, 0, destString.length());

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
    public boolean parseToolsConfig() throws IOException {
        String fileName = ".\\config\\Tools.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "gbk"));
        String bufferData = null;

        String description = "";
        String id = "";
        String activityID = "";
        String toolID = "";

        while ((bufferData = bufferedReader.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferData);
            int numTokenizer = stringTokenizer.countTokens();
            String[] toolsDefsList = new String[numTokenizer];
            int i = 0;

            //按照空格进行解析
            while (stringTokenizer.hasMoreElements()) {
                toolsDefsList[i] = stringTokenizer.nextToken();
                i++;
            }
            //将解析结果进行处理
            description = toolsDefsList[1];
            id = toolsDefsList[0];

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
            toolID = activityIDInfoList[3];

            loadToolsToList(description, toolID, activityID);

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
    public boolean parseToolsDefsConfig() throws IOException {
        String fileName = ".\\config\\ToolsDefs.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "gbk"));
        String bufferData = null;

        String description = "";
        String id = "";
        String activityID = "";

        while ((bufferData = bufferedReader.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferData);
            int numTokenizer = stringTokenizer.countTokens();
            String[] toolsDefsList = new String[numTokenizer];
            int i = 0;

            //按照空格进行解析
            while (stringTokenizer.hasMoreElements()) {
                toolsDefsList[i] = stringTokenizer.nextToken();
                i++;
            }
            //将解析结果进行处理
            description = toolsDefsList[1];
            id = toolsDefsList[0];

            loadToolsToList(description, id, activityID);
        }

        bufferedReader.close();

        return true;
    }
}

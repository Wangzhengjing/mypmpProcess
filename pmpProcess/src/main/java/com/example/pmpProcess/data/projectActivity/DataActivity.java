package com.example.pmpProcess.data.projectActivity;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;

@Data
public class DataActivity {
    /**
     * 活动名称描述
     */
    String description;
    /**
     * 活动唯一标识ID信息
     */
    String ID;
    /**
     * 所属知识领域名称描述
     */
    String parentField;
    /**
     * 所属知识领域唯一标识ID信息
     */
    String parentFieldID;
    /**
     * 所属过程组名称描述
     */
    String process;
    /**
     * 所属过程组唯一标识ID信息
     */
    String processID;
    /**
     * 活动的输入列表
     */
    ArrayList<DataInput> inputList;
    /**
     * 活动的输出列表
     */
    ArrayList<DataOutput> outputList;
    /**
     * 活动的工具列表
     */
    ArrayList<DataTools> toolsList;
    /**
     * 活动说明
     */
    String textDesc;

    public static DataInput getInputByID(String inputID, ArrayList inputList){
        int LinkedListSize = inputList.size();
        for (int i = 0; i < LinkedListSize; i++) {
            DataInput element = (DataInput) inputList.get(i);
            if (element.getID().equalsIgnoreCase(inputID)) {
                //TODO 增加日志信息
                return element;
            }
        }

        return null;
    }

    public static DataOutput getOutputByID(String outputID, ArrayList outputList){
        int LinkedListSize = outputList.size();
        for (int i = 0; i < LinkedListSize; i++) {
            DataOutput element = (DataOutput) outputList.get(i);
            if (element.getID().equalsIgnoreCase(outputID)) {
                //TODO 增加日志信息
                return element;
            }
        }

        return null;
    }

    public static DataTools getToolByID(String toolID, ArrayList toolsList){
        int LinkedListSize = toolsList.size();
        for (int i = 0; i < LinkedListSize; i++) {
            DataTools element = (DataTools) toolsList.get(i);
            if (element.getID().equalsIgnoreCase(toolID)) {
                //TODO 增加日志信息
                return element;
            }
        }

        return null;
    }
}

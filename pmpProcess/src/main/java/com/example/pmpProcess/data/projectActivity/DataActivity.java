package com.example.pmpProcess.data.projectActivity;

import lombok.Data;

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
    static LinkedList<DataInput> inputList;
    /**
     * 活动的输出列表
     */
    static LinkedList<DataOutput> outputList;
    /**
     * 活动的工具列表
     */
    static LinkedList<DataTools> toolsList;
    /**
     * 活动说明
     */
    String textDesc;

    /**
     * 向项目活动中添加输入对象
     *
     * @param dataInput 输入对象
     */
    public static void addInput(DataInput dataInput) {
        inputList.add(dataInput);

        return;
    }
    public static DataInput getInputByID(String inputID){
        int LinkedListSize = inputList.size();
        for (int i = 0; i < LinkedListSize; i++) {
            DataInput element = inputList.get(i);
            if (element.getID() == inputID) {
                //TODO 增加日志信息
                return element;
            }
        }

        return null;
    }

    /**
     * 向项目活动中添加输出对象
     *
     * @param dataOutput 输出对象
     */
    public static void addOutput(DataOutput dataOutput) {
        outputList.add(dataOutput);

        return;
    }
    public static DataOutput getOutputByID(String outputID){
        int LinkedListSize = outputList.size();
        for (int i = 0; i < LinkedListSize; i++) {
            DataOutput element = outputList.get(i);
            if (element.getID() == outputID) {
                //TODO 增加日志信息
                return element;
            }
        }

        return null;
    }


    /**
     * 向项目活动中添加项目工具
     *
     * @param dataTools 工具对象
     */
    public static void addTools(DataTools dataTools) {
        toolsList.add(dataTools);

        return;
    }

    public static DataTools getToolByID(String toolID){
        int LinkedListSize = toolsList.size();
        for (int i = 0; i < LinkedListSize; i++) {
            DataTools element = toolsList.get(i);
            if (element.getID() == toolID) {
                //TODO 增加日志信息
                return element;
            }
        }

        return null;
    }

}

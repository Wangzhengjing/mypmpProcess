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
    Integer ID;
    /**
     * 所属知识领域名称描述
     */
    String parentField;
    /**
     * 所属知识领域唯一标识ID信息
     */
    Integer parentFieldID;
    /**
     * 所属过程组名称描述
     */
    String process;
    /**
     * 所属过程组唯一标识ID信息
     */
    Integer processID;
    /**
     * 活动的输入列表
     */
    LinkedList<DataInput> inputList;
    /**
     * 活动的输出列表
     */
    LinkedList<DataOutput> outputList;
    /**
     * 活动的工具列表
     */
    LinkedList<DataTools> toolsList;
}

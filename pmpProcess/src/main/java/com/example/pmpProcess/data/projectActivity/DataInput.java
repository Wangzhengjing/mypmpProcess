package com.example.pmpProcess.data.projectActivity;

import lombok.Data;

@Data
public class DataInput {
    /**
     * 输入名称描述
     */
    String description;
    /**
     * 输入唯一标识ID信息
     */
    Integer id;
    /**
     * 所属活动描述
     */
    String parentAct;
    /**
     * 所属活动ID
     */
    Integer parentID;
    /**
     * 本输入是否为必要输出，true表示必要，false表示不必要
     */
    boolean isNeed;
    /**
     * 输入说明
     */
    String textDesc;

    /**
     * 记录输入Id的游标
     */
    private Integer tmpIdCursor = 0;

    public Integer getInputID() {
        return tmpIdCursor++;
    }
}

package com.example.pmpProcess.data.projectActivity;

import lombok.Data;

@Data
public class DataOutput {
    /**
     * 输出名称描述
     */
    String description;
    /**
     * 输出唯一标识ID信息
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
     * 本输出是否为必要输出，true表示必要，false表示不必要
     */
    boolean isNeed;
}

package com.example.pmpProcess.data.projectProcess;

import com.example.pmpProcess.data.projectActivity.DataActivity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;

@Data
@Slf4j
public class DataProcess {
    /**
     * 项目过程组名称描述
     */
    String description;
    /**
     * 项目过程组唯一标识ID信息
     */
    String ID;
    /**
     * 过程组所涉及到的所有活动
     */
    ArrayList<DataActivity> activityArrayList;
    /**
     * 过程组说明
     */
    String textDesc;
}

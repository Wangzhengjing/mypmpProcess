package com.example.pmpProcess.dto.projField;

import com.example.pmpProcess.data.projectActivity.DataActivity;
import com.example.pmpProcess.data.projectField.DataProjectField;
import lombok.Data;

import java.util.LinkedList;

@Data
public class FieldManager {
    /**
     *
     */
    DataProjectField projectField;
    /**
     * 设置项目知识领域详细信息
     *
     * @param fieldDesc
     * @param processDesc
     * @return
     */
    public DataProjectField setProjectField(String fieldDesc, String processDesc) {
        DataProjectField dataProjectField = new DataProjectField();

        dataProjectField.setProjectField(fieldDesc);

        return dataProjectField;
    }

    public void addActivity(DataActivity activity){
        LinkedList fieldActivity = projectField.getFieldActivity();

        fieldActivity.add(activity);
    }
}

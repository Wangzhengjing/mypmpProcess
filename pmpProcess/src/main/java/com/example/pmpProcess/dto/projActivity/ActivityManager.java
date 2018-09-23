package com.example.pmpProcess.dto.projActivity;

import com.example.pmpProcess.data.projectActivity.DataActivity;
import com.example.pmpProcess.data.projectActivity.DataInput;
import com.example.pmpProcess.data.projectActivity.DataOutput;
import com.example.pmpProcess.data.projectActivity.DataTools;
import lombok.Data;

@Data
public class ActivityManager {
    /**
     * 设置活动的输入信息
     *
     * @param inputDesc 输入描述
     * @param actDesc 活动描述
     * @return 返回数据信息
     */
    public DataInput setActInput(String inputDesc, String actDesc) {
        DataInput dataInput = new DataInput();
        dataInput.setDescription(inputDesc);
        dataInput.setId(dataInput.getInputID());
        dataInput.setParentAct(actDesc);

        return dataInput;
    }

    /**
     * 设置活动的输出
     *
     * @param outputDesc 输出描述
     * @param actDesc 活动描述
     * @return 返回输出信息
     */
    public DataOutput setActOutput(String outputDesc, String actDesc) {
        DataOutput dataOutput = new DataOutput();

        dataOutput.setDescription(outputDesc);
        dataOutput.setParentAct(actDesc);

        return dataOutput;
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
     * 设置活动名称等信息
     *
     * @param actDesc 活动描述
     * @param fieldDesc 知识域描述
     * @param processDesc 过程组描述
     * @return 返回活动信息
     */
    public DataActivity setActivity(String actDesc, String fieldDesc, String processDesc) {
        DataActivity dataActivity = new DataActivity();

        dataActivity.setDescription(actDesc);
        dataActivity.setParentField(fieldDesc);
        dataActivity.setProcess(processDesc);

        return dataActivity;
    }
}

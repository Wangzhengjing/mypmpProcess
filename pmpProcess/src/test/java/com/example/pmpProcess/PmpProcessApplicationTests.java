package com.example.pmpProcess;

import com.example.pmpProcess.data.projectActivity.DataInput;
import com.example.pmpProcess.data.projectActivity.DataOutput;
import com.example.pmpProcess.data.projectActivity.DataTools;
import com.example.pmpProcess.dto.projActivity.ActivityManager;
import com.example.pmpProcess.dto.projActivity.InputManager;
import com.example.pmpProcess.dto.projActivity.OutputManager;
import com.example.pmpProcess.dto.projActivity.ToolsManager;
import com.example.pmpProcess.dto.projField.FieldManager;
import com.example.pmpProcess.dto.projProcess.ProcessManager;
import com.example.pmpProcess.ui.FrameManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PmpProcessApplicationTests {

	@Test
	public void contextLoads() throws IOException {
		ActivityManager activityManager = new ActivityManager();
		ProcessManager processManager = new ProcessManager();
		FieldManager fieldManager = new FieldManager();
//
		InputManager inputManager = new InputManager();
		OutputManager outputManager = new OutputManager();
		ToolsManager toolsManager = new ToolsManager();
//
		//获取过程组配置信息
		processManager.parseProcessConfig();
		//获取知识域配置信息
		fieldManager.parseProjFieldConfig();
		//获取项目活动配置信息
		activityManager.parseActivityConfig();
//
		//processManager.displayActivities("3");
		//fieldManager.displayActivities("4");
//
		////配置输入信息
		//DataOutput dataOutput = new DataOutput();
		//dataOutput.setDescription("aaaa");
		//dataOutput.setID("1");
		//dataOutput.setActivityID("4.4.4");
//
		//outputManager.insertOutputConfig(dataOutput);
//
		//DataOutput dataOutput1 = new DataOutput();
		//dataOutput1.setDescription("bbbb");
		//dataOutput1.setID("2");
		//dataOutput1.setActivityID("4.4.5");
//
		//outputManager.insertOutputConfig(dataOutput1);
//
		////配置输出信息
		//DataInput dataInput = new DataInput();
		//dataInput.setDescription("这个是输入测试描述");
		//dataInput.setID("2");
		//dataInput.setActivityID("4.4.4");
//
		//inputManager.insertInputConfig(dataInput);
		////配置工具信息
		//DataTools dataTools = new DataTools();
		//dataTools.setDescription("这个是工具测试描述");
		//dataTools.setID("3");
		//dataTools.setActivityID("4.4.4");
//
		//toolsManager.insertToolsConfig(dataTools);
//
		//outputManager.parseOutputConfig();
		//inputManager.parseInputConfig();
		//toolsManager.parseToolsConfig();
//
		//outputManager.displayActivities("2");
		//outputManager.displayOutputs();
//
		//inputManager.displayActivities("2");
		//inputManager.displayInputs();
//
		//toolsManager.displayActivities("3");
		//toolsManager.displayTools();

		//inputManager.parseInputDefsConfig();
		//outputManager.parseOutputDefsConfig();
		//toolsManager.parseToolsDefsConfig();

		//processManager.displayActivities("1");
		FrameManager frameManager = new FrameManager(processManager);

		return;
	}

}

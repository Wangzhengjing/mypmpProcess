package com.example.pmpProcess;

import com.example.pmpProcess.data.projectActivity.DataOutput;
import com.example.pmpProcess.dto.projActivity.ActivityManager;
import com.example.pmpProcess.dto.projActivity.InputManager;
import com.example.pmpProcess.dto.projActivity.OutputManager;
import com.example.pmpProcess.dto.projActivity.ToolsManager;
import com.example.pmpProcess.dto.projField.FieldManager;
import com.example.pmpProcess.dto.projProcess.ProcessManager;
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

		InputManager inputManager = new InputManager();
		OutputManager outputManager = new OutputManager();
		ToolsManager toolsManager = new ToolsManager();

		//获取过程组配置信息
		processManager.parseProcessConfig();
		//获取知识域配置信息
		fieldManager.parseProjFieldConfig();
		//获取项目活动配置信息
		activityManager.parseActivityConfig();

		processManager.displayActivities("3");
		fieldManager.displayActivities("4");

		//配置输入信息
		DataOutput dataOutput = new DataOutput();
		dataOutput.setDescription("aaaa");
		dataOutput.setID("1");
		dataOutput.setActivityID("4.4.4");

		outputManager.insertOutputConfig(dataOutput);
		//配置输出信息
		//配置工具信息

		outputManager.parseOutputConfig();

		inputManager.parseInputDefsConfig();
		outputManager.parseOutputDefsConfig();
		toolsManager.parseToolsDefsConfig();

		return;
	}

}

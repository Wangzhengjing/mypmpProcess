package com.example.pmpProcess;

import com.example.pmpProcess.dto.projActivity.ActivityManager;
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
		System.out.println("wangpeng");
		String actDesc = "活动名称";
		String fieldDesc = "知识域名称";
		String processDesc = "过程组名称";
		String processDescText = "过程组说明";

		ActivityManager activityManager = new ActivityManager();
		ProcessManager processManager = new ProcessManager();
		FieldManager fieldManager = new FieldManager();

		activityManager.addActivityToList(actDesc, 1, fieldDesc, 1, processDesc, 1);
		fieldManager.addProjectFieldToList(fieldDesc, 1);
		processManager.addProcessToList(processDesc, 1, processDescText);

		activityManager.setActivityConfig();
		fieldManager.setProjectFieldConfig();
		processManager.setProcessConfig();

		activityManager.getActivityConfig();
		fieldManager.getProjFieldConfig();
		processManager.getProcessConfig();

		return;
	}

}

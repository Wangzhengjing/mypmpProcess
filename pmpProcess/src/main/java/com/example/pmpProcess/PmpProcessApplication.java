package com.example.pmpProcess;

import com.example.pmpProcess.dto.projActivity.ActivityManager;
import com.example.pmpProcess.dto.projActivity.InputManager;
import com.example.pmpProcess.dto.projActivity.OutputManager;
import com.example.pmpProcess.dto.projActivity.ToolsManager;
import com.example.pmpProcess.dto.projField.FieldManager;
import com.example.pmpProcess.dto.projProcess.ProcessManager;
import com.example.pmpProcess.ui.FrameManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PmpProcessApplication {

    //public static void main(String[] args) {SpringApplication.run(PmpProcessApplication .class,args);}

    public static void main(String[] args) throws IOException {

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

        FrameManager frameManager = new FrameManager(processManager);

    }

}

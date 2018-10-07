package com.example.pmpProcess.ui;

import com.example.pmpProcess.dto.projActivity.ActivityManager;
import com.example.pmpProcess.dto.projActivity.InputManager;
import com.example.pmpProcess.dto.projActivity.OutputManager;
import com.example.pmpProcess.dto.projActivity.ToolsManager;
import com.example.pmpProcess.dto.projField.FieldManager;
import com.example.pmpProcess.dto.projProcess.ProcessManager;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

@Slf4j
public class FrameManager extends JFrame {

    public static boolean processDisplayOptionsLoad(JPanel processJPanel, ProcessManager processManager) {
        JPanel jPanel1 = new JPanel();

        JLabel jLabel = new JLabel("请指定过程组ID：");
        JTextField jTextField = new JTextField();
        jTextField.setColumns(10);
        jPanel1.add(jLabel);
        jPanel1.add(jTextField);

        //按钮
        JPanel buttonPanel = new JPanel();
        jPanel1.add(buttonPanel);

        JButton jButton = new JButton("确定");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processManager.displayActivities(jTextField.getText());

                System.exit(0);
            }
        });
        jButton.setVisible(true);
        buttonPanel.add(jButton);

        processJPanel.add(jPanel1);

        return true;
    }


    public static boolean processConfigOptionsLoad(JPanel processJPanel) {
        JPanel jPanel1 = new JPanel();
        JLabel processJlabel = new JLabel("过程组：");
        JTextField processTextField = new JTextField();
        processTextField.setColumns(10);
        jPanel1.add(processJlabel);
        jPanel1.add(processTextField);

        JLabel processIDLabel = new JLabel("过程组ID：");
        JTextField processIDTextField = new JTextField();
        processIDTextField.setColumns(10);
        jPanel1.add(processIDLabel);
        jPanel1.add(processIDTextField);

        //按钮
        JPanel buttonPanel = new JPanel();
        jPanel1.add(buttonPanel);

        JButton jButton = new JButton("确定");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = processTextField.getText();
                String age = processIDTextField.getText();

                log.info(name);
                log.info(age);
                System.exit(0);
            }
        });
        jButton.setVisible(true);
        buttonPanel.add(jButton);

        processJPanel.add(jPanel1);

        return true;
    }

    public static boolean processOptions(JPanel tableJPanel, ProcessManager processManager) {
        JTabbedPane optionsTabbedPane = new JTabbedPane();

        JPanel processJPanel = new JPanel();
        optionsTabbedPane.addTab("配置", processJPanel);
        processConfigOptionsLoad(processJPanel);

        JPanel projFieldJPanel = new JPanel();
        optionsTabbedPane.addTab("关联", projFieldJPanel);
        processConfigOptionsLoad(projFieldJPanel);

        JPanel activityJPanel = new JPanel();
        optionsTabbedPane.addTab("展示", activityJPanel);
        processDisplayOptionsLoad(activityJPanel, processManager);

        tableJPanel.add(optionsTabbedPane);

        return true;
    }

    public static boolean Table(JPanel contentPanel, ProcessManager processManager) {
        //存放选项卡的组件
        JTabbedPane configSetTabbedPane = new JTabbedPane();

        JPanel processJPanel = new JPanel();
        processJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        processJPanel.setLayout(new GridLayout(1, 1, 1, 1));
        configSetTabbedPane.addTab("过程组", processJPanel);
        processOptions(processJPanel, processManager);

        JPanel projFieldJPanel = new JPanel();
        projFieldJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        projFieldJPanel.setLayout(new GridLayout(3, 1, 5, 5));
        configSetTabbedPane.addTab("知识域", projFieldJPanel);
        processOptions(projFieldJPanel, processManager);

        JPanel activityJPanel = new JPanel();
        activityJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        activityJPanel.setLayout(new GridLayout(3, 1, 5, 5));
        configSetTabbedPane.addTab("项目活动", activityJPanel);
        processOptions(activityJPanel, processManager);

        JPanel toolJPanel = new JPanel();
        toolJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        toolJPanel.setLayout(new GridLayout(3, 1, 5, 5));
        configSetTabbedPane.addTab("项目工具", toolJPanel);
        processOptions(toolJPanel, processManager);

        JPanel inputJPanel = new JPanel();
        inputJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        inputJPanel.setLayout(new GridLayout(3, 1, 5, 5));
        configSetTabbedPane.addTab("活动输入", inputJPanel);
        processOptions(inputJPanel, processManager);

        JPanel outputJPanel = new JPanel();
        outputJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        outputJPanel.setLayout(new GridLayout(3, 1, 5, 5));
        configSetTabbedPane.addTab("活动输出", outputJPanel);
        processOptions(outputJPanel, processManager);

        contentPanel.add(configSetTabbedPane);


        return true;
    }

    public FrameManager(ProcessManager processManager) {
        //文本框
        this.setTitle("文本框使用");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(200, 200, 500, 400);
        this.setLayout(null);

        JPanel contentPanel = new JPanel();
        this.setContentPane(contentPanel);

        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(new GridLayout(3, 1, 5, 5));
        if (false) {
            JFrame frame = new JFrame("选项卡套用");
            JTabbedPane tabbed1 = new JTabbedPane(JTabbedPane.LEFT);
            JTabbedPane tabbed2 = new JTabbedPane();
            JTabbedPane tabbed3 = new JTabbedPane();
            JTabbedPane tabbed4 = new JTabbedPane();
            JPanel p1, p2, p3, p11, p12, p13, p21, p22, p23, p31, p32, p33;

            Container cp = frame.getContentPane();
            cp.setLayout(new FlowLayout());
            p1 = new JPanel();
            p1.setBackground(Color.RED);
            p2 = new JPanel();
            p2.setBackground(Color.GREEN);
            p3 = new JPanel();
            p3.setBackground(Color.BLUE);
            p11 = new JPanel();
            p11.setBackground(Color.WHITE);
            p12 = new JPanel();
            p12.setBackground(Color.BLACK);
            p13 = new JPanel();
            p13.setBackground(Color.YELLOW);
            p21 = new JPanel();
            p21.setBackground(Color.RED);
            p22 = new JPanel();
            p22.setBackground(Color.BLUE);
            p23 = new JPanel();
            p23.setBackground(Color.YELLOW);
            p31 = new JPanel();
            p31.setBackground(Color.GREEN);
            p32 = new JPanel();
            p32.setBackground(Color.RED);
            p33 = new JPanel();
            p33.setBackground(Color.YELLOW);

            tabbed2.addTab("红1", null, p11, "红色面板1");
            tabbed2.addTab("红2", null, p12, "红色面板2");
            tabbed2.addTab("红3", null, p13, "红色面板3");
            tabbed3.addTab("绿1", null, p21, "绿色面板1");
            tabbed3.addTab("绿2", null, p22, "绿色面板2");
            tabbed3.addTab("绿3", null, p23, "绿色面板3");
            tabbed4.addTab("蓝1", null, p31, "蓝色面板1");
            tabbed4.addTab("蓝2", null, p32, "蓝色面板2");
            tabbed4.addTab("蓝3", null, p33, "蓝色面板3");
            p1.add(tabbed2);
            p2.add(tabbed3);
            p3.add(tabbed4);
            tabbed1.addTab("红", null, p1, "红色面板");
            tabbed1.addTab("绿", null, p2, "绿色面板");
            tabbed1.addTab("蓝", null, p3, "蓝色面板");
            cp.add(tabbed1);
            frame.setBounds(200, 200, 300, 300);
            frame.setVisible(true);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            if (true) {
                this.Table(contentPanel, processManager);
            } else {
                //过程组标签
                JPanel processJPanel = new JPanel();
                contentPanel.add(processJPanel);

                JLabel processJlabel = new JLabel("过程组：");
                JTextField processTextField = new JTextField();
                processTextField.setColumns(10);
                processJPanel.add(processJlabel);
                processJPanel.add(processTextField);

                JLabel processIDLabel = new JLabel("过程组ID：");
                JTextField processIDTextField = new JTextField();
                processIDTextField.setColumns(10);
                processJPanel.add(processIDLabel);
                processJPanel.add(processIDTextField);

                //知识域标签
                JPanel ageJPanel = new JPanel();
                contentPanel.add(ageJPanel);

                JLabel projFieldJlabel = new JLabel("知识域：");
                JTextField projFieldTextField = new JTextField();
                projFieldTextField.setColumns(10);
                ageJPanel.add(projFieldJlabel);
                ageJPanel.add(projFieldTextField);

                JLabel projFieldIDJlabel = new JLabel("知识域ID：");
                JTextField projFieldTextFieldIDTextField = new JTextField();
                projFieldTextFieldIDTextField.setColumns(10);
                ageJPanel.add(projFieldIDJlabel);
                ageJPanel.add(projFieldTextFieldIDTextField);

                //活动标签
                JPanel locationJPanel = new JPanel();
                contentPanel.add(locationJPanel);

                JLabel activityJlabel = new JLabel("活动名称：");
                JTextField locationTextField = new JTextField();
                locationTextField.setColumns(10);
                locationJPanel.add(activityJlabel);
                locationJPanel.add(locationTextField);

                //按钮
                JPanel buttonPanel = new JPanel();
                contentPanel.add(buttonPanel);

                JButton jButton = new JButton("确定");
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = processJlabel.getText();
                        String age = processJlabel.getText();
                        String location = locationTextField.getText();

                        log.info(name);
                        log.info(age);
                        log.info(location);
                        System.exit(0);
                    }
                });
                jButton.setVisible(true);
                buttonPanel.add(jButton);
            }
        }
        this.setVisible(true);


    }

    public static void main(String args[]) throws IOException {
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

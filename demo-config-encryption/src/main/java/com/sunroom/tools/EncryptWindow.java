package com.sunroom.tools;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class EncryptWindow extends JFrame {
    private static AlgorithmUtil algorithmUtil = new AlgorithmUtil();

    EncryptWindow() {
        init();
        this.setTitle("敏感信息处理工具");
        this.setResizable(true);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.white);
//        Image imageIcon = Toolkit.getDefaultToolkit().createImage(EncryptWindow.class.getResource("logo.png"));
//        this.setIconImage(imageIcon);
        this.setVisible(true);
    }

    void init() {
        String infoMsg = "<html>" +
                            "<body>" +
                                "<font style='color:red'>敏感信息加解密工具功能：<br></font>" +
                                "1. 可对敏感信息进行加密操作；<br>" +
                                "2. 可对该工具加密的信息进行解密操作；<br>" +
                                "3. 加解密的方式使用了jasypt工具包；<br>" +
                                "3. 加解密使用的盐值固定为：<font color='blue'>ENN</font>；<br>" +
                            "<body>" +
                        "</html>";

        // 主面板，表格布局，设置三行一列表格
        JPanel mainLayout = new JPanel(new GridLayout(3, 1));

        // 表格布局-第一行：信息说明
        JPanel infoLayout = new JPanel(new FlowLayout());
        JLabel info = new JLabel(infoMsg);
        infoLayout.add(info);
        mainLayout.add(infoLayout);

        // 表格布局-第二行：输入与输出
        JPanel gridLayout = new JPanel(new GridLayout(2,1));
        mainLayout.add(gridLayout);

        JPanel orgLayout = new JPanel();
        gridLayout.add(orgLayout);
        // 原始值标签
        JLabel orgLabel = new JLabel("原始值");
        JTextField orgTextField = new JTextField("");
        orgTextField.setPreferredSize(new Dimension(300,30));
        orgLayout.add(orgLabel);
        orgLayout.add(orgTextField);

        // 加/解密值标签
        JPanel encLayout = new JPanel();
        JLabel encLabel = new JLabel("结果值");
        JTextField resultTextField = new JTextField("");
        resultTextField.setEnabled(false);
        resultTextField.setDisabledTextColor(Color.BLACK);
        resultTextField.setPreferredSize(new Dimension(300,30));
        encLayout.add(encLabel);
        encLayout.add(resultTextField);
        gridLayout.add(encLayout);

        // 表格布局-第三行：按钮组
        JPanel flowLayout = new JPanel(new FlowLayout());
        mainLayout.add(flowLayout);
        // 加密按钮
        JButton enBtn = new JButton("加密计算");
        enBtn.addActionListener(event -> {
            // 获取原始值
            String orgValue = orgTextField.getText();
            String deValue = algorithmUtil.encryptData(orgValue);
            resultTextField.setText(deValue);
        });

        // 解密按钮
        JButton deBtn = new JButton("解密计算");
        deBtn.addActionListener(event -> {
            // 获取原始值
            String orgValue = orgTextField.getText();
            String resultValue = algorithmUtil.decryptData(orgValue);
            resultTextField.setText(resultValue);
        });

        // 复制按钮
        JButton copyBtn = new JButton("复制结果值");
        copyBtn.addActionListener(event -> {
            // 获取结果值
            String resultValue = resultTextField.getText();
            if (resultValue != null && !resultValue.trim().equals("")) {
                // 将结果值放入系统剪切板
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable trans = new StringSelection(resultValue);
                clipboard.setContents(trans, null);
                JOptionPane.showMessageDialog(null, "复制成功！", "复制结果",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        flowLayout.add(enBtn);
        flowLayout.add(deBtn);
        flowLayout.add(copyBtn);

        Container container = this.getContentPane();
        container.setBackground(Color.white);
        container.add(mainLayout);
    }

    public static void main(String[] args) {
        new EncryptWindow().init();
    }
}

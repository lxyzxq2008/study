package com.sunroom.tools;

import javax.swing.*;

public class BoxLaYoutExample extends JFrame {
    JButton btn1=new JButton("one");
    JButton btn2=new JButton("two");
    JButton btn3=new JButton("three");
    JButton btn4=new JButton("four");
    JButton btn5=new JButton("five");
    BoxLaYoutExample(){
        init();
        this.setTitle("表格布局");
        this.setResizable(true);
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    void init(){
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.X_AXIS));
        //可以使用Box容器代替
        //Box box = new Box(BoxLayout.Y_AXIS);box.add(btn...);box.add(creat..);
        this.add(btn1);
        this.add(btn2);
        this.getContentPane().add(Box.createHorizontalStrut(10)); //采用x布局时，添加固定宽度组件隔开
        //this.getContentPane().add(Box.createVerticalStrut(5)); //采用y布局时，添加固定高度组件隔开
        this.add(btn3);
        this.add(btn4);
        this.add(btn5);
    }
    public static void main(String args[]){
        new BoxLaYoutExample();
    }
}
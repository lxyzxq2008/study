package com.sunroom.tools;

import javax.swing.*;

public class NullLayoutExample extends JFrame {
    JButton btn1=new JButton("one");
    JButton btn2=new JButton("two");
    JButton btn3=new JButton("three");
    JButton btn4=new JButton("four");
    JButton btn5=new JButton("five");
    NullLayoutExample(){
        init();
        this.setTitle("空布局");
        this.setResizable(true);
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    void init(){
        this.setLayout(null);
        btn1.setBounds(10, 0, 100, 50); //x坐标10，y坐标0，组件宽100，高50
        btn2.setBounds(20, 50, 100, 50);
        btn3.setBounds(30, 100, 100, 50);
        btn4.setBounds(40, 150, 100, 50);
        btn5.setBounds(50, 200, 100, 50);
        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        this.add(btn4);
        this.add(btn5);
    }
    public static void main(String args[]){
        new NullLayoutExample();
    }
}
package com.sunroom.tools;

import javax.swing.*;
import java.awt.*;

public class GridLayoutExample extends JFrame {
    JButton btn1=new JButton("one");
    JButton btn2=new JButton("two");
    JButton btn3=new JButton("three");
    JButton btn4=new JButton("four");
    JButton btn5=new JButton("five");
    GridLayoutExample(){
        init();
        this.setTitle("表格布局");
        this.setResizable(true);
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    void init(){
        this.setLayout(new GridLayout(2,3,10,5)); //默认为1行，n列；2行3列，水平间距10，垂直间距5
        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        this.add(btn4);
        this.add(btn5);
    }
    public static void main(String args[]){
        new GridLayoutExample();
    }
}
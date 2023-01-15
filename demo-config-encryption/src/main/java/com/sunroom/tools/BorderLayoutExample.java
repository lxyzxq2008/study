package com.sunroom.tools;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutExample extends JFrame {
  JButton btn1=new JButton("东");
  JButton btn2=new JButton("南");
  JButton btn3=new JButton("西");
  JButton btn4=new JButton("北");
  JButton btn5=new JButton("中");
  BorderLayoutExample(){
    init();
    this.setTitle("边界布局");
    this.setResizable(true);
    this.setSize(600, 400);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
  }
  void init(){
    //默认为0，0；水平间距10，垂直间距5
    this.setLayout(new BorderLayout(10,5));
    this.add(btn1,BorderLayout.EAST);
    this.add(btn2,BorderLayout.SOUTH);
    this.add(btn3,BorderLayout.WEST);
    this.add(btn4,BorderLayout.NORTH);
    this.add(btn5,BorderLayout.CENTER);
  }
  public static void main(String args[]){
    new BorderLayoutExample();
  }
}
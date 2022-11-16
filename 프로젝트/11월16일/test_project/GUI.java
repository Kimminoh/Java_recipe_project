package test_project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends Button {
    public GUI() {
        Container contentPaneGUI = getContentPane();

        contentPaneGUI.setBackground(Color.white);

        JLabel SetName1 = new JLabel("오늘의 추천 레시피");
        SetName1.setFont(SetName1.getFont().deriveFont(30.0f));
        SetName1.setBounds(270,20,300,40);
        contentPaneGUI.add(SetName1);
        JPanel mainpl1 = new JPanel(); // 추가한 재료를 보여주는 판넬
        mainpl1.setBounds(180, 100, 450, 430);
        mainpl1.setBackground(Color.LIGHT_GRAY);
        contentPaneGUI.add(mainpl1);

        JLabel SetName2 = new JLabel("레시피 검색");
        SetName2.setFont(SetName2.getFont().deriveFont(30.0f));
        SetName2.setBounds(900,20,300,40);
        contentPaneGUI.add(SetName2);
        JScrollPane mainpl2 = new JScrollPane();  // 스크롤패널을 선언
        mainpl2.setBounds(760, 150, 450, 460);
        mainpl2.setBackground(Color.LIGHT_GRAY);
        contentPaneGUI.add(mainpl2);

        JTextField text1 = new JTextField(10000);
        text1.setBounds(760,100,390,30);
        text1.setBackground(Color.white);
        contentPaneGUI.add(text1);

        JButton serch = new JButton("검색");
        serch.setBounds(1150,100,60,30);
        contentPaneGUI.add(serch);

    }
}
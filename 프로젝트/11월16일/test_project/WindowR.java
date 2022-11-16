package test_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WindowR extends Button {
	public WindowR() {
		Container contentPaneR = getContentPane();
		
		JButton insert = new JButton("등록");
		insert.setLocation(300,100);
		insert.setSize(80,80);
		contentPaneR.add(insert);
		insert.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				new WindowA();
	    	}
	    });
		JButton change = new JButton("수정");
		change.setLocation(300,250);
		change.setSize(80,80);
		contentPaneR.add(change);
		
		JButton delete = new JButton("삭제");
		delete.setLocation(300,400);
		delete.setSize(80,80);
		contentPaneR.add(delete);
		
		JLabel SetName5 = new JLabel("레시피 검색");
        SetName5.setFont(SetName5.getFont().deriveFont(30.0f));
        SetName5.setBounds(900,20,300,40);
        contentPaneR.add(SetName5);
        JScrollPane mainpl2 = new JScrollPane();  // 레시피 검색 스크롤패널을 선언
        mainpl2.setBounds(760, 130, 450, 405);
        mainpl2.setBackground(Color.LIGHT_GRAY);
        contentPaneR.add(mainpl2);
        
        JTextField text5 = new JTextField(10000); // 검색 필드
        text5.setBounds(760,100,390,30);
        text5.setBackground(Color.white);
        contentPaneR.add(text5);
        
        JButton serch = new JButton("검색"); // 검색 버튼
        serch.setBounds(1150,100,60,29);
        contentPaneR.add(serch);
	}
	
	
}
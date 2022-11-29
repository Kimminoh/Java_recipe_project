package test_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WindowR extends Button {
	public WindowR() {
		Container contentPaneR = getContentPane();
		a.setBackground(Color.WHITE);

		RoundButton insert = new RoundButton("등록");
		insert.setFont(insert.getFont().deriveFont(15.0f));
		insert.setLocation(385,140);
		insert.setSize(100,100);
		img.add(insert);
		insert.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				new WindowA();
	    	}
	    });
		RoundButton change = new RoundButton("수정");
		change.setFont(change.getFont().deriveFont(15.0f));
		change.setLocation(385,305);
		change.setSize(100,100);
		img.add(change);
		change.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WindowS();
            }
        });
		
		RoundButton delete = new RoundButton("삭제");
		delete.setFont(delete.getFont().deriveFont(15.0f));
		delete.setLocation(385,475);
		delete.setSize(100,100);
		img.add(delete);
		
		JLabel SetName5 = new JLabel("레시피 검색");
        SetName5.setFont(SetName5.getFont().deriveFont(30.0f));
        SetName5.setBounds(830,70,300,40);
        img.add(SetName5);
        JScrollPane mainpl2 = new JScrollPane();  // 레시피 검색 스크롤패널을 선언
        mainpl2.setBounds(710, 170, 450, 405);
        mainpl2.setBackground(Color.LIGHT_GRAY);
        img.add(mainpl2);
        
        JTextField text5 = new JTextField(10000); // 검색 필드
        text5.setBounds(710,140,390,30);
        text5.setBackground(Color.white);
        img.add(text5);
        
        JButton serch = new JButton("검색"); // 검색 버튼
        serch.setBounds(1100,140,60,29);
        img.add(serch);
	}	
}

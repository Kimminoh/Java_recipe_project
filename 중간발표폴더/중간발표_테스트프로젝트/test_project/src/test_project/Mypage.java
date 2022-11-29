package test_project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Mypage extends Button{
	private String[] ing= {"양파", "두부","계란", "우유", "김치","대파","고추", "버터"}; // 임시 냉장고 재료 리스트
	public Mypage() {
		e.setBackground(Color.WHITE);
		Container contentPaneA = getContentPane();
		contentPaneA.setBackground(Color.white);

		setVisible(true);
		setDefaultCloseOperation(Mypage.EXIT_ON_CLOSE);	
		
		JLabel SetName1 = new JLabel("마이 페이지");
		 SetName1.setFont(SetName1.getFont().deriveFont(30.0f));
	        SetName1.setLocation(630,30);
	        SetName1.setSize(200,50);
	        Dimension frameSize = getSize();
	        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
	        setLocation((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2);
	        
	    img.add(SetName1);
	    
	    JLabel img1 = new JLabel();
	    ImageIcon icon = new ImageIcon("basic.png");
	    ImageIcon icon2 = new ImageIcon("option.png");
	    ImageIcon icon3 = new ImageIcon("option2.png");
	    
	    img1.setIcon(icon); 
	    img1.setBounds(300,150,270,270);
	    img.add(img1); //프로필 사진
	    
	    JButton Jb = new JButton(icon2); //마이페이지 수정 버튼
	    Jb.setRolloverIcon(icon3);
	    Jb.setLocation(1100,50);
	    Jb.setSize(80,80);
	    Jb.setBorderPainted(false);
	    
	    img.add(Jb);
	    
	    JLabel SetName2 = new JLabel("ID : ");
	    SetName2.setFont(SetName1.getFont().deriveFont(25.0f));
	    SetName2.setBounds(300,400,100,50);
	    
	    img.add(SetName2);
	    
	    JLabel SetName3 = new JLabel("코인 : ");
	    SetName3.setFont(SetName1.getFont().deriveFont(25.0f));
	    SetName3.setBounds(273,470,100,50);
	    
	    img.add(SetName3);
	    
	    JLabel SetName4 = new JLabel("나의 냉장고 ");
	    SetName4.setFont(SetName1.getFont().deriveFont(25.0f));
	    SetName4.setBounds(750,150,200,50);
	    
	    JList<String> list = new JList<String>(ing); //냉장고 재료 스크롤리스트
	    JScrollPane Sc = new JScrollPane(list);
	    list.setFont(list.getFont().deriveFont(25.0f));
	    Sc.setBounds(700,200,250,250);
	    
	    img.add(Sc);
	    img.add(SetName4);
	    
	    JButton exit = new JButton("닫기");
        exit.setLocation(1150,605);
        exit.setSize(70,40);
        img.add(exit);
        exit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
				new GUI();
        		}
	
        	});
		}
}
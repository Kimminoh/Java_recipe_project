package test_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class MainWindow extends JFrame{	
	
	public MainWindow() {
		setTitle("요리조리");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        Container contentPaneMain = getContentPane();//mainwindow에 컨텐트팬(JFrame상속)
        contentPaneMain.setBackground(Color.white);
        contentPaneMain.setLayout(null); 
        contentPaneMain.setBackground(Color.LIGHT_GRAY);
        
        JLabel title = new JLabel("요리조리");//요리조리라는 
        title.setFont(title.getFont().deriveFont(100.0f));
        title.setBounds(440,100,500,100);
        title.setForeground(Color.white);
        contentPaneMain.add(title);
        
        JButton a = new JButton("시작하기");
        a.setBounds(575,500,100,50);
        contentPaneMain.add(a);       
        a.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GUI();
        	}
        });
        
        JLabel login1 = new JLabel("ID");
        login1.setFont(login1.getFont().deriveFont(18.0f));//라벨 글자크기조절
        login1.setBounds(470,310,80,30); //라벨 사이즈 조절
        JTextField text1 = new JTextField(10000); //텍스트박스
        text1.setBounds(525,310,200,30); //텍스트박스 사이즈 조절
        text1.setBackground(Color.white);
        contentPaneMain.add(login1);
        contentPaneMain.add(text1);
        
        JLabel login2 = new JLabel("PW");
        login2.setFont(login2.getFont().deriveFont(18.0f));//라벨 글자크기조절
        login2.setBounds(470,360,80,30); //라벨 사이즈 조절
        JTextField text2 = new JTextField(10000); //텍스트박스
        text2.setBounds(525,360,200,30); //텍스트박스 사이즈 조절
        text2.setBackground(Color.white);
        contentPaneMain.add(login2);
        contentPaneMain.add(text2);
        
        JButton login3 = new JButton("로그인"); //ID PW가 사용자 정보와 일치하면 로그인
        login3.setBounds(740,310,100,79);
        contentPaneMain.add(login3);       
        login3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null, "로그인이 완료되었습니다!","MESSAGE",JOptionPane.INFORMATION_MESSAGE);
        	}
        });
        
        JButton join = new JButton("회원가입");
        join.setBounds(525,390,100,30);
        contentPaneMain.add(join);       
        join.setBackground(Color.LIGHT_GRAY);
        join.setBorderPainted(false);
        join.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new WindowJoin();
        	}
        });

        setSize(1280,699);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        
        setResizable(false);// 창 크기 고정
	}
    
    public static void main(String[]args) {
        new MainWindow();
    }

}
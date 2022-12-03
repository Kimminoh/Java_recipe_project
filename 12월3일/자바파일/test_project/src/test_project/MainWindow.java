package test_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class MainWindow extends JFrame{	
	JLabel mimg;
	getID id=new getID();
	
	public MainWindow() {
		
		setTitle("요리조리");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPaneMain = getContentPane();//mainwindow에 컨텐트팬(JFrame상속)
        contentPaneMain.setBackground(Color.white);
        contentPaneMain.setLayout(null); 
        contentPaneMain.setBackground(Color.LIGHT_GRAY);

        ImageIcon icon = new  ImageIcon("이미지\\메인배경.png");
        mimg = new JLabel(icon);
        mimg.setBounds(0,0,icon.getIconWidth(), icon.getIconHeight());
        contentPaneMain.add(mimg);
        
        mimg.setBackground(Color.white);
        ImageIcon logo = new ImageIcon("이미지\\로고.png");
        JLabel Jlogo = new JLabel(logo);
        Jlogo.setBounds(480, 10, 338, 360);
        mimg.add(Jlogo);
        
        JLabel login1 = new JLabel("ID");
        login1.setFont(login1.getFont().deriveFont(18.0f));//라벨 글자크기조절
        login1.setBounds(470,400,80,30); //라벨 사이즈 조절
        JTextField text1 = new JTextField(10000); //텍스트박스
        text1.setBounds(525,400,200,30); //텍스트박스 사이즈 조절
        text1.setBackground(Color.white);
        mimg.add(login1);
        mimg.add(text1);
        
        JLabel login2 = new JLabel("PW");
        login2.setFont(login2.getFont().deriveFont(18.0f));//라벨 글자크기조절
        login2.setBounds(470,450,80,30); //라벨 사이즈 조절
        JTextField text2 = new JTextField(10000); //텍스트박스
        text2.setBounds(525,450,200,30); //텍스트박스 사이즈 조절
        text2.setBackground(Color.white);
        mimg.add(login2);
        mimg.add(text2);
        
        ImageIcon loginig = new ImageIcon("이미지\\로그인.png");
        JButton login3 = new JButton(loginig); //ID PW가 사용자 정보와 일치하면 로그인
        login3.setBounds(735,400,81,83);
        login3.setOpaque(false);
        login3.setContentAreaFilled(false);
        login3.setBorderPainted(false);
        mimg.add(login3);       
        login3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Connection conn = null; // DB연결된 상태(세션)을 담은 객체
            	
            	try {
            		int num = 0; // 총 데이터(열) 수를 저장할 변수
            		int i; // 반복문 변수 및 카운트 변수
            		boolean I = false; // ID 비교 결과를 저장하는 변수
            		boolean P = false; // PW 비교 결과를 저장하는 변수
            		String EID = null; // 데이터의 ID 값을 저장할 변수
            		String EPW = null; // 데이터의 PW 값을 저장할 변수
                    conn = DBConnection.getConnection();
                    Statement stmt = conn.createStatement();
                    String quary = "SELECT COUNT (*) FROM 회원"; // 총 데이터(열) 수를 구하는 쿼리문
                    ResultSet rs = stmt.executeQuery(quary); // SELECT의 반환값은 ResultSet에 저장해야함
                    if (rs.next()) num = rs.getInt(1); // 구한 총 데이터수(열)를 num에 저장
                    // 열마다 ID를 구해 입력된 아이디랑 비교하는 반복문
                    for(i = 0; i < num + 1; i++) {
                    	quary = "SELECT ID FROM 회원 WHERE NO = " + Integer.toString(i); // i열의 ID를 가져오는 쿼리문
                    	rs = stmt.executeQuery(quary); // SELECT의 반환값은 ResultSet에 저장해야함
                    	if (rs.next()) EID = rs.getString(1); // i열의 ID를 EID 변수에 저장
                    	
                    	quary = "SELECT PW FROM 회원 WHERE NO = " + Integer.toString(i); // i열의 PW를 가져오는 쿼리문
                    	rs = stmt.executeQuery(quary); // SELECT의 반환값은 ResultSet에 저장해야함
                    	if (rs.next()) EPW = rs.getString(1); // i열의 PW를 EPW 변수에 저장
                    	
                    	String ID = text1.getText(); // 입력받은 ID를 ID 변수에 저장
                    	String PW = text2.getText(); // 입력받은 ID를 ID 변수에 저장
                    	System.out.println(ID); // 입력받은 ID를 확인할려고 넣어놨음 빼도 됨
                    	System.out.println(PW); // 입력받은 ID를 확인할려고 넣어놨음 빼도 됨
                    	// i의 ID와 PW를 비교하는 조건문
                    	if(ID.equals(EID)) {
                    		// ID가 같으면 I는 true
                    		I = true;
                    		if(PW.equals(EPW)) {
                    			// PW가 같으면 P는 true
                    			P = true;
                    			break; // ID와 PW가 둘 다 일치하므로 반복문 종료
                    		}
                    	}
                    }
                    
                    // 로그인 변수값을 비교하여 팝업을 띄우는 조건문
                    if(I) {
                    	if(P) {
                        	JOptionPane.showMessageDialog(null, "로그인이 완료되었습니다!","MESSAGE",JOptionPane.INFORMATION_MESSAGE); // 반복문이 끝까지 실행됐으면 일치하는 것이므로 로그인 성공 팝업을 띄움
                        	
                        	setVisible(false);
            				new GUI(EID);
                    	}
                    	
                    	//ID가 참이면
                    	else JOptionPane.showMessageDialog(null, "PW가 일치하지 않습니다.","MESSAGE",JOptionPane.ERROR_MESSAGE); // 조건이 거짓이면 팝업을 띄우고 반복문 종료
                    }
                    
                    else {
                        JOptionPane.showMessageDialog(null, "등록되지 않은 ID 입니다.","MESSAGE",JOptionPane.ERROR_MESSAGE); // 조건이 거짓이면 팝업을 띄우고 반복문 종료
                    }

                    
                    if ( stmt != null ){stmt.close();}   
                    if ( conn != null ){conn.close(); }
            	}
            	catch(SQLException ex){
            		ex.printStackTrace();
            	}
        	}
        });
        ImageIcon joinig = new ImageIcon("이미지\\회원가입.png");
        JButton join = new JButton(joinig);
        join.setBounds(525,480,91,30);
        mimg.add(join);       
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
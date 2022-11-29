package test_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class WindowJoin extends JFrame{
	public WindowJoin() {
		Container contentPanejoin = getContentPane();
		setTitle("회원가입");
		contentPanejoin.setLayout(null); 
        
        JLabel login1 = new JLabel("ID");
        login1.setFont(login1.getFont().deriveFont(18.0f));//라벨 글자크기조절
        login1.setBounds(20,20,80,30); //라벨 사이즈 조절
        JTextField text1 = new JTextField(100); //텍스트박스
        text1.setBounds(60,20,200,30); //텍스트박스 사이즈 조절
        text1.setBackground(Color.white);
        contentPanejoin.add(login1);
        contentPanejoin.add(text1);
        
        JLabel login2 = new JLabel("PW");
        login2.setFont(login2.getFont().deriveFont(18.0f));//라벨 글자크기조절
        login2.setBounds(20,80,80,30); //라벨 사이즈 조절
        JTextField text2 = new JTextField(10000); //텍스트박스
        text2.setBounds(60,80,200,30); //텍스트박스 사이즈 조절
        text2.setBackground(Color.white);
        contentPanejoin.add(login2);
        contentPanejoin.add(text2);
                
        JButton check = new JButton("중복확인"); //중복확인 함수 추가
        check.setBounds(270,20,84,28);
        contentPanejoin.add(check);
        check.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Connection conn = null; // DB연결된 상태(세션)을 담은 객체
            	
            	try {
            		int num = 0; // 총 데이터(열) 수를 저장할 변수
            		int i; // 반복문 변수 및 카운트 변수
            		String EID = null; // 데이터의 ID 값을 저장할 변수
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
                    	String ID = text1.getText(); // 입력받은 ID를 ID 변수에 저장
                    	System.out.println(ID); // 입력받은 ID를 확인할려고 넣어놨음 빼도 됨
                    	// i의 ID와 입력받은 ID를 비교하는 조건문
                    	if(ID.equals(EID)) {
                            JOptionPane.showMessageDialog(null, "중복된 ID 입니다.","MESSAGE",JOptionPane.ERROR_MESSAGE); // 조건이 참이면 팝업을 띄우고 반복문 종료
                            break;
                    	}
                    }
                    if(i == num + 1)
                		JOptionPane.showMessageDialog(null, "사용 가능한 ID 입니다.","MESSAGE",JOptionPane.INFORMATION_MESSAGE); // 반복문이 끝까지 실행됐으면 중복이 없는 것이므로 사용가능 팝업을 띄움
                    if ( stmt != null ){stmt.close();}   
                    if ( conn != null ){conn.close(); }
            	}
            	catch(SQLException ex){
            		ex.printStackTrace();
            	}
        	}
        });
        JButton enjoin = new JButton("가입하기"); // 중복확인이 완료되고 pw까지 입력하면 가입완료
        enjoin.setBounds(140,145,90,30);
        contentPanejoin.add(enjoin);
        enjoin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Connection conn = null; // DB연결된 상태(세션)을 담은 객체
                
                try {
                	int num = 0;
                    conn = DBConnection.getConnection();
                    Statement stmt = conn.createStatement();
                    String ID = text1.getText();
                    String PW = text2.getText();
                    String quary = "SELECT COUNT (*) FROM 회원"; // 현재 등록된 멤버 수를 구하는 쿼리문
                    ResultSet rs = stmt.executeQuery(quary); // SELECT 쿼리문이기 때문에 ResultSet 객체를 만들어 값을 받아야함
                    if (rs.next()) num = rs.getInt(1); // num에 반환된 멤버 수 저장
                    quary = "INSERT INTO 회원 (NO, ID, PW) VALUES (" + num + ", '" + ID + "', '" + PW +"')"; // 입력받은 ID, PW와 구한 num을 입력하는 쿼리문
                    System.out.println("실행 구문"); // 확인용 문장 삭제해도 됨
                    System.out.println(quary); // 확인용 문장 삭제해도 됨
                    boolean b = stmt.execute(quary); // 쿼리문 실행
                    quary = "COMMIT"; // SELECT 구문이 아닌 쿼리문은 반드시 COMMIT을 실행
                    b = stmt.execute(quary); // COMMIT 실행
                    
                    System.out.println(); // 확인용 문장 삭제해도 됨
                    System.out.println("테스트 완료"); // 확인용 문장 삭제해도 됨
                    
                    if ( stmt != null ){stmt.close();}   
                    if ( conn != null ){conn.close(); }
                    
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }   
        		JOptionPane.showMessageDialog(null, "가입이 완료되었습니다.","MESSAGE",JOptionPane.INFORMATION_MESSAGE);
        		dispose();
        	}
        });
        
        setSize(380,250);    
        setVisible(true);
		setResizable(false);
		Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
	}

}
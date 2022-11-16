package test_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
        		JOptionPane.showMessageDialog(null, "사용 가능한 ID 입니다.","MESSAGE",JOptionPane.INFORMATION_MESSAGE);
        	}
        });
        JButton enjoin = new JButton("가입하기"); // 중복확인이 완료되고 pw까지 입력하면 가입완료
        enjoin.setBounds(140,145,90,30);
        contentPanejoin.add(enjoin);
        enjoin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Connection conn = null; // DB연결된 상태(세션)을 담은 객체
                
                try {
                    conn = DBConnection.getConnection();
                    Statement stmt = conn.createStatement();
                    
                    
                    String ID = text1.getText();
                    String PW = text2.getText();
                    String quary = "INSERT INTO 회원 (ID, PW) VALUES ('" + ID + "', '" + PW + "')";
                    System.out.println("실행 구문");
                    System.out.println(quary);
                    boolean b = stmt.execute(quary);
                    
                    System.out.println();
                    System.out.println("테스트 완료");
                    
                    
                    //호출완료 후 종료해야하므로 해당 부분에 선언
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
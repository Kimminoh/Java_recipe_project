package test_project;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class WindowA extends JFrame {
	JButton a = new JButton("등록");
	JTextField text1 = new JTextField(10000); //텍스트박스
	JTextField text2 = new JTextField(10000);
	JTextField text3 = new JTextField(10000);
	JPanel pl = new JPanel();
	TextArea tarea = new TextArea(21,63);
	JButton image = new JButton("사진찾기"); // 메인 음식 사진 추가
	
	public WindowA() {
		setTitle("레시피 등록");
		Container contentPaneA = getContentPane();
		contentPaneA.setBackground(Color.white);	
		setSize(800,800);
		setVisible(true);	
		contentPaneA.setLayout(null); 

		a.setBounds(570,700,70,40);
        contentPaneA.add(a);
        a.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Connection conn=null;//우선 빈 값
        		//Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체
        		//주의 : Statement 하나 당 한개의 ResultSet 객체만 열 수 있음		
                try {
                		int num = 0;
        	            conn = DBConnection.getConnection();
        	            Statement stmt = conn.createStatement();
        	            //sql
        	            String quary = "SELECT COUNT (*) FROM 임시레시피테이블"; // 총 데이터(열) 수를 구하는 쿼리문
                        ResultSet rs = stmt.executeQuery(quary); // SELECT의 반환값은 ResultSet에 저장해야함
                        if (rs.next()) num = rs.getInt(1); // 구한 총 데이터수(열)를 num에 저장

        				String rname = text1.getText();
        				String ring = text2.getText();
        				String rpic = text3.getText();
        				String retc = tarea.getText();
                        quary = "INSERT INTO 임시레시피테이블 ("
                        		+ "레시피코드, 레시피이름, 재료, 이미지경로, 레시피설명) VALUES (" 
                        		+ num + ", '" + rname + "', '" + ring +"', '" + rpic + "', '" + retc + "')"; // 입력받은 ID, PW와 구한 num을 입력하는 쿼리문
        				boolean b = stmt.execute(quary);
        				quary = "COMMIT";
        				b = stmt.execute(quary);

        				JOptionPane.showMessageDialog(null, "레시피를 등록하였습니다!","MESSAGE",JOptionPane.INFORMATION_MESSAGE);

        	            //호출완료 후 종료해야하므로 해당 부분에 선언
        	            if ( stmt != null ){stmt.close();}   
        	            if ( conn != null ){conn.close();}
        	    }
                
                catch (SQLException ex) {ex.printStackTrace();}   
        	    }
        });
        
        JLabel SetName1 = new JLabel("레시피 이름 ");
        SetName1.setFont(SetName1.getFont().deriveFont(14.0f));//라벨 글자크기조절
        SetName1.setBounds(100,50,80,30); //라벨 사이즈 조절
        
        text1.setBounds(215,50,450,30); //텍스트박스 사이즈 조절
        text1.setBackground(Color.white);
        contentPaneA.add(text1);
        
        JLabel SetName2 = new JLabel("재료");
        SetName2.setFont(SetName2.getFont().deriveFont(14.0f));
        SetName2.setBounds(100,100,80,30);        
        
        text2.setBounds(215,100,450,30);
        text2.setBackground(Color.white);
        contentPaneA.add(text2);
        
        JLabel SetName3 = new JLabel("사진");
        SetName3.setFont(SetName3.getFont().deriveFont(14.0f));
        SetName3.setBounds(100,150,80,30);   
        
        text3.setBounds(215,150,450,30); 
        text3.setBackground(Color.white);
        contentPaneA.add(text3);
        
        JLabel SetName4 = new JLabel("설명");
        SetName4.setFont(SetName4.getFont().deriveFont(14.0f));
        SetName4.setBounds(100,200,80,30);  
        
        pl.setBackground(Color.white);
       
        pl.add(tarea);
        pl.setBounds(205,207,470,350);
        contentPaneA.add(pl);
        pl.setVisible(true);
    
       
        image.setFont(image.getFont().deriveFont(12.0f));
        image.setBorderPainted(false);
        image.setBackground(Color.white);
        image.setBounds(670, 150,100,30);
        contentPaneA.add(image);
        image.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				setVisible(true); // 등록버튼 클릭시 기존의 창 없애는 기능
				String str=e.getActionCommand();
				  if(str.equals("사진찾기"))
				  {
				   // file chooser
				   JFileChooser chooser = new JFileChooser();
				   chooser.setAcceptAllFileFilterUsed(true);
				   chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		            
		            int ret=chooser.showOpenDialog(null);
		            if(ret!=JFileChooser.APPROVE_OPTION){
		                JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.","경고",JOptionPane.WARNING_MESSAGE);
		                return;
		            }
		            
		            String filePath = chooser.getSelectedFile().toString();
		            text3.setText(filePath);
		            ImageIcon icon = new ImageIcon(filePath);
		            JLabel imgla = new JLabel(icon);
		            contentPaneA.add(imgla);
		            imgla.setBounds(980,50,200,200); // 사진 추가는 되지만 사진 크기 고정은 안됨
			    }				   			
        	}
        });
        
        JButton exit = new JButton("닫기");
        exit.setLocation(670,700);
        exit.setSize(70,40);
        contentPaneA.add(exit);
        exit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
				new WindowR();
        	}
        });
        contentPaneA.add(SetName1);	
        contentPaneA.add(SetName2);		
        contentPaneA.add(SetName3);
        contentPaneA.add(SetName4);
        
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기

	}	
}
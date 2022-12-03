package test_project;
//패널 전환이 안됨
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.swing.*;

public class WindowA extends JFrame {
	JPanel change = new JPanel(); // <> 버튼 클릭 시 화면전환되는 패널
	JButton a = new JButton("등록");
	JTextField text1 = new JTextField(10000); //레시피 이름 텍스트 박스
	JTextField text2 = new JTextField(10000); //재료 텍스트 박스
	JTextField text3 = new JTextField(10000); //사진 주소 텍스트 박스
	JTextArea text4 = new JTextArea(); //설명 텍스트박스
	
	String Rname; //레시피 이름 저장
	String Ringre; //레시피 재료 저장
	
	TextArea tarea = new TextArea(40,15);
	JButton image = new JButton("사진찾기"); // 메인 음식 사진 추가
	JButton before = new JButton("<"); // 내부저장소에 적었던 내용 불러오기
	JLabel imgla;
	ImageIcon N = new ImageIcon("");
	Image Nimage;
	
	JLabel SetName1 = new JLabel("레시피 이름 ");
	JLabel SetName2 = new JLabel("재료");
	JLabel SetName3 = new JLabel("사진");
	JLabel SetName4 = new JLabel("설명");
	
	Vector<String> v3 = new Vector<>(99); //사진주소
	Vector<Icon> v4 = new Vector<>(99); //사진
	Vector<String> v5 = new Vector<>(99); //내용
	int i = 0;
	public WindowA() {
		setTitle("레시피 등록");
		Container contentPaneA = getContentPane();
		contentPaneA.setBackground(Color.white);	
		setSize(1000,800);
		setVisible(true);
		contentPaneA.setLayout(null); 
		
		change.setBounds(100, 20, 800, 650);
		change.setBackground(Color.white);
		change.setLayout(null);
		contentPaneA.add(change);
		
		 tarea.setLocation(150, 300); // 패널에 textArea 안붙음
	     tarea.setBackground(Color.black);
	     tarea.setVisible(true);
	     change.add(tarea); //textArea 설명을 입력하는 패널  
		
		a.setBounds(770,700,70,40);
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
        
        
        SetName1.setFont(SetName1.getFont().deriveFont(14.0f));//라벨 글자크기조절
        SetName1.setBounds(100,30,80,30); //라벨 사이즈 조절
        
        text1.setBounds(200,30,450,30); //텍스트박스 사이즈 조절
        text1.setBackground(Color.white);
        change.add(text1);        
        
        SetName2.setFont(SetName2.getFont().deriveFont(14.0f));
        SetName2.setBounds(100,80,80,30);        
        
        text2.setBounds(200,80,450,30);
        text2.setBackground(Color.white);
        change.add(text2);      
        
        SetName3.setFont(SetName3.getFont().deriveFont(14.0f));
        SetName3.setBounds(100,130,80,30);        
        
        text3.setBounds(200,130,450,30); 
        text3.setBackground(Color.white);
        change.add(text3);        
        
        SetName4.setFont(SetName4.getFont().deriveFont(14.0f));
        SetName4.setBounds(100,500,80,30);
        
        text4.setBounds(200, 500, 450, 100);
        text4.setBackground(Color.LIGHT_GRAY);
        change.add(text4);

        image.setFont(image.getFont().deriveFont(12.0f));
        image.setBorderPainted(false);
        image.setBackground(Color.white);
        image.setBounds(680, 130,100,30);
        change.add(image);
        
        image.addActionListener(new ActionListener() {
        	// 사진 바꾸기가 안됨
        	public void actionPerformed(ActionEvent e) {
				setVisible(true); // 등록버튼 클릭시 기존의 창 없애는 기능
				String str=e.getActionCommand();
				  if(str.equals("사진찾기"))
				  {
				   // file chooser
					  if(imgla != null) { // 사진 바꾸는 기능
						  imgla.setIcon(N); // 사진이 존재할 때만 비우고 다시 넣는 방식
					  }
				   JFileChooser chooser = new JFileChooser();
				   chooser.setAcceptAllFileFilterUsed(true);
				   chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		            
		            int ret=chooser.showOpenDialog(null);
		            if(ret!=JFileChooser.APPROVE_OPTION){
		                JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.","경고",JOptionPane.WARNING_MESSAGE);
		                return;
		            }
		            
		            String filePath = chooser.getSelectedFile().toString();
		            text3.setText(filePath); //사진주소
		            ImageIcon icon = new ImageIcon(filePath); //사진출력
		            
		            Image img = icon.getImage(); // 여기서부터 3줄은 사진크기 고정기능
		            //사진 크기변경은 getScaledInstance(가로,세로) 변경하면 됨
		            Image changeimg = img.getScaledInstance(400, 310, Image.SCALE_SMOOTH);
		            ImageIcon changeIcon = new ImageIcon(changeimg);
		            
		            imgla = new JLabel(changeIcon);
		            
		            change.add(imgla);
		            imgla.setBounds(200,180,400,310); // 이미지 레이블 위치
		            
		            
			    }				   			
        	}
        });
        
        JButton next = new JButton(">"); // 내부저장소에 적었던 내용 저장
		next.setBounds(920, 340, 55, 60);
		contentPaneA.add(next);
		next.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//첫 번째 페이지 이후로 제목과 재료를 안 보이게함
        		if(i<1) {
        			//setVisible() 중복발생 방지 코드
        			if(SetName1.isVisible()==false)
        				return;
        			SetName1.setVisible(false);
        			SetName2.setVisible(false);	        
        			text1.setVisible(false);
        			text2.setVisible(false);
        		}
        		i+=1;        		        		
        		
        		if(v3.size() == 0) { //첫 페이지 생성할 때
        			Rname = text1.getText();
        			Ringre = text2.getText();     			
        			
        			v3.add(i-1,text3.getText());
        			v5.add(i-1,text4.getText());
        			ImageIcon a = new ImageIcon(text3.getText());
        			v4.add(i-1, a); // v4에는 이미지 주소를 넣음
        			imgla.setIcon(N); // 이미지 레이블 비우기
        			        			
        			text3.setText(null);
        			text4.setText(null);       		   
        		}
        		else if(v3.size() > i) { // 페이지 불러오기       			
            		text3.setText(v3.get(i)); 
            		text4.setText(v5.get(i));
            		
            		ImageIcon icon = new ImageIcon(v3.get(i)); //사진출력
            		Image img = icon.getImage(); // 사진 이미지 크기 고정
    		        Image changeimg = img.getScaledInstance(400, 310, Image.SCALE_SMOOTH);
    		        ImageIcon changeIcon = new ImageIcon(changeimg);		            		   
            		imgla.setIcon(changeIcon);
        		}
        		else if(v3.size() == i) {
        			if(v3.size()-1 < i) {       				
            			text3.setText(null);
            			text4.setText(null);
            			imgla.setIcon(N); // 사진 레이블 비우기
            			return;
        			}       			
        			v3.add(i-1,text3.getText());
        			v5.add(i-1,text4.getText());
        			ImageIcon a = new ImageIcon(text3.getText());
        			v4.add(i-1, a); // v4에는 이미지 주소를 넣음
        			
        			imgla.setIcon(N); // 비우기        			        			
        			text3.setText(null);       
        			text4.setText(null);
        		}
        		
        		else if(i > v3.size()) {      	
        			v3.add(i-1,text3.getText());
        			v5.add(i-1,text4.getText());
        			ImageIcon a = new ImageIcon(text3.getText());
        			v4.add(i-1, a); // v4에는 이미지 주소를 넣음

        			text3.setText(null); 
        			text4.setText(null);
        			imgla.setIcon(N); // 비우기
        		}
        		if(before.isVisible() == false) {
        			before.setVisible(true);
        		}
        		
        	}
        });
		
		
		before.setBounds(30, 340, 55, 60);
		contentPaneA.add(before);
		before.setVisible(false);
		before.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		i -= 1;
        		if(i+1 == v3.size()) {
        			//현재페이지에 내용을 입력하고 이전 버튼 클릭 시
        			//현재 작성한 내용 저장하고 이전 페이지 불러옴
        			v3.add(i+1,text3.getText());
        			v5.add(i+1,text4.getText());

            		text3.setText(v3.get(i));
            		text4.setText(v5.get(i));
            		ImageIcon icon = new ImageIcon(v3.get(i)); //사진출력
            		Image img = icon.getImage(); //사진 크기 고정
    		        Image changeimg = img.getScaledInstance(400, 310, Image.SCALE_SMOOTH);
    		        ImageIcon changeIcon = new ImageIcon(changeimg);		            		   
            		imgla.setIcon(changeIcon);
            		
        		}// 작성한 내용이 없는 경우 <클릭 시 저장x 불러오기만 함
        		else if(text1.getText() == "") {        		
            		text3.setText(v3.get(i));
            		text4.setText(v5.get(i));

            		ImageIcon icon = new ImageIcon(v3.get(i)); //사진출력
            		Image img = icon.getImage();
    		        Image changeimg = img.getScaledInstance(400, 310, Image.SCALE_SMOOTH);
    		        ImageIcon changeIcon = new ImageIcon(changeimg);		            		   
            		imgla.setIcon(changeIcon);
        		}
        		//그냥 < 버튼 클릭했을 경우        		
        		text3.setText(v3.get(i));
        		text4.setText(v5.get(i));
        		
        		ImageIcon icon = new ImageIcon(v3.get(i)); //사진출력
        		Image img = icon.getImage();
		        Image changeimg = img.getScaledInstance(400, 310, Image.SCALE_SMOOTH);
		        ImageIcon changeIcon = new ImageIcon(changeimg);		            		   
        		imgla.setIcon(changeIcon);
        		
        		//첫 번째 페이지일 경우 왼쪽버튼 숨김
        		//제목과 재료의 레이블과 텍스트필드를 보이게 함
        		if(i == 0) { 
        			before.setVisible(false);
        			SetName1.setVisible(true);
        			SetName2.setVisible(true);
        			text1.setVisible(true);
        			text2.setVisible(true);
        			
        		}
        		
        	}
        });
        
        JButton exit = new JButton("닫기");
        exit.setLocation(870,700);
        exit.setSize(70,40);
        contentPaneA.add(exit);
        exit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//닫기버튼 이벤트
        		//닫기버튼 클릭 시 벡터와 i 초기화
        		//등록버튼에도 넣어야함 
        		i=0;
        		v3.clear();
        		v4.clear();
        		v5.clear();
        		setVisible(false);			
        	}
        });
        change.add(SetName1);	
        change.add(SetName2);		
        change.add(SetName3);
        change.add(SetName4);
        
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기

	}	
}
package test_project;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.*;

//레시피를 등록하기 위한 탭 (다시 구성하여야함)

public class Recipe_register extends JFrame {
	
	static String rname;
	
	JButton reg = new JButton("등록");
	JButton plus = new JButton("추가등록");
	JTextField recipe_info = new JTextField(10000); //텍스트박스
	JPanel pl=new JPanel();
	
	//이미지 관련 
	ImageIcon jpgimage;
	
	JLabel image_pathlabel=new JLabel();
	JButton image = new JButton("사진찾기"); // 메인 음식 사진 추가
	private JLabel img_label;
	private BufferedImage imgImage; //이미지 객체
	String filePath;
	
	TextArea tarea = new TextArea(21,30);
	
	
	public Recipe_register(String rname_key) {//레시피이름이 키!
		setTitle("레시피 등록");
		rname=rname_key;
		Container contentPaneA = getContentPane();//컨텐트팬 정의
		contentPaneA.setBackground(Color.white);  //컨텐트팬 배경 색
		setSize(800,800);						  //Frame의 크기
		setVisible(true);						  //보이게 하기
		contentPaneA.setLayout(null); 			  //배치 레이아웃 임의설정을 위해 null
		reg.setBounds(570,700,70,40);			  //등록 버튼 임의 배치(setBounds)
        contentPaneA.add(reg);					  //해당 등록 버튼 컨텐트팬에 부착
        // 등록 버튼 클릭 시, 이벤트 설정
        
        reg.addActionListener(new Reg());
        
        plus.setBounds(470,700,70,40);
        contentPaneA.add(plus);
        plus.addActionListener(new Reg());
        
        
        
        JLabel SetName3 = new JLabel("사진");
        SetName3.setFont(SetName3.getFont().deriveFont(14.0f));
        SetName3.setBounds(100,150,80,30);   
        
        image_pathlabel.setBounds(215,150,450,30); 
        image_pathlabel.setBackground(Color.white);
        contentPaneA.add(image_pathlabel);
        
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
        
        //사진찾기 기능
        contentPaneA.add(image);
        image.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				setVisible(true); // 등록버튼 클릭시 기존의 창 없애는 기능
				img_label=new JLabel(new ImageIcon());//빈 값으로 처음 비우기
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
		            
		            filePath = chooser.getSelectedFile().toString();
		            //선택된 파일의 절대경로 : filePath
		            
		            image_pathlabel.setText(filePath);
		            //이미지 경로 표시
		            
		            img_label=new JLabel(new ImageIcon(filePath));
		            img_label.setBounds(0,0,500,500);
		            contentPaneA.add(img_label);
		            
		            
		            /*
		            try {
						imgImage = ImageIO.read(new File(filePath));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            
		            img_label=new JLabel(new ImageIcon(imgImage));
		            img_label.setBounds(150,0,500,500);
		            contentPaneA.add(img_label);
		            
		            */
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
				
        	}
        });
        
        contentPaneA.add(SetName3);
        contentPaneA.add(SetName4);
        
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기

	}	
	
	
	class Reg implements ActionListener{
		Connection conn=null;
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==reg) {
				try {
	            conn = DBConnection.getConnection();
	            Statement stmt = conn.createStatement();
	           
	            //(3)
	            String quary = "INSERT INTO NEW레시피이미지경로테이블 (레시피명,이미지경로1) VALUES('"
	            		+ rname+"', '"+filePath+"')"; 
                boolean b = stmt.execute(quary);
				quary = "COMMIT";
				b = stmt.execute(quary);

				String retc = tarea.getText();
				quary = "INSERT INTO NEW레시피텍스트테이블 (레시피명,텍스트1) VALUES('"
	            		+ rname+"', '"+retc+"')"; 
				
				b = stmt.execute(quary);
				quary = "COMMIT";
				b = stmt.execute(quary);

				JOptionPane.showMessageDialog(null, "레시피를 등록하였습니다!","MESSAGE",JOptionPane.INFORMATION_MESSAGE);
				

	            //호출완료 후 종료해야하므로 해당 부분에 선언
	            if ( stmt != null ){stmt.close();}   
	            if ( conn != null ){conn.close();}
	            dispose();
	    }
        
        catch (SQLException ex) {ex.printStackTrace();} 
			}
			
			//만약 버튼중 추가설명 버튼 클릭 시
			//레시피 이미지, 텍스트 테이블의 열 추가 실행
			if(e.getSource()==plus) {
				//텍스트창들 비우기
				int num =1;//이미지경로
				
				//그 후 똑같은 삽입 
				try {
		            conn = DBConnection.getConnection();
		            Statement stmt = conn.createStatement();
		           
		           
		            String quary = "INSERT INTO NEW레시피이미지경로테이블 (레시피명,이미지경로1) VALUES('"
		            		+ rname+"', '"+filePath+"')"; 
	                boolean b = stmt.execute(quary);
					quary = "COMMIT";
					b = stmt.execute(quary);

					String retc = tarea.getText();
					quary = "INSERT INTO NEW레시피텍스트테이블 (레시피명,텍스트1) VALUES('"
		            		+ rname+"', '"+retc+"')"; 
					
					b = stmt.execute(quary);
					quary = "COMMIT";
					b = stmt.execute(quary);
					
					
					quary="ALTER TABLE NEW레시피이미지경로테이블 ADD 이미지경로"+num
							+ " VARCHAR2(2000)";
					b=stmt.execute(quary);
					quary="COMMIT";
					b=stmt.execute(quary);

					quary="ALTER TABLE NEW레시피텍스트테이블 ADD 텍스트"+num+" VARCHAR2(2000)";
					b=stmt.execute(quary);
					quary="COMMIT";
					b=stmt.execute(quary);
					
					//텍스트창과 이미지 레이블 비우기
					filePath=null;
					tarea.setText("");
					++num;
					JOptionPane.showMessageDialog(null, "레시피를 추가성공하였습니다!","MESSAGE",JOptionPane.INFORMATION_MESSAGE);
					

		            //호출완료 후 종료해야하므로 해당 부분에 선언
		            if ( stmt != null ){stmt.close();}   
		            if ( conn != null ){conn.close();}
			}catch (SQLException ex) {ex.printStackTrace();} 
		}

		}
	}
}
	
	
	
	
	

package test_project;
import java.awt.Color;
import java.awt.Container;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class WindowA extends Button implements ActionListener {
	
	JButton a = new JButton("등록");
	JLabel SetName1 = new JLabel("레시피 이름 ");
	JTextField text1 = new JTextField(10000); //텍스트박스
	JLabel SetName2 = new JLabel("재료");
	JTextField text2 = new JTextField(10000);
	 JLabel SetName3 = new JLabel("사진");
	 JTextField text3 = new JTextField(10000);
	 JLabel SetName4 = new JLabel("설명");
	 JPanel pl = new JPanel();
	 TextArea tarea = new TextArea(21,63);
	 JButton image = new JButton("사진찾기"); // 메인 음식 사진 추가

	 
	 
	 
	
	public WindowA() {
		
		Container contentPaneA = getContentPane();//contentPane A -> 등록하는 메인화면
		contentPaneA.setBackground(Color.white);		
				
		setVisible(true);
		setDefaultCloseOperation(WindowA.EXIT_ON_CLOSE);		
		
		
		a.setBounds(1050,605,70,40);
        contentPaneA.add(a);
        a.addActionListener(this);
        
        
        SetName1.setFont(SetName1.getFont().deriveFont(14.0f));//라벨 글자크기조절
        SetName1.setBounds(250,50,80,30); //라벨 사이즈 조절

        text1.setBounds(350,50,500,30); //텍스트박스 사이즈 조절
        text1.setBackground(Color.white);
        contentPaneA.add(text1);
        
        
        SetName2.setFont(SetName2.getFont().deriveFont(14.0f));
        SetName2.setBounds(250,100,80,30);        
        
        text2.setBounds(350,100,500,30);
        text2.setBackground(Color.white);
        contentPaneA.add(text2);
        
       
        SetName3.setFont(SetName3.getFont().deriveFont(14.0f));
        SetName3.setBounds(250,150,80,30);   
        
        text3.setBounds(350,150,500,30); 
        text3.setBackground(Color.white);
        contentPaneA.add(text3);
        
        
        SetName4.setFont(SetName4.getFont().deriveFont(14.0f));
        SetName4.setBounds(250,200,80,30);  
        
       
        pl.add(tarea);
        pl.setBounds(350,207,500,350);
        contentPaneA.add(pl);
        pl.setVisible(true);
    
       
        image.setFont(image.getFont().deriveFont(12.0f));
        image.setBorderPainted(false);
        image.setBackground(Color.white);
        image.setBounds(850, 150,100,30);
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
        exit.setLocation(1150,605);
        exit.setSize(70,40);
        contentPaneA.add(exit);
        exit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
				new GUI();
        	}
        });
        contentPaneA.add(SetName1);	
        contentPaneA.add(SetName2);		
        contentPaneA.add(SetName3);
        contentPaneA.add(SetName4);
        
        
        //Dimension frameSize = getSize();
        //Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
       //((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
	}
	
	
	
	
	
	
	
	//boolean result;
	int number=8;//제일 row 값을 적용시켜서 실행(프로그램 껐다켰다할때 문제 발생 미연에 방지)
	@Override
	public void actionPerformed(ActionEvent e) {
		Connection conn=null;//우선 빈 값
		//Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체
		//주의 : Statement 하나 당 한개의 ResultSet 객체만 열 수 있음		
		
		PreparedStatement ps;
		ResultSet rs;
        try {
        	if(e.getSource()==a) {//등록 버튼 클릭 시/
    		
      
	        	
	            conn = DBConnection.getConnection();
	            Statement st = conn.createStatement();
            
            
	            //sql
	            
	            ps=conn.prepareStatement("insert into 임시레시피테이블 values(?,?,?,?,?)");
				ps.setInt(1, number);
				ps.setString(2,text1.getText());//레시피명
				ps.setString(3,text2.getText());
				ps.setString(4,text3.getText());
				ps.setString(5,tarea.getText());
				
				
				
				ps.execute();
				number=number+1;
	           
	         
	            System.out.println();
	            System.out.println("테스트 완료");
	            
	            
	            //호출완료 후 종료해야하므로 해당 부분에 선언
	            if ( st != null ){st.close();}   
	            if ( conn != null ){conn.close();}
	        	}
	        	}catch (SQLException ex) {ex.printStackTrace();}   
	
		
	    		}
			
		}


//delete --> 익명클래스로?(따로할려면)

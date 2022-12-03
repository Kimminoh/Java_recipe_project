package test_project;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.*;

//레시피 등록, 삭제, 수정 하기 위한 레시피 메인 메뉴 


public class WindowR extends Button {
	private DefaultListModel model = new DefaultListModel();
	JList recipelog = new JList(model);
	//선택을 위해 단일 선택모드로 변경
	recipe_detail rd;//레시피 상세정보 창을 위한 프레임 생성
	
	JTextField text5 = new JTextField(10000); // 검색 필드
	JTextField text6 = new JTextField(10000);
	String rname;
	ImageIcon srchimg = new ImageIcon("이미지\\검색.png");
	
	public WindowR(String ID) {
		super(ID);
		Container contentPaneR = getContentPane();
		a.setBackground(Color.WHITE);
		
		ImageIcon imageicon1 = new ImageIcon("이미지\\등록.png");
		JButton insert = new JButton(imageicon1);
		insert.setOpaque(false);
		insert.setContentAreaFilled(false);
		insert.setBorderPainted(false);
		insert.setLocation(280,150);
		insert.setSize(121,121);
		img.add(insert);
		insert.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				new WindowA();
	    		//new Recipe_register(rname_input());
	    	}
	    });
		ImageIcon imageicon2 = new ImageIcon("이미지\\수정.png");
		JButton change = new JButton(imageicon2);
		change.setOpaque(false);
		change.setContentAreaFilled(false);
		change.setBorderPainted(false);
		change.setLocation(280,315);
		change.setSize(120,120);
		img.add(change);
		change.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WindowS();
            }
        });
		ImageIcon imageicon3 = new ImageIcon("이미지\\삭제.png");
		JButton delete = new JButton(imageicon3);
		delete.setBorderPainted(false);
		delete.setOpaque(false);
		delete.setContentAreaFilled(false);
		delete.setLocation(280,480);
		delete.setSize(122,121);
		img.add(delete);
		delete.addActionListener(new ActionListener() {
			
			//현재 JList에서만 삭제된것처럼 보이게 연출
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected=recipelog.getSelectedIndex();
				Object rname=recipelog.getSelectedValue();
				//remove_item(selected);
				delete_recipe(rname);
			}
		});
		
		
		
		ImageIcon setname55 = new ImageIcon("이미지\\레시피검색.png");
		JLabel SetName5 = new JLabel(setname55);
        SetName5.setBounds(780,48,228,52);
        img.add(SetName5);
        
        
        
        //단일 선택모드 지정
        recipelog.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        
        JScrollPane mainpl2 = new JScrollPane(recipelog);  // 레시피 검색 스크롤패널을 선언
        mainpl2.setBounds(670, 200, 450, 180);
        mainpl2.setBackground(Color.white);
        img.add(mainpl2);
       
        text5.setBounds(670,157,395,35);
        text5.setBackground(Color.white);
        img.add(text5);
        
        JButton lookup = new JButton(srchimg); // 검색 버튼
        lookup.setBounds(1072,150,49,49);
        lookup.setBorderPainted(false);
        img.add(lookup);
        lookup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String item=recipelog.getSelectedValue().toString();
				model.removeAllElements();
				search_recipe_log();
			}
        });
        JScrollPane mainpl3 = new JScrollPane();  // 레시피 검색 스크롤패널을 선언
        mainpl3.setBounds(670, 440, 450, 180);
        mainpl3.setBackground(Color.white);
        img.add(mainpl3);
        
        text6.setBounds(670,397,395,35);
        text6.setBackground(Color.white);
        img.add(text6);
        
        JButton lookup2 = new JButton(srchimg); // 검색 버튼
        lookup2.setBounds(1072,390,49,49);
        lookup2.setBorderPainted(false);
        img.add(lookup2);
        
        ImageIcon sch = new ImageIcon("이미지\\전체조회.png");
		JButton search = new JButton(sch); // 검색 버튼
	    search.setBounds(540,200,122,41);
	    search.setBorderPainted(false);
	    search.setOpaque(false);
	    search.setContentAreaFilled(false);
	    img.add(search);
	    search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String item=recipelog.getSelectedValue().toString();
				model.removeAllElements();
				add_recipe_log();
			}
	    });
	    ImageIcon exitt = new ImageIcon("이미지\\냉장고x.png");
        JButton exit = new JButton(exitt);
        exit.setBounds(1180,580,51,50);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        img.add(exit);
        exit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
				new GUI(ID);
        	}
        });
	    
	    ImageIcon vi = new ImageIcon("이미지\\레시피보기.png");
	    JButton view = new JButton(vi); // 검색 버튼
	    view.setBounds(540,260,120,40);
	    view.setBorderPainted(false);
	    view.setOpaque(false);
	    view.setContentAreaFilled(false);
	    img.add(view);
	    view.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==view) {
					rd=new recipe_detail();
				}
			}
		});
	    //recipelog.addListSelectionListener(new List);
	
	
	}
        
        
      //레시피 칸에 현재 데이터베이스에있는 레시피 뿌려주는 기능
    	
        
	

	//레시피 칸에 현재 데이터베이스에있는 레시피 뿌려주는 기능(조회기능)
	void add_recipe_log() { 
		Connection conn;//우선 빈 값
    		//Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체
    		//주의 : Statement 하나 당 한개의 ResultSet 객체만 열 수 있음		
            try {

    	            conn = DBConnection.getConnection();
    	            Statement stmt = conn.createStatement();
    	            
    	            String quary = "SELECT * FROM NEW레시피메인테이블"; // 총 데이터(열) 수를 구하는 쿼리문
                    ResultSet rs = stmt.executeQuery(quary); // SELECT의 반환값은 ResultSet에 저장해야함
                    
                    while(rs.next()) { 
                    	String recipename =rs.getString("레시피명");
                    	model.addElement(recipename+"\n");
                    }
                    
                    
    	            //호출완료 후 종료해야하므로 해당 부분에 선언
    	            if ( stmt != null ){stmt.close();}   
    	            if ( conn != null ){conn.close();}
    	    }
            
            catch (SQLException ex) {ex.printStackTrace();}   
            
    	    }
	
	void search_recipe_log() {  
		Connection conn;//우선 빈 값
		//Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체
		//주의 : Statement 하나 당 한개의 ResultSet 객체만 열 수 있음		
        try {
        	
	            conn = DBConnection.getConnection();
	            Statement stmt = conn.createStatement();
	            
	            String quary = "SELECT * FROM NEW레시피메인테이블"; // 총 데이터(열) 수를 구하는 쿼리문
                ResultSet rs = stmt.executeQuery(quary); // SELECT의 반환값은 ResultSet에 저장해야함
                
                String textstinString=text5.getText();
                
                while(rs.next()) { 
                	String recipename =rs.getString("레시피명");
                	
                	if(textstinString.equals(recipename))
                		model.addElement(recipename+"\n");
                    
                	
                }
                
                
	            //호출완료 후 종료해야하므로 해당 부분에 선언
	            if ( stmt != null ){stmt.close();}   
	            if ( conn != null ){conn.close();}
	    }
        
        catch (SQLException ex) {
        	ex.printStackTrace();
        }   
        
	    
	}
	
	//아이템을 지정하여 제거하는 알고리즘
	void remove_item(int idx) {
		if(idx<0) {
			if(model.size()==0) {  
				return;
			}
			idx=0;
		}
		model.remove(idx);
	}
	

	void delete_recipe(Object rname) {  
		Connection conn;
		
        try {
        		
	            conn = DBConnection.getConnection();
	            
	            String r_name=rname.toString();
	            System.out.println(rname);
	         
	            
	            String sql ="DELETE FROM NEW레시피메인테이블 WHERE 레시피명='"+r_name+"'";
	            Statement stmt = conn.createStatement();
	            boolean b = stmt.execute(sql);
				sql = "COMMIT";	
				b = stmt.execute(sql);

	            if ( stmt != null ){stmt.close();}   
	            if ( conn != null ){conn.close();}
	    }
        
        catch (SQLException ex) {
        	ex.printStackTrace();
        }   
	}
	
	//등록 탭에 들어가기전 등록할 레시피 이름 입력하는 칸(데이터베이스 전달)
    String rname_input() {
		Connection conn;
        try {
	            conn = DBConnection.getConnection();
	            
	            JOptionPane rname_inp=new JOptionPane();
	            rname=rname_inp.showInputDialog("등록할 레시피 이름을 입력해주세요.");    
	            String sql ="INSERT INTO NEW레시피메인테이블 ("
                		+ "레시피명, 레시피작성자ID, 레시피코드) VALUES ('" 
                		+ rname + "', '" + null + "', '" + 0 +"')"; // 입력받은 ID, PW와 구한 num을 입력하는 쿼리문
	            
	           
	            Statement stmt = conn.createStatement();
	            boolean b = stmt.execute(sql);
				sql = "COMMIT";
				b = stmt.execute(sql);
	            if ( stmt != null ){stmt.close();}   
	            if ( conn != null ){conn.close();}   
        }
        
        catch (SQLException ex) {
        	ex.printStackTrace();
        }
		return rname;   
	}
	
}


//새창 띄우는 클래스 (해당 클래스에 데이터베이스 정보 받아와서 정보 띄우기)

class recipe_detail extends JDialog {
	JLabel jb=new JLabel("");
	public recipe_detail() {
		getContentPane().add(jb);
		
		
		this.setSize(200,100);
		this.setModal(true);
		this.setVisible(true);
	}
}
	

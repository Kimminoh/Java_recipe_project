package test_project;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

//레시피 등록, 삭제, 수정 하기 위한 레시피 메인 메뉴 


public class WindowR extends Button {

	private DefaultListModel model = new DefaultListModel();
	JList recipelog = new JList(model);
	//선택을 위해 단일 선택모드로 변경
	recipe_detail rd;//레시피 상세정보 창을 위한 프레임 생성
	
	JTextField text5 = new JTextField(10000); // 검색 필드
	
	String rname;
	
	
	
	
	public WindowR() {
		Container contentPaneR = getContentPane();
		a.setBackground(Color.WHITE);

		RoundButton insert = new RoundButton("등록");
		insert.setFont(insert.getFont().deriveFont(15.0f));
		insert.setLocation(385,140);
		insert.setSize(100,100);
		img.add(insert);
		insert.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				new Recipe_register(rname_input());
	    	}
	    });
		RoundButton change = new RoundButton("수정");
		change.setFont(change.getFont().deriveFont(15.0f));
		change.setLocation(385,305);
		change.setSize(100,100);
		img.add(change);
		change.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WindowS();
            }
        });
		
		
		
		RoundButton delete = new RoundButton("삭제");
		delete.setFont(delete.getFont().deriveFont(15.0f));
		delete.setLocation(385,475);
		delete.setSize(100,100);
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
		
		
		
		
		JLabel SetName5 = new JLabel("레시피 검색");
        SetName5.setFont(SetName5.getFont().deriveFont(30.0f));
        SetName5.setBounds(830,70,300,40);
        img.add(SetName5);
        
        
        
        //단일 선택모드 지정
        recipelog.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        
        JScrollPane mainpl2 = new JScrollPane(recipelog);  // 레시피 검색 스크롤패널을 선언
        mainpl2.setBounds(710, 170, 450, 405);
        mainpl2.setBackground(Color.LIGHT_GRAY);
        img.add(mainpl2);
        
       
        text5.setBounds(710,140,390,30);
        text5.setBackground(Color.white);
        img.add(text5);
        
        JButton lookup = new JButton("검색"); // 검색 버튼
        lookup.setBounds(1100,140,60,29);
        img.add(lookup);
        lookup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String item=recipelog.getSelectedValue().toString();
				model.removeAllElements();
				search_recipe_log();
			}
        });
	
		JButton search = new JButton("조회"); // 검색 버튼
	    search.setBounds(600,140,60,29);
	    img.add(search);
	    search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String item=recipelog.getSelectedValue().toString();
				model.removeAllElements();
				add_recipe_log();
			}
	    });
	    
	    JButton view = new JButton("레시피보기"); // 검색 버튼
	    view.setBounds(600,180,120,29);
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
	

	

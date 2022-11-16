package test_project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.text.html.HTMLDocument.Iterator;

public class WindowB extends Button {			
	JButton Button[] = new JButton[100];
	String [] ingrediants = {"감자","간장","소금","토마토","계란"};
	Vector<String> v = new Vector<String>();	
	Vector<String> vResult = new Vector<String>();
	String RefSave;
	JList<String> strList = new JList<String>();
	JScrollPane scroll;
	public WindowB() {
		
		Container contentPaneA = getContentPane();
		contentPaneA.setBackground(Color.white);		
				
		setVisible(true);
		setDefaultCloseOperation(WindowA.EXIT_ON_CLOSE);		
        
        JLabel SetName1 = new JLabel("나만의 냉장고");
        SetName1.setFont(SetName1.getFont().deriveFont(30.0f));
        SetName1.setLocation(580,5);
        SetName1.setSize(200,50);               
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기    
        contentPaneA.add(SetName1);
        
        for(int i = 0; i < ingrediants.length; i++) {
			Button[i] = new JButton(ingrediants[i]);		
			Button[i].setFont(Button[i].getFont().deriveFont(30.0f));
			contentPaneA.add(Button[i]);
			Button[i].addActionListener(new MyActionListener());
		}
        
        JButton Ingre = new JButton("재료추가");
        Ingre.setBounds(180, 80, 100, 40);
        contentPaneA.add(Ingre);
        Ingre.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new IngrePlus();
        	}
        });
        
        JPanel IngrePanel = new JPanel(); // 추가한 재료를 보여주는 판넬
        IngrePanel.setBounds(180, 130, 450, 500);
        IngrePanel.setBackground(Color.LIGHT_GRAY);
        contentPaneA.add(IngrePanel);
        
        JPanel SeeRecipe = new JPanel(); // 내가 가지고 있는 재료로 검색한 레시피를 보여주는 판넬
        SeeRecipe.setBounds(670, 130, 450, 500);
        SeeRecipe.setBackground(Color.LIGHT_GRAY);
        contentPaneA.add(SeeRecipe);
        
        strList.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        strList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);      
        scroll = new JScrollPane(strList);
        scroll.setPreferredSize(new Dimension(300, 300));
        SeeRecipe.add(scroll);         
        
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
        
        JButton save = new JButton("검색");
        save.setLocation(560,80);
        save.setSize(70,40);
        contentPaneA.add(save);
        save.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		vResult.removeAllElements();       		
        		Data();
    			v.removeAllElements();
    			strList.setListData(vResult);
        	}
        });
	}
	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			v.add(b.getText());			
		}		
	}
		public void Data(){
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			v.add("당근");
			try {				
				String quary = "SELECT 레시피코드 FROM 재료 WHERE 재료 IN (";		
				java.util.Iterator<String> it = v.iterator();
				while(it.hasNext()) {
					String n = it.next();
					quary = quary + "'" + n + "'";
				}	
				quary = quary + ") GROUP BY 레시피코드 HAVING COUNT(순서) >= " + v.size() + " ORDER BY 레시피코드";
				System.out.println(quary);
				conn = DBConnection.getConnection();
	            pstm = conn.prepareStatement(quary);
	            rs = pstm.executeQuery();	           
				
				while(rs.next()) {
					System.out.println(rs.getString(1));
					vResult.add(rs.getString(1));			
				}								
				System.out.println(vResult.get(0));
			}catch(SQLException sqle) {
				System.out.println("SELECT문에서 예외 발생");
				sqle.printStackTrace();
			}finally {
				try {
					if(rs != null) {rs.close();}
					if(pstm != null) {pstm.close();}
					if(conn != null) {conn.close();}
				}catch(Exception e) {
					throw new RuntimeException(e.getMessage());
				}
			}
			/*
			try {
				String quary;
				java.util.Iterator<String> it2 = vResult.iterator();
				while(it2.hasNext()) {
					String n = it2.next();
					RefSave = RefSave + n + ",";
				}
				quary = "UPDATE 사용자 SET '" + RefSave + "' WHERE 사용자명 = " + 사용자명;
				pstm = conn.prepareStatement(quary);
			}catch(SQLException sqle) {
				System.out.println("SELECT문에서 예외 발생");
				sqle.printStackTrace();
			}finally {
				try {
					if(rs != null) {rs.close();}
					if(pstm != null) {pstm.close();}
					if(conn != null) {conn.close();}
				}catch(Exception e) {
					throw new RuntimeException(e.getMessage());
				}
			}
			*/
			
		}
}

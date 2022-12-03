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
	public WindowB(String ID) {
		super(ID);
		
		Container contentPaneA = getContentPane();
		contentPaneA.setBackground(Color.white);		
				
		setVisible(true);
		setDefaultCloseOperation(Recipe_register.EXIT_ON_CLOSE);		
        
		ImageIcon setname11 = new ImageIcon("이미지\\나만의냉장고.png");
		JLabel SetName1 = new JLabel(setname11);
        SetName1.setFont(SetName1.getFont().deriveFont(30.0f));
        SetName1.setBounds(590,30,272,48);            
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기    
        img.add(SetName1);
        
        for(int i = 0; i < ingrediants.length; i++) {
			Button[i] = new JButton(ingrediants[i]);		
			Button[i].setFont(Button[i].getFont().deriveFont(30.0f));
			contentPaneA.add(Button[i]);
			Button[i].addActionListener(new MyActionListener());
		}
        ImageIcon nangzang = new ImageIcon("이미지\\냉장고.png");
        JLabel nang = new JLabel(nangzang);
        nang.setBounds(1070,20,169,125);
        img.add(nang);
        
        ImageIcon rer = new ImageIcon("이미지\\냉장고재료.png");
        JLabel re = new JLabel(rer);
        re.setBounds(250,110,430,521);
        img.add(re);
        
        
        ImageIcon jae = new ImageIcon("이미지\\재료추가.png");
        JButton Ingre = new JButton(jae);
        Ingre.setBounds(30, 80, 51, 51);
        Ingre.setOpaque(false);
        Ingre.setContentAreaFilled(false);
        Ingre.setBorderPainted(false);
        re.add(Ingre);
        Ingre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new IngrePlus();
            }
        });
        JPanel IngrePanel = new JPanel(); // 추가한 재료를 보여주는 판넬
        IngrePanel.setBounds(190, 10, 170, 420);
        //IngrePanel.setBackground(Color.black); // 패널위치 확인할 때 사용
        IngrePanel.setBackground(new Color(255,0,0,0));
        re.add(IngrePanel);
        
        JPanel SeeRecipe = new JPanel(); // 내가 가지고 있는 재료로 검색한 레시피를 보여주는 판넬
        SeeRecipe.setBounds(770, 150, 350, 450);
        SeeRecipe.setBackground(new Color(255,0,0,0));
        img.add(SeeRecipe);
        
        strList.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        strList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        strList.setBackground(Color.white);
        scroll = new JScrollPane(strList);
        scroll.setPreferredSize(new Dimension(345, 445));
        SeeRecipe.add(scroll);         
        
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
        ImageIcon save1 = new ImageIcon("이미지\\검색1.png");
        JButton save = new JButton(save1);
        save.setBounds(38,160,51,52);
        save.setOpaque(false);
        save.setContentAreaFilled(false);
        save.setBorderPainted(false);
        re.add(save);
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
			
		}
}
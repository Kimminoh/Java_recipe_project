package test_project;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;

public class WindowS extends JFrame {
	
	JList<String> strList = new JList<String>();
	JScrollPane scroll;
	JTextField JS = new JTextField(10000);	
	String text;
	Vector<String> result = new Vector<String>();
	public WindowS() {
		setTitle("레시피 삭제");
		
		Container s = getContentPane();
		s.setSize(new Dimension(1100,1100));
		s.setBackground(Color.white);
		s.setLayout(null);
		
		JS.setBounds(215, 50, 450, 30);
		JS.setBackground(Color.white);
		s.add(JS);
		
		JPanel SeeRecipe = new JPanel(); // 내가 가지고 있는 재료로 검색한 레시피를 보여주는 판넬
        SeeRecipe.setBounds(215, 130, 450, 500);
        SeeRecipe.setBackground(Color.LIGHT_GRAY);
        s.add(SeeRecipe);
        
		strList.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		strList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		strList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				JList<String> temp = (JList<String>) e.getSource(); 
				String name = temp.getSelectedValue();
				System.out.println(name);
				int yn = JOptionPane.showConfirmDialog(null,
						"삭제하시겠습니까?","확인",JOptionPane.YES_NO_OPTION);
				if(yn == JOptionPane.YES_OPTION) {					
					DataDel(name);	
				}	
			}
		});
		scroll = new JScrollPane(strList);
		scroll.setPreferredSize(new Dimension(450,500));
		SeeRecipe.add(scroll);
		scroll.setLocation(215, 130);
		
		JButton save = new JButton("검색");
		save.setLocation(680, 50);
		save.setSize(70, 40);
		s.add(save);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Data();
				System.out.println(result.get(0));
				if(result != null)
					strList.setListData(result);
			}
		});
		
		setSize(800,800);
		Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2);
		setVisible(true);
		setResizable(false);
		
	}
	public void Data() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		result.removeAllElements();
		try {
			String quary;
			if(JS.getText() != null)
				quary = "SELECT 레시피이름 FROM 임시레시피테이블";
			else
				quary = "SELECT 레시피이름 FROM 임시레시피테이블 "+JS.getText();
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1));
				result.add(rs.getString(1));
			}
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
	public void DataDel(String s) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			String quary = "DELETE FROM 임시레시피테이블 WHERE 레시피이름='"+s+"'";
			System.out.println(quary);
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(quary);
			System.out.println("삭제되었습니다.");
		}catch(SQLException sqle) {
			System.out.println("SELECT문에서 예외 발생");
			sqle.printStackTrace();
		}finally {
			try {
				if(pstm != null) {pstm.close();}
				if(conn != null) {conn.close();}
			}catch(Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

}
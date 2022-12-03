package test_project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class RecipePage extends JFrame {
	String name;
	
	Vector<String> imgUrl = new Vector<String>();
	Vector<String> text = new Vector<String>();
	Vector<Integer> page = new Vector<Integer>();
	int maxPage, nowPage = 0;//인덱스번호 
	String url;//이미지 url
	
	JPanel Jp;
	JTextField Jt;
	JButton prev = new JButton("<");
	JButton next = new JButton(">");
	JLabel imgLabel;
	ImageIcon icon;
	
	//생성자 (생성자 호출시 해당 구문 사용)
	public RecipePage(String n) {
		this.name = n;
		Data(name);	//Data함수 호출 -> 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		//해당 JFrame의 컨턴트팬 : c 
		Container c = getContentPane();
		
		//url의 문자열을 imgUrl 벡터 공간에 삽입(nowPage는 인덱스 nowPage 숫자를 움직여준다)
		url = imgUrl.get(nowPage);
		
		icon = new ImageIcon(url); //이미지 url 받아야 하는 부분
		imgLabel = new JLabel(icon,JLabel.CENTER);
		imgLabel.setBounds(200,0,600,300);
		c.add(imgLabel);					//이미지 붙이는 라벨
		
		Jt = new JTextField();
		Jt.setText(text.get(nowPage));			
		c.add(Jt);
		Jt.setBounds(0,300,1000,200);	// 설명 텍스트창
			
		c.add(prev);					//이전 페이지로 넘길 버튼
		prev.setBounds(50, 200, 100, 100);		
		if(nowPage == 0)
			prev.setVisible(false);
		else
			prev.setVisible(true);	
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nowPage--;
				Jt.setText(text.get(nowPage));
				url = imgUrl.get(nowPage);
				icon = new ImageIcon(url);
				imgLabel.setIcon(icon);
				imgLabel.repaint();
				if(nowPage == 0)
					prev.setVisible(false);
				else
					prev.setVisible(true);
				next.setVisible(true);
			}
		});
		
		c.add(next);					//다음 페이지로 넘길 버튼
		next.setBounds(850,200,100,100);
		if(nowPage >= maxPage)
			next.setVisible(false);
		else
			next.setVisible(true);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nowPage++;
				Jt.setText(text.get(nowPage));
				url = imgUrl.get(nowPage);
				icon = new ImageIcon(url);
				imgLabel.setIcon(icon);
				imgLabel.repaint();
				if(nowPage >= maxPage)
					next.setVisible(false);
				else
					next.setVisible(true);
				prev.setVisible(true);
			}
		});		
		setSize(1000,500);
		setResizable(false);
		setVisible(true);
	}

	public void Data(String name) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			String quary;
			quary = "SELECT * FROM RECIPE_TABLE WHERE 레시피명= '" + name + "' ORDER BY 순서";
			System.out.println(quary);
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();
			
			int i = -1;
			while(rs.next()) {
				page.add(rs.getInt(3));
				text.add(rs.getString(4));
				imgUrl.add(rs.getString(5));
				i++;
			}
			maxPage = i;
			
			
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
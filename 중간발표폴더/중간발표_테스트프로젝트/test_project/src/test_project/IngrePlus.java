package test_project;

import javax.swing.*;
import java.awt.*;

public class IngrePlus extends JFrame {
	public IngrePlus() {
		setTitle("재료추가");
        
        Container CIP = getContentPane();
        CIP.setBackground(Color.white);
        CIP.setLayout(null);
        
        JPanel IngrePanel = new JPanel(); // 재료버튼을 담을 판넬
        IngrePanel.setBounds(50, 80, 390, 460);
        IngrePanel.setBackground(Color.LIGHT_GRAY);
        CIP.add(IngrePanel);
        
        JButton Search = new JButton("검색");
        Search.setBounds(380, 40, 60, 25);
        CIP.add(Search);
        
        JTextField JTA = new JTextField();
        JTA.setBounds(50, 40, 300, 25);
        JTA.setBackground(Color.white);
        CIP.add(JTA);
        
        JButton add = new JButton("추가");
        add.setBounds(300, 555, 60, 35);
        CIP.add(add);
        
        JButton close = new JButton("닫기");
        close.setBounds(380, 555, 60, 35);
        CIP.add(close);
        
        setSize(500,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        
        setResizable(false);// 창 크기 고정
            
	}
}

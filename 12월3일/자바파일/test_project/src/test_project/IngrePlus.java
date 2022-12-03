package test_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngrePlus extends JFrame {
	public IngrePlus() {
		setTitle("재료추가");
        
        Container CIP = getContentPane();
        CIP.setBackground(Color.white);
        CIP.setLayout(null);
        
        ImageIcon icon = new  ImageIcon("이미지\\재료추가배경.png");
        JLabel img = new JLabel(icon);
        img.setBounds(0,0,icon.getIconWidth(), icon.getIconHeight());
        CIP.add(img);
        
        JPanel IngrePanel = new JPanel(); // 재료버튼을 담을 판넬
        IngrePanel.setBounds(50, 140, 380, 400);
        IngrePanel.setBackground(Color.white);
        img.add(IngrePanel);
        
        ImageIcon s = new ImageIcon("이미지\\돋보기.png");
        JButton Search = new JButton(s);
        Search.setBounds(380, 85, 50, 49);
        Search.setOpaque(false);
        Search.setContentAreaFilled(false);
        Search.setBorderPainted(false);
        img.add(Search);
        
        JTextField JTA = new JTextField();
        JTA.setBounds(50, 90, 315, 35);
        JTA.setBackground(Color.white);
        img.add(JTA);
        
        ImageIcon ad = new ImageIcon("이미지\\추가.png");
        JButton add = new JButton(ad);
        add.setBounds(300, 555, 51, 50);
        add.setOpaque(false);
        add.setContentAreaFilled(false);
        add.setBorderPainted(false);
        img.add(add);
        
        ImageIcon cl = new ImageIcon("이미지\\x.png");
        JButton close = new JButton(cl);
        close.setBounds(380, 555, 51, 50);
        close.setOpaque(false);
        close.setContentAreaFilled(false);
        close.setBorderPainted(false);
        img.add(close);
        close.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				setVisible(false);
        	}
        });
        
        setSize(490,650);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        
        setResizable(false);// 창 크기 고정
            
	}
}
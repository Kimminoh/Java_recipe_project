package test_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Button extends JFrame{
	public RoundButton a;
	public RoundButton b;
	public RoundButton c;
	public RoundButton d;
	public RoundButton e;
	public RoundButton f;
	JLabel img;
	public Button() {
		setTitle("요리조리");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        Container contentPaneButton = getContentPane();
        contentPaneButton.setBackground(Color.white);
        contentPaneButton.setLayout(null); 
        
        ImageIcon icon = new  ImageIcon("pic.png");
        img = new JLabel(icon);
        img.setBounds(0,0,icon.getIconWidth(), icon.getIconHeight());
        contentPaneButton.add(img);
        
        a = new RoundButton("레시피");
        a.setFont(a.getFont().deriveFont(15.0f));
        a.setBounds(87,110,120,60);
        img.add(a);       
        a.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new WindowR();
        	}
        });
        
        b = new RoundButton("나만의 냉장고");
        b.setFont(b.getFont().deriveFont(15.0f));
        b.setBounds(87,181,120,60);
        img.add(b);
        b.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new WindowB();
        	}
        });

        c = new RoundButton("c");
        c.setFont(c.getFont().deriveFont(15.0f));
        c.setBounds(87,252,120,60);
        img.add(c);
        
        d = new RoundButton("d");
        d.setFont(d.getFont().deriveFont(15.0f));
        d.setBounds(87,323,120,60);
        img.add(d);

        e = new RoundButton("마이페이지");
        e.setFont(e.getFont().deriveFont(15.0f));
        e.setBounds(87,394,120,60);
        img.add(e);
        e.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Mypage();
            }
        });
        
        f = new RoundButton("홈");
        f.setFont(f.getFont().deriveFont(15.0f));
        f.setBounds(87,465,120,60);
        img.add(f);
        f.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GUI();
        	}
        });

        setSize(1280,699);
        setVisible(true);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        
        setResizable(false);// 창 크기 고정
	}

}


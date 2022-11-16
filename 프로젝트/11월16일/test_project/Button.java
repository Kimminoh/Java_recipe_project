package test_project;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Button extends JFrame{
	public Button() {
		setTitle("요리조리");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        Container contentPaneButton = getContentPane();
        contentPaneButton.setBackground(Color.white);
        contentPaneButton.setLayout(null);        
        
        JButton a = new JButton("등록");
        a.setLocation(0,0);
        a.setSize(120,110);
        contentPaneButton.add(a);       
        a.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new WindowA();
        	}
        });
        
        JButton b = new JButton("나만의 냉장고");
        b.setLocation(0,110);
        b.setSize(120,110);
        contentPaneButton.add(b);
        b.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new WindowB();
        	}
        });

        JButton c = new JButton("c");
        c.setLocation(0,220);
        c.setSize(120,110);
        contentPaneButton.add(c);
        
        JButton d = new JButton("d");
        d.setLocation(0,330);
        d.setSize(120,110);
        contentPaneButton.add(d);

        JButton e = new JButton("e");
        e.setLocation(0,440);
        e.setSize(120,110);
        contentPaneButton.add(e);
        
        JButton f = new JButton("메인");
        f.setLocation(0,550);
        f.setSize(120,110);
        contentPaneButton.add(f);
        f.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new MainWindow();
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
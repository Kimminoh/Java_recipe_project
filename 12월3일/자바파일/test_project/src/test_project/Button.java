package test_project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Button extends JFrame{
	
	
	ImageIcon recipe = new  ImageIcon("이미지\\레시피버튼.png");
	public JButton a = new JButton(recipe);
	ImageIcon mynangjang = new  ImageIcon("이미지\\나만의냉장고버튼.png");
	public JButton b = new JButton(mynangjang);
	ImageIcon timer = new  ImageIcon("이미지\\타이머버튼.png");
	public JButton c= new JButton(timer);
	ImageIcon point = new  ImageIcon("이미지\\포인트버튼.png");
	public JButton d= new JButton(point);
	ImageIcon mypageb = new  ImageIcon("이미지\\마이페이지버튼.png");
	public JButton e = new JButton(mypageb);
    ImageIcon home = new ImageIcon("이미지\\홈.png");
	public JButton f = new JButton(home);
	EmptyBorder b1 = new EmptyBorder(5,3,5,3);
	JLabel img;
	
	
	
	
	public Button(String ID) {
		setTitle("요리조리");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        
        Container contentPaneButton = getContentPane();
        contentPaneButton.setBackground(Color.white);
        contentPaneButton.setLayout(null); 
        
        ImageIcon icon = new  ImageIcon("이미지\\배경.png");
        img = new JLabel(icon);
        img.setBounds(0,0,icon.getIconWidth(), icon.getIconHeight());
        contentPaneButton.add(img);
        
        a.setBounds(40,110,156,70);
        img.add(a);       
        a.setOpaque(false);
        a.setContentAreaFilled(false);
        a.setBorderPainted(false);
        a.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new WindowR(ID);
        	}
        });
        
        b.setBounds(40,190,156,70);
        img.add(b);
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new WindowB(ID);
        	}
        });

        c.setBounds(40,270,156,70);
        c.setOpaque(false);
        c.setContentAreaFilled(false);
        c.setBorderPainted(false);
        img.add(c);
        
        d.setBounds(40,350,156,70);
        d.setOpaque(false);
        d.setContentAreaFilled(false);
        d.setBorderPainted(false);
        img.add(d);
        
        e.setBounds(40,430,156,70);
        img.add(e);
        e.setOpaque(false);
        e.setContentAreaFilled(false);
        e.setBorderPainted(false);
        e.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Mypage(ID);
            }
        });
  
        f.setBounds(20,20,63,62);
        img.add(f);
        f.setOpaque(false);
		f.setContentAreaFilled(false);
		f.setBorderPainted(false);
        f.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GUI(ID);
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

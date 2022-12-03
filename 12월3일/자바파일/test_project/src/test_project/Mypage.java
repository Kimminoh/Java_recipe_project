package test_project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Mypage extends Button{
	getID id=new getID();
	private String[] ing= {"양파", "두부","계란", "우유", "김치","대파","고추", "버터","당근","오이","양배추","배추","양상추"}; // 임시 냉장고 재료 리스트
	public Mypage(String ID) {
		super(ID);
		Container contentPaneA = getContentPane();
		contentPaneA.setBackground(Color.white);

		setVisible(true);
		setDefaultCloseOperation(Mypage.EXIT_ON_CLOSE);	
		
		ImageIcon setname11 = new ImageIcon("이미지\\마이페이지.png");
		JLabel SetName1 = new JLabel(setname11);
	    SetName1.setBounds(600,40,223,46);
	    Dimension frameSize = getSize();
	    Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setLocation((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2);    
	    img.add(SetName1);
	    
	    JLabel img1 = new JLabel();
	    ImageIcon icon = new ImageIcon("이미지\\프로필.png");
	    ImageIcon icon2 = new ImageIcon("이미지\\설정.png");
	    ImageIcon icon3 = new ImageIcon("이미지\\설정누름.png");
	    
	    img1.setIcon(icon); 
	    img1.setBounds(325,120,241,229);
	    img.add(img1); //프로필 사진
	    
	    JButton Jb = new JButton(icon2); //마이페이지 수정 버튼
	    Jb.setRolloverIcon(icon3);
	    Jb.setOpaque(false);
	    Jb.setContentAreaFilled(false);
	    Jb.setBorderPainted(false);
	    Jb.setBounds(240,20,70,70);
	    Jb.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				new MypageB();
        	}
        });
	    img.add(Jb);
	    
	   
	    JLabel SetName2 = new JLabel("ID : "+ID);
	    SetName2.setFont(SetName1.getFont().deriveFont(25.0f));
	    SetName2.setBounds(290,420,100,50);
	    img.add(SetName2);
	    
	    JLabel SetName3 = new JLabel("포인트 : ");
	    SetName3.setFont(SetName1.getFont().deriveFont(25.0f));
	    SetName3.setBounds(290,490,100,50);
	    img.add(SetName3);
	    
	    ImageIcon sunban = new ImageIcon("이미지\\부엌선반.png");
	    JLabel sun = new JLabel(sunban);
	    sun.setBounds(685,345,246,234);
	    img.add(sun);
	    
	    ImageIcon mynang = new ImageIcon("이미지\\나의냉장고.png");
	    JLabel SetName4 = new JLabel(mynang);
	    SetName4.setBounds(930,150,215,430);
	    img.add(SetName4);
	    
	    Font font = new Font("맑은 고딕",Font.BOLD,27);
	    JList<String> list = new JList<String>(ing); //냉장고 재료 스크롤리스트
	    list.setOpaque(false);
	    list.setFont(font);
	    list.setBackground(new Color(255,0,0,0));
	    JScrollPane Sc = new JScrollPane(list);
	    Sc.setBorder(b1);
	    Sc.setOpaque(false);
	    Sc.getViewport().setOpaque(false);
	    Sc.setBackground(new Color(255,0,0,0));
	    Sc.setBounds(70,50,130,315);
	    SetName4.add(Sc);

	    
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
		}
}
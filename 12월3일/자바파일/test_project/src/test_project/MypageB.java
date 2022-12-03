package test_project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MypageB extends JFrame {
	
	JTextField text1 = new JTextField(100); 
	JTextField text2 = new JTextField(100);
	JTextField text3 = new JTextField(100);
	JPanel pl = new JPanel();
	TextArea tarea = new TextArea(21,63);
	JLabel img1 = new JLabel();
	public ImageIcon icon = new ImageIcon("이미지\\수정프로필.png");
	JButton image = new JButton("사진변경");
	
	public MypageB() {
		setTitle("회원 정보 수정");
		Container contentPaneA = getContentPane();
		contentPaneA.setBackground(Color.white);	
		setSize(650,650);
		setVisible(true);	
		contentPaneA.setLayout(null); 
		
		JButton a = new JButton("수정");
		a.setBounds(450,550,70,40);
        contentPaneA.add(a);
		
		 JLabel SetName1 = new JLabel("닉네임");
	     SetName1.setFont(SetName1.getFont().deriveFont(15.0f));
	     SetName1.setBounds(150,340,100,50); 
	        
	     text1.setBounds(230,350,193,30);
	     text1.setBackground(Color.white);
	     contentPaneA.add(text1);
	        
	     JLabel SetName2 = new JLabel("ID");
	     SetName2.setFont(SetName2.getFont().deriveFont(15.0f));
	     SetName2.setBounds(150,390,100,50);        
	        
	        text2.setBounds(230,400,193,30);
	        text2.setBackground(Color.white);
	        contentPaneA.add(text2);
	        
	        JLabel SetName3 = new JLabel("PW");
	        SetName3.setFont(SetName3.getFont().deriveFont(15.0f));
	        SetName3.setBounds(150,440,100,50);   
	        
	        text3.setBounds(230,450,193,30); 
	        text3.setBackground(Color.white);
	        contentPaneA.add(text3);
	        
	        img1.setIcon(icon); 
		    img1.setBounds(200,80,232,232);
		    contentPaneA.add(img1); //프로필 사진
	        
	        image.setFont(image.getFont().deriveFont(15.0f));//프로필 사진 변경
	        image.setBorderPainted(false);
	        image.setBackground(Color.white);
	        image.setBounds(445, 180,150,50);
	        contentPaneA.add(image);
	        image.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
					setVisible(true); 
					String str=e.getActionCommand();
					  if(str.equals("사진변경"))
					  {
					   
					   JFileChooser chooser = new JFileChooser();
					   chooser.setAcceptAllFileFilterUsed(true);
					   chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			            
			            int ret=chooser.showOpenDialog(null);
			            if(ret!=JFileChooser.APPROVE_OPTION){
			                JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.","경고",JOptionPane.WARNING_MESSAGE);
			                return;
			            }
			            
			            String filePath = chooser.getSelectedFile().toString();
			            text3.setText(filePath);
			            ImageIcon icon = new ImageIcon(filePath);
			            JLabel imgla = new JLabel(icon);
			            contentPaneA.add(imgla);
			            imgla.setBounds(980,50,200,200); 
				    }				   			
	        	}
	        });
	        
	        JButton exit = new JButton("닫기");
	        exit.setLocation(550,550);
	        exit.setSize(70,40);
	        contentPaneA.add(exit);
	        exit.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		setVisible(false);
	        	}
	        });
	        
	        pl.setBackground(Color.white);
	        
	        contentPaneA.add(SetName1);	
	        contentPaneA.add(SetName2);		
	        contentPaneA.add(SetName3);
	        
	        Dimension frameSize = getSize();
	        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
	        setLocation((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기 
	}
}

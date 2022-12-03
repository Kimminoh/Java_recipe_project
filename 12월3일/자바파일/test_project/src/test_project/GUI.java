package test_project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//메인화면


public class GUI extends Button {
	
	
    public GUI(String ID) {
    	super(ID);
    	
    	Container contentPaneGUI = getContentPane();
    	
    	contentPaneGUI.setBackground(Color.white);
    	
    	ImageIcon recipeday = new ImageIcon("이미지\\오늘의레시피.png");
    	JLabel SetName1 = new JLabel(recipeday);
        SetName1.setBounds(590,40,267,50);
        img.add(SetName1);
        JPanel mainpl = new JPanel(); // 추천 레시피를 보여주는 판넬, 이미지 붙일 예정
        mainpl.setBounds(560, 175, 350, 350);
        mainpl.setBackground(Color.white);
        mainpl.setBackground(new Color(255,0,0,0));
        img.add(mainpl);
        
        ImageIcon leftb = new ImageIcon("이미지\\왼쪽화살표.png");
        JButton left = new JButton(leftb); // 버튼 클릭하면 추천 레시피 바꿈
        left.setOpaque(false);
        left.setContentAreaFilled(false);
        left.setBorderPainted(false);
        left.setBounds(430, 300, 100, 100);
        left.setBackground(Color.white);
        left.setBorderPainted(false);
        img.add(left);

        ImageIcon rightb = new ImageIcon("이미지\\오른쪽화살표.png");
        JButton right = new JButton(rightb); // 버튼 클릭하면 추천 레시피 바꿈
        right.setOpaque(false);
        right.setContentAreaFilled(false);
        right.setBorderPainted(false);
        right.setBounds(940, 300, 100, 100);
        right.setBackground(Color.white);
        right.setBorderPainted(false);
        img.add(right);
        
        ImageIcon sujeoimg = new ImageIcon("이미지\\수저.png");
        JLabel sujeo = new JLabel(sujeoimg);
        sujeo.setBounds(1010,430,203,188);
        img.add(sujeo);

        JLabel recipeName = new JLabel("레시피 이름"); // 좌 우 버튼 클릭 시 레시피 제목도 바뀜
        recipeName.setBounds(690, 125, 200, 50);
        recipeName.setFont(recipeName.getFont().deriveFont(15.0f));
        img.add(recipeName);
    }
}
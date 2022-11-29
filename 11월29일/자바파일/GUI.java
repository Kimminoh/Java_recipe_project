package test_project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//메인화면


public class GUI extends Button {
    public GUI() {
    	f.setBackground(Color.WHITE);
    	Container contentPaneGUI = getContentPane();
    	
    	contentPaneGUI.setBackground(Color.white);
    	
    	JLabel SetName1 = new JLabel("오늘의 추천 레시피");
    	SetName1.setFont(SetName1.getFont().deriveFont(30.0f));
        SetName1.setBounds(590,40,300,40);
        img.add(SetName1);
        JPanel mainpl = new JPanel(); // 추천 레시피를 보여주는 판넬, 이미지 붙일 예정
        mainpl.setBounds(500, 150, 450, 400);
        mainpl.setBackground(Color.LIGHT_GRAY);
        img.add(mainpl);

        JButton left = new JButton("<"); // 버튼 클릭하면 추천 레시피 바꿈
        left.setFont(SetName1.getFont().deriveFont(30.0f));
        left.setBounds(380, 300, 100, 100);
        left.setBackground(Color.white);
        left.setBorderPainted(false);
        img.add(left);

        JButton right = new JButton(">"); // 버튼 클릭하면 추천 레시피 바꿈
        right.setFont(SetName1.getFont().deriveFont(30.0f));
        right.setBounds(970, 300, 100, 100);
        right.setBackground(Color.white);
        right.setBorderPainted(false);
        img.add(right);

        JLabel recipeName = new JLabel("레시피 이름"); // 좌 우 버튼 클릭 시 레시피 제목도 바뀜
        recipeName.setBounds(660, 100, 200, 50);
        recipeName.setFont(recipeName.getFont().deriveFont(15.0f));
        img.add(recipeName);
    }
}
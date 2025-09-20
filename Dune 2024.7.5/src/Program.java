import java.awt.Cursor;

import javax.swing.JFrame;

public class Program {

	public static void main(String[] args) {
		
		// Sound.playSound("sountrack.wav");
		
		Texture.load("sprites.png");
		
		// 0 - машинка
		Texture.crop(5, 0, 0);
		Texture.crop(5, 1, 1);
		Texture.crop(5, 2, 2);
		Texture.crop(5, 3, 3);
		
		Texture.crop(0, 2, 4);
		Texture.crop(0, 3, 5);
		
		Texture.crop(1, 1, 6);
		Texture.crop(1, 6, 7);
		Texture.crop(1, 7, 8);
		
		Texture.crop(1, 0, 9);
		
		Texture.crop(6, 0, 10);
		
		Texture.crop(0, 1, 11);
		
		
		
		Texture.crop(4, 0, 12);
		Texture.crop(4, 1, 13);
		
		
		
		
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		
		
		
		GamePanel panel = new GamePanel();
		frame.add(panel);
		StatusBar.setPanel(panel);
		StatusBar.show("Игра началась", 20000);
		
		panel.addMouseListener(panel);
		panel.addMouseMotionListener(panel);
		
		frame.addKeyListener(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		frame.revalidate();
	}

}

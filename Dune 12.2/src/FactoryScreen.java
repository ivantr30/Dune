import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FactoryScreen {
	
	private static boolean active = false;
	
	static Factory current;

	
	private static JPanel panel;
	
	public static void setPanel(JPanel p) {
		panel = p;
	}
	
	public static void toggle() {
		active = !active;
	}

	public static void paint(Graphics g ) {
		if (active) {
			g.setColor(Color.BLACK);
			g.fillRect(0,  0, panel.getWidth(), panel.getHeight());	
			g.setColor(Color.WHITE);
			g.setFont(new Font(Font.MONOSPACED, 0, 32));
			g.drawString("Производятся: " + current.unitCount , panel.getWidth() / 2 , panel.getHeight() );
			
		}
	}

}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class StatusBar {
	
	private static boolean active = true;
	private static Color bg = new Color(43, 255, 0, 127);
	private static Color border = new Color(34, 204, 0, 255);
	private static Dimension size = new Dimension(400, 200);
	private static JPanel panel;
	private static String text = "Золото: 20";
	private static Font f = new Font(Font.MONOSPACED, Font.BOLD, 32);
	
	private static int timeLimit = 2000;
	private static int lifeTime = 0;
	
	
	public static void setPanel(JPanel p) {
		panel = p;
	}
	
	public static void setText(String text) {
		StatusBar.text = text;
	}
	
	public static void toggle() {
		active = !active;
	}
	
	public static void update(int ms) {
		lifeTime += ms;
		if (lifeTime > timeLimit) {
			hide();
		}
	}
	
	public static void show(String text, int timeLimit) {
		setText(text);
		active = true;
		StatusBar.timeLimit = timeLimit;
		lifeTime = 0;
		
	}
	
	
	public static void show(String text) {
		setText(text);
		active = true;
		timeLimit = 100000000;
		
	}
	
	public static void show() {
		active = true;
		timeLimit = 100000000;
	}
	
	public static void hide() {
		active = false;
	}

	public static void paint(Graphics g ) {
		if (active && panel != null) {
			g.setColor(bg);
			g.fillRoundRect(panel.getWidth() / 2 - size.width / 2, panel.getHeight() - size.height, size.width, size.height, 10, 10);
            g.setColor(border);
            g.drawRoundRect(panel.getWidth() / 2 - size.width / 2, panel.getHeight() - size.height, size.width, size.height, 10, 10);
            g.setColor(Color.BLACK);
            g.setFont(f);
            g.drawString(text, panel.getWidth() / 2 - size.width / 2 + 20, panel.getHeight() - size.height + 50);
            
            g.drawString("Денег: "+ResourceManager.getGold(), panel.getWidth() / 2 - size.width / 2 + 20, panel.getHeight() - size.height + 90);
            
            
            
		}
	}
	
	
}

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class StatusBar {
	
	private static boolean active = true;
	private static Color bg = new Color(107,255,36,127);
	private static Color border = new Color(34,204,0,255);
	private static JPanel panel;
	private static String text = "";
	private static Font f = new Font(Font.MONOSPACED,Font.BOLD,32);
	
	private static int timeLimit = 2000;
	private static int lifeTime = 0;
	
	public static void setText(String t) {
		text = t;
	}
	
	public static void setPanel(JPanel p) {
		panel = p;
	}
	
	public static void toggle() {
		active = !active;
	}
	
	public static void show(String t) {
		active = true;
		setText(t);
		timeLimit=1000000;
	}
	
	public static void update(int ms) {
		lifeTime+=ms;
		if(lifeTime>timeLimit) {
			hide();
		}
	}
	
	public static void show(String t, int timeLimit) {
		active = true;
		setText(t);
		StatusBar.timeLimit = timeLimit;
		lifeTime=0;
	}
	
	public static void show() {
		active = true;
		timeLimit=1000000;
	}
	
	public static void hide() {
		active = false;
	}
	
	public static void paint(Graphics g) {
		if(active) {
			if(panel != null) {
				g.setColor(bg);
				g.fillRoundRect(panel.getWidth() / 2 - 400/2, panel.getHeight() - 200, 400, 200,10,10);
				g.setColor(border);
				g.drawRoundRect(panel.getWidth() / 2 - 400/2, panel.getHeight() - 200, 400, 200,10,10);
				g.setColor(Color.BLACK);
				g.setFont(f);
				g.drawString(text, panel.getWidth() / 2 - 400/2+20, panel.getHeight() - 200+50);
				g.drawString("Денег: "+ResourceManager.getGold(), panel.getWidth() / 2 - 400 / 2 + 20, panel.getHeight() - 200 + 90);
			}
		}
	}
}

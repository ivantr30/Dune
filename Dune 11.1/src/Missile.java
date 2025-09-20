import java.awt.Graphics;
import java.awt.Point;

public abstract class Missile implements PaintUpdatable {

	protected Sprite main;
	protected Unit owner;
	protected boolean active;
	
	protected int maxDistance = Block.SIZE * 18;
	protected Point start;

	
	public Missile (Unit owner, double x, double y, double speed, int picId) {
		main = new Sprite(x, y, speed, picId);
		main.setAlpha(owner.getGunAlpha());
		this.owner = owner;
		active = true;
		start = new Point((int)main.getX(), (int)main.getY());
	}
	
	public boolean isDestroyed() {
		return !active;
	}
	
	public void destroy() {
		active = false;
	}
	
	public void onMaxDistanceDestroy() {
		
	}
	
	public void paint(Graphics g) {
		if (active) {
			main.paint(g);
		}
	}
	
	public void update(int ms) {
		if (active) {
			
			double d = Point.distance(start.x, start.y, main.getX(), main.getY());
			
			if (d >= maxDistance) {
				destroy();
				onMaxDistanceDestroy();
			}
			
			main.update(ms);
		}
	}

}

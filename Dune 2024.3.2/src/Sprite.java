import java.awt.Graphics;

public class Sprite {

	private double x, y;
	private double alpha = 0;
	private double speed = 0;
	private double _speed = 0;
	protected boolean active;
	
	private int[] frames = new int[100];
	
	private int currentFrame = 0;
	private int framesCount = 1;
	
	private int frameTime = 120;
	private int elapsedTime = 0;
	
	private boolean animateWhenStopped = false;
	
	
	public void setSpeed(double speed) {
		this._speed = speed;
	}
		
	public void addFrame(int picId) {
		currentFrame++;
		frames[currentFrame] = picId;
		framesCount++;
	}
	
	public void nextFrame() {
		currentFrame ++;
		currentFrame = currentFrame % framesCount;
	}

	
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public double getAlpha() {
		return alpha;
	}

	

	public double getSpeed() {
		return speed;
	}

	public Sprite (double x, double y, double speed, int mainPicId) {
		frames[0] = mainPicId;
		this.x = x;
		this.y = y;
		this.speed  = speed;
		this._speed = speed;
	}
	
	private void updateFramesAnimation(int ms) {
		elapsedTime += ms;
		if (elapsedTime >= frameTime) {
			nextFrame();
			elapsedTime = 0;
		}
	}
	
	public void update(int ms) {
		
		if (speed != 0 || animateWhenStopped) {
			updateFramesAnimation(ms);
		}

		
		x = x + Math.cos(alpha) * speed * ms / 1000.0;
		y = y + Math.sin(alpha) * speed * ms / 1000.0;
	}
	
	public void paint(Graphics g) {
		int picId = frames[currentFrame];
		Texture.paint(
				Camera.getScreenX(x), 
				Camera.getScreenY(y),
				picId, g, alpha);
		
	}
	
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	
	public void up() {
		speed = _speed;
		alpha = 3*Math.PI/2;
	}
	public void down() {
		speed = _speed;
		alpha = Math.PI/2;
	}
	public void right() {
		speed = _speed;
		alpha = 0;
	}
	public void left() {
		speed = _speed;
		alpha = Math.PI;
	}
	
	public void stop() {
		this.speed = 0;
	}

	public void setActive(boolean b) {
		active = b;
	}
	public boolean isDestroyed() {
		return !active;
	}
	
}

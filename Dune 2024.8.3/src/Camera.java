public class Camera {

	private static double x;
	private static double y ;
	private static int width;
	private static int height;
	
	private static double savedX, savedY;
	
	public static void save() {
		savedX = x;
		savedY = y;
	}
	
	public static void restore() {
		setPos(savedX, savedY);
	}
		
	
	public static double getWorldX(int screenX) {
		return screenX + x;
	}

	public static double getWorldY(int screenY) {
		return screenY + y;
	}
	
	public static int getScreenX(double x) {
		return (int)(x - Camera.x);
	}
	
	public static int getScreenY(double y) {
		return (int)(y - Camera.y);
	}
	
	public static void move(double dx, double dy) {
		setPos(getX() + dx, getY() + dy);
	}
	
	public static void setPos(double x, double y) {
		Camera.x = x;
		Camera.y = y;
		
		if (Camera.x < 0) {
			Camera.x = 0;
		}
		if (Camera.y < 0) {
			Camera.y = 0;
		}
		
		if (Camera.x > Map.getWidth() - Camera.width) {
			Camera.x = Map.getWidth() - Camera.width;
		}
		if (Camera.y > Map.getHeight() - Camera.height) {
			Camera.y = Map.getHeight() - Camera.height;
		}
	}
	
	public static int getWidth() {
		return width;
	}
	public static void setWidth(int width) {
		Camera.width = width;
	}
	public static int getHeight() {
		return height;
	}
	public static  void setHeight(int height) {
		Camera.height = height;
	}
	public static double getX() {
		return x;
	}
	public static double getY() {
		return y;
	}
	
	public static double getBottom() {
		return getY() + getHeight() - 1;
	}
	public static double getRight() {
		return getX() + getWidth() - 1;
	}
	
}

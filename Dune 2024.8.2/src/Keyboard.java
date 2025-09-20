
public class Keyboard {

	private static boolean [] keys = new boolean [1024];
	private static boolean [] prevKeys = new boolean [1024];
	
	
	public static void update() {
		prevKeys = keys.clone();
	}
	
	public static boolean wasKeyPressing(int keyCode) {
		return keys[keyCode] && !prevKeys[keyCode];
	}
	
	
	public static void registerKeyDown(int keyCode) {
		keys[keyCode] = true;
	}
	
	public static void registerKeyUp(int keyCode) {
		keys[keyCode] = false;
	}
	
	
	public static boolean isKeyDown(int keyCode) {
		return keys[keyCode];
	}
	
	
}



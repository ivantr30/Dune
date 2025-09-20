
public class Keyboard {

	private static boolean [] keys = new boolean [1024];
	
	
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

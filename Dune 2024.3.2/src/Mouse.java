import java.awt.Point;

public class Mouse {

	private static boolean [] states = new boolean [10]; 
	private static boolean [] prevStates = new boolean [10]; 
	private static Point mousePos = new Point();
	
	public static boolean wasButtonPressing(int button) {
		return states[button] && !prevStates[button];
	}
	
	public static void update() {
		prevStates = states.clone();
	}
	
	public static void registerMouseCoordinate(int x, int y) {
       mousePos.x = x;
       mousePos.y = y;
    }

    public static Point getMouseCoordinate() {
        return mousePos;
    }
	
    public static int getCol() {
    	return Map.getCol(Camera.getWorldX(mousePos.x));
    }
    public static int getRow() {
    	return Map.getRow(Camera.getWorldY(mousePos.y));
    }
	
	public static void registerMouseButton(int button, boolean state) {
		if (button < 0 || button >= states.length) {
			return;
		}
		
		states[button] = state;		
	}
	
	public static boolean isMouseDown(int button) {
		if (button < 0 || button >= states.length) {
			return false;
		}
		
		return states[button];
	}
	
	
	
}


public class GameState {
	final static int GAME = 0;
	final static int CONSTRUCTOR = 1;
	final static int MENU = 2;
	final static int INSIDE_BUILDING = 3;
	
	private static int state = CONSTRUCTOR;
	
	public static int getState() {
		return state;
	}
}

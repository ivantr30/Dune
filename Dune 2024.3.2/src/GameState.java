
public class GameState {

	public static final int GAME        = 0;
	public static final int CONSTRUCTOR = 1;
	public static final int MENU        = 2;
	public static final int INSIDE_BUILDING = 3;
	
	private static int state = CONSTRUCTOR;
	
	public static int getState() {
		return state;
	}
	public static void setState(int state) {
		 GameState.state = state;
	}
}

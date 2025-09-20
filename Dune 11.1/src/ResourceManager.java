
public class ResourceManager {

	private static int gold = 500;
	
	public static int getGold() {
		return gold;
	}
	
	public static int increaseGold(int value) {
		gold = gold + value;
		return gold;
	}
	
	
	
	
	public static boolean decreaseGold(int value) {
		int t = gold - value;
		if (t < 0) {
			return false;
		}
		else {
			gold -= value; 
			return true;
		}
	}
	
}

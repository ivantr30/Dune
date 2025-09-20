import java.awt.Graphics;

public class ResourceManager {
	private static int gold = 500;
	public static int getGold() {
		return gold;
	}
	public static int increaseGold(int val) {
		gold+= val;
		return gold;
	}
	
	public static boolean decreaseGold(int val) {
		if(val<= gold) {
			gold-= val;
			return true;
		}
		return false;
	}
}

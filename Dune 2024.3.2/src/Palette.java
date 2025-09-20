
public class Palette {
	private static int[] palette = new int[] {Block.GROUND,Block.WALL,Block.WATER,Block.GOLD,Block.FACTORY,Block.SUPPLY};
	private static int currentIndex = 0;
	
	public static void setCurrentIndex(int index) {
		if(index<palette.length|| index>-1) {
			currentIndex = index;
		}
		else {
			return;
		}
	}
	
	public static int getCurrent() {
		return palette[currentIndex];
	}
}



public class Palette {

	private static int[] palette = new int[] {
			Block.GROUND,
			Block.WALL, 
			Block.WATER,
			Block.GOLD
			};
	private static int currentIndex = 0;
	
	public static void setCurrentIndex(int index) {
		if (index < 0 || index >= palette.length ) {
			return;
		}
		currentIndex = index;
	}
	
	public static int getCurrent() {
		return palette[currentIndex];
	}

}

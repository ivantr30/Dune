import java.awt.Color;
import java.awt.Graphics;

public class Gold extends Block {

	
	
	public Gold() {
		super(GOLD);
		passable = true;
		breakable = false;
		picId = 11;
		value = (int)(Math.random() * 80) + 20;
	}
	
	@Override
	public int takeResource(int value) {
		if (value <= 0) {
			return 0;
		}
		int value2 = Math.min(value, this.value);
		this.value -= value2;
		
		return value2;
	}
	

	
}

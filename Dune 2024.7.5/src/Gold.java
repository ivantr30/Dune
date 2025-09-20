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
	

	
}

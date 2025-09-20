import java.awt.Color;
import java.awt.Graphics;

public class Water extends Block{

	public Water() {
		super(WATER);
		breakable = false;
		hp = 100;
		passable =true;
		active = true;
		picId = 5;
	}
	

	@Override
	public void update(int ms) {
	
		
	}

}
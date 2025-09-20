import java.awt.Color;
import java.awt.Graphics;

public class Concrete extends Block{
	
	
	public Concrete() {
		super(WALL);
		passable = false;
		breakable = false;
		picId = 9;
	}
	
}

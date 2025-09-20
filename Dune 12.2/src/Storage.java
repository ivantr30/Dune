import java.awt.Graphics;

public class Storage extends Block {

	public Storage() {
		super(STORAGE);
		picId = 15;
		passable = true;
		breakable = false;
		
	}
	
	@Override
	public void paint(Graphics g, int screenX, int screenY) {
		picId = 4;
		super.paint(g, screenX, screenY);
		picId = 15;
		super.paint(g, screenX, screenY);
	}

}

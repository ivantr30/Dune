import java.awt.Graphics;

public class Supply extends Block {

	public Supply() {
		super(SUPPLY);
		breakable = true;
		passable = true;
		picId = 15;
	}
	@Override
	public void paint(Graphics g, int screenX, int screenY) {
		picId = 4;
		super.paint(g, screenX, screenY);
		picId = 15;
		super.paint(g, screenX, screenY);
	}

}

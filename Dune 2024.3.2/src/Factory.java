import java.awt.Graphics;

public class Factory extends Block {
	
	int unitCount = 5;

	public Factory() {
		super(FACTORY);
		breakable = true;
		passable = false;
		picId = 14;
	}
	@Override
	public void paint(Graphics g, int screenX, int screenY) {
		picId = 4;
		super.paint(g, screenX, screenY);
		picId = 14;
		super.paint(g, screenX, screenY);
	}
}

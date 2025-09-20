import java.awt.Graphics;

public class Factory extends Block {

	
	int unitCount = 5;
	
	
	public Factory() {
		super(FACTORY);
		picId = 14;
		breakable = true;
		
	}
	
	@Override
	public void paint(Graphics g, int screenX, int screenY) {
		picId = 4;
		super.paint(g, screenX, screenY);
		picId = 14;
		super.paint(g, screenX, screenY);
	}

}

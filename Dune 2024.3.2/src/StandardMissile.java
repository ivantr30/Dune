
public class StandardMissile extends Missile{

	public StandardMissile(Unit owner) {
		super(owner.getX(), owner.getY(), Block.SIZE*3, 12,owner);
		
		if(owner.getAlpha() == 3*Math.PI/2) {
			main.up();
		}
		else if(owner.getAlpha() == Math.PI/2) {
			main.down();
		}
		else if(owner.getAlpha() == 0) {
			main.right();	
		}
		else if(owner.getAlpha() == Math.PI) {
			main.left();
		}
	}

}

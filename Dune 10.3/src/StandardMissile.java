
public class StandardMissile extends Missile  {

	public StandardMissile(Unit owner) {
		super(owner, owner.getX(), owner.getY(), Block.SIZE * 3, 12);
		
	}

}

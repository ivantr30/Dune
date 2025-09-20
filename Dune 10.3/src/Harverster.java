
public class Harverster extends Unit {

	public Harverster(int x, int y) {
		super(x, y, Block.SIZE, 0);
		main.addFrame(1);
		main.addFrame(2);
		main.addFrame(3);

	
	}
	
	@Override
	protected void onReachedCurrentNode() {
		super.onReachedCurrentNode();
		
	}
	
	@Override
	protected void cantReachTarget() {
		super.cantReachTarget();
		
		
	}

	
}

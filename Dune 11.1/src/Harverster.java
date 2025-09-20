
public class Harverster extends Unit {
	
	private int goldCount = 90;
	
	private int currentTime = 0;
	private int timePerPiceOfGold = 1000;

	public Harverster(int x, int y) {
		super(x, y, Block.SIZE, 0);
		main.addFrame(1);
		main.addFrame(2);
		main.addFrame(3);
		
		// goldCount = (int)(Math.random() * 101);

	
	}
	
	private void onEmptyGoldBlock() {
		// Когда золото добыто
		super.onReachedControlNode();
		findNewTarget();
	}
	
	private void onStorageIsFull() {
		super.onReachedControlNode();
		findNewTarget();
	}
	
	private void onMining() {
		if (goldCount >= 100) {
			onStorageIsFull();
			return;
		}
		if (currentTime >= timePerPiceOfGold) {
			currentTime = 0;
			Node n = getCurrentNode();
			int a = Map.getBlock(n.y, n.x).takeResource(1);
			if (a == 0) {
				onEmptyGoldBlock();
			}
			else {
				goldCount += a;
				ResourceManager.increaseGold(a);
			}
			
		}
		stop();
	}
	
	@Override
	public void update(int ms) {
		super.update(ms);
		currentTime += ms;
	}
	
	
	@Override
	protected void onReachedControlNode() {
		Node n = getCurrentNode();
		if (Map.getBlock(n.y, n.x).getType() == Block.GOLD) {
			onMining();
		}
		else {
			super.onReachedControlNode();
		}
	}
	
	
	
	@Override
	protected void onReachedCurrentNode() {
		super.onReachedCurrentNode();
		
	}
	
	@Override
	protected void cantReachTarget() {
		super.cantReachTarget();
		
		
	}

	@Override
	public String getTextForStatusBar() {
		return "Комбаин/Золото: "+ goldCount;
	}

	
}


public class Harvester extends Unit {
	
	private int goldCount = 90;
	
	private int currentTime = 0;
	private int timePerPieceOfGold = 1000;
	
	private boolean firstTime = true;

	public Harvester(int x, int y) {
		super(x, y, Block.SIZE, 0);
		main.addFrame(1);
		main.addFrame(2);
		main.addFrame(3);
		
		// goldCount = (int)(Math.random() * 101);

	}
	
	private void goToStorage() {
		if (points.nextNodeByType(Block.STORAGE)) {
			findNewTarget();
			StatusBar.show("Поехал на базу", 2000);
		}
		else {
			// Не смог найти базу, куда выгружать
			StatusBar.show("Укажите ячейку базы", 2000);
		}
	}
	
	private void goToGold() {
		if (points.nextNodeByType(Block.GOLD)) {
			findNewTarget();
			StatusBar.show("Поехал на золото", 2000);
		}
		else {
			// Не смог найти шахту
			StatusBar.show("Укажите ячейку шахты", 2000);
		}
	}
	
	
	private void onEmptyGoldBlock() {
		// Когда золото добыто
		super.onReachedControlNode();
		findNewTarget();
	}
	
	private void onStorageIsFull() {
		goToStorage();	
	}
	
	private void onMining() {
		if (goldCount >= 100) {
			onStorageIsFull();
			return;
		}
		if (currentTime >= timePerPieceOfGold) {
			currentTime = 0;
			Node n = getCurrentNode();
			int a = Map.getBlock(n.y, n.x).takeResource(1);
			if (a == 0) {
				onEmptyGoldBlock();
			}
			else {
				goldCount += a;
				// ResourceManager.increaseGold(a);
			}
			
		}
		stop();
	}
	
	@Override
	public void update(int ms) {
		super.update(ms);
		currentTime += ms;
	}
	
	
	private void onEnterOnStorage() {
		ResourceManager.increaseGold(goldCount);
		StatusBar.show("Пополнение на "+ goldCount + " золота", 2000);
		goldCount = 0;
		stop();
		goToGold();
	}
	
	private void onStayOnStorage() {
	
	}
	
	@Override
	protected void onReachedControlNode() {
		Node n = getCurrentNode();
		if (Map.getBlock(n.y, n.x).getType() == Block.GOLD) {
			onMining();
		}
		else if (Map.getBlock(n.y, n.x).getType() == Block.STORAGE) {
			if (firstTime) {
				onEnterOnStorage();
				firstTime = false;
			}
			onStayOnStorage();
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

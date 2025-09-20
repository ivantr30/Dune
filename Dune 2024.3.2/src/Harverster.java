
public class Harverster extends Unit{

	private int goldCount = 90;
	private int currentTime = 0;
	private int timePerPieceOfGold = 1000;
	private boolean firstTime = true;
	
	public Harverster(int x, int y) {
		super(x, y, Block.SIZE, 0);
		main.addFrame(1);
		main.addFrame(2);
		main.addFrame(3);
		
	}
	@Override
	protected void cantReachTheTarget() {
		super.cantReachTheTarget();
	}
	@Override
	protected void onReachedCurrentNode() {
		super.onReachedCurrentNode();
	}
	@Override
	public String getTextForStatusBar() {
		return "Комбайн. Золото: " + goldCount;
	}
	@Override
	public void update(int ms) {
		super.update(ms);
		currentTime+=ms;
	}
	private void onMining() {
		if(goldCount >=100) {
			onStorageIsFull();
			return;
		}
		if (currentTime >= timePerPieceOfGold) {
			currentTime = 0;
			System.out.println("Майнинг");
			Node n = getCurrentNode();
			int value =Map.getBlock(n.y, n.x).takeResourse(1);
			if(value==0) {
				onEmptyGoldBlock();
			}
			else {
				goldCount+=value;
				//ResourceManager.increaseGold(value);
			}
			
		}
		stop();
	}
	
	private void onStayOnStorage() {
		
	}
	
	private void onEnterOnStorage() {
		ResourceManager.increaseGold(goldCount);
		StatusBar.show("Пополнение на "+goldCount+" золота",2000);
		goldCount = 0;
		stop();
		goToGold();
	}
	
	private void goToStorage() {
		if(points.nextNodeByType(Block.SUPPLY)) {
			findNewTarget();
			StatusBar.show("Поехал до базы",2000);
		}
		else {
			StatusBar.show("Укажите ячейку базы",2000);
		}
	}
	private void goToGold() {
		if(points.nextNodeByType(Block.GOLD)) {
			findNewTarget();
			StatusBar.show("Поехал на золото",2000);
		}
		else {
			StatusBar.show("Укажите ячейку шахты",2000);
		}
	}
	private void onStorageIsFull() {
		goToStorage();
	}
	private void onEmptyGoldBlock() {
		super.onReachedControlNode();
		findNewTarget();
	}
	@Override
	public void onReachedControlNode() {
		Node n = getCurrentNode();
		if(Map.getBlock(n.y, n.x).getType()==Block.GOLD) {
			onMining();
		}
		else if(Map.getBlock(n.y, n.x).getType()==Block.SUPPLY) {
			if(firstTime) {
				onEnterOnStorage();
				firstTime = false;
			}
			onStayOnStorage();
		}
		else {
			super.onReachedControlNode();
		}
	}
}


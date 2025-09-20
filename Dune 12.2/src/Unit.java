import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public abstract class Unit  implements PaintUpdatable{
	
	private int size = Block.SIZE;
	protected Sprite main;
	protected boolean bot = true;
	protected boolean selected = false;

	protected AStarSolver solver = new AStarSolver(); 
	protected ArrayList<Node> path;
	protected Node currentTarget;
	protected ControlPoints points;
	
	
	public abstract String getTextForStatusBar();
	
	public void clearControlPoints() {
		points.clear();
	}
	
	public void showOnStatusBar() {
		StatusBar.show(getTextForStatusBar(), 2000);
	}
	
	public void addControlPoint(int row, int col) {
		points.add(row, col);
	}
	
	public void setStrategy(StrategyType type) {
		points.setStrategy(type);
	}

	public double getGunAlpha() {
		return getAlpha();
	}
	
	public Point getGunEdge() {
		return new Point((int)getX(), (int)getY());
	}
	
	public boolean isDestroyed() {
		return false;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public double getAlpha() {
		return main.getAlpha();
	}
	
	public Unit(int x, int y, double speed, int mainPicId) {
		main = new Sprite(x, y, speed, mainPicId);
		if (bot) {
			
			/*points = new ControlPoints(
					(int)(Math.random() * 50),
					(int)(Math.random() * 50));*/
			
			points = new ControlPoints(
					3,
					5);
						
			findNewTarget();
		}
		
		Color randColor = new Color(new Random().nextInt(256),
				new Random().nextInt(256),
				new Random().nextInt(256), 140);
		
		points.setColor(randColor);
		
		
	}
	
	protected Node getCurrentNode() {
		int col = Map.getCol(getCenterX());
		int row = Map.getRow(getCenterY());
		
		return new Node(col, row, null, 0, 0);
	}
	
	protected void findNewTarget() {
		
		path = solver.solve( getCurrentNode(), points.getCurrentFinish());
		
		
		
		if (path.size() > 1) {  		
			currentTarget = path.get(1);
		}
		else {
			if (path.size() == 0) {
				System.out.println("Путь не найден");
				System.out.println(getCurrentNode() + " " + points.getCurrentFinish());
				
				cantReachTarget();
			}
			
		}
	}
	
	public void setSpeed(double speed) {
		main.setSpeed(speed);
	}
	
	public void stop() {
		main.stop();
	}
	

	public void up() {
		main.up();
	}
	public void down() {
		main.down();
	}
	public void right() {
		main.right();
	}
	public void left() {
		main.left();
	}

	public int getSize() {
		return size;
	}
	
	public double getX() {
		return main.getX();
	}
	public double getY() {
		return main.getY();
	}
	
	public double getCenterX() {
		return getX() + size/2;
	}
	public double getCenterY() {
		return getY() + size/2;
	}
	
	public void gotoNextNode() {
		points.nextNode();
		findNewTarget();
	}
	
	protected void cantReachTarget () {
		stop();
		points.nextNode();
	}
	
	protected void onReachedControlNode() {
		stop();
		points.nextNode();
	}
	
	protected void onReachedCurrentNode() {
		nextPathNode();
	}
	
	private void nextPathNode() {
		int index = path.indexOf(currentTarget);
		if (index + 1 < path.size()) {
			currentTarget = path.get(index + 1);
			if (!Map.getBlock(currentTarget.y, currentTarget.x).isPassable()) {
				findNewTarget();
			}
		}
		else {
			findNewTarget();
		}
		
	}

	protected void aiFollowToPath() {
		// Идти к ближайшей клетке
		
		if ( reachedCurrentTargetNode() ) {
			if (currentTarget.equals(points.getCurrentFinish())) {
				onReachedControlNode();
			}
			
			onReachedCurrentNode();
			
		
		}
		else {
			int centerTargetX = currentTarget.x * Block.SIZE + Block.SIZE / 2;
			int centerTargetY = currentTarget.y * Block.SIZE + Block.SIZE / 2;
			
			if (centerTargetX - getCenterX() >= 1) {
				right();
			}
			if (getCenterX() - centerTargetX  >= 1) {
				left();
			}
			
			if (centerTargetY - getCenterY() >= 1) {
				down();
			}
			if (getCenterY() - centerTargetY  >= 1) {
				up();
			}
			
			
		}
		
	}
	
	// Достигли целевой ячейки
	public boolean reachedCurrentTargetNode() {
		int centerTargetX = currentTarget.x * Block.SIZE + Block.SIZE / 2;
		int centerTargetY = currentTarget.y * Block.SIZE + Block.SIZE / 2;
		
		double d = Point.distance(getCenterX(), getCenterY(), centerTargetX, centerTargetY);
		
		return d < 1;
		
		
	}
	
	
	public void update(int ms) {
		main.update(ms);
		if (bot) {
			aiFollowToPath();
		}
			
	}
		

	public void paint(Graphics g) {
		
		if (path != null) {
			g.setColor(new Color(192, 192, 192, 128));
			for (Node n : path) {
				g.fillRect(
						Camera.getScreenX(n.x * Block.SIZE),
						Camera.getScreenY(n.y * Block.SIZE),
						Block.SIZE,
						Block.SIZE);
			}
		}
		
		
		if (currentTarget != null) {
			g.setColor(new Color(0, 255, 0, 50));
			g.fillRect(
			Camera.getScreenX(currentTarget.x * Block.SIZE),
			Camera.getScreenY(currentTarget.y * Block.SIZE),
			Block.SIZE,
			Block.SIZE);
			
			g.setColor(new Color(255, 0, 0, 50));
			g.fillRect(
			Camera.getScreenX(getCurrentNode().x * Block.SIZE),
			Camera.getScreenY(getCurrentNode().y * Block.SIZE),
			Block.SIZE,
			Block.SIZE);
		}
		
		main.paint(g);
		
		
		
		
		if (selected) {
			Graphics2D g2 = (Graphics2D) g;
			
			
			g2.setColor(points.getColor());
			g2.setStroke(new BasicStroke(3));
			
			g.drawRect(Camera.getScreenX( main.getX()),
					Camera.getScreenY( main.getY()),
					Block.SIZE, Block.SIZE);
			
			points.paint(g);
		}
		
		
		
	}


	
}

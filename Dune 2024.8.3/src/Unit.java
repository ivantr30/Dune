import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Unit  implements PaintUpdatable{
	
	private int size = Block.SIZE;
	protected Sprite main;
	protected boolean bot = true;
	
	
	protected AStarSolver solver = new AStarSolver(); 
	protected ArrayList<Node> path;
	protected Node currentTarget;
	protected ControlPoints points;
	
	
	public double getGunAlpha() {
		return getAlpha();
	}
	
	public Point getGunEdge() {
		return new Point((int)getX(), (int)getY());
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
					10);
			points.add(3, 30);
			points.add(20, 30);
			points.add(20, 10);
			
			findNewTarget();
		}
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
	
	protected void cantReachTarget () {
		stop();
		points.nextNode();
	}
	
	protected void onReachedControlNode() {
		System.out.println("Достигли целевой ячейки");
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
		
		
	}


	
}

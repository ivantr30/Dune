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
			points.add(10, 10);
			points.add(10, 2);
			points.add(2, 3);
			
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
			// Разделить на два случая - когда пути нет и когда мы достигли целевой точки
			onReachedFinishNode();
			points.nextNode();
			findNewTarget();
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
	
	protected void cantReacheTarget () {
		
	}
	
	protected void onReachedFinishNode() {
		
		
	}
	
	protected void onReachedNode() {
		
	}
	
	protected void aiFollowToPath() {
		// Идти к ближайшей клетке
		
		if ( reachedTargetNode() ) {
			onReachedNode();
			
			/*if (currentTarget == points.getCurrentFinish()) {
				onReachedFinishNode();
				points.nextNode();
				
			}*/
			findNewTarget();
		}
		else {
			// Сближаться с целью
			if (getCurrentNode().x < currentTarget.x) {
				right();
			}
			else if (getCurrentNode().x > currentTarget.x) {
				left();
			}
			else if (getCurrentNode().y > currentTarget.y) {
				up();
			}
			else if (getCurrentNode().y < currentTarget.y) {
				down();
			}
			
		}
		
	}
	
	// Достигли целевой ячейки
	public boolean reachedTargetNode() {
		int centerTargetX = currentTarget.x * Block.SIZE + Block.SIZE / 2;
		int centerTargetY = currentTarget.y * Block.SIZE + Block.SIZE / 2;
		
		double d = Point.distance(getCenterX(), getCenterY(), centerTargetX, centerTargetY);
		
		return d < 2;
		
		
	}
	
	
	public void update(int ms) {
		main.update(ms);
		if (bot) {
			aiFollowToPath();
		}
			
	}
		

	public void paint(Graphics g) {
		/*
		if (path != null) {
			g.setColor(Color.LIGHT_GRAY);
			for (Node n : path) {
				g.fillRect(
						Camera.getScreenX(n.x * Block.SIZE),
						Camera.getScreenY(n.y * Block.SIZE),
						Block.SIZE,
						Block.SIZE);
			}
		}*/
		if (currentTarget != null) {
			g.setColor(Color.GREEN);
			g.fillRect(
			Camera.getScreenX(currentTarget.x * Block.SIZE),
			Camera.getScreenY(currentTarget.y * Block.SIZE),
			Block.SIZE,
			Block.SIZE);
		}
		
		main.paint(g);
		
		
	}


	
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class ControlPoints implements PaintUpdatable  {

	private List<Node> list = new ArrayList<Node>();
	private StrategyType type = StrategyType.CIRCLE;
	private int currentIndex = -1;
	private Color color;
	
	public ControlPoints() {
		
	}
	
	public ControlPoints(int row, int col) {
		add(row, col);
	}
	
	public void clear() {
		
		List<Node> list2 =  new ArrayList<Node>();
		list2.add(list.get(list.size()-1));
		 
		list = list2;
		currentIndex = 0;
	}

	
	
	public void setStrategy(StrategyType type) {
		this.type = type;
	}
	
	public void add(int row, int col) {
		list.add( new Node(col, row, null, 0, 0) );
		if (currentIndex == -1) {
			currentIndex = 0;
		}
	}
	
	public void nextNode() {
		if (type == StrategyType.CIRCLE) {
			currentIndex ++;
			if (currentIndex >= list.size()) {
				currentIndex = 0;
			}
		}
		else if (type == StrategyType.LAST) {
			currentIndex = list.size() - 1;
		}
	}
	
	public Node getCurrentFinish() {
		if (currentIndex == -1) {
			return null;
		}
		else {
			return list.get( currentIndex );
		}
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public Color getColor() {
		return color;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(color);
		
		if (type == StrategyType.LAST) {
			Node last = list.get(list.size()-1);
			g.drawRect(
					Camera.getScreenX(last.x * Block.SIZE), 
					Camera.getScreenY(last.y * Block.SIZE), 
					Block.SIZE, Block.SIZE);
			return;
		}
		
		
		
		for (Node n : list) {
			g.drawRect(
					Camera.getScreenX(n.x * Block.SIZE), 
					Camera.getScreenY(n.y * Block.SIZE), 
					Block.SIZE, Block.SIZE);
		}
		
		int iRight, iLeft;
		Node left, right;
		for (iLeft = 0; iLeft <= list.size()-2; iLeft++) {
			iRight = iLeft+1;
			left = list.get(iLeft);
			right = list.get(iRight);
			
			g.drawLine(Camera.getScreenX(left.x * Block.SIZE + Block.SIZE/2),Camera.getScreenY(left.y * Block.SIZE + Block.SIZE/2) ,
					Camera.getScreenX(right.x * Block.SIZE + Block.SIZE/2),Camera.getScreenY(right.y * Block.SIZE + Block.SIZE/2) );
			
		}
		if (type == StrategyType.CIRCLE) {
			left = list.get(0);
			right = list.get(list.size()-1);
			g.drawLine(Camera.getScreenX(left.x * Block.SIZE + Block.SIZE/2),Camera.getScreenY(left.y * Block.SIZE + Block.SIZE/2) ,
					Camera.getScreenX(right.x * Block.SIZE + Block.SIZE/2),Camera.getScreenY(right.y * Block.SIZE + Block.SIZE/2) );
		}
		
		
	}

	@Override
	public void update(int ms) {
		
	}

}

enum StrategyType {
	CIRCLE,
	SHUTTLE, 
	LAST
}

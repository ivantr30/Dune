import java.util.ArrayList;
import java.util.List;

public class ControlPoints {

	private List<Node> list = new ArrayList<Node>();
	private StrategyType type = StrategyType.CIRCLE;
	private int currentIndex = -1;
	
	
	public ControlPoints() {
		
	}
	
	public ControlPoints(int row, int col) {
		add(row, col);
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
	}
	
	public Node getCurrentFinish() {
		if (currentIndex == -1) {
			return null;
		}
		else {
			return list.get( currentIndex );
		}
	}

}

enum StrategyType {
	CIRCLE,
	SHUTTLE	
}

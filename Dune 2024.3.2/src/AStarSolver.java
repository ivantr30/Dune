import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

class Node  implements Comparable<Node>  {
	
	int x, y;
	Node parent;
	
	int a; // ���������� ������ �� ��������� ������
	int b; // ����������������� ���������� �� ������
	
	int priority;

	public Node(int x, int y, Node parent, int a, int b) {
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.a = a;
		this.b = b;
		priority = a + b;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + "]";
	}


	@Override
	public int compareTo(Node o) {
		
		return priority - o.priority;
	}
	
}


public class AStarSolver {
	
	
	public ArrayList<Node> solve(Node start, Node finish) {
		
		int [] dx = {-1, 0, 1, 0};
		int [] dy = {0, -1, 0, 1};
		
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		HashSet<Node> blackList = new HashSet<Node>();
		
		queue.add(start);
		
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			blackList.add(current);
			
			// ����� �� ������
			if(finish == null) {
				return new ArrayList<Node>();
			}
			if (current.x == finish.x && current.y == finish.y) {
				// ������������ ���� �� ������
				
				ArrayList<Node> path = new ArrayList<Node>();
				
				while (current != null) {
					path.add(current);
					current = current.parent;
				}
				
				Collections.reverse(path);
				return path;
				
			}
			else {
				// ������� �������
				int x = current.x;
				int y = current.y;
				
				for (int i = 0; i < 4; i++) {
					int new_x = x + dx[i];
					int new_y = y + dy[i];
					
					if (new_x < 0 || new_y < 0 || new_x >= Map.getColsNumber() || new_y >= Map.getRowsNumber()) {
						continue;
					}
					
					int b = Math.abs(finish.x - new_x) + Math.abs(finish.y - new_y);
					
					Node child = new Node(
							new_x, 
							new_y, 
							current, 
							current.a + 1, 
							b);
					
					if (blackList.contains(child)) {
						continue;
					}
					
					if (!Map.getBlock(new_y, new_x).isPassable()) {
						continue;
					}
					if (!Map.getBlock(y, x).isPassable()) {
						continue;
					}
					if(queue.size()>1000) {
						System.out.println("Слишком много блоков");
						return new ArrayList<Node>();
					}
					
					queue.add(child);					
					
						
				}
				
			
			}
		
		}
		
		return new ArrayList<Node>();	
		
	}

}









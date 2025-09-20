import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

class Node implements Comparable<Node>{
	int x,y;
	Node parent;
	int a; // колво клеток от первой
	int b; // манхеттенское расстояние до финиша
	int priority;
	public Node(int x, int y,Node parent, int a, int b) {
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.a = a;
		this.b = b;
		priority = a+b;
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
	public int compareTo(Node o) {
		return priority - o.priority;
	}
}



public class AStarSolver {
	
	public ArrayList<Node> solve(Node start, Node finish) {
		int[] dx = {-1,0,1,0};
		int[] dy = {0,-1,0,1};
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		HashSet<Node> blackList = new HashSet<Node>();
		
		queue.add(start);
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			blackList.add(current);
			if(current.x == finish.x&&current.y == finish.y) {
				ArrayList<Node> path = new ArrayList<Node>();
				while(current != null) {
					path.add(current);
					current = current.parent;
				}
				Collections.reverse(path);
				System.out.println("Путь найден");
				return path;
			}
			else {
				int x = current.x;
				int y = current.y;
				for(int i = 0; i < 4;i++) {
					int newX = x + dx[i];
					int newY = y + dy[i];
					if(newX < 0 || newY < 0 || newX >= Map.getCols() || newY >= Map.getRows()) {
						continue;
					}
					int b = Math.abs(finish.x - newX)+Math.abs(finish.y - newY);
					Node child = new Node(newX,newY,current,current.a+1,b);
					if(blackList.contains(child)) {
						continue;
					}
					if(!Map.getBlock(x, y).isPassable()) {
						continue;
					}
					queue.add(child);
				}
			}
		}
		return new ArrayList<Node>();
	}
	
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Map {

	private static Block[][] map;
	
	
	private static final Color selected = new Color(76, 255, 0, 50);
	
	static {
		generate(100, 100);
	}
	
	public static int getRowsNumber() {
		return map.length;
	}
	
	public static int getColsNumber() {
		return map[0].length;
	}
	
	
	public static Block setSelectedCell(int row, int col) {
		if (row < 0 || col < 0 || row >= map.length || col >= map[0].length ) {
			return null;
		}
		
		return getBlock(row, col);
		
		
	}
	
	public static Block getBlock(int row, int col) {
		
		if (row < 0 || col < 0 || row >= map.length || col >= map[0].length ) {
			return Block.getEmpty();
		}
		
		return map[row][col];
	}
	
	public static int getCol (double x) {
		return (int)x / Block.SIZE;
	}
	
	public static int getRow (double y) {
		return (int) y / Block.SIZE; 
	}
	
	public static int getWidth() {
		return map[0].length * Block.SIZE;
	}
	public static int getHeight() {
		return map.length * Block.SIZE;
	}
	
	public static void paint(Graphics g) {
		
		int firstRow, firstCol, lastRow, lastCol;
		
		firstRow = getRow(Camera.getY());
		firstCol = getCol(Camera.getX());
		lastRow  = getRow(Camera.getBottom());
		lastCol  = getCol(Camera.getRight());
		
		for (int row = firstRow; row <= lastRow; row++) {
			for (int col = firstCol; col <= lastCol; col++) {
				map[row][col].update(10);
				map[row][col].paint(g,
						Camera.getScreenX(col * Block.SIZE),
						Camera.getScreenY(row * Block.SIZE));
				
				
				if (ContextManager.isNodeActive() && 
					ContextManager.getCurrentNode().y == row &&
					ContextManager.getCurrentNode().x == col
					) {
					g.setColor(selected);
					g.fillRect(
							Camera.getScreenX(col * Block.SIZE),
							Camera.getScreenY(row * Block.SIZE), 
							Block.SIZE, Block.SIZE);
				}
				
			}
		}
	}
	
	

	
	public static void setBlock(int row, int col, Block b) {
		if (row < 0 || col < 0 || row >= map.length || col >= map[0].length ) {
			return;
		}
		
		
		map[row][col] = b;
		
			
		
	}
	
	public static void generate(int rows, int cols) {
		map = new Block[rows][cols];
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col ++) {
				if (row == 0 || col == 0 || row == rows-1 || col == cols-1) {
					map[row][col] = new Concrete();
				}
				else {
					map[row][col] = new Grass(); 
				}
			}
		}
	}
	
}

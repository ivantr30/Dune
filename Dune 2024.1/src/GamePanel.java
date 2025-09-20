import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener, MouseListener,MouseMotionListener {

	
	Character player;
	
	private long t2;
	
	Node start = new Node(2,2,null,0,0);
	Node finish = new Node(10,5,null,0,0);
	
	ArrayList<Node> path = new ArrayList<Node>();
	
	public void findPath() {
		AStarSolver solver = new AStarSolver();
		path = solver.solve(start, finish);
	}
	
	public GamePanel() {
		
		t2 = System.currentTimeMillis();
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
	}
	
	private void controlConstructorState() {
		Point mousePos = Mouse.getMouseCoordinate();
		if(Mouse.isMouseDown(MouseEvent.BUTTON1)) {
			int col = Map.getCol(Camera.getGlobalX(mousePos.x));
			int row = Map.getRow(Camera.getGlobalY(mousePos.y));
			Map.setBlock(row,col, new Brick());
		}
	}
	
	private void control() {
		Point mousePos = Mouse.getMouseCoordinate();
		if (Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)) {
			System.exit(0);
		}
		
		if(GameState.getState() == GameState.CONSTRUCTOR) {
			controlConstructorState();
		}
		else if(GameState.getState() == GameState.GAME) {
		
		}
		if(mousePos.x >= getWidth()-200) {
			Camera.move(1, 0);
		}
		if(mousePos.x <= 200) {
			Camera.move(-1, 0);
		}
		if(mousePos.y >= getHeight()-200) {
			Camera.move(0, 1);
		}
		if(mousePos.y <= 200) {
			Camera.move(0, -1);
		}
		
	}
	
	
	private void update(int ms) {
		Camera.setWidth( getWidth() );
		Camera.setHeight( getHeight() );
		
	}
	
	private void paintGame(Graphics g) {
		Map.paint(g);
		for(Node node : path) {
			g.setColor(Color.GREEN);
			g.fillRect(Camera.getScreenX(node.x*Block.SIZE), Camera.getScreenY(node.y*Block.SIZE), Block.SIZE, Block.SIZE);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int ms = 10;
		
		long t1 = System.currentTimeMillis();
		ms = (int)(t1 - t2);
		control();
		update(ms);
		paintGame(g);
		t2 = t1; 
		repaint();		
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		Keyboard.registerKeyDown(e.getKeyCode());
		if(e.getKeyCode() == e.VK_SPACE) {
			findPath();
		}
		

		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Keyboard.registerKeyUp(e.getKeyCode());
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		Mouse.registerMouseButton(e.getButton(), true);
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		Mouse.registerMouseButton(e.getButton(), false);
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		Mouse.registerMouseCoordinate(e.getX(), e.getY());
		
	}
}

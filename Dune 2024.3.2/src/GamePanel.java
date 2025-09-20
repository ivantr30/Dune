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

public class GamePanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener  {
	
	private long t2;
	
	Node start =  new Node(2, 2, null, 0, 0);
	Node finish = new Node(10, 6, null, 0, 0);
	
	ArrayList<Node> path = new ArrayList<Node>();
	
	
	
	public void findPath() {
		AStarSolver solver = new AStarSolver();
		path = solver.solve(start, finish);
	}
	
	
	
	public GamePanel() {
		UnitCollection.get().add(new Harverster(2 * Block.SIZE, 3 * Block.SIZE));
		UnitCollection.get().add(new Harverster(5 * Block.SIZE, 1 * Block.SIZE));
		UnitCollection.get().add(new Harverster(25 * Block.SIZE, 10 * Block.SIZE));
		t2 = System.currentTimeMillis();
		
	}
	
	
	private void controlConstuctorState() {
		
		Point mousePos = Mouse.getMouseCoordinate();
		
		if(Keyboard.isKeyDown(KeyEvent.VK_1)){
			Palette.setCurrentIndex(Block.GROUND);
		}
		else if(Keyboard.isKeyDown(KeyEvent.VK_2)){
			Palette.setCurrentIndex(Block.WALL);
		}
		else if(Keyboard.isKeyDown(KeyEvent.VK_3)){
			Palette.setCurrentIndex(Block.WATER);
		}
		else if(Keyboard.isKeyDown(KeyEvent.VK_4)){
			Palette.setCurrentIndex(Block.GOLD);
		}
		else if(Keyboard.isKeyDown(KeyEvent.VK_5)){
			Palette.setCurrentIndex(Block.FACTORY);
		}
		else if(Keyboard.isKeyDown(KeyEvent.VK_6)){
			Palette.setCurrentIndex(Block.SUPPLY);
		}
		
		if (Mouse.wasButtonPressing(MouseEvent.BUTTON1)) {
			
			int col = Map.getCol( Camera.getWorldX( mousePos.x ) );
			int row = Map.getRow( Camera.getWorldY( mousePos.y ) );
			if (Map.getBlock(row, col).getType() != Block.GROUND) {
				return;
			}
			if(Palette.getCurrent()==Block.FACTORY) {
				if(ResourceManager.getGold()>=70) {
					ResourceManager.decreaseGold(70);
					Map.setBlock(row, col, Block.getBlock(Block.FACTORY));
				}
			}
			else if(Palette.getCurrent()==Block.SUPPLY) {
				if(ResourceManager.getGold()>=50) {
					ResourceManager.decreaseGold(50);
					Map.setBlock(row, col, Block.getBlock(Block.SUPPLY));
				}
			}
			else {
				Map.setBlock(row, col, Block.getBlock(Palette.getCurrent()));
			}

		}
	}
	
	
	private void control() {
		
		Point mousePos = Mouse.getMouseCoordinate();
		ContextManager.control();
	
		if (Keyboard.wasKeyPressing(KeyEvent.VK_SPACE)) {
			MissileCollection.get().add(new StandardMissile(ContextManager.getCurrentUnit()));
		}
		if (Keyboard.wasKeyPressing(KeyEvent.VK_Q)) {
			StatusBar.toggle();
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_7)) {
			GameState.setState(GameState.CONSTRUCTOR);
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_8)) {
			GameState.setState(GameState.GAME);
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_9)) {
			GameState.setState(GameState.INSIDE_BUILDING);
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_0)) {
			GameState.setState(GameState.MENU);
		}
		
		if (Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)) {
			System.exit(0);
		}
		
		if (GameState.getState() == GameState.CONSTRUCTOR) {
			controlConstuctorState();
		}
		else if (GameState.getState() == GameState.GAME && (Mouse.wasButtonPressing(MouseEvent.BUTTON2)||Mouse.wasButtonPressing(MouseEvent.BUTTON1))) {
			// �������� ������ �� �����
			Block b = Map.setSelectedCell(Mouse.getRow(), Mouse.getCol());
			if (b!= null) {
				if (b.type == Block.GOLD) {
					StatusBar.show("Золото: " + b.value);
				}
				else {
					StatusBar.hide();
				}
			}
		}
		
		
		
		// ������� �����
		if ( mousePos.x > getWidth() - 20 ) {
			Camera.move(1, 0);
		}
		if ( mousePos.y > getHeight() - 20 ) {
			Camera.move(0, 1);
		}
		
		if ( mousePos.x < 20 ) {
			Camera.move(-1, 0);
		}
		if ( mousePos.y < 20 ) {
			Camera.move(0, -1);
		}
		

		Keyboard.update();
		Mouse.update();
		
	}
	
	
	private void update(int ms) {
		Camera.setWidth( getWidth() );
		Camera.setHeight( getHeight() );
		StatusBar.update(ms);
		UnitCollection.get().update(ms);
		MissileCollection.get().update(ms);
	}
	
	private void paintGame(Graphics g) {
		if(!FactoryScreen.isActive()) {
			Map.paint(g);
			UnitCollection.get().paint(g);
			MissileCollection.get().paint(g);
			StatusBar.paint(g);
		}
		else {
			FactoryScreen.paint(g);
		}
		
//		for (Node n : path) {
//			g.setColor(Color.GREEN);
//			g.fillRect( Camera.getScreenX( n.x * Block.SIZE ) ,
//					    Camera.getScreenY( n.y * Block.SIZE ),
//					    Block.SIZE, Block.SIZE);
//		}
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
		Keyboard.setShift(e.isShiftDown());
		Keyboard.setControl(e.isControlDown());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Keyboard.registerKeyUp(e.getKeyCode());
		Keyboard.setShift(e.isShiftDown());
		Keyboard.setControl(e.isControlDown());
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
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

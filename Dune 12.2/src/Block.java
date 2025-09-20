import java.awt.Color;
import java.awt.Graphics;

 public abstract class Block {
	 
	public static final int EMPTY = -1;
	public static final int GROUND = 0;
	public static final int WALL = 1;
	public static final int WATER = 2;
	public static final int GOLD = 3;	
	
	public static final int FACTORY = 4;	
	public static final int STORAGE = 5;	
	
	protected int value = 100; 
	
	public static final int SIZE = 32;
	protected int type = GROUND;
	protected boolean passable = false;
	protected int hp = 100;
	protected boolean breakable = false;
	protected boolean active = true;
	protected int picId = 0;

	
	public static final Block EMPTY_BLOCK = new Block(EMPTY) {

	@Override
	public void paint(Graphics g, int screenX, int screenY) {
			
		}
	};
	
	public static Block getBlock(int type) {
		if (type == WALL) {
			return new Brick();
		}
		else if (type == GROUND) {
			return new Grass();
		}
		else if (type == WATER) {
			return new Water();
		}
		else if (type == GOLD) {
			return new Gold();
		}
		else if(type == Block.FACTORY) {
			return new Factory();
		}
		else if(type == Block.STORAGE) {
			return new Storage();
		}
		else {
			return getEmpty();
		}
	}
	

	public Block (int type) {
		this.type = type;
	}
	
	public static Block getEmpty() {
		return EMPTY_BLOCK;
	}
	
	public int getType() {
		return type;
	}


	public boolean isPassable() {
		return passable;
	}


	public int getHp() {
		return hp;
	}
	
	public int takeResource(int value) {
		return 0;
	}


	public boolean isBreakable() {
		return breakable;
	}


	public boolean isActive() {
		return active;
	}


	public void paint(Graphics g, int screenX, int screenY) {
		Texture.paint(screenX, screenY, picId, g);
		
	}
	
	
	
	public void destroy() {
		if (active) {
			active = false;
			onDestroy();
		}
	}
	
	protected void onDestroy() {
		
	}
	
	public void update(int ms) {
		
	}

	public boolean isEmpty() {
		return type == EMPTY;
	}
	
	
}

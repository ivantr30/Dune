import java.awt.Color;
import java.awt.Graphics;

 public abstract class Block {
	 
	public static final int EMPTY = -1;
	public static final int GROUND = 0;
	public static final int WALL = 1;
	public static final int WATER = 2;
	public static final int GOLD = 3;

	public static final int FACTORY = 4;
	public static final int SUPPLY = 5;
	
	public static final int SIZE = 32;
	protected int value = 100;
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
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
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
	
	public int takeResourse(int value) {
		return 0;
	}

	public boolean isPassable() {
		return passable;
	}


	public int getHp() {
		return hp;
	}


	public boolean isBreakable() {
		return breakable;
	}


	public boolean isActive() {
		return active;
	}
	
	public static Block getBlock(int type) {
		if(type == Block.WALL) {
			return new Brick();
		}
		else if(type == Block.WATER) {
			return new Water();
		}
		else if(type == Block.GOLD) {
			return new Gold();
		}
		else if(type == Block.GROUND) {
			return new Grass();
		}
		else if(type == Block.FACTORY) {
			return new Factory();
		}
		else if(type == Block.SUPPLY) {
			return new Supply();
		}
		return getEmpty();
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

import java.awt.Color;
import java.awt.Graphics;

public class Brick extends Block{

		
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("”ничтожен кирпич");
		
	}

	@Override
	public void update(int ms) {
		if (hp <= 20) {
			picId = 8;
		}
		else if (hp <= 50) {
			picId = 7;
		}
		else {
			picId = 6;
		}
		
	}
	public Brick() {
		super(WALL);
		breakable = true;
		hp = 100;
		passable = false;
		picId = 6;
		
		if (Math.random() < 0.1) {
			hp = 50;
			if (Math.random() < 0.2) {
				hp = 20;
			}
		}
	}
}
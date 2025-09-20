import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class MissileCollection implements PaintUpdatable{
	private List<Missile> collection = new ArrayList<>();
	
	private static MissileCollection instance = new MissileCollection();
	
	public static MissileCollection get() {
		return instance;
	}

	private MissileCollection() {
	}
	
	public void add(Missile m) {
		collection.add(m);
	}
	
	@Override
	public void paint(Graphics g) {
		for(Missile m: collection) {
			m.paint(g);
		}
	}

	@Override
	public void update(int ms) {
		for(int i = collection.size()-1; i>=0;i--) {
			Missile m = collection.get(i);
			if(m.isDestroyed()) {
				collection.remove(i);
			}
			else {
				m.update(ms);
				
			}
		}
	}
}

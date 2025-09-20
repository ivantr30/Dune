import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class UnitCollection implements PaintUpdatable{
	
	private List<Unit> units = new ArrayList<Unit>();
	
	private static UnitCollection instance = new UnitCollection();
	
	public static UnitCollection get() {
		return instance;
	}

	private UnitCollection() {
	}
	
	public void add(Unit u) {
		units.add(u);
	}
	
	public Unit getUnitByCell(int row,int col) {
		for(Unit unit : units) {
			Node n = unit.getCurrentNode();
			if(n.x == col && n.y== row) {
				return unit;
			}
		}
		return null;
	}

	@Override
	public void paint(Graphics g) {
		for(Unit u : units) {
			u.setSelected(false);
			if(u == ContextManager.getCurrentUnit()) {
				u.setSelected(true);
			}
			u.paint(g);
		}
	}

	@Override
	public void update(int ms) {
		for(int i = units.size()-1; i>=0;i--) {
			Unit u = units.get(i);
			if(u.isDestroyed()) {
				units.remove(i);
			}
			else {
				u.update(ms);
				
			}
		}
	}
}

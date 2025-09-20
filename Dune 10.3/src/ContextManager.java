import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ContextManager {
	
	private static Unit current = null;
	
	
	public static boolean isUnitActive() {
		return current != null;
	}
	
	public static Unit getCurrentUnit() {
		return current;
	}
	
	private static void onUnitActive() {
		if (Mouse.wasButtonPressing(MouseEvent.BUTTON3)) {
			// Добавить маршрут для патрулирования
			int row = Mouse.getRow();
			int col = Mouse.getCol();
			current.addControlPoint(row, col);
			
			if (Keyboard.isShift()) {
				// Установить режим патрулирования
				current.setStrategy(StrategyType.CIRCLE);
			}
			else {
				// Перейти к последней ноде
				current.setStrategy(StrategyType.LAST);
				current.gotoNextNode();
			}
			
		}
	}
	
	private static void checkUnitSelected () {
		
		if (Mouse.wasButtonPressing(MouseEvent.BUTTON1)) {
			int row = Mouse.getRow();
			int col = Mouse.getCol();
			current = UnitCollection.get().getUnitByCell(row, col);
		}
	
	}
	
	public static void control() {
		checkUnitSelected();
		if (isUnitActive()) {
			onUnitActive();
		}
		
	}
	
	

}

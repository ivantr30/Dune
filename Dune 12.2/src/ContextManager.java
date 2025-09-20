import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ContextManager {
	
	private static Unit current = null;
	private static Node currentBlock = null;
	
	
	public static boolean isUnitActive() {
		return current != null;
	}
	
	public static Unit getCurrentUnit() {
		return current;
	}
	
	public static boolean isNodeActive() {
		return currentBlock != null;
	}
	
	public static Node getCurrentNode() {
		return currentBlock;
	}
	
	private static void onUnitActive() {
		
		if (Keyboard.wasKeyPressing(KeyEvent.VK_T)) {
			current.clearControlPoints();
		}
		
		
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
	
	private static void onUnitSelection() {
		current.showOnStatusBar();
		
		
	}
	
	private static void onUnitUnselected () {
		StatusBar.show("Вы сняли выделение с юнита",2000);
	}
	
	
	private static void checkUnitSelected () {
		
		if (Mouse.wasButtonPressing(MouseEvent.BUTTON1)) {
			int row = Mouse.getRow();
			int col = Mouse.getCol();
			
			Unit prevCurrent = current;
			current = UnitCollection.get().getUnitByCell(row, col);
			
			if (prevCurrent != current) {
				if (isUnitActive()) {
					onUnitSelection();
				}
				else {
					onUnitUnselected();
				}
			}
			
		}
	
	}
	
	private static void onNodeActive() {
		
		if (Map.getBlock(currentBlock.y, currentBlock.x).getType() == Block.FACTORY) {
			onFactoryNode();
		}
	}
	
	
	private static void onFactoryNode() {
		if (Keyboard.wasKeyPressing(KeyEvent.VK_ENTER) ) {
			FactoryScreen.current = (Factory)Map.getBlock(currentBlock.y, currentBlock.x);
			FactoryScreen.toggle();
		}
		
	}

	private static void checkBlockSelected () {
		
		if (Mouse.wasButtonPressing(MouseEvent.BUTTON3) ) {
			int row = Mouse.getRow();
			int col = Mouse.getCol();
			
			Block b = Map.getBlock(row, col);
				
			currentBlock = new Node(col, row, null, 0, 0);
			
			StatusBar.show("Выделена ячейка");
			
		}
	
	}
	
	public static void control() {
		checkUnitSelected();
		checkBlockSelected();
		if (isUnitActive()) {
			onUnitActive();
		}
		
		if(isNodeActive()) {
			onNodeActive();
		}
		
	}
	
	

}

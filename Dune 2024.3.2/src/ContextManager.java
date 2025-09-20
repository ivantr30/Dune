import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ContextManager {
	private static Unit current = null;
	private static Node currentNode = null;
	
	public static boolean isUnitActive() {
		return current != null;
	}
	public static boolean isNodeActive() {
		return currentNode != null;
	}
	private static void onUnitNotSelected() {
	}

	public static Node getCurrentNode() {
		return currentNode;
	}
	private static void onUnitSelection() {
		current.showOnStatusBar();
	}
	private static void onNodeActive() {
		if(Map.getBlock(currentNode.y, currentNode.x).getType() == Block.FACTORY) {
			onFactoryNode();
		}
	}
	private static void onFactoryNode() {
		if(Keyboard.wasKeyPressing(KeyEvent.VK_ENTER)) {
			FactoryScreen.current = (Factory)Map.getBlock(currentNode.y, currentNode.x);
			FactoryScreen.toggle();
		}
	}
	private static void checkUnitSelected() {
		if(Mouse.wasButtonPressing(MouseEvent.BUTTON1)) {
			int col = Mouse.getCol();
			int row = Mouse.getRow();
			Unit prevCurrent = current;
			current = UnitCollection.get().getUnitByCell(row, col);
			if(prevCurrent != current) {
				if(isUnitActive()) {
					onUnitSelection();
				}
				else {
					onUnitNotSelected();
				}
			}
		}
	}
	
	private static void onUnitActive() {
		if (Keyboard.wasKeyPressing(KeyEvent.VK_T)) {
			current.clearControlPoints();
		}
		if (Mouse.wasButtonPressing(MouseEvent.BUTTON3)) {
			// Добавить маршрут для патрулирования
			
				int row = Mouse.getRow();
				int col = Mouse.getCol();
				if(Keyboard.wasKeyPressing(KeyEvent.VK_CONTROL)) {
					current.deleteControlPoint(row, col);
				}
				else if (Keyboard.isShift()) {
					current.addControlPoint(row, col);
					current.setStrategy(StrategyType.CIRCLE);
				}
				else {
					current.addControlPoint(row, col);
					current.setStrategy(StrategyType.LAST);
					current.goToNextNode();
				}
			
		}
	}
	
	public static Unit getCurrentUnit() {
		return current;
	}
	
	private static void checkBlockSelected() {
		if(Mouse.wasButtonPressing(MouseEvent.BUTTON1 )) {
			int col = Mouse.getCol();
			int row = Mouse.getRow();
			currentNode = new Node(col,row,null,0,0);
			StatusBar.show("Выделена ячейка");
			
		}
	}
	public static void control() {
		checkBlockSelected();
		checkUnitSelected();
		if(isUnitActive()) {
			onUnitActive();
		}
		if(isNodeActive()) {
			onNodeActive();
		}
	}
}

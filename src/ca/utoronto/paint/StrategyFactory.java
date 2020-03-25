package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

/**
 * A factory of mouse events, when certain mouseEvents is detected,
 * a strategy of the mouse event handler will be created.
 * @author Jianhao Tian
 *
 */
public class StrategyFactory {

	public MouseStrategy createMouseStrategy(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			return new mouseDragged();
		} else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			return new mousePressed();
		} else if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			return new mouseMoved();
		} else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			return new mouseClicked();
		} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			return new mouseReleased();
		} else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			return new mouseEntered();
		} else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
			return new mouseExited();
		} else {
			return new doNothing();   
		}
	}
	
}

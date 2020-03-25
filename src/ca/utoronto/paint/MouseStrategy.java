package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

/**
 * an mouse event strategy interface that can be implemented
 * by diffrent mouse events
 * @author Jianhao Tian
 *
 */
public interface MouseStrategy {
	void addShape(PaintPanel paintPanel, MouseEvent e);
}

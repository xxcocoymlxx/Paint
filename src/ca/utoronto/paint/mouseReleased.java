package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * a Mouse event handler when the mouse is released
 * @author Junwen Shen
 *
 */
public class mouseReleased implements MouseStrategy {

	@Override
	public void addShape(PaintPanel paintPanel, MouseEvent e) {
        PaintModel paintModel = paintPanel.getModel();
        ArrayList<Shape> shapes = paintModel.getShapes().getToDraw();
        if (shapes.size() > 0) {
            Point p = new Point((int)e.getX(), (int)e.getY());
            Shape shape = shapes.get(shapes.size()-1);
            if (shape.getClass() != Polyline.class && (!paintModel.getMode().equals("Polyline"))) {
                shape.calcPoints(p);
                paintModel.update();
            }
            else if (shape.getClass() == Polyline.class) {
                shape.calcPoints(p);
                paintModel.update();
            }
        }
	}
}

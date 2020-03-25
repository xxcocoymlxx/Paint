package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * a Mouse event handler when the mouse is dragged,
 * which allows the user to see instant feedback of 
 * the shape they draw.
 * @author Junwen Shen
 *
 */
public class mouseDragged implements MouseStrategy {

	@Override
	public void addShape(PaintPanel paintPanel, MouseEvent e) {
        PaintModel paintModel = paintPanel.getModel();
        ArrayList<Shape> toDraw = paintModel.getShapes().getToDraw();
        Point p = new Point((int) e.getX(), (int) e.getY());
        if (paintModel.getMode().equals("Polyline")) {
            Point origin = new Point();
            origin.setX(p.getX());
            origin.setY(p.getY());
            if (toDraw.size() == 0) {
                paintModel.addShape(new mouseClicked().build(paintModel, origin));
            }
        }
        Shape shape = toDraw.get(toDraw.size()-1);
        shape.calcPoints(p);
        paintModel.update();
	}
}

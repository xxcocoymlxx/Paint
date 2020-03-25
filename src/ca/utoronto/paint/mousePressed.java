package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

/**
 * a Mouse event handler when the mouse is pressed
 * @author Junwen Shen
 *
 */
public class mousePressed implements MouseStrategy{

	@Override
	public void addShape(PaintPanel paintPanel, MouseEvent e) {
	    Point origin = new Point((int)e.getX(), (int)e.getY());
        PaintModel paintModel = paintPanel.getModel();

        if (!paintModel.getMode().equals("Polyline")) {
            Shape shape = new ShapeBuilder().getShape(paintModel.getMode(), origin, paintModel.getColor(),
                    paintModel.getStrokeType(), paintModel.getFilled(), paintModel.getThickness(),
                    paintModel.getOutlined(),
                    paintModel.getOutlineColor());
            paintModel.addShape(shape);
        }
	}
}

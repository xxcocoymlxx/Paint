package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * a Mouse event handler when the mouse is clicked
 * @author Junwen Shen
 *
 */
public class mouseClicked implements MouseStrategy{

	@Override
	public void addShape(PaintPanel paintPanel, MouseEvent e) {
        Point origin = new Point((int)e.getX(), (int)e.getY());
        PaintModel paintModel = paintPanel.getModel();

        if (paintModel.getMode().equals("Polyline")) {
            ArrayList<Shape> toDraw = paintModel.getShapes().getToDraw();
            if (toDraw.size() >= 1) {
                Shape shape = toDraw.get(toDraw.size()-1);
                if (shape.getClass() == Polyline.class) {
                    Polyline polyline = (Polyline)shape;
                    if (!polyline.getFinished()) polyline.addPoint(origin);
                    else if (!polyline.getDifference(origin)) paintModel.addShape(build(paintModel, origin));
                }
                else paintModel.addShape(build(paintModel, origin));
            }
            else paintModel.addShape(build(paintModel, origin));
        }
	}

	 Shape build(PaintModel paintModel, Point origin) {
        return new ShapeBuilder().getShape(paintModel.getMode(), origin, paintModel.getColor(),
                paintModel.getStrokeType(), paintModel.getFilled(), paintModel.getThickness(),
                paintModel.getOutlined(),
                paintModel.getOutlineColor());
    }
}

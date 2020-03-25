package ca.utoronto.utm.paint;


import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;

/**
 *  Subclass from Shape, which creates a group of points where the mouse is
 * dragged and are connected by short lines.
 * @author Mengning Yang
 *
 */
public class Squiggle extends Shape {
	private ArrayList<Point> points;
	
	
	/**
	 * constructor of the squiggle, which creates new squiggle object
	 * 
	 * @param origin
	 *            initial Point of the squiggle
	 * @param outlineColor
	 *            color of the squiggle
	 * @param thickness thickness of the squiggle
	 */
	public Squiggle(Point origin, Color outlineColor, int thickness) {
		super(origin, outlineColor, thickness);
		setStroke(StrokeType.OUTSIDE);
		points = new ArrayList<>();
		points.add(origin);
	}

	
	/**
	 * override method of class Shape, which the shape knows how to
	 * draw itself.
	 */
	@Override
	public void draw(GraphicsContext g) {
	    g.setLineWidth(this.thickness);
		g.setStroke(this.outlineColor);

		for (int i = 0; i < points.size() - 1; i++) {
			Point p1 = points.get(i);
			Point p2 = points.get(i + 1);
			g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
	}


	/**
	 * adds new point to the collection of points
	 * of the squiggle
	 */
	@Override
	public void calcPoints(Point p) {
		points.add(p);
	}

}

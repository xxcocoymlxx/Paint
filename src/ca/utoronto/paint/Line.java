package ca.utoronto.utm.paint;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.shape.StrokeType;

/**
 * Subclass from Shape, which allows the user to draw straight lines
 * by click and drag on the canvas.
 * @author Mengning Yang
 *
 */
public class Line extends Shape{

	private ArrayList<Point> points;
	
	/**
	 * constructor of straight line, which create a line object
	 * @param start
	 * 				initial point of the line
	 * @param outlineColor
	 * 				color of the line
	 * @param thickness
	 * 				thickness of the line
	 */
	public Line(Point start, Color outlineColor, int thickness) {
		super(start, outlineColor, thickness);
		this.setStroke(StrokeType.OUTSIDE);
		this.points = new ArrayList<>();
		this.calcPoints(start);
	}


	/**
	 * a override method of shape, which the line knows how 
	 * to draw itself.
	 */
	@Override
	public void draw(GraphicsContext g) {
		g.setLineWidth(this.thickness);
		g.setStroke(this.getOutlineColor());
		for (int i = 0; i < points.size()-1 ; i++) {
			Point p1 = points.get(i);
			Point p2 = points.get(i + 1);
			g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
	}
	
	/**
	 * add new point to the collection of points of the line.
	 * @param p
	 */
	public void addPoints(Point p) {
		this.points.add(p);
	}


	/**
	 * This method updates the points of a line.
	 */
	@Override
	public void calcPoints(Point p) {
		if (this.points.size() > 1) {
			this.updatePoint(p);
		} else {
			this.points.add(p);
		}
		this.setChanged();
		this.notifyObservers();

	}
	
	/**
	 * replace the given point with the last point in 
	 * the collection of points of the line.
	 * @param p a point
	 */
	private void updatePoint(Point p) {
		this.points.set(this.points.size() - 1, p);
	}
}


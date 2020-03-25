package ca.utoronto.utm.paint;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.shape.StrokeType;

/**
 * a subclass from Shape, creates a polyline object which allows user
 * to click on the canvas and the polyline will be automatically formed.
 * @author Mengning Yang
 * Modified by Junwen Shen
 */
public class Polyline extends Shape{
	private ArrayList<Double> xPoints;//the parameter of creating a Polyline requires array of points
	private ArrayList<Double> yPoints;//but We don't have a fixed number of points yet
	private int nPoints;
    private Point last;
	
    /**
     * Constructor of the Polyline object, which has
     * an arrayList that stores all the points that 
     * the user clicks.
     * @param origin
     * 				initial point of the polyline
     * @param outlineColor
     * 				color of the polyline
     * @param thickness
     * 				thickness of the polyline
     */
	public Polyline(Point origin, Color outlineColor, int thickness) {
		super(origin, outlineColor, thickness);
		setStroke(StrokeType.OUTSIDE);
        xPoints = new ArrayList<>();
        yPoints = new ArrayList<>();

        this.xPoints.add(origin.getX());
        this.yPoints.add(origin.getY());
        this.nPoints = 1;
	}


	/**
	 * override method from Shape, which the polyline knows
	 * how to draw it self,given a double array of points,
	 * lines will be automatically connected between those points.
	 * Note: When the last point(the polygon is finished) is close to the
	 * initial point of the polyline, it will form a Polygon.
	 */
	@Override
	public void draw(GraphicsContext g) {
        g.setStroke(this.outlineColor);
        g.setLineWidth(this.thickness);

	    double[] x = this.xPoints.stream().mapToDouble(d -> d).toArray();
	    double[] y = this.yPoints.stream().mapToDouble(d -> d).toArray();
	    if (this.finished) {
            g.strokePolygon(x, y, nPoints);
        }
        else if (this.nPoints == 2) {
            g.strokeLine(origin.getX(), origin.getY(), last.getX(), last.getY());
        }
        else if (this.nPoints > 2){
            g.strokePolyline(x, y, this.nPoints);
        }
	}

	/**
	 * calculation before a new point is added.
	 */
	@Override
	public void calcPoints(Point p) {
	    if (! finished) {
            if (this.nPoints >= 2) {
                this.updatePoint(p);
            }
            else {
                this.addPoint(p);
            }
            this.last = p;
            this.finished = checkFinished();
        }
	}
	
	/**
	 * This method sets the last point of the XY arrayList
	 * to the given point.
	 * @param p
	 */
	private void updatePoint(Point p) {
		this.xPoints.set(this.nPoints - 1, p.getX());
		this.yPoints.set(this.nPoints - 1, p.getY());
	}

	/**
	 * Add new points to the arrayList of points collection
	 * of the polyline object.
	 */
	public void addPoint(Point p) {
		this.xPoints.add(p.getX());
		this.yPoints.add(p.getY());
		this.nPoints++;
	}

	/**
	 * @param point
	 * 				last point of the polyline obeject
	 * @return
	 * 			return true if the different of the given point and
	 * 			the last point of the polyline is less than 5.
	 */
    public boolean getDifference(Point point) {
	    return Math.abs(last.getX()-point.getX()) <= 5 && Math.abs(last.getY()-point.getY()) <= 5;
    }

    /**
     * @return
     * 			returns true if the difference between the the initial point
     * and the last point of the polyline of less than 5 units.
     */
    private boolean checkFinished() {
	    if (! finished && last != origin)
	        return Math.abs(last.getX()- origin.getX()) <= 5 && Math.abs(last.getY()- origin.getY()) <= 5;
	    return true;
    }

}

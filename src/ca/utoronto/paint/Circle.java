package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;
import javafx.scene.paint.*;
import javafx.scene.shape.StrokeType;
/**
 * Subclass of Shape, which creates a circle centered at first click, with
 * radius of distance from first click to the last Point clicked.
 * 
 * @author Haoran Xiao
 *
 */
public class Circle extends Shape{
	
	private Point XY;
	private double radius;

	/**
	 * Circle object to be created
	 * @param start
	 * 				centre of the circle
	 * @param color
	 * 				color that the circle will be
	 * @param stroke
	 * 				strokeType of the circle
	 */
	public Circle(Point start, Color color, StrokeType stroke){
		super(start, color, stroke);
	}

	/**
	 * Another circle object constructor
	 * @param start
	 * 				centre of the circle
	 * @param color
	 * 				color that the circle will be
	 * @param stroke
	 * 				strokeType of the circle
	 * @param fill
	 * 				whether the circle is filled or not
	 * @param outlined
	 * 				whether the circle is outlined or not
	 * @param thickness
	 * 				thickness of the stroke of a circle
	 * @param outlineColor
	 * 				outline color of the circle
	 */
	public Circle(Point start, Color color, StrokeType stroke, Boolean fill, Boolean outlined,
                  int thickness, Color outlineColor){
	    super(start, color, stroke, fill, outlined, thickness, outlineColor);
    }

	/**
	 * @return
	 * 		 the centre point of the circle.
	 */
	public Point getCenter() {
		return this.origin;
	}
	
	/**
	 * @param p
	 * 		given a point p, set it as the current
	 * 		circle object's centre.
	 */
	public void SetCenter(Point p) {
		this.origin = p;
	}

	/**
	 * 
	 * @param p
	 * 			given a point p, set it as the circle's	
	 * 			xy coordinates.
	 */
	public void SetXY(Point p) {
	    this.XY = p;
    }

	/**
	 * 
	 * @return
	 * 			return a xy coordinates of the circle.
	 */
	public Point getXY() {
		return this.XY;
	}

	/**
	 * 
	 * @return
	 * 			returns the radius of the circle.
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * 
	 * @param radius
	 * 			given an integer and set it as the
	 * 			clrcle's radius.
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * override method from Shape class. Circle object draw it self.
	 * According to different attribute of the circle to draw diffrent 
	 * types of circles.
	 */
	@Override
	public void draw(GraphicsContext g) {
	    if (this.XY != null) {
            if (this.outlined & this.filled) {
                g.setStroke(this.outlineColor);
                g.setLineWidth(this.thickness);
                g.setFill(this.color);
                g.strokeOval(this.XY.getX(), this.XY.getY(), radius, radius);
                g.fillOval(this.XY.getX(), this.XY.getY(), radius, radius);
            }else if (this.outlined) {
                g.setStroke(this.outlineColor);
                g.setLineWidth(this.thickness);
                g.strokeOval(this.XY.getX(), this.XY.getY(), radius, radius);
            }else if (this.filled) {
                g.setFill(this.color);
                g.fillOval(this.XY.getX(), this.XY.getY(), radius, radius);
            }
        }
	}

	/**
	 * override method from Shape, which updates the point of
	 * circle objects, allows circle to be drawn in many
	 * different directions.
	 */
    @Override
    public void calcPoints(Point p) {
        double difX = Math.abs(this.origin.getX() - p.getX());
        double difY = Math.abs(this.origin.getY() - p.getY());
        int radius = (int)Math.sqrt(difX * difX + difY * difY);
        setRadius(radius*2);
        double X = this.origin.getX() - radius;
        double Y = this.origin.getY() - radius;
        SetXY(new Point(X, Y));
    }

    /**
     * returns a string representation of the circle.
     */
	public String toString(){ 
		return "Center: " + this.origin + ", Radius: " + this.radius + ", XY: " + this.XY;
	}
}

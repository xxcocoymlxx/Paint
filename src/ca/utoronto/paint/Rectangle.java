package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;

/**
 * Subclass of Shape, which creates a rectangle object with
 *  two opposite corners at first click and the last.
 * @author Mengning Yang
 *
 */
public class Rectangle extends Shape{
	private double width;
	private double height;	
	protected Point start;
	private double x = this.getOrigin().getX();
	private double y = this.getOrigin().getY();

	/**
	 * Constructor of the outline of a rectangle
	 * @param start
	 * 				initial point of the rectangle
	 * @param color
	 * 				color of the rectangle
	 * @param stroke
	 * 				strokeType of the rectangle
	 */
	public Rectangle(Point start, Color color, StrokeType stroke){
		super(start, color, stroke);
	}
	
	/**
	 * Another constructor with more detailed attribute of a rectangle.
	 * @param start initial point of the rectangle
	 * @param colour color of the fill of rectangle
	 * @param stroke strokeType of the rectangle
	 * @param filled whether the rectangle is filled
	 * @param outlined whether the rectangle has outline
	 * @param thickness thickness of the outline of the rectangle
	 * @param outlineColor outline color of the rectangle
	 */
	public Rectangle(Point start, Color colour, StrokeType stroke,
                     Boolean filled, boolean outlined, int thickness, Color outlineColor) {
		super(start, colour, stroke, filled, outlined, thickness, outlineColor);
		this.start = new Point(start.getX(), start.getY());
	}
	
	public double getWidth() {
		return this.width;
	}

	public double getHeight() {
		return this.height;
	}


	/**
	 * an override method from Shape, which draws the rectangle
	 * according to different conditions, outlined, filled, etc.
	 */
	@Override
	public void draw(GraphicsContext g) {
		if (this.outlined & this.filled) {
		    g.setStroke(this.outlineColor);
		    g.setLineWidth(this.thickness);
		    g.setFill(this.color);
            g.strokeRect(this.x, this.y, this.getWidth(), this.getHeight());
            g.fillRect(this.x, this.y, this.getWidth(), this.getHeight());
        }else if (this.outlined) {
		    g.setStroke(this.outlineColor);
		    g.setLineWidth(this.thickness);
		    g.strokeRect(this.x, this.y, this.getWidth(), this.getHeight());
        }else if (this.filled) {
            g.setFill(this.color);
            g.fillRect(this.x, this.y, this.getWidth(), this.getHeight());
        }
	}

	/**
	 * This method updates the point of a rectangle, which
	 * allows the user to draw the rectangle from different
	 * directions.
	 */
	@Override
	public void calcPoints(Point p) {
		this.width = Math.abs(this.start.getX() - p.getX());
		this.height = Math.abs(this.start.getY() - p.getY());
		if (this.start.getX() > p.getX()) {
			this.x = this.getOrigin().getX() - this.getWidth();
		}
		if (this.start.getY() > p.getY()) {
			this.y = this.getOrigin().getY() - this.getHeight();
		}
		
	}
}

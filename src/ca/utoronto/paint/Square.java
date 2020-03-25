package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;


/**
 * Subclass of Shape, which creates a square object with
 *  two opposite corners at first click and the last.
 * @author Haoran Xiao
 *
 */
public class Square extends Shape{
	private double side_len;
	protected Point start;
	private double x = this.getOrigin().getX();
	private double y = this.getOrigin().getY();

	/**
	 * Constructor of the outline of a square
	 * @param start
	 * 				initial point of the square
	 * @param color
	 * 				color of the square
	 * @param stroke
	 * 				strokeType of the square
	 */
	public Square(Point start, Color color, StrokeType stroke){
		super(start, color, stroke);
	}
	
	/**
	 * Another constructor with more detailed attribute of a square
	 * @param start initial point of the square
	 * @param colour color of the fill of square
	 * @param stroke strokeType of the square
	 * @param filled whether the square is filled
	 * @param outlined whether the square has outline
	 * @param thickness thickness of the outline of the square
	 * @param outlineColor outline color of the square
	 */
	public Square(Point start, Color colour, StrokeType stroke,
                     Boolean filled, boolean outlined, int thickness, Color outlineColor) {
		super(start, colour, stroke, filled, outlined, thickness, outlineColor);
		this.start = new Point(start.getX(), start.getY());
	}
	
	public double getSide() {
		return this.side_len;
	}
	
	public void setSide(double s) {
		this.side_len = s;
	}

	public Point getStartpt() {
		return this.start;
	}

	/**
	 * an override method from Shape, which draws the square
	 * according to different conditions, outlined, filled, etc.
	 */
	@Override
	public void draw(GraphicsContext g) {
		if (this.outlined & this.filled) {
		    g.setStroke(this.outlineColor);
		    g.setLineWidth(this.thickness);
		    g.setFill(this.color);
            g.strokeRect(this.x, this.y, this.side_len, this.side_len);
            g.fillRect(this.x, this.y, this.side_len, this.side_len);
        }else if (this.outlined) {
		    g.setStroke(this.outlineColor);
		    g.setLineWidth(this.thickness);
		    g.strokeRect(this.x, this.y, this.side_len, this.side_len);
        }else if (this.filled) {
            g.setFill(this.color);
            g.fillRect(this.x, this.y, this.side_len, this.side_len);
        }
	}


	/**
	 * This method updates the point of a square, which
	 * allows the user to draw the square from different
	 * directions and if the user drags the square into an
	 * rectangle, it will be rounds to the nearest square.
	 */
	@Override
	public void calcPoints(Point p) {
		double diffX = Math.abs(this.start.getX() - p.getX());
		double diffY = Math.abs(this.start.getY() - p.getY());
		setSide(Math.min(diffX,diffY));
		if (this.start.getX() > p.getX()) {
			this.x = this.getOrigin().getX() - this.side_len;
		}
		if (this.start.getY() > p.getY()) {
			this.y = this.getOrigin().getY() - this.side_len;
		}
	}
}

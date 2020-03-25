package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;

/**
 * A subclass from Shape, which creates a diamond (tetragon) object,
 * which is able to be drawn from different directions and the size 
 * of the shape is also customized by the user.
 * @author Haoran Xiao
 *
 */
public class Diamond extends Shape{
	
	protected Point start;
	private double[] xPoints = new double[4];
	private double[] yPoints = new double[4];


	/**
	 * Constructor for the diamond shape, if the user only 
	 * wants to draw an outline of the diamond.
	 * @param start
	 * 				initial point of the diamond object.
	 * @param color
	 * 				color of the outline of the object.
	 * @param stroke
	 * 				strokeType of the object.
	 */
	public Diamond(Point start, Color color, StrokeType stroke){
		super(start, color, stroke);
	}
	
	/**
	 * Another constructor for the diamond shape, which is able to 
	 * create diamond with more characteristics.
	 * @param start initial point of the diamond
	 * @param colour color of fill of the diamond
	 * @param stroke strokeType of the diamond
	 * @param filled whether the diamond is filled
	 * @param outlined whether the diamond has outline
	 * @param thickness thickness of the outline of the diamond
	 * @param outlineColor color of the outline of the diamond
	 */
	public Diamond(Point start, Color colour, StrokeType stroke,
                     Boolean filled, boolean outlined, int thickness, Color outlineColor) {
		super(start, colour, stroke, filled, outlined, thickness, outlineColor);
		this.start = new Point(start.getX(), start.getY());
	}
	

	public Point getStartpt() {
		return this.start;
	}

	/**
	 * an override method from Shape, which draws the diamond itself.
	 * With different attribute passed in, it can draw different 
	 * types of diamond.
	 */
	@Override
	public void draw(GraphicsContext g) {
		if (this.outlined & this.filled) {
		    g.setStroke(this.outlineColor);
		    g.setLineWidth(this.thickness);
		    g.setFill(this.color);
		    g.strokePolygon(xPoints, yPoints, 4);
            g.fillPolygon(xPoints, yPoints, 4);
        }else if (this.outlined) {
		    g.setStroke(this.outlineColor);
		    g.setLineWidth(this.thickness);
		    g.strokePolygon(xPoints, yPoints, 4);
        }else if (this.filled) {
            g.setFill(this.color);
            g.fillPolygon(xPoints, yPoints, 4);
        }
	}


	/**
	 * This method updates the points of diamond object,
	 * which allows user to draw the diamond from different
	 * directions and customize the size of the diamond by
	 * dragging the mouse.
	 */
	@Override
    public void calcPoints(Point p) {
        double MiddX = Math.abs(this.start.getX() - p.getX()) * 0.5;
        double MiddY = Math.abs(this.start.getY() - p.getY()) * 0.5;

        if (this.start.getX() < p.getX()) {
            xPoints[0] = (this.start.getX() + MiddX);
            xPoints[1] = this.start.getX();
            xPoints[2] = (this.start.getX() + MiddX);
            xPoints[3] = p.getX();
        } else {
            xPoints[0] = (this.start.getX() - MiddX);
            xPoints[1] = this.start.getX();
            xPoints[2] = (this.start.getX() - MiddX);
            xPoints[3] = p.getX();
        }
        if (this.start.getY() < p.getY()) {
            yPoints[0] = this.start.getY();
            yPoints[1] = (this.start.getY() + MiddY);
            yPoints[2] = p.getY();
            yPoints[3] = (this.start.getY() + MiddY);
        } else {
            yPoints[0] = this.start.getY();
            yPoints[1] = (this.start.getY() - MiddY);
            yPoints[2] = p.getY();
            yPoints[3] = (this.start.getY() - MiddY);
        }
    }
}
package ca.utoronto.utm.paint;

/**
 * A point class to record the xy coordinates of all the shapes.
 * @author 
 *
 */
public class Point {
	private double x, y;

	public Point() {}
	public Point(double d, double e) {
		this.x = d;
		this.y = e;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String toString(){ 
		return "("+ this.x +", "+ this.y +")";
	}

}

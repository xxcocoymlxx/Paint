package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Creates a shapeStack model for PaintModel. 
 * which holds toDraw Stack, and canceled Stack to 
 * store all the shapes to be drawn and canceled,and
 * allows redo and undo to happen.
 * @author Mengning Yang and Junwen Shen 
 *
 */
public class ShapeStack{
	private ArrayList<Shape> toDraw;
	private Stack<Shape> canceled;

	/**
	 * Constructor of the shapeStack which creates
	 * two arrayList of shapes.
	 */
	ShapeStack() {
		this.toDraw = new ArrayList<>();
		this.canceled = new Stack<>();
	}

	/**
	 * removes the last item in toDraw Array and puts into the canceled Array.
	 */
	public void redo() {
		if (this.canceled.size() > 0) {
			this.toDraw.add(this.canceled.get(this.canceled.size() - 1));
			this.canceled.remove(this.canceled.size() - 1);
		}
	}
	
	/**
	 * removes the last item in canceled Array and put into the toDraw Array.
	 */
	public void undo() {
		if (this.toDraw.size() > 0) {
			this.canceled.add(this.toDraw.get(this.toDraw.size() - 1));
			this.toDraw.remove(this.toDraw.size() - 1);
		}
	}

	/**
	 * add a new shape to the toDraw arrayList
	 */
	public void addShape(Shape shape) {
	    toDraw.add(shape);
	    this.canceled.clear();
    }
	
	public void clear() {
		this.toDraw.clear();
		this.canceled.clear();
	}

	/**
	 * @return
	 * 		the arrayList of toDraw.
	 */
    public ArrayList<Shape> getToDraw() {
        return toDraw;
    }
}
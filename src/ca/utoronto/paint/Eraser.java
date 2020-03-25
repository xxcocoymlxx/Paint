package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;

/**
 * subclass of Squiggle class, which creates an Eraser
 * object, which can draw squiggles in the color of the 
 * Canvas, acts like an Eraser.
 * @author Mengning Yang
 *
 */
public class Eraser extends Squiggle{

	public Eraser(Point origin, int thickness) {
	    super(origin, Color.WHITE, thickness);
	}
}

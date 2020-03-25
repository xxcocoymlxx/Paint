package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;

/**
 * a shape Factory, which given an string of the desired object,
 * and the factory returns the object with given attribute.
 * @author Mengning Yang and Junwen Shen
 *
 */
public class ShapeBuilder {

	public Shape getShape(String mode, Point origin, Color color, StrokeType stroke,
                          boolean fill, int thickness, boolean outlined, Color outlineColor) {
		if (mode.equals("Circle")) {
			return new Circle(origin, color, stroke, fill, outlined, thickness, outlineColor);
		}
		if (mode.equals("Rectangle")) {
			return new Rectangle(origin, color, stroke, fill, outlined, thickness, outlineColor);
		}
		if (mode.equals("Square")) {
			return new Square(origin, color, stroke, fill, outlined, thickness, outlineColor);
		}
		if (mode.equals("Polyline")) {
			return new Polyline(origin, outlineColor, thickness);
		}
		if (mode.equals("Squiggle")) {
			return new Squiggle(origin, outlineColor, thickness);
		}
		if (mode.equals("Diamond")) {
		    return new Diamond(origin, color, stroke, fill, outlined, thickness, outlineColor);
        }
		if (mode.equals("Eraser")) {
			return new Eraser(origin, thickness);
		}
		if (mode.equals("Line")) {
		    return new Line(origin, outlineColor, thickness);
        }

		return null;
	}
}

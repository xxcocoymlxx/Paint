package ca.utoronto.utm.paint;

import java.util.Observable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.shape.StrokeType;

/**
 * Base class of all the shape object, which contains the basic
 * attribute and methods that every shape has.
 * @author Mengning Yang
 *
 */
public abstract class Shape extends Observable {
		protected Color color;
		protected StrokeType stroke;
		protected Point origin;
		protected Boolean filled = false;
		protected Boolean outlined = true;
		protected Color outlineColor;
		protected Boolean finished = false;
		protected int thickness;
		
		/**
		 * Constructor for the shape if the shape only has an outline.
		 * @param origin initial point of the shape
		 * @param color color of the fill of a shape
		 * @param stroke strokeType of a shape
		 */
		public Shape(Point origin, Color color, StrokeType stroke) {
			this.color = color;
			this.stroke = stroke;
			this.origin = origin;
		}

		/**
		 * Constructor for the shape if the shape only has only outline.
		 * @param origin initial point of the shape
		 * @param outlineColor color of the fill of the outline of the shape
		 * @param thickness thickness of the outline of the shape
		 */
		public Shape(Point origin, Color outlineColor, int thickness) {
		    this.outlineColor = outlineColor;
		    this.origin = origin;
		    this.thickness = thickness;
        }
		
		/**
		 * A more detailed constructor of a shape.
		 * @param start initial point of the shape
		 * @param color color of the fill of a shape
		 * @param stroke strokeType of a shape
		 * @param fill whether the shape is filled
		 * @param outlined whether the shape has outline
		 * @param thickness thickness of the outline of a shape
		 * @param outlineColor outline color of a shape
		 */
		public Shape(Point start, Color color, StrokeType stroke,
                     Boolean fill, Boolean outlined, int thickness, Color outlineColor) {
			this(start, color, stroke);
			this.filled = fill;
			this.outlined = outlined;
			this.outlineColor = outlineColor;
			this.thickness = thickness;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		public void setFilled(Boolean fill) {
			this.filled = fill;
		}

		public void setThickness(int thickness) {
		    this.thickness = thickness;
        }

        public int getThickness() {
		    return thickness;
        }

		public void addPoint(Point p) {
			return;
		}

		public boolean getFilled() {
			return this.filled;
		}

		public Color getOutlineColor() {
			return this.outlineColor;
		}

		public void setOutlined() {
			this.outlined = !this.outlined;
		}

		public Boolean getOutlined() {
			return this.outlined;
		}

		public void setStroke(StrokeType stroke) {
			this.stroke = stroke;
		}

		public StrokeType getStroke() {
			return this.stroke;
		}

		public Color getColor() {
			return this.color;
		}

		public void setOrigin(Point p) {
			this.origin = p;
		}

		public Point getOrigin() {
			return this.origin;
		}

		public Boolean getFinished() {
			return this.finished;
		}

		public abstract void draw(GraphicsContext g);
		public abstract void calcPoints(Point p);
		

}

package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;

import java.util.Observable;

public class PaintModel extends Observable {

    private String mode;
    private Color color;
    private StrokeType strokeType;
    private Boolean filled;
    private Boolean outlined;
    private int thickness;
    private Color outlineColor;
	ShapeStack shapeStack = new ShapeStack();
	private ShapeBuilder shapeBuilder;

	PaintModel(){
        this.color = Color.WHITE;
	    this.strokeType = StrokeType.OUTSIDE;
	    this.filled = false;
	    this.outlined = true;
	    this.thickness = 1;
        this.outlineColor = Color.BLACK;
        this.shapeBuilder = new ShapeBuilder();
    }

	public void addShape(Shape s) {
		this.shapeStack.addShape(s);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Remove the last shape object created.
	 */
	public void Undo() {
		this.shapeStack.undo();
	}
	
	/**
	 * Add the last shape object removed back
	 */
	public void Redo() {
		this.shapeStack.redo();
	}

	public void update() {
        this.setChanged();
        this.notifyObservers();
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFilled(Boolean filled) {
        this.filled = filled;
    }

    public void setOutlined(Boolean outlined) {
        this.outlined = outlined;
    }

    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
    }

    public void setStrokeType(StrokeType strokeType) {
        this.strokeType = strokeType;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public String getMode() {
        return mode;
    }

    public Color getColor() {
        return color;
    }

    public Boolean getFilled() {
        return filled;
    }

    public Boolean getOutlined() {
        return outlined;
    }

    public Color getOutlineColor() {
        return outlineColor;
    }

    public StrokeType getStrokeType() {
        return strokeType;
    }

    public int getThickness() {
        return thickness;
    }

    public ShapeStack getShapes() {
        return this.shapeStack;
    }

}

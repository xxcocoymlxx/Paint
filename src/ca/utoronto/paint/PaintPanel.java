package ca.utoronto.utm.paint;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.util.*;


class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {

	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	private Canvas canvas;

	public PaintPanel(PaintModel model, View view, Canvas canvas) {

		this.canvas = canvas;
		this.getChildren().add(this.canvas);//add canvas to StackPane

		// The canvas is transparent, so the background color of the
		// containing pane serves as the background color of the canvas.
		this.setStyle("-fx-background-color: white");

		//any mouse events on the canvas will be handled
		this.addEventHandler(MouseEvent.ANY, this);
		this.model = model;
		this.model.addObserver(this);
		this.view = view;
	}

	public void repaint() {

		GraphicsContext g = this.canvas.getGraphicsContext2D();

		// Clear the canvas
		g.clearRect(0, 0, this.getWidth(), this.getHeight());

		ArrayList<Shape> shapes = this.model.getShapes().getToDraw();
		for (Shape shape: shapes) {
		    shape.draw(g);
        }
	}

	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint();
	}

	/**
	 * Controller aspect of this
	 */

    public PaintModel getModel() {
        return model;
    }

    @Override
	public void handle(MouseEvent event) {
		StrategyFactory strategyFactory = new StrategyFactory();
		MouseStrategy mouseStrategy = strategyFactory.createMouseStrategy(event);
		mouseStrategy.addShape(this, event);
	}
}

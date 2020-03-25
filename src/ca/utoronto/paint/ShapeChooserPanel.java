package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * This class creates a GUI view of collection of shapes for the
 * user to choose from.
 * @author 
 *
 */
public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {
    private Button prev;
	private View view; // So we can talk to our parent or other components of the view

	public ShapeChooserPanel(View view) {
		this.view = view;

		String[] buttonLabels = {"Squiggle", "Polyline", "Line", "Circle",
                "Rectangle", "Square",  "Diamond", "Eraser"};

		int row = 0;
		for (String label : buttonLabels) {
			Button button = new Button();
			Image icon = new Image(getClass().getResourceAsStream("/Icons/"+label+".png"));
            Tooltip tooltip = new Tooltip(label);
            button.getStyleClass().add("button");
            button.setGraphic(new ImageView(icon));
            button.setTooltip(tooltip);
			button.setPrefWidth(50);

			this.add(button, 0, row);
            button.setOnAction(this);

            if (label.equals("Squiggle")){
                prev = button;
                prev.setStyle("-fx-background-color: #99d9ea");
                this.view.getPaintPanel().getModel().setMode(label);
            }
            row++;
		}
	}

	@Override
	public void handle(ActionEvent event) {
	    Button button = (Button)event.getSource();
	    prev.setStyle(null);
        prev = button;
        button.setStyle("-fx-background-color: #99d9ea");
		String command = button.getTooltip().getText();

		if (command.equals("Squiggle")|command.equals("Polyline")|command.equals("Line")|command.equals("Eraser")) {
		    this.view.getFillStyleChooserPanel().setButtonDisable(true);
			this.view.getFillStyleChooserPanel().clearOutput();
		}
		else {
		    this.view.getFillStyleChooserPanel().setButtonDisable(false);
		    this.view.getFillStyleChooserPanel().Output();
		}
	

		this.view.getPaintPanel().getModel().setMode(command);
		System.out.println(command);
	}

}

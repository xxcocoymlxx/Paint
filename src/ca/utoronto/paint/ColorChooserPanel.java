package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

/**
 * Creates a GUI view and controller for selecting color.
 * @author Jianhao Tian
 * Modified by Junwen Shen
 */
public class ColorChooserPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view;
	private TypeChooserPanel typeChooserPanel;

	public ColorChooserPanel(View view) {
		this.view = view;

		String[] buttonLabels = {"RED", "WHITE", "YELLOW", "ORANGE", "GREEN", "Blue","GREY","BLACK","BEIGE","BROWN",
				"CORAL","FUCHSIA","INDIGO","LAWNGREEN","PERU" };

		int row = 0;

		for (String label : buttonLabels) {
			Button button = new Button();
			button.getStyleClass().add("button");
			button.setPrefWidth(100);
			String colorLabel = "-fx-background-color: " + label;
			button.setStyle(colorLabel);
			button.setOnAction(this);
			this.add(button,0,row);
			row++;
		}
		typeChooserPanel = new TypeChooserPanel();
        this.add(this.typeChooserPanel, 0, row);
	}    

	/**
	 * ActionEvent handler for the color chooser buttons.
	 * Set the color of following shapes to be the button 
	 * selected.
	 */
	@Override
	public void handle(ActionEvent event) {
		Button button = (Button)event.getSource();
        Color color = Color.web(button.getStyle().substring(22));

        String type = this.typeChooserPanel.getType();
        if (type.equals("Outline")) this.view.getPaintPanel().getModel().setOutlineColor(color);
        else this.view.getPaintPanel().getModel().setColor(color);

	}
}

/**
 * Creates a GUI view and controller for selecting drawing style,
 * such as outlined, filled.
 * @author Junwen Shen
 *
 */
class TypeChooserPanel extends GridPane{
    private String type = "Outline";
    private CheckBox outline;
    private boolean o = true;
    private CheckBox fill;
    private boolean f = false;

    TypeChooserPanel() {
        this.outline = new CheckBox("Outline");
        this.outline.setTooltip(new Tooltip("Outline Color"));
        this.outline.setSelected(true);

        this.fill = new CheckBox("Fill");
        this.fill.setTooltip(new Tooltip("Fill color"));
        this.fill.setSelected(false);

        this.outline.selectedProperty().addListener(e->{
            if (o) {
                o = false;
                this.type = "Fill";
            }
            else {
                o = true;
                this.type = "Outline";
                this.fill.setSelected(false);
            }
        });

        this.fill.selectedProperty().addListener(e->{
            if (f) {
                f = false;
                this.type = "Outline";
            }
            else {
                f = true;
                this.type = "Fill";
                this.outline.setSelected(false);
            }
        });
        this.add(new Label("Color of: "), 0, 0);
        outline.setPrefWidth(100);
        this.add(this.outline, 0, 1);
        fill.setPrefWidth(100);
        this.add(this.fill, 0, 2);
    }

    public String getType() {
        return type;
    }
}
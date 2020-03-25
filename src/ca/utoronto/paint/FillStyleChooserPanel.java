package ca.utoronto.utm.paint;

import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;

/**
 * This class creates a GUI view for the style selector which
 * allows the user to choose the shape to be outlined or filled
 * or both, and the thickness of the outline. Note: Lines do 
 * not have the option to be filled.
 * @author Jianhao Tian
 *
 */
public class FillStyleChooserPanel extends GridPane implements EventHandler<ActionEvent> {
    private View view;
    private int OutlinedNum, FilledNum;
    private TextField notify = new TextField ();
    private Button Outline = new Button();
    private Button Fill = new Button();
    private Boolean Outlined= true, Filled = false;
    
    FillStyleChooserPanel(View view){
        this.view = view;
        
        Image icon = new Image(getClass().getResourceAsStream("/Icons/Outlined.png"));
        Outline.getStyleClass().add("button");
        Outline.setGraphic(new ImageView(icon));
        Outline.setTooltip(new Tooltip("Outlined"));
        Outline.setPrefWidth(50);
        this.add(Outline, 0, 0);
        Outline.setDisable(true);
        Outline.setOnAction(this);
        
        icon = new Image(getClass().getResourceAsStream("/Icons/Filled.png"));
        Fill.getStyleClass().add("button");
        Fill.setGraphic(new ImageView(icon));
        Fill.setTooltip(new Tooltip("Filled"));
        Fill.setPrefWidth(50);
        this.add(Fill, 1, 0);
        Fill.setDisable(true);
        Fill.setOnAction(this);
        
        notify.setPrefWidth(220);
        notify.setText("Outlined:. Filled:.");
        notify.setEditable(false);
        notify.clear();
        this.add(notify, 2, 0);
        
        this.add(new Label("          Thickness: "), 4, 0);
        
        //sets the thickness
        Slider slider = new Slider();
		slider.setMin(1);
		slider.setMax(10);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(false);
		this.add(slider, 5, 0);
		slider.valueProperty().addListener(e ->
                this.view.getPaintPanel().getModel().setThickness((int) slider.getValue()));
        
    }

    /**
     * ActionEvant handler for the style selector bars,
     * which sets the user's selection of style to the
     * paintModel.
     */
    @Override
    public void handle(ActionEvent event) {
    	
		Button EventButton = (Button)event.getSource();
		String text = EventButton.getTooltip().getText();

		if (text.equals("Outlined")){
			OutlinedNum = OutlinedNum + 1;
		}else if(text.equals("Filled")){
			FilledNum = FilledNum + 1;
		}
		Outlined = OutlinedNum == 0 || OutlinedNum % 2== 0;
		Filled = FilledNum == 0 || FilledNum % 2== 0;
		
		Output();
		
        PaintModel paintModel = this.view.getPaintPanel().getModel();
        String mode = paintModel.getMode();

        if (!mode.equals("Squiggle")&&!mode.equals("Line")&&!mode.equals("Polyline")&&!mode.equals("Eraser")) {
            paintModel.setOutlined(Outlined);
            paintModel.setFilled(Filled);
        }
    }
    
    /**
     * disable the button if the chosen shape is Line.
     * @param disable
     */
    public void setButtonDisable(Boolean disable) {
    	Outline.setDisable(disable);
    	Fill.setDisable(disable);
    }
    
    /**
     * Show the current status of outlined and filled.
     */
    public void Output() {
    	notify.setText("Outlined: "+ Outlined +". Filled: "+ Filled +".");
    }
    
    public void clearOutput() {
    	notify.setText("");
    }
}


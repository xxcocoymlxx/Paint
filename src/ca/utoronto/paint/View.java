package ca.utoronto.utm.paint;

import java.util.Optional;

import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class View implements EventHandler<ActionEvent> {

	private PaintModel model;
	private ColorChooserPanel colorChooserPanel;
	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
    private FillStyleChooserPanel fillStyleChooserPanel;
    private Canvas canvas = new Canvas(600, 600);

	public View(PaintModel model, Stage stage) {
		this.model = model;
		initUI(stage);
	}

	private void initUI(Stage stage) {

		this.paintPanel = new PaintPanel(this.model, this, canvas);
		this.shapeChooserPanel = new ShapeChooserPanel(this);
		this.fillStyleChooserPanel = new FillStyleChooserPanel(this);
		this.colorChooserPanel = new ColorChooserPanel(this);

		BorderPane root = new BorderPane();
		root.setTop(createMenuBar());
		root.setCenter(this.paintPanel);
		root.setLeft(this.shapeChooserPanel);
		root.setBottom(this.fillStyleChooserPanel);
		root.setRight(this.colorChooserPanel);

		Scene scene = new Scene(root);
		scene.getStylesheets().add(View.class.getResource("/CSS/default.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Paint");
		stage.show();
		
		DoubleBinding heightBinding = root.heightProperty()
	            .subtract(root.bottomProperty().getValue().getBoundsInParent().getHeight())
	            .subtract(root.topProperty().getValue().getBoundsInParent().getHeight());
		DoubleBinding widthBinding = root.widthProperty()
	            .subtract(root.rightProperty().getValue().getBoundsInParent().getWidth())
	            .subtract(root.leftProperty().getValue().getBoundsInParent().getWidth());
	    canvas.widthProperty().bind(widthBinding);
	    canvas.heightProperty().bind(heightBinding);
	}

	public PaintPanel getPaintPanel() {
		return paintPanel;
	}

	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}

	public FillStyleChooserPanel getFillStyleChooserPanel() {return fillStyleChooserPanel;}

	private MenuBar createMenuBar() {

		MenuBar menuBar = new MenuBar();
		Menu menu;
		MenuItem menuItem;

		// A menu for File

		menu = new Menu("File");

		menuItem = new MenuItem("New");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Open");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Save");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menu.getItems().add(new SeparatorMenuItem());

		menuItem = new MenuItem("Exit");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuBar.getMenus().add(menu);

		// Another menu for Edit

		menu = new Menu("Edit");

		menuItem = new MenuItem("Cut");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Copy");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Paste");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menu.getItems().add(new SeparatorMenuItem());

		menuItem = new MenuItem("Undo");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Redo");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuBar.getMenus().add(menu);

		return menuBar;
	}

	@Override
	public void handle(ActionEvent event) {
		if (((MenuItem)event.getSource()).getText() == "Undo") {
			this.model.Undo();
			this.model.update();
		}else if (((MenuItem)event.getSource()).getText() == "Redo") {
			this.model.Redo();
			this.model.update();
		}else if (((MenuItem)event.getSource()).getText() == "New") {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText("Do you want to clear the canvas?");
			alert.setContentText("Are you sure you want to clear the canvas?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				this.model.shapeStack.clear();
				this.model.update();
			} else {
			}
		}else if (((MenuItem)event.getSource()).getText() == "Exit") {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText("Do you want to exit the paint program?");
			alert.setContentText("Are you really sure you want to exit?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				System.exit(0);
			} else {
			}
		}else if (((MenuItem)event.getSource()).getText() == "Save") {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Something's wrong!");
			alert.setContentText("Sorry, we can't save any files right now.");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
			} else {
			}
		}else if (((MenuItem)event.getSource()).getText() == "Open") {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Something's wrong!");
				alert.setContentText("Sorry, we can't open any files right now.");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
				} else {
				}
			}
		}
	}

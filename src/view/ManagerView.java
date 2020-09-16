package view;

import controller.ListManager;
import controller.Create;
import controller.Remove;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import model.Player;

/**
 * The manager tab of the team that is selected 
 * Shows the manager's name and star rating in a table view. 
 * The table view sorts the manager's by name by clicking on the name tab or star rating by clicking on the star rating tab
 * It has buttons to add and remove a manager
 * 
 * @author Ryan Kelleher
 * @version 1.0
 * @since 07/05/2020
 * 
 *
 */
public class ManagerView extends Tab {
	

	private ListManager controller = new ListManager();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ManagerView(String team) {

		setText("Managers");
		VBox vb = new VBox();
		vb.setId("pane");
		vb.getStylesheets().add("styles/stylesheet.css");
		HBox hb = new HBox();
		TableView table = new TableView();
		table.setEditable(true);

		table.setMaxSize(300, 500);
		TableColumn nameCol = new TableColumn("Name");
		TableColumn starCol = new TableColumn("Star Rating");

		nameCol.setMinWidth(100);
		nameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));

		starCol.setMinWidth(100);
		starCol.setCellValueFactory(new PropertyValueFactory<Player, Integer>("starRating"));

		table.setItems(controller.listManager(team));

		table.getColumns().addAll(nameCol, starCol);

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		Button btnAddManager = new Button("Add Manager");
		btnAddManager.setOnAction(e -> {
			this.addManager(team);
			table.setItems(controller.listManager(team));
		});
		
		
		Button btnRemoveManager = new Button("Remove Manager");
		Remove r = new Remove();
		btnRemoveManager.setOnAction(e -> {
			Alert alert = new Alert(AlertType.WARNING);
			if (table.getSelectionModel().getSelectedItem() == null) {
				alert.setHeaderText("Please Select Manager");
				alert.showAndWait();
			}
			else {
				String[] manager = table.getSelectionModel().getSelectedItem().toString().trim().split("[0-9]");
				System.out.println(manager[0]);
				String m = manager[0];
				String m2 = "";
				if(m.contains("null")) {
					m2 = m.replace("null", "");
				}
				System.out.println(m2);
				r.removeManager(m2);
				table.setItems(controller.listManager(team));
			}			
		});
		
		
		hb.getChildren().add(btnAddManager);	
		hb.getChildren().add(btnRemoveManager);
		hb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(table, hb);
		vb.setAlignment(Pos.CENTER);
		setContent(vb);
		
	}

	/**
	 * Displays a dialog box with input for the manager's name and star rating
	 * @param team the team that the manager is added to
	 */
	public void addManager(String team) {

		// create a text input dialog
		Dialog<String> dialog = new Dialog<String>();
		dialog.setTitle("Add Manager to Team");

		// create a button;
		ButtonType buttonTypeAddManager = new ButtonType("Add Manager", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeAddManager);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeCancel);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField name = new TextField();
		TextField starRating = new TextField();

		grid.add(new Label("Name:"), 0, 0);
		grid.add(name, 1, 0);
		grid.add(new Label("Star Rating:"), 0, 2);
		grid.add(starRating, 1, 2);

		dialog.getDialogPane().setContent(grid);
		Create c = new Create();
		Node addManager = dialog.getDialogPane().lookupButton(buttonTypeAddManager);

		((ButtonBase) addManager)
				.setOnAction(e -> c.createManager(name.getText(), Integer.parseInt(starRating.getText()), team));

		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.showAndWait();
	}

}

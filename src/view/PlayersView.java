package view;

import controller.ListManager;
import controller.Create;
import controller.Remove;
import controller.Update;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import model.Player;

/**
 * The players tab of the team that is selected 
 * Shows the names and goals scored by the player's in a table view 
 * The table view sorts the player's by name by clicking on the name tab or goals by clicking on the goals tab
 * It has buttons to add a player to the team , remove a player and edit the player's goals 
 * 
 * @author Ryan Kelleher
 * @version 1.0
 * @since 07/05/2020
 * 
 *
 */
public class PlayersView extends Tab {
	
	
	ListView<String> listOfPlayers;
	private ListManager controller = new ListManager();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PlayersView(String team) {
		setText("Players");
		
		VBox vb = new VBox();
		vb.setId("pane");
		vb.getStylesheets().add("styles/stylesheet.css");
		
		HBox hb = new HBox();	
		
		TableView table = new TableView();
		table.getStyleClass().add("table-view");
	    table.setEditable(true);

	    table.setMaxSize(300, 500);
	    TableColumn nameCol = new TableColumn("Name");
	    TableColumn goalsCol = new TableColumn("Goals");

	    nameCol.setMinWidth(100);
	    nameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
	    
	    goalsCol.setMinWidth(100);
	    goalsCol.setCellValueFactory(new PropertyValueFactory<Player, Integer>("goals"));


	    	
	    table.setItems(controller.listPlayers(team));
	    
	    table.getColumns().addAll(nameCol, goalsCol);
	    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		Button btnAddPlayer = new Button("Add Player");
		Button btnRemovePlayer = new Button("Remove Player");
		Button btnEditPlayer = new Button("Edit Player");
		
		hb.getChildren().addAll(btnAddPlayer, btnRemovePlayer, btnEditPlayer);
		hb.setSpacing(3);
		hb.setAlignment(Pos.CENTER);
		Remove r = new Remove();
		
		btnAddPlayer.setOnAction(e-> {
			this.addPlayer(team);
			table.setItems(controller.listPlayers(team));
		});
		
		btnRemovePlayer.setOnAction(e-> {
			Alert alert = new Alert(AlertType.WARNING);
			if (table.getSelectionModel().getSelectedItem() == null) {
				alert.setHeaderText("Please Select Player");
				alert.showAndWait();
			}
			else {
				String[] player = table.getSelectionModel().getSelectedItem().toString().trim().split("[0-9]");
				System.out.println(player[0]);
				r.removePlayer(player[0]);
				table.setItems(controller.listPlayers(team));
			}			
		});
		
		btnEditPlayer.setOnAction(e-> {
			Alert alert = new Alert(AlertType.WARNING);
			if (table.getSelectionModel().getSelectedItem() == null) {
				alert.setHeaderText("Please Select Player");
				alert.showAndWait();
			}
			else {
				String[] player = table.getSelectionModel().getSelectedItem().toString().trim().split("[0-9]");
				this.updateGoals(player[0]);
				table.setItems(controller.listPlayers(team));
			}
			
		});
		
		 vb.setSpacing(5);
	     vb.setPadding(new Insets(10, 0, 0, 10));
	        
		vb.getChildren().addAll(table, hb);
		
		vb.setAlignment(Pos.CENTER);

		setContent(vb);
	}

	/**
	 * Displays a dialog box with input for the player's name and goals 
	 * @param team the team that the player is added to
	 */
	public void addPlayer(String team) {

		// create a text input dialog
		Dialog<String> dialog = new Dialog<String>();
		dialog.setTitle("Add Player to Team");

		// create a button
		ButtonType buttonTypeAddPlayer = new ButtonType("Add Player", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeAddPlayer);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeCancel);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField playerName = new TextField();
		TextField goals = new TextField();

		grid.add(new Label("Player Name:"), 0, 0);
		grid.add(playerName, 1, 0);
		grid.add(new Label("Goals:"), 0, 1);
		grid.add(goals, 1, 1);

		dialog.getDialogPane().setContent(grid);
		Create c = new Create();
		Node addTeam = dialog.getDialogPane().lookupButton(buttonTypeAddPlayer);

		((ButtonBase) addTeam)
				.setOnAction(e -> c.createPlayer(playerName.getText(), Integer.parseInt(goals.getText()), team));

		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.showAndWait();
	}

	/**
	 * Displays a dialog box with to update the player's goals 
	 * @param player the player that's goals need to be update 
	 */
	public void updateGoals(String player) {

		// create a text input dialog
		Dialog<String> dialog = new Dialog<String>();
		dialog.setTitle("Update Player's Goals");

		// create a button
		ButtonType buttonTypeEditPlayer = new ButtonType("Edit Player", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeEditPlayer);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeCancel);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField goals = new TextField();
		grid.add(new Label("Goals:"), 0, 1);
		grid.add(goals, 1, 1);

		dialog.getDialogPane().setContent(grid);
		Node addTeam = dialog.getDialogPane().lookupButton(buttonTypeEditPlayer);

		Update u = new Update();

		((ButtonBase) addTeam).setOnAction(e -> u.editPlayerGoals(player, Integer.parseInt(goals.getText())));

		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.showAndWait();
	}

}

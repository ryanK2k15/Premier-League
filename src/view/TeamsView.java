package view;

import java.io.File;
import java.util.List;
import controller.ListManager;
import controller.MemoryUse;
import controller.Create;
import controller.Remove;
import controller.Search;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import model.Manager;
import model.Player;
import model.Team;

/**
 * The main menu of the application 
 * Displays a list of teams in the league
 * It has buttons to add a team, remove a team, manage a team and search for a player 
 * 
 * @author Ryan Kelleher
 * @version 1.0
 * @since 07/05/2020
 * 
 *
 */
public class TeamsView {
	
	private Scene scene;
	private ListView<String> listOfTeams;
	private ListManager controller = new ListManager();

	
	/**
	 * Displays a listview with the teams 
	 * @return scene the scene of the teams view
	 */
	public Scene displayTeams() {

		VBox vb = new VBox();
		scene = new Scene(vb, 750, 500);
		scene.getStylesheets().add("styles/stylesheet.css");
		HBox hb = new HBox();

		File file2 = new File("images/leaguelogo.png");
		Image image2 = new Image(file2.toURI().toString());
		ImageView imageView2 = new ImageView(image2);

		Button btnSearchPlayer = new Button("Search Player");
		Button btnManageTeam = new Button("Manage Team");
		Button btnAddTeam = new Button("Add Team");
		Button btnListTeams = new Button("List Teams");
		Button btnRemoveTeam = new Button("Remove Team");
		Button btnMemoryUse = new Button("Memory Use");
		
		

		ObservableList<String> olTeams = controller.showTeams();
		listOfTeams = new ListView<String>(olTeams);

		hb.setPadding(new Insets(10, 50, 50, 50));
	    hb.setSpacing(10);
	    
		hb.getChildren().add(imageView2);
		vb.getChildren().add(hb);
		vb.getChildren().add(listOfTeams);

		HBox hb2 = new HBox();
		hb2.setPadding(new Insets(10, 50, 50, 50));
		hb2.setSpacing(10);
		
		hb2.getChildren().add(btnAddTeam);
		hb2.getChildren().add(btnRemoveTeam);
		hb2.getChildren().add(btnManageTeam);
		hb2.getChildren().add(btnSearchPlayer);
		hb2.getChildren().add(btnMemoryUse);

		hb2.setAlignment(Pos.CENTER);
		
		btnAddTeam.setOnAction(e -> {
			this.addTeam();
			listOfTeams.setItems(controller.showTeams());
		});

		btnListTeams.setOnAction(e -> listOfTeams.setItems(controller.showTeams()));
		
		Label lblTeam  = new Label();
		
		Button btnBack = new Button("Back");
		btnBack.setOnAction(e -> scene.setRoot(vb));
		btnManageTeam.setOnAction(e -> {
			Alert alert = new Alert(AlertType.WARNING);
			if (listOfTeams.getSelectionModel().getSelectedItem() == null) {
				alert.setHeaderText("Please Select Team");
				alert.showAndWait();
			} else {
				PlayersView pv = new PlayersView(listOfTeams.getSelectionModel().getSelectedItem());
				ManagerView mv = new ManagerView(listOfTeams.getSelectionModel().getSelectedItem());
				TabPane tp = new TabPane();
				tp.setPrefSize(650, 500);
				tp.getTabs().add(pv);
				tp.getTabs().add(mv);
				VBox vb2 = new VBox();
				HBox hb3 = new HBox();
				hb3.getChildren().add(btnBack);
				hb3.getChildren().add(lblTeam);
				lblTeam.getStylesheets().add("styles/stylesheet.css");	
				lblTeam.getStyleClass().add("lblTeam");
				lblTeam.setText("\t\t\t\t\t\t\t" + listOfTeams.getSelectionModel().getSelectedItem());
				vb2.getChildren().addAll(hb3, tp);
				scene.setRoot(vb2);
			}
		});

		Remove r = new Remove();
		btnRemoveTeam.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Alert alert = new Alert(AlertType.WARNING);
				if (listOfTeams.getSelectionModel().getSelectedItem() == null) {
					alert.setHeaderText("Please Select Team");
					alert.showAndWait();
				}
				else {
					r.removeTeam(listOfTeams.getSelectionModel().getSelectedItem());
					listOfTeams.setItems(controller.showTeams());
				}		
			}
		});

		btnSearchPlayer.setOnAction(e -> this.searchPlayer());
		
		btnMemoryUse.setOnAction(e -> {
			MemoryUse m = new MemoryUse();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Memory Use");
			alert.setContentText(m.showMemoryUse().toString());
			alert.showAndWait();		
		});
		
		vb.getChildren().add(hb2);
		vb.setAlignment(Pos.CENTER);

		return scene;
	}

	/**
	 * Displays a dialog box with input for the name of the team and the jersey colour 
	 */
	public void addTeam() {

		// create a text input dialog
		Dialog<String> dialog = new Dialog<String>();
		dialog.setTitle("Add Team to League");

		// create a button
		ButtonType buttonTypeAddTeam = new ButtonType("Add Team", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeAddTeam);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeCancel);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField teamName = new TextField();
		TextField jerseyColour = new TextField();

		grid.add(new Label("Team Name:"), 0, 0);
		grid.add(teamName, 1, 0);
		grid.add(new Label("Jersey Colour:"), 0, 1);
		grid.add(jerseyColour, 1, 1);

		dialog.getDialogPane().setContent(grid);
		Create c = new Create();
		Node addTeam = dialog.getDialogPane().lookupButton(buttonTypeAddTeam);

		((ButtonBase) addTeam).setOnAction(e -> c.createTeam(teamName.getText(), jerseyColour.getText()));

		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.showAndWait();
	}

	/**
	 * Displays a dialog box with input for player's name 
	 * An alert box is shown with the player's details, team and manager details if the player is found
	 * An alert box is shown if the player is not found
	 */
	public void searchPlayer() {
		Dialog<String> dialog = new Dialog<String>();
		dialog.setTitle("Search for a Player");
		
	
		ButtonType buttonTypeSearchPlayer = new ButtonType("Search Player", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
	
		dialog.getDialogPane().getButtonTypes().add(buttonTypeSearchPlayer);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeCancel);
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		TextField playerName = new TextField();
		grid.add(new Label("Name:"), 0, 1);
		grid.add(playerName, 1, 1);
		dialog.getDialogPane().setContent(grid);
		Search s = new Search();
		Node searchPlayer = dialog.getDialogPane().lookupButton(buttonTypeSearchPlayer);
		((ButtonBase) searchPlayer).setOnAction(e -> s.searchPlayer(playerName.getText()));
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.showAndWait();

		List<Player> list = s.searchPlayer(playerName.getText());
		int teamID = s.findTeamID(playerName.getText());
		List<Manager> list2 = s.searchManager(teamID);
		List<Team> list3 = s.findTeam(teamID);
		String s1 = "";
		for (Team t : list3) {
			s1 += "Team: " + t.getName() + "\n";
		}
		for (Player p : list) {
			s1 += "Player Name: " + p.getName() + "\nGoals: " + p.getGoals() + "\n";
		}
		for (Manager m : list2) {
			s1 += "Manager Name: " + m.getName() + "\n" + "Star Rating: " + m.getStarRating() + "\n";
		}

		Alert alert = new Alert(AlertType.INFORMATION);
		if (s1 == "") {
			alert.setHeaderText("Player Not Found");
			File file = new File("images/x icon.png");
			Image image = new Image(file.toURI().toString());
	        ImageView icon = new ImageView(image);
	        icon.setFitHeight(48);
	        icon.setFitWidth(48);
	        alert.getDialogPane().setGraphic(icon);
			alert.showAndWait();
		} else {
			alert.setHeaderText("Player Found");
			File file = new File("images/tick.png");
			Image image = new Image(file.toURI().toString());
	        ImageView icon = new ImageView(image);
	        icon.setFitHeight(48);
	        icon.setFitWidth(48);
	        alert.getDialogPane().setGraphic(icon);
			alert.setContentText(s1);
			alert.showAndWait();
		}

	}

}

package view;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The intro screen of the soccer league application
 * 
 * @author Ryan Kelleher
 * @version 1.0
 * @since 07/05/2020
 * 
 *
 */
public class Intro {

	

	/**
	 * Displays an intro screen that shows an image of the teams in the league
	 * It has a start button that loads the team from the database when clicked 
	 * @param primaryStage the primary stage of the javafx application
	 * @return scene the intro scene 
	 */
	public Scene showIntro(Stage primaryStage) {
		
		VBox vb = new VBox();
		Scene scene = new Scene(vb, 750, 500);
		scene.getStylesheets().add("styles/stylesheet.css");
		File file = new File("images/premierleague.png");
		Image image = new Image(file.toURI().toString());
		ImageView imageView = new ImageView(image);
		Button start = new Button("Start");
		vb.getChildren().add(imageView);
		vb.getChildren().add(start);
		vb.setPadding(new Insets(10,10,10,10));
		vb.setSpacing(5);

		vb.setAlignment(Pos.CENTER);
		TeamsView teamsView = new TeamsView();
		start.setOnAction(e -> primaryStage.setScene(teamsView.displayTeams()));
		return scene;
	}
}

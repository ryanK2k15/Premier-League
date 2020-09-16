package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Manager;
import model.Player;

/**
 * This class manages lists of teams, players and managers for use in the application
 * @author Ryan Kelleher
 * @version 1.0
 * @since 07/05/2020
 * 
 *
 */
public class ListManager {
	

	/**
	 * display the teams in the league
	 * 
	 * @return olTeams the observable list of team in the league
	 */
	public ObservableList<String> showTeams() {
		Load l = new Load();
		List<String> teams = l.loadTeams();
		ArrayList<String> alTeams = new ArrayList<String>(teams);
		ObservableList<String> olTeams = FXCollections.observableArrayList(alTeams);
		return olTeams;
	}
	
	/**
	 * display the players in the team
	 * 
	 * @param team the name of the team
	 * @return olPlayers the observable list of players in the team
	 */
	public ObservableList<Player> listPlayers(String team) {
		Load l = new Load();
		List<Player> players = l.loadPlayers(team);
		ArrayList<Player> alPlayers = new ArrayList<Player>(players);
		ObservableList<Player> olPlayers = FXCollections.observableArrayList(alPlayers);
		return olPlayers;
	}
	
	/**
	 * display the managers of the team
	 * 
	 * @param team the name of the team
	 * @return olmanager the observable list of managers in the team
	 */
	public ObservableList<Manager> listManager(String team) {
		Load l = new Load();
		List<Manager> manager = l.loadManager(team);
		ArrayList<Manager> almanager = new ArrayList<Manager>(manager);
		ObservableList<Manager> olmanager = FXCollections.observableArrayList(almanager);
		return olmanager;
	}
}

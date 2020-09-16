package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "player")
/**
 *This is a class to represent a Player in the Soccer League Application
 *It uses JPA to persist the name of the player and the number of goals the player scored to a database
 *The teamId is the id of the team the player plays for and is a foreign key to the team table 
 *@author Ryan Kelleher
 *@version 1.0
 *@since 07/05/2020
 * 
 *
 */
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String name;
	private int goals;
	@Transient private boolean goalie;
	private int teamId;
	
//	public Player(Name n, String p, String e) {
//		super(n, p, e);
//	}

	public Player() {
		super();
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "goals")
	public int getGoals() {
		return goals;
	}

	public void setGoals(int numOfGoals) {
		this.goals = numOfGoals;
	}

	public boolean isGoalie() {
		return goalie;
	}

	public void setGoalie(boolean goalie) {
		this.goalie = goalie;
	}
	
	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String toString(){
		return this.getName() +  " " + this.goals + " " + this.goalie;
	}
	
}


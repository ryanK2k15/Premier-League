package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "manager")
/**
 *This is a class to represent a Manager in the Soccer League Application
 *It uses JPA to persist the name of the manager and the manager's star rating to a database
 *The teamId is the id of the team the manager manages and is a foreign key to the team table 
 *@author Ryan Kelleher
 *@version 1.0
 *@since 07/05/2020
 * 
 *
 */
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String name;
	@Transient private String dateOfBirth;
	private int starRating;
	@Transient private Team team;
	private int teamId;
	
//	public Manager(Name n, String p, String e) {
//		super(n, p, e);
//	}

	public Manager() {
		
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "starRating")
	public int getStarRating() {
		return starRating;
	}

	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}
	
	
	public int getTeamId() {
		return teamId;
	}


	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}


	public String toString(){
		return this.getName().toString() + " " + this.getDateOfBirth() + " " + this.getStarRating();
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	
}


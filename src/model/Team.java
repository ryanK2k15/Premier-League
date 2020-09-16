package model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "team")  
/**
 *This is a class to represent a Team in the Soccer League Application
 *It uses JPA to persist the name of the team and the jersey colour to a database
 *The teamId is autoincremented
 *@author Ryan Kelleher
 *@version 1.0
 *@since 07/05/2020
 * 
 *
 */
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 	
	private String name;
	@Transient private Manager manager;
	@Transient private ArrayList<Player> players;
	private String jerseyColour;
	private int teamId;
	
	
	public Team() {

	}
	
	public Team(String n, Manager m, String j){
		this.name = n;
		this.manager = m;
		this.players = new ArrayList<Player>();
		this.jerseyColour = j;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

		public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public void removeManager(){
		this.manager = null;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void addPlayer(Player p) {
		this.players.add(p);
	}
	public void removePlayer(Player p){
		this.players.remove(p);
	}
	
	@Column(name = "jerseyColour")
	public String getJerseyColour() {
		return jerseyColour;
	}
	public void setJerseyColour(String jerseyColour) {
		this.jerseyColour = jerseyColour;
	}
	
	public String toString(){
		return this.name + " " + this.manager + " " + this.players + " " + this.jerseyColour;
	}
	
	public void print(){		
		System.out.println(this.toString());
	}
}


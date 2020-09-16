package controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Manager;
import model.Player;
import model.Team;

/**
 * This class searches for a player and finds that player's details, team and manager details
 * 
 * @author Ryan Kelleher
 * @version 1.0
 * @since 07/05/2020
 * 
 *
 */
public class Search {
	
	/**
	 * Search for a player in the database
	 * 
	 * @param name the name of the player to be searched 
	 * @return list the details of the player that was searched 
	 */
	public List<Player> searchPlayer(String name) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Soccer League");
		EntityManager entitymanager = emfactory.createEntityManager();
		
		Query query = entitymanager.createQuery("SELECT p FROM Player p WHERE p.name = :name");
		query.setParameter("name", name);
		System.out.println(name);
		@SuppressWarnings("unchecked")
		List<Player> list = (List<Player>)query.getResultList();
		List<String> listOfPlayers = new ArrayList<String>();
		for(Player p:list) {
	         System.out.println("Players :" + p.getName() + " " + p.getGoals());     
	         listOfPlayers.add(p.getName() + p.getGoals());
	      }
		System.out.println(list.toString());
		
		return list;

	}
	
	/**
	 * Find the player's teamId
	 * 
	 * @param playerName the name of the player
	 * @return teamId the id of the player's team
	 */
	public int findTeamID(String playerName) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Soccer League");
		EntityManager entitymanager = emfactory.createEntityManager();
		Query query1 = entitymanager.createQuery("Select p.teamId from Player p WHERE p.name = :name");
		query1.setParameter("name", playerName);
		@SuppressWarnings("unchecked")
		List<Integer> list1 = query1.getResultList();
		int teamId = 0;
		for(int i:list1) {
			 System.out.println("id :" + i);
	         teamId = i;
	      }
		return teamId;
	}
	
	/**
	 * Find the team that the player plays for 
	 * 
	 * @param teamId the teamId of the player's team
	 * @return list the details of the player's team 
	 */
	public List<Team> findTeam(int teamId) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Soccer League");
		EntityManager entitymanager = emfactory.createEntityManager();
		Query query = entitymanager.createQuery("Select t from Team t WHERE t.teamId = :teamId");
		query.setParameter("teamId", teamId);
		@SuppressWarnings("unchecked")
		List<Team> list = query.getResultList();
		return list;
	}
	
	/**
	 * Find the player's team manager
	 * 
	 * @param teamId the teamId of the manager's team
	 * @return list the player's manager details 
	 */
	public List<Manager> searchManager(int teamId){
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Soccer League");
		EntityManager entitymanager = emfactory.createEntityManager();
		Query query = entitymanager.createQuery("SELECT m FROM Manager m WHERE m.teamId = :teamId");
		query.setParameter("teamId", teamId);
		@SuppressWarnings("unchecked")
		List<Manager> list = query.getResultList();
		return list;
	}
	
	
}

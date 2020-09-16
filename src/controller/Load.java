package controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Manager;
import model.Player;

/**
 * This class uses JPA to load entities from the database to be used in the application
 * 
 * @author Ryan Kelleher
 * @version 1.0
 * @since 07/05/2020
 * 
 *
 */
public class Load {



	/**
	 * Load teams from the database
	 * 
	 * @return list the list of teams in the league
	 */
	public List<String> loadTeams() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Soccer League");
		EntityManager entitymanager = emfactory.createEntityManager();
		//Team team = entitymanager.find(Team.class, "My Team");
		Query query = entitymanager.createQuery("Select t.name from Team t");
		@SuppressWarnings("unchecked")
		List<String> list = query.getResultList();
		
		for(String t:list) {
	         System.out.println("Teams :" + t);
	      }
		
		return list;
	}
	
	/**
	 * Load players from the database
	 * 
	 * @param team the name of the team the player plays for 
	 * @return list the list of players in the team
	 */
	public List<Player> loadPlayers(String team) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Soccer League");
		EntityManager entitymanager = emfactory.createEntityManager();
		
		Query query1 = entitymanager.createQuery("Select t.teamId from Team t WHERE t.name = :team");
		query1.setParameter("team", team);
		@SuppressWarnings("unchecked")
		List<Integer> list1 = query1.getResultList();
		int teamId = 0;
		for(int i:list1) {
			 System.out.println("id :" + i);
	         teamId = i;
	      }
		
		Query query2 = entitymanager.createQuery("SELECT p FROM Player p WHERE p.teamId = :teamId ORDER BY p.goals");
		query2.setParameter("teamId", teamId);

		@SuppressWarnings("unchecked")
		List<Player> list = (List<Player>)query2.getResultList();
		List<String> listOfPlayers = new ArrayList<String>();
		for(Player p:list) {
	         System.out.println("Players :" + p.getName() + " " + p.getGoals());     
	         listOfPlayers.add(p.getName() + p.getGoals());
	      }
		
		return list;
	}
	
	/**
	 * Load managers from the database
	 * 
	 * @param team the name of the team that is managed
	 * @return list the list of managers in the team
	 */
	public List<Manager> loadManager(String team) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Soccer League");
		EntityManager entitymanager = emfactory.createEntityManager();
		
		Query query1 = entitymanager.createQuery("Select t.teamId from Team t WHERE t.name = :team");
		query1.setParameter("team", team);
		@SuppressWarnings("unchecked")
		List<Integer> list1 = query1.getResultList();
		int teamId = 0;
		for(int i:list1) {
			 System.out.println("id :" + i);
	         teamId = i;
	      }
		
		Query query2 = entitymanager.createQuery("SELECT m FROM Manager m WHERE m.teamId = :teamId");
		query2.setParameter("teamId", teamId);

		@SuppressWarnings("unchecked")
		List<Manager> list = (List<Manager>)query2.getResultList();
		List<String> listManager = new ArrayList<String>();
		for(Manager m:list) {
	         System.out.println("Manager :" + m.getName() + " " + m.getStarRating());     
	         listManager.add(m.getName() + m.getStarRating());
	      }
		
		return list;
	}
}

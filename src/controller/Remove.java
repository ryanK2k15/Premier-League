package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Manager;
import model.Player;
import model.Team;

/**
 * This class uses JPA to delete entities from the database
 * 
 * @author Ryan Kelleher
 * @version 1.0
 * @since 07/05/2020
 * 
 *
 */
public class Remove {

	/**
	 * Delete a team from the database
	 * 
	 * @param teamName the name of the team to be deleted
	 */
	public void removeTeam(String teamName) {
		 EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Soccer League");
		    EntityManager entitymanager = emfactory.createEntityManager();
		    entitymanager.getTransaction().begin();
		    
		    Team teamToRemove = entitymanager.find(Team.class, teamName);
		    entitymanager.remove(teamToRemove);
		    entitymanager.getTransaction().commit();
		    entitymanager.close();
		    emfactory.close();
	}
	
	/**
	 * Delete a player from the database
	 * 
	 * @param playerName the name of the player to be deleted
	 */
	public void removePlayer(String playerName) {
		 EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Soccer League");
		    EntityManager entitymanager = emfactory.createEntityManager();
		    entitymanager.getTransaction().begin();
		    
		    Player playerToRemove = entitymanager.find(Player.class, playerName.trim());
		    entitymanager.remove(playerToRemove);
		    entitymanager.getTransaction().commit();
		    entitymanager.close();
		    emfactory.close();
	}
	
	/**
	 * Delete a manager from the database
	 * 
	 * @param managerName the name of the manager to be deleted
	 */
	public void removeManager(String managerName) {
		 EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Soccer League");
		    EntityManager entitymanager = emfactory.createEntityManager();
		    entitymanager.getTransaction().begin();
		    
		    Manager managerToRemove = entitymanager.find(Manager.class, managerName.trim());
		    entitymanager.remove(managerToRemove);
		    entitymanager.getTransaction().commit();
		    entitymanager.close();
		    emfactory.close();
	}
   
}

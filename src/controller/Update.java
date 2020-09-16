package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Player;

/**
 * This class updates a player's attributes
 * 
 * @author Ryan Kelleher
 * @version 1.0
 * @since 07/05/2020
 * 
 *
 */
public class Update {
	
	
	/**
	 * Search for a player in the database
	 * 
	 * @param playerName the name of the player that's goals need to updated   
	 * @param goals the new number of goals the player scored  
	 */
	public void editPlayerGoals(String playerName, int goals) {
		
	      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Soccer League");
	      
	      EntityManager entitymanager = emfactory.createEntityManager();
	      entitymanager.getTransaction().begin();
	      Player player = entitymanager.find(Player.class, playerName.trim());
	      
	      //before update
	      System.out.println(player);
	      player.setGoals(goals);
	      entitymanager.getTransaction().commit();
	      
	      //after update
	      System.out.println(player);
	      entitymanager.close();
	      emfactory.close();
	   }
	
}

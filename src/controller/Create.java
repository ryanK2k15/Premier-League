package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Manager;
import model.Player;
import model.Team;

/**
 * This class uses JPA to create entities and add them to a database
 * @author Ryan Kelleher
 * @version 1.0
 * @since 07/05/2020
 * 
 *
 */
public class Create {
	/**
	 * Create a team and add it to the database
	 * 
	 * @param name  name of the team
	 * @param color the team's jersey color
	 */
	public void createTeam(String name, String color) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Soccer League");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Team t1 = new Team();
		t1.setName(name);
		t1.setJerseyColour(color);

		entitymanager.persist(t1);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}

	/**
	 * Create a player and add it to the database
	 * 
	 * @param name  name of the player
	 * @param goals the number of goals the player scored
	 * @param team  the team the player plays for
	 */
	public void createPlayer(String name, int goals, String team) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Soccer League");

		EntityManager entitymanager = emfactory.createEntityManager();

		Query query = entitymanager.createQuery("Select t.teamId from Team t WHERE t.name = :name");
		query.setParameter("name", team);
		@SuppressWarnings("unchecked")
		List<Integer> list = query.getResultList();
		int teamId = list.get(0);
		entitymanager.getTransaction().begin();

		Player p = new Player();
		p.setName(name);
		p.setGoals(goals);
		p.setTeamId(teamId);

		entitymanager.persist(p);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}

	/**
	 * Create a manager and add it to the database
	 * 
	 * @param name       name of the manager
	 * @param starRating the star rating of the manager
	 * @param team       the team that is managed
	 */
	public void createManager(String name, int starRating, String team) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Soccer League");

		EntityManager entitymanager = emfactory.createEntityManager();

		Query query = entitymanager.createQuery("Select t.teamId from Team t WHERE t.name = :name");
		query.setParameter("name", team);
		@SuppressWarnings("unchecked")
		List<Integer> list = query.getResultList();
		int teamId = list.get(0);
		entitymanager.getTransaction().begin();

		Manager m = new Manager();
		m.setName(name);
		m.setStarRating(starRating);
		m.setTeamId(teamId);

		entitymanager.persist(m);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}

}

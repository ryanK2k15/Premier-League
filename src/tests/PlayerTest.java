package tests;

import static org.junit.Assert.*;

import javax.persistence.Column;
import javax.persistence.Table;
import org.junit.Test;
import model.Player;

public class PlayerTest {

	/*
	 * It uses the ReflectTool to get the Table annotation from the Player class
	 * then asserts the name of the table is "player".
	 */
	@Test
	public void table() {
		// setup
		Table t = ReflectTool.getClassAnnotation(Player.class, Table.class);
		// assert
		assertEquals("player", t.name());
	}

	/*
	 * The rest of the unit test methods in PlayerTest assert the values of the
	 * annotations in the Player class.
	 */
	@Test
	public void name() {
		// setup
		Column c = ReflectTool.getMethodAnnotation(Player.class, "getName", Column.class);
		// assert
		assertEquals("name", c.name());
	}

	@Test
	public void goals() {
		// setup
		Column c = ReflectTool.getMethodAnnotation(Player.class, "getGoals", Column.class);
		// assert
		assertEquals("goals", c.name());
	}

}

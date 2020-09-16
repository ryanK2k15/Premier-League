package tests;

import static org.junit.Assert.*;
import javax.persistence.Column;
import javax.persistence.Table;
import org.junit.Test;
import model.Team;

public class TeamTest {

	/*
	 * It uses the ReflectTool to get the Table annotation from the Team class
	 * then the name of the table is "team".
	 */
	@Test
	public void table() {
		// setup
		Table t = ReflectTool.getClassAnnotation(Team.class, Table.class);
		// assert
		assertEquals("team", t.name());
	}

	/*
	 * The rest of the unit test methods in TeamTest assert the values of the
	 * annotations in the Team class.
	 */
	@Test
	public void name() {
		// setup
		Column c = ReflectTool.getMethodAnnotation(Team.class, "getName", Column.class);
		// assert
		assertEquals("name", c.name());
	}

	@Test
	public void jerseyColour() {
		// setup
		Column c = ReflectTool.getMethodAnnotation(Team.class, "getJerseyColour", Column.class);
		// assert
		assertEquals("jerseyColour", c.name());
	}

}

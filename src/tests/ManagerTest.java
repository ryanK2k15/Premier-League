package tests;

import static org.junit.Assert.*;
import javax.persistence.Column;
import javax.persistence.Table;
import org.junit.Test;
import model.Manager;

public class ManagerTest {

	/*
	 * It uses the ReflectTool to get the Table annotation from the Manager class
	 * then asserts the name of the table is "manager".
	 */
	@Test
	public void table() {
		// setup
		Table t = ReflectTool.getClassAnnotation(Manager.class, Table.class);
		// assert
		assertEquals("manager", t.name());
	}

	/*
	 * The rest of the unit test methods in ManagerTest assert the values of the
	 * annotations in the Manager class.
	 */
	@Test
	public void name() {
		// setup
		Column c = ReflectTool.getMethodAnnotation(Manager.class, "getName", Column.class);
		// assert
		assertEquals("name", c.name());
	}

	@Test
	public void starRating() {
		// setup
		Column c = ReflectTool.getMethodAnnotation(Manager.class, "getStarRating", Column.class);
		// assert
		assertEquals("starRating", c.name());
	}

}

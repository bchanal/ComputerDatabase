import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.impl.CompanyDaoImpl;
import com.excilys.cdb.persistence.impl.ComputerDaoImpl;


public class ComputerDaoImplTest {

	public ComputerDaoImplTest() {

	}

	/**
	 * Test for @linkComputerDAOImpl
	 * 
	 * @author berangere
	 *
	 */
	
	@Autowired
	ComputerDaoImpl ctdao;
	@Autowired
	CompanyDaoImpl cndao;

	@Before
	public void setUp() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * test for the getAll method
	 */
	@Test
	public void testgetAll() {
		List<Computer> listComputer = ctdao.getAll();
		assertNotNull(listComputer);

		Computer comp = ctdao.getById(1);
		assertEquals(listComputer.get(0), comp);

		int sizeDB = ctdao.getNbComputers("");
		assertEquals(listComputer.size(), sizeDB);
	}

	/**
	 * test for getById : legal calls, valid and invalid
	 * 
	 */
	@Test
	public void testgetById() {

		Computer comp = ctdao.getById(14);
		assertNotNull(comp);

		Computer comp2 = ctdao.getById(14);
		assertEquals(comp, comp2);

		comp = ctdao.getById(7000);
		assertNull(comp);

	}

	/**
	 * test fot illegal call of getById
	 */
	@Test(expected = SQLException.class)
	public void testgetByIdInvalid() {

		Computer comp = ctdao.getById(-1);
		assertNotNull(comp);

	}

	/**
	 * test for legal calls of delete : valid
	 */
	@Test
	public void testDelete() {
		Computer comp = ctdao.getById(2);
		assertNotNull(comp);

		ctdao.delete(2);
		comp = ctdao.getById(2);

		assertNull(comp);

	}

	/**
	 * test for illegal call of delete method
	 */
	@Test(expected = SQLException.class)
	public void testDeleteInvalid() {

		ctdao.delete(-1);

	}

	/**
	 * tests for the create method
	 * 
	 */
	@Test
	public void testCreate() {

		List<Computer> listComputer = ctdao.getAll();
		int sizeMax = listComputer.size();

		Computer comp = ctdao.getById(sizeMax + 1);
		assertNull(comp);

		ctdao.create("ordiTest", null, null, 35);
		// check success

		comp = ctdao.getById(sizeMax + 1);
		assertNotNull(comp);

		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm");

		LocalDateTime ldt = LocalDateTime.parse("2015-12-12 12:12", formatter);
		LocalDateTime ldt2 = LocalDateTime.parse("2016-12-12 12:12", formatter);

		ctdao.create("ordiTest", ldt, null, 25);
		// check success : size de la liste ?
		ctdao.create("ordiTest", ldt, ldt2, 25);
		// check success

	}

	// /**
	// * test for invalid call (id <0) of the create method
	// */
	// @Test(expected = RuntimeException.class)
	// public void testCreateInvalid() {
	// ctdao.create("fail", null, null, -1);
	//
	// }
	/**
	 * test for invalid call (wrong date format) of the create method
	 */
	@Test(expected = RuntimeException.class)
	public void testCreateInvalidDate() {
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime ldt = LocalDateTime.parse("2016-12-12 12:12:12",
				formatter);

		ctdao.create("fail", null, ldt, 1);

	}

	/**
	 * test for the updatemethod, legal calls
	 */
	@Test
	public void testUpdate() {

		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime ldt = LocalDateTime.parse("2015-12-12 12:12", formatter);

		Company company = null;
		try {
			company = cndao.getById(7);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		Computer comp = ctdao.getById(14);
		Computer comp2 = new Computer(14, "computerTest", ldt, null, company);

		assertFalse(comp.equals(comp2));
		ctdao.update(comp2);

		comp = ctdao.getById(14);
		assertEquals(comp, comp2);

	}

	// @Test(expected = NullPointerException.class)
	// public void testUpdateInvalid() {
	//
	// Company company = null;
	// Computer comp2 = new Computer(-7, "computerTest", null, null, company);
	// ctdao.update(comp2);
	//
	// }

}

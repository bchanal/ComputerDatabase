import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.CompanyMapper;
import com.excilys.cdb.persistence.ComputerDAO;
import com.excilys.cdb.persistence.ComputerDAOImpl;
import com.excilys.cdb.persistence.ComputerMapper;
import com.excilys.cdb.persistence.ConnectDAO;


/**
 * Test for @linkComputerDAOImpl
 * @author berangere
 *
 */
public class ComputerDAOImplTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	
	
	@Before
	public void setUp() throws Exception {
				
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testgetAll() {
		List<Computer> listComputer =ComputerDAOImpl.getAll();
		assertNotNull(listComputer);
		
		Computer comp = ComputerDAOImpl.getById(1);
		assertEquals(listComputer.get(1),comp);
		
		assertNull(listComputer.get(-1));
		assertTrue(listComputer.size()==575);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testgetById() {
		
		Computer comp = ComputerDAOImpl.getById(12);
		assertNotNull(comp);
		
		Computer comp2 = ComputerDAOImpl.getById(12);
		assertEquals(comp,comp2);
		
		comp = ComputerDAOImpl.getById(7000);
		assertNull(comp);
		
		comp = ComputerDAOImpl.getById(-1);
		assertNull(comp);
		
	}

	@Test
	public void testDelete(){
		Computer comp = ComputerDAOImpl.getById(12);
		assertNotNull(comp);
		
		ComputerDAOImpl.delete(12);
		comp = ComputerDAOImpl.getById(12);

		assertNull(comp);
		
	}
	
	@Test
	public void testCreate(){
		
		List<Computer> listComputer =ComputerDAOImpl.getAll();
		int sizeMax=listComputer.size();

		
		Computer comp = ComputerDAOImpl.getById(sizeMax+1); // replace 575 by size+1
		assertNull(comp);
		
		ComputerDAOImpl.create("ordiTest", null,
				null, 35);
		//check success
		
		comp = ComputerDAOImpl.getById(sizeMax+1);
		assertNotNull(comp);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		LocalDateTime ldt =LocalDateTime.parse("2015-12-12 12:12", formatter);
		ComputerDAOImpl.create("ordiTest", ldt, null, 25);

		
		
	}
	
	
/**

	public void create(String name, LocalDateTime dateTime,
			LocalDateTime dateTimeFin, int comp);

	public void update(Computer computer);
 */
}

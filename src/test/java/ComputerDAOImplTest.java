import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.CompanyDaoImpl;
import com.excilys.cdb.persistence.ComputerDaoImpl;


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
		List<Computer> listComputer =ComputerDaoImpl.getAll();
		assertNotNull(listComputer);
		
		Computer comp = ComputerDaoImpl.instance.getById(1);
		assertEquals(listComputer.get(0),comp);
		
		assertNull(ComputerDaoImpl.instance.getById(5000));
		
		int sizeDB = ComputerDaoImpl.instance.getNbComputers();
		assertEquals(listComputer.size(),sizeDB);
	}
	
	@Test
	public void testgetById() {
		
		Computer comp = ComputerDaoImpl.instance.getById(14);
		assertNotNull(comp);
		
		Computer comp2 = ComputerDaoImpl.instance.getById(14);
		assertEquals(comp,comp2);
		
		comp = ComputerDaoImpl.instance.getById(7000);
		assertNull(comp);
		
		comp = ComputerDaoImpl.instance.getById(-1);
		assertNull(comp);
		
	}
	
	@Test(expected = SQLException.class)
	public void testgetByIdInvalid() {
		
		Computer comp = ComputerDaoImpl.instance.getById(-1);
		assertNotNull(comp);
		
	}

	@Test
	public void testDelete(){
		Computer comp = ComputerDaoImpl.instance.getById(2);
		assertNotNull(comp);
		
		ComputerDaoImpl.delete(2);
		comp = ComputerDaoImpl.instance.getById(2);

		assertNull(comp);
		
	}
	
	@Test(expected = SQLException.class)
	public void testDeleteInvalid() {
		
		ComputerDaoImpl.instance.delete(-1);
		
	}
	
	@Test
	public void testCreate(){
		
		List<Computer> listComputer =ComputerDaoImpl.getAll();
		int sizeMax=listComputer.size();

		
		Computer comp = ComputerDaoImpl.instance.getById(sizeMax+1); // replace 575 by size+1
		assertNull(comp);
		
		ComputerDaoImpl.instance.create("ordiTest", null, null, 35);
		//check success
		
		comp = ComputerDaoImpl.instance.getById(sizeMax+1);
		assertNotNull(comp);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		LocalDateTime ldt =LocalDateTime.parse("2015-12-12 12:12", formatter);
		LocalDateTime ldt2 = LocalDateTime.parse("2016-12-12 12:12", formatter);
		//LocalDateTime ldt3 = LocalDateTime.parse("2016-99-99 99:99", formatter);

		
		ComputerDaoImpl.instance.create("ordiTest", ldt, null, 25);
		//check success : size de la liste ?
		ComputerDaoImpl.instance.create("ordiTest", ldt, ldt2, 25);
		//check success
		
		//ComputerDAOImpl.instance.create("ordiTestFail", null, ldt3,1);
		//check fail
		//ComputerDAOImpl.instance.create("ordiTestFail", null, null, -1);
		//check fail
			
	}
	
	@Test(expected = SQLException.class)
	public void testCreateInvalid() {
		
		ComputerDaoImpl.instance.create("fail", null, null, -1);
		
	}
	
	@Test
	public void testUpdate() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime ldt =LocalDateTime.parse("2015-12-12 12:12", formatter);
		
		Company company=null;
		try {
			company = CompanyDaoImpl.instance.getById(7);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}		
		Computer comp = ComputerDaoImpl.instance.getById(14);
		Computer comp2 = new Computer(14, "computerTest", ldt,
			null, company);
		
		assertFalse(comp.equals(comp2));
		ComputerDaoImpl.instance.update(comp2);
		
		comp = ComputerDaoImpl.instance.getById(14);
		assertEquals(comp, comp2);


	}
	
	@Test(expected = SQLException.class)
	public void testUpdateInvalid() {
		
		Company company=null;
		Computer comp2 = new Computer(-7, "computerTest", null,	null, company);
		ComputerDaoImpl.instance.update(comp2);
		
	}

}

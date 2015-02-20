import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

import com.excilys.cdb.dto.ComputerDto;
import com.excilys.cdb.dto.DtoMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.CompanyDaoImpl;
import com.excilys.cdb.persistence.ComputerDaoImpl;
import com.excilys.cdb.service.ComputerServiceImpl;

/**
 * Test for @linkComputerDerviceImpl
 * 
 * @author bchanal
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ComputerServiceImplTest extends TestCase {
	
	@Mock private ComputerDaoImpl computerDao;
	private Page page;
	private Page page2;
	private List<ComputerDto> listDto;
	private List<Computer> list;

	@Before
	public void setUp(){
		
	page = ComputerDaoImpl.instance.getAPage(1,20, "");
	page2 = ComputerDaoImpl.instance.getAPage(1,20, "test");
	listDto = page.getList();
	list = new ArrayList<Computer>();
	
	for (ComputerDto cdto : listDto){
		Computer c = DtoMapper.dtoToComputer(cdto);
		list.add(c);
	}

	when(computerDao.getAll()).thenReturn(list);
	when(computerDao.getById(3)).thenReturn(list.get(3));
	when(computerDao.getAPage(1,20,"test")).thenReturn(page);
	when(computerDao.getNbComputers("test")).thenReturn(10);
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
		List<Computer> listComputer = ComputerServiceImpl.instance.getAll();
		assertNotNull(listComputer);

		Computer comp = ComputerServiceImpl.instance.getById(1);
		assertEquals(listComputer.get(0), comp);

		int sizeDB = ComputerServiceImpl.instance.getAPage(1, 50, "").getNbTotalComputer();
		assertEquals(listComputer.size(), sizeDB);
	}
/**
 * test for getById : legal calls, valid and invalid
 * 
 */
	@Test
	public void testgetById() {

		Computer comp = ComputerServiceImpl.instance.getById(14);
		assertNotNull(comp);

		Computer comp2 = ComputerServiceImpl.instance.getById(14);
		assertEquals(comp, comp2);

		comp = ComputerServiceImpl.instance.getById(7000);
		assertNull(comp);

	}
/** 
 * test fot illegal call of getById
 */
	@Test(expected = SQLException.class)
	public void testgetByIdInvalid() {

		Computer comp = ComputerServiceImpl.instance.getById(-1);
		assertNotNull(comp);

	}
/**
 * test for legal calls of delete : valid
 */
	@Test
	public void testDelete() {
		Computer comp = ComputerServiceImpl.instance.getById(2);
		assertNotNull(comp);

		ComputerDaoImpl.delete(2);
		comp = ComputerServiceImpl.instance.getById(2);

		assertNull(comp);

	}
/**
 * test for illegal call of delete method
 */
	@Test(expected = SQLException.class)
	public void testDeleteInvalid(){

		ComputerServiceImpl.instance.delete(-1);

	}
/**
 * tests for the create method
 * 
 */
	@Test
	public void testCreate() {

		List<Computer> listComputer = ComputerServiceImpl.instance.getAll();
		int sizeMax = listComputer.size();

		Computer comp = ComputerServiceImpl.instance.getById(sizeMax + 1);
		assertNull(comp);

		ComputerDaoImpl.instance.create("ordiTest", null, null, 35);
		// check success

		comp = ComputerDaoImpl.instance.getById(sizeMax + 1);
		assertNotNull(comp);

		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm");

		LocalDateTime ldt = LocalDateTime.parse("2015-12-12 12:12", formatter);
		LocalDateTime ldt2 = LocalDateTime.parse("2016-12-12 12:12", formatter);

		ComputerServiceImpl.instance.create("ordiTest", ldt, null, 25);
		// check success : size de la liste ?
		ComputerServiceImpl.instance.create("ordiTest", ldt, ldt2, 25);
		// check success


	}

	/**
	 * test for invalid call (wrong date format) of the create method
	 */
	@Test(expected = RuntimeException.class)
	public void testCreateInvalidDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime ldt = LocalDateTime.parse("2016-12-12 12:12:12", formatter);

		ComputerServiceImpl.instance.create("fail", null, ldt, 1);

	}
/**
 * test for the updatemethod, legal calls
 */
	@Test
	public void testUpdate() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime ldt = LocalDateTime.parse("2015-12-12 12:12", formatter);

		Company company = null;
		try {
			company = CompanyDaoImpl.instance.getById(7);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		Computer comp = ComputerDaoImpl.instance.getById(14);
		Computer comp2 = new Computer(14, "computerTest", ldt, null, company);

		assertFalse(comp.equals(comp2));
		ComputerServiceImpl.instance.update(comp2);

		comp = ComputerServiceImpl.instance.getById(14);
		assertEquals(comp, comp2);

	}


}

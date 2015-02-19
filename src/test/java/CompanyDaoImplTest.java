import java.sql.SQLException;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDaoImpl;

/**
 * Test for @linkComputerDAOImpl
 * 
 * @author berangere
 *
 */
public class CompanyDaoImplTest extends TestCase {

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

	/**
	 * test the getAll method : legal calls, valid and invalid
	 */
	@Test
	public void testgetAll() {
		List<Company> listCompany;
		try {
			listCompany = CompanyDaoImpl.instance.getAll();
			assertNotNull(listCompany);

			Company comp = CompanyDaoImpl.instance.getById(1);
			assertEquals(listCompany.get(0), comp);

			assertNull(CompanyDaoImpl.instance.getById(1000));
			assertTrue(listCompany.size() == 42);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	/**
	 * test the getById method : legal calls, valid and invalid
	 */
	@Test
	public void testgetById() {

		Company comp;
		try {
			comp = CompanyDaoImpl.instance.getById(12);
			assertNotNull(comp);

			Company comp2 = CompanyDaoImpl.instance.getById(12);
			assertEquals(comp, comp2);

			comp = CompanyDaoImpl.instance.getById(100);
			assertNull(comp);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();

		}

	}
/**
 * test : illegal call of getById method
 * @throws SQLException
 */
	@Test(expected = SQLException.class)
	public void testgetByIdInvalid() throws SQLException {
		assertNull(CompanyDaoImpl.instance.getById(-1));

	}
}

import java.sql.SQLException;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDAOImpl;

/**
 * Test for @linkComputerDAOImpl
 * 
 * @author berangere
 *
 */
public class CompanyDAOImplTest extends TestCase {

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
		List<Company> listCompany;
		try {
			listCompany = CompanyDAOImpl.instance.getAll();
			assertNotNull(listCompany);

			Company comp = CompanyDAOImpl.instance.getById(1);
			assertEquals(listCompany.get(0), comp);

			assertNull(CompanyDAOImpl.instance.getById(1000));
			assertTrue(listCompany.size() == 42);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	@Test
	public void testgetById() {

		Company comp;
		try {
			comp = CompanyDAOImpl.instance.getById(12);
			assertNotNull(comp);

			Company comp2 = CompanyDAOImpl.instance.getById(12);
			assertEquals(comp, comp2);

			//comp = CompanyDAOImpl.instance.getById(100);
			//assertNull(comp);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();

		}

	}
}

import java.sql.SQLException;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.impl.CompanyDaoImpl;

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
	 * test the getAll method : legal calls, valid and invalid
	 */
	@Test
	public void testgetAll() {
		List<Company> listCompany;
		try {
			listCompany = cndao.getAll();
			assertNotNull(listCompany);

			Company comp = cndao.getById(1);
			assertEquals(listCompany.get(0), comp);

			assertNull(cndao.getById(1000));
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
			comp = cndao.getById(12);
			assertNotNull(comp);

			Company comp2 = cndao.getById(12);
			assertEquals(comp, comp2);

			comp = cndao.getById(100);
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
		assertNull(cndao.getById(-1));

	}
}

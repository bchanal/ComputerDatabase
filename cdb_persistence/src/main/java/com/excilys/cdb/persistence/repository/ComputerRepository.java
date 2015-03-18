package com.excilys.cdb.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.excilys.cdb.model.Computer;
/**
 *
 * @author berangere
 *
 */
public interface ComputerRepository extends CrudRepository<Computer, Integer> {
    /**
     * delete a company in the db
     * @param id the id of the computer to delete
     */
    @Query(value="DELETE FROM computer WHERE company_id= :id", nativeQuery = true)
    public void deleteByCompany(@Param("id") int id);

/**
 * get a page of computers
 * @param index the first id to get
 * @param nb the number of computers to get
 * @param name the name searched
 * @param column the criterion (name of a column) to sort the results
 * @return the page of computers
 */
    @Query(value = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id "
            + "WHERE computer.name LIKE %:search% OR company.name LIKE %:search% ORDER BY :column LIMIT :index , :nb ;", nativeQuery = true)
    public List<Computer> getAPage(@Param("index") int index, @Param("nb") int nb,
            @Param("search") String name, @Param("column") int column);
/**
 * search a name in a list of computers
 * @param name the name to search
 * @param companyName the name to search, in the companies names
 * @return the page of results
 */
    @Query(value= "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id "
               + "WHERE computer.name LIKE %:name% OR company.name LIKE %:companyName%;", nativeQuery=true)
    public List<Computer> findByName(@Param("name") String name, @Param("companyName") String companyName);

/**
 * get the numbers of computers
 * @param name the name searched
 * @return the number of computers
 */
    @Query(value = "SELECT COUNT(*) FROM computer LEFT JOIN company ON computer.company_id = company.id "
            + "WHERE computer.name LIKE %:search% OR company.name LIKE %:search%", nativeQuery = true)
    public int getCountComputers(@Param("search") String name);

}

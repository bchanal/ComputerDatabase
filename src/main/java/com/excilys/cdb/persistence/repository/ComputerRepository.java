package com.excilys.cdb.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.excilys.cdb.model.Computer;

public interface ComputerRepository extends CrudRepository<Computer, Integer> {
    
    @Query(value="DELETE FROM computer WHERE id= :id", nativeQuery = true)
    public void deleteByCompany(@Param("id") int id);

    @Query(value = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id "
            + "WHERE computer.name LIKE %:search% OR company.name LIKE %:search% ORDER BY :column LIMIT :index , :nb ;", nativeQuery = true)
    public List<Computer> getAPage(@Param("index") int index, @Param("nb") int nb,
            @Param("search") String name, @Param("column") int column);

    @Query(value= "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id "
               + "WHERE computer.name LIKE %:name% OR company.name LIKE %:companyName%;", nativeQuery=true)
    public List<Computer> findByName(@Param("name") String name, @Param("companyName") String companyName);

    @Query(value = "SELECT COUNT(*) FROM computer LEFT JOIN company ON computer.company_id = company.id "
            + "WHERE computer.name LIKE %:search% OR company.name LIKE %:search%", nativeQuery = true)
    public int getCountComputers(@Param("search") String name);

}

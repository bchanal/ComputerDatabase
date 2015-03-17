package com.excilys.cdb.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.excilys.cdb.model.User;

public interface UserRepository extends CrudRepository<User, String> {
    
    public User findOne(String login);

}

package com.excilys.cdb.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.cdb.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    
    public User findOne(String login);

}

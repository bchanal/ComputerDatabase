package com.excilys.cdb.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.excilys.cdb.model.User;

public interface UserService extends UserDetailsService{
/**
 * get a user with his login
 * @param login the login to get
 * @return the user
 */
    public User getUser(String login);
    
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException;

}

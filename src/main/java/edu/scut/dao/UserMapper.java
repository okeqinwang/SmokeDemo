package edu.scut.dao;

import java.util.List;

import edu.scut.model.User;

public interface UserMapper {
	public List<User> findByName(String name);  
    public void insertUser(User user); 
}

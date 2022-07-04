package dev.llewellyn.services;

import dev.llewellyn.models.User;
import dev.llewellyn.repositories.UserDAO;

public class UserService {

	private static UserDAO userDao;

	public UserService(UserDAO userDao) {
		UserService.userDao = userDao;
	}
	
	public User loginUser(User loginUser) throws Exception {
		User user = userDao.getUserByEmail(loginUser.getEmail());
		
		if (user == null) {
			throw new Exception("User with email " + loginUser.getEmail() + " not found");
		}
		
		if (user.getEmail().equals(loginUser.getEmail()) && user.getPass().equals(loginUser.getPass())) {
			return user;
		} else {
			throw new Exception("Invalid credentials");
		}
	}

	public User createUser(User u) throws Exception {
		User createdUser = userDao.createUser(u);
		
		if (createdUser == null) {
			throw new Exception("Email already in use");
		} else {
			return createdUser;
		}
	}
}

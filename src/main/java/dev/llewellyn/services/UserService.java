package dev.llewellyn.services;

import java.util.List;

import dev.llewellyn.models.User;
import dev.llewellyn.repositories.UserDAO;

public class UserService {

	private static UserDAO userDao;

	public UserService(UserDAO userDao) {
		UserService.userDao = userDao;
	}

	public User createUser(User u) {
		User createdUser = userDao.createUser(u);
		return createdUser;
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public User getUserById(int id) throws Exception {
		User u = userDao.getUserById(id);

		if (u == null) {
			throw new Exception("User not found");
		}

		return u;
	}
}

package dev.llewellyn.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.llewellyn.models.User;
import dev.llewellyn.repositories.UserDAO;

public class UserServiceTests {

	private static UserDAO mockUserDao;
	private static UserService us;
	
	@BeforeEach
	public void setUp() {
		mockUserDao = mock(UserDAO.class);
		us = new UserService(mockUserDao);
	}
	
	@Test
	public void shouldReturnNewUser() {
		User u = new User(1, "Jay", "Crandal", "jc@yahoo.com", "pass", 1000, false);
		
		when(mockUserDao.createUser(u)).thenReturn(u);
		
		try {
			assertEquals(u, us.createUser(u));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void loginAttemptWithValidCredentials() {
		User u = new User(1, "Elon", "Musk", "em@yahoo.com", "space", 1000, true);
		
		when(mockUserDao.getUserByEmail("em@yahoo.com")).thenReturn(u);
		
		try {
			assertEquals(u, us.loginUser(u));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void loginAttemptWithInvalidEmail() {
		User uAttempt = new User(1, "Elon", "Musk", "em@yahoo.com", "space", 1000, true);
		
		when(mockUserDao.getUserByEmail("em@yahoo.com")).thenReturn(null);
		
		Exception thrown = assertThrows(Exception.class, () -> { us.loginUser(uAttempt); });
		
		assertEquals("User not found", thrown.getMessage());
	}
	
	@Test
	public void loginAttemptWithInvalidPassword() {
		User uAttempt = new User(1, "Elon", "Musk", "em@yahoo.com", "space", 1000, true);
		User uReal = new User(1, "Elon", "Musk", "em@yahoo.com", "rocket", 1000, true);
		
		when(mockUserDao.getUserByEmail("em@yahoo.com")).thenReturn(uReal);
		
		Exception thrown = assertThrows(Exception.class, () -> { us.loginUser(uAttempt); });
		
		assertEquals("Invalid credentials", thrown.getMessage());
	}
}

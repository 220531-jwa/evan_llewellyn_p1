package dev.llewellyn.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dev.llewellyn.models.User;
import dev.llewellyn.repositories.UserDAO;
import dev.llewellyn.services.UserService;
import io.javalin.http.Context;

public class UserController {

	private static UserService us = new UserService(new UserDAO());
	private static Logger log = LogManager.getLogger(UserController.class);

	public static void loginUser(Context ctx) {
		log.info("HTTP Request received at endpoint /login");	
		User userFromReqBody = ctx.bodyAsClass(User.class);
		
		try {
			User u = us.loginUser(userFromReqBody);
			log.info("Successful login");
			ctx.json(201);
			ctx.json(u);
		} catch (Exception e) {
			log.info("Login failed - Invalid credentials");
			ctx.status(404);
			ctx.result("Invalid credentials");
		}
	}
	
	public static void createNewUser(Context ctx) {
		// Need to check if email is already in use, do in service
		log.info("HTTP Request recieved at endpoint /users");
		// Should check to make sure new user was created
		
		User userFromReqBody = ctx.bodyAsClass(User.class);
		User u = us.createUser(userFromReqBody);
		ctx.status(201);
		ctx.json(u);
	}

	public static void getAllUsers(Context ctx) {
		ctx.status(200);
		List<User> users = us.getAllUsers();
		ctx.json(users);
	}

	public static void getUserById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		
		try {
			User c = us.getUserById(id);
			ctx.status(200);
			ctx.json(c);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.status(404);
			ctx.result("User not found");
		}
	}	
}

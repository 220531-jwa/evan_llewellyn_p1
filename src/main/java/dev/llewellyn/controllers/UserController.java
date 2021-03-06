package dev.llewellyn.controllers;

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
		log.info("HTTP POST Request received at endpoint /login");	
		User userFromReqBody = ctx.bodyAsClass(User.class);
		
		try {
			User u = us.loginUser(userFromReqBody);
			log.info("Successful login for user with id " + u.getUId());
			ctx.status(200);
			ctx.json(u);
		} catch (Exception e) {
			log.error("Login failed - " + e.getMessage());
			e.printStackTrace();
			ctx.status(400);
			ctx.result(e.getMessage());
		}
	}
	
//	public static void createNewUser(Context ctx) {
//		log.info("HTTP POST Request recieved at endpoint /users");
//		User userFromReqBody = ctx.bodyAsClass(User.class);
//		
//		try {
//			User u = us.createUser(userFromReqBody);
//			ctx.status(201);
//			ctx.json(u);
//		} catch (Exception e) {
//			log.error("Couldn't create user - " + userFromReqBody.getEmail() + " already in use");
//			e.printStackTrace();
//			ctx.status(400);
//			ctx.result("Email already in use");
//		}	
//	}
}

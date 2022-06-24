package dev.llewellyn;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import static io.javalin.apibuilder.ApiBuilder.*;

import dev.llewellyn.controllers.ReimbursementController;
import dev.llewellyn.controllers.UserController;

public class Driver {
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> {
			config.enableCorsForOrigin("http://localhost:8080");
			config.addStaticFiles("/public", Location.CLASSPATH);
		});
				
		app.start(8080);
		
		app.routes(() -> {
			path("/login", () -> {
//				get(UserController::loginUser);
			});
			path("/users", () -> {
//				post(UserController::createNewUser);
				path("/{id}", () -> {
					get(UserController::getUserById);
//					put(UserController::updateUser);
//					delete(UserController::deleteUser);
					path("/reimbursements", () -> {
						post(ReimbursementController::createNewReimbursement);
						get(ReimbursementController::getAllReimbursements);
						path("/{reimbursementId}", () -> {
//							get(ReimbursementController::getReimbursementById); // Not sure if necessary
							put(ReimbursementController::updateReimbursement);
//							delete(ReimbursementController::deleteReimbursement);
						});
					});
				});
			});
		});
		
		// Will direct to login page
		app.get("/", ctx -> {
			ctx.status(200);
			ctx.result("Welcome to the Employee Reimbursement Service!");
		});
	}
	
}

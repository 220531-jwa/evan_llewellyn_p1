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
				post(UserController::loginUser);
			});
			path("/reimbursements", () -> {
				get(ReimbursementController::getAllReimbursements);
			});
			path("/users", () -> {
//				post(UserController::createNewUser); // Stretch goal
				path("/{id}", () -> {
					get(UserController::getUserById);
//					put(UserController::updateUser); // Stretch goal
					path("/reimbursements", () -> {
						post(ReimbursementController::createNewReimbursement);
						get(ReimbursementController::getAllReimbursementsForUser);
						path("/{reimbursementId}", () -> {
//							get(ReimbursementController::getReimbursementById); // Not sure if necessary
							put(ReimbursementController::updateReimbursement);
						});
					});
				});
			});
		});
		
		// Redirect base url to login page
		app.get("/", ctx -> {
			ctx.redirect("login.html");
		});
	}
	
}

package dev.llewellyn.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dev.llewellyn.models.Reimbursement;
import dev.llewellyn.models.UserReimbursementJoin;
import dev.llewellyn.repositories.ReimbursementDAO;
import dev.llewellyn.repositories.UserDAO;
import dev.llewellyn.services.ReimbursementService;
import io.javalin.http.Context;

public class ReimbursementController {

	private static ReimbursementService rs = new ReimbursementService(new ReimbursementDAO(), new UserDAO());
	private static Logger log = LogManager.getLogger(UserController.class);

	public static void createNewReimbursement(Context ctx) {
		log.info("HTTP POST Request received at endpoint /reimbursements");	
		int id = Integer.parseInt(ctx.pathParam("id"));
		Reimbursement rFromReqBody = ctx.bodyAsClass(Reimbursement.class);
		rFromReqBody.setUserId(id);

		try {
			Reimbursement r = rs.createReimbursement(rFromReqBody);
			ctx.status(201);
			ctx.json(r);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			ctx.status(400);
			ctx.result(e.getMessage());
		}
		
	}

	public static void getAllReimbursements(Context ctx) {
		log.info("HTTP GET Request received at endpoint /reimbursements");
		ctx.status(200);
		List<UserReimbursementJoin> reimbursements = rs.getAllReimbursements();
		ctx.json(reimbursements);
	}
	
	public static void getAllReimbursementsForUser(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		log.info("HTTP GET Request received at endpoint /users/" + id + "/reimbursements");
		List<Reimbursement> reimbursements = rs.getAllReimbursementsForUser(id);
		
		ctx.status(200);
		ctx.json(reimbursements);
	}

	public static void updateReimbursement(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		int rId = Integer.parseInt(ctx.pathParam("reimbursementId"));
		log.info("HTTP PATCH Request received at endpoint /users/" + id + "/reimbursements/" + rId);
		Reimbursement rFromReqBody = ctx.bodyAsClass(Reimbursement.class);
		rFromReqBody.setUserId(id);
		rFromReqBody.setrId(rId);

		try {
			rs.updateReimbursement(rFromReqBody);
			log.info("Succefully updated reimbursement with id " + rId);
			ctx.status(204);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			ctx.status(404);
			ctx.result(e.getMessage());
		}
	}
}

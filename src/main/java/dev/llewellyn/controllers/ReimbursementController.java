package dev.llewellyn.controllers;

import java.util.List;

import dev.llewellyn.models.Reimbursement;
import dev.llewellyn.models.UserReimbursementJoin;
import dev.llewellyn.repositories.ReimbursementDAO;
import dev.llewellyn.services.ReimbursementService;
import io.javalin.http.Context;

public class ReimbursementController {

	private static ReimbursementService rs = new ReimbursementService(new ReimbursementDAO());

	public static void createNewReimbursement(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		Reimbursement rFromReqBody = ctx.bodyAsClass(Reimbursement.class);
		rFromReqBody.setUserId(id);
		Reimbursement r = rs.createReimbursement(rFromReqBody);
		ctx.status(201);
		ctx.json(r);
	}

	public static void getAllReimbursements(Context ctx) {
		ctx.status(200);
		List<UserReimbursementJoin> reimbursements = rs.getAllReimbursements();
		ctx.json(reimbursements);
	}
	
	public static void getAllReimbursementsForUser(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		List<Reimbursement> reimbursements = rs.getAllReimbursementsForUser(id);
		
		ctx.status(200);
		ctx.json(reimbursements);
	}

	public static void updateReimbursement(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		int rId = Integer.parseInt(ctx.pathParam("reimbursementId"));
		Reimbursement rFromReqBody = ctx.bodyAsClass(Reimbursement.class);
		rFromReqBody.setUserId(id);
		rFromReqBody.setrId(rId);

		try {
			rs.updateReimbursement(rFromReqBody);
			ctx.status(204);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.status(404);
			ctx.result("Reimbursement not found");
		}
	}
}

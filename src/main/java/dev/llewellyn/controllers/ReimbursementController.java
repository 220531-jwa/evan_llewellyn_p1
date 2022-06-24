package dev.llewellyn.controllers;

import java.util.List;

import dev.llewellyn.models.Reimbursement;
import dev.llewellyn.repositories.ReimbursementDAO;
import dev.llewellyn.services.ReimbursementService;
import io.javalin.http.Context;

public class ReimbursementController {

	private static ReimbursementService rs = new ReimbursementService(new ReimbursementDAO());

	public static void createNewReimbursement(Context ctx) {
		ctx.status(201);
		Reimbursement rFromReqBody = ctx.bodyAsClass(Reimbursement.class);
		Reimbursement r = rs.createReimbursement(rFromReqBody);
		ctx.json(r);
	}

	public static void getAllReimbursements(Context ctx) {
		ctx.status(200);
		List<Reimbursement> reimbursements = rs.getAllReimbursements();
		ctx.json(reimbursements);
	}

	public static void updateReimbursement(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("reimbursementId"));
		Reimbursement rFromReqBody = ctx.bodyAsClass(Reimbursement.class);
		rFromReqBody.setUserId(id);

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

package dev.llewellyn.services;

import java.util.List;

import dev.llewellyn.models.Reimbursement;
import dev.llewellyn.repositories.ReimbursementDAO;

public class ReimbursementService {

	private static ReimbursementDAO rDao;

	public ReimbursementService(ReimbursementDAO rDao) {
		ReimbursementService.rDao = rDao;
	}

	public Reimbursement createReimbursement(Reimbursement r) {
		Reimbursement createdReimbursement = rDao.createUser(r);
		return createdReimbursement;
	}

	public List<Reimbursement> getAllReimbursements() {
		return rDao.getAllReimbursements();
	}

	public int updateReimbursement(Reimbursement rChanged) throws Exception {
		int success = rDao.updateReimbursement(rChanged);

		if (success == 0) {
			throw new Exception("Reimbursement not found");
		}

		return success;
	}
}

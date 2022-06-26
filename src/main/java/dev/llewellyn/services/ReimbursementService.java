package dev.llewellyn.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import dev.llewellyn.models.Reimbursement;
import dev.llewellyn.repositories.ReimbursementDAO;

public class ReimbursementService {

	private static ReimbursementDAO rDao;

	public ReimbursementService(ReimbursementDAO rDao) {
		ReimbursementService.rDao = rDao;
	}

	public Reimbursement createReimbursement(Reimbursement r) {
		double rValue;
		
		switch(r.getRType()) {
			case "University Course":
				rValue = r.getRCost() * 0.8;
				break;
			case "Seminar":
				rValue = r.getRCost() * 0.6;
				break;
			case "Certification":
				rValue = r.getRCost();
				break;
			case "Certification Preparation Class":
				rValue = r.getRCost() * 0.75;
				break;
			case "Technical Training":
				rValue = r.getRCost() * 0.9;
				break;
			case "Other":
				rValue = r.getRCost() * 0.3;
				break;
			default:
				rValue = 0d;
		}
		
		// Make sure value has only two places
		BigDecimal bd = new BigDecimal(Double.toString(rValue));
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
	    r.setReimbursementAmount(bd.doubleValue());
		
		Reimbursement createdReimbursement = rDao.createReimbursement(r);
		return createdReimbursement;
	}

	public List<Reimbursement> getAllReimbursements() {
		return rDao.getAllReimbursements();
	}

	public int updateReimbursement(Reimbursement rChanged) throws Exception {
		int success = rDao.updateReimbursement(rChanged);

		if (success == 0) {
			throw new Exception("Reimbursement not found");
		} else {
			return success;
		}
	}
}

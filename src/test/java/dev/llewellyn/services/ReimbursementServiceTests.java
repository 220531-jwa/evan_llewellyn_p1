package dev.llewellyn.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.llewellyn.models.Reimbursement;
import dev.llewellyn.models.UserReimbursementJoin;
import dev.llewellyn.repositories.ReimbursementDAO;
import dev.llewellyn.repositories.UserDAO;

public class ReimbursementServiceTests {

	private static ReimbursementDAO mockRDao;
	private static UserDAO mockUserDao;
	private static ReimbursementService rs;

	private static Date startDate;
	private static Date endDate;

	@BeforeEach
	public void setUp() {
		mockRDao = mock(ReimbursementDAO.class);
		mockUserDao = mock(UserDAO.class);
		rs = new ReimbursementService(mockRDao, mockUserDao);

		LocalDate localDate = LocalDate.of(2022, 7, 1);
		startDate = Date.valueOf(localDate);
		LocalDate localDate2 = LocalDate.of(2022, 7, 20);
		endDate = Date.valueOf(localDate2);
	}

	@Test
	public void shouldReturnNewReimbursement() {
		Reimbursement input = new Reimbursement(1, 1, "Submitted", "Desc", 100.00, "WPI", startDate, endDate,
				Time.valueOf("18:45:00"), Time.valueOf("20:50:00"), "Certification", "Letter", "C", "", false, 0);
		Reimbursement output = new Reimbursement(1, 1, "Submitted", "Desc", 100.00, "WPI", startDate, endDate,
				Time.valueOf("18:45:00"), Time.valueOf("20:50:00"), "Certification", "Letter", "C", "", false, 100.00);

		when(mockRDao.createReimbursement(input)).thenReturn(output);

		try {
			assertEquals(output, rs.createReimbursement(input));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldReturnAllReimbursements() {
		List<UserReimbursementJoin> reimbursements = new ArrayList<>();

		reimbursements.add(new UserReimbursementJoin("Evan", "Llewellyn", "el@gmail.com", "123", 1000.00, false, 1, 1,
				"Submitted", "Desc", 100.00, "WPI", startDate, endDate, Time.valueOf("18:45:00"),
				Time.valueOf("20:50:00"), "Certification", "Letter", "C", "", false, 0));
		reimbursements.add(new UserReimbursementJoin("Lord", "Farquad", "lf@gmail.com", "shortKing", 1000.00, false, 2,
				2, "Urgent", "", 200.00, "RPI", startDate, endDate, Time.valueOf("18:45:00"), Time.valueOf("20:50:00"),
				"Seminar", "Pass/Fail", "Pass", "", false, 120.00));

		when(mockRDao.getAllReimbursements()).thenReturn(reimbursements);

		assertEquals(reimbursements, rs.getAllReimbursements());
	}

	@Test
	public void shouldReturnAllReimbursementsForUser() {
		List<Reimbursement> reimbursements = new ArrayList<>();

		reimbursements.add(new Reimbursement(2, 2, "Urgent", "", 200.00, "RPI", startDate, endDate,
				Time.valueOf("18:45:00"), Time.valueOf("20:50:00"), "Seminar", "Pass/Fail", "Pass", "", false, 120.00));
		reimbursements.add(new Reimbursement(3, 2, "Urgent", "", 200.00, "RPI", startDate, endDate,
				Time.valueOf("18:45:00"), Time.valueOf("20:50:00"), "Seminar", "Presentation", "N/A", "N/A", false, 120.00));

		when(mockRDao.getAllReimbursementsForUser(2)).thenReturn(reimbursements);

		assertEquals(reimbursements, rs.getAllReimbursementsForUser(2));
	}
	
	@Test
	public void shouldReturnSuccessAfterReimbursementUpdate() {
		Reimbursement input = new Reimbursement(0, 0, "Submitted", "", 0, "", null, null,
				null, null, "", "", "", "A", false, 120.55);
		
		when(mockRDao.updateReimbursement(input)).thenReturn(1);
		
		try {
			assertEquals(1, rs.updateReimbursement(input));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

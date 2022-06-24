package dev.llewellyn.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.llewellyn.models.Reimbursement;
import dev.llewellyn.utils.ConnectionUtil;

public class ReimbursementDAO {

	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	public Reimbursement createUser(Reimbursement r) {
		String sql = "insert into reimbursements values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning *";

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, r.getRId());
			ps.setInt(2, r.getUserId());
			ps.setString(3, r.getStatus());
			ps.setString(4, r.getDescription());
			ps.setInt(5, r.getRCost());
			ps.setString(6, r.getDatetime());
			ps.setString(7, r.getRType());
			ps.setString(8, r.getGradeType());
			ps.setString(9, r.getPassingGrade());
			ps.setString(10, r.getGradeReceived());
			ps.setBoolean(11, r.isPresentationSubmitted());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Reimbursement(rs.getInt("r_id"), rs.getInt("user_id"), rs.getString("status"),
						rs.getString("description"), rs.getInt("r_cost"), rs.getString("datetime"),
						rs.getString("r_type"), rs.getString("grade_type"), rs.getString("passing_grade"),
						rs.getString("grade_received"), rs.getBoolean("presentation_submitted"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Reimbursement> getAllReimbursements() {
		List<Reimbursement> reimbursements = new ArrayList<>();
		String sql = "select * from reimbursements";

		try (Connection conn = cu.getConnection();) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("r_id"), rs.getInt("user_id"), rs.getString("status"),
						rs.getString("description"), rs.getInt("r_cost"), rs.getString("datetime"),
						rs.getString("r_type"), rs.getString("grade_type"), rs.getString("passing_grade"),
						rs.getString("grade_received"), rs.getBoolean("presentation_submitted"));
				reimbursements.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}

	public int updateReimbursement(Reimbursement changedReimbursement) {
		String sql = "update reimbursements set user_id = ?, status = ?, description = ?, r_cost = ?, "
				+ "datetime = ?, r_type = ?, grade_type = ?, passing_grade = ?, grade_received = ?, "
				+ "presentation_submitted = ? where r_id = ?";

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, changedReimbursement.getUserId());
			ps.setString(2, changedReimbursement.getStatus());
			ps.setString(3, changedReimbursement.getDescription());
			ps.setInt(4, changedReimbursement.getRCost());
			ps.setString(5, changedReimbursement.getDatetime());
			ps.setString(6, changedReimbursement.getRType());
			ps.setString(7, changedReimbursement.getGradeType());
			ps.setString(8, changedReimbursement.getPassingGrade());
			ps.setString(9, changedReimbursement.getGradeReceived());
			ps.setBoolean(10, changedReimbursement.isPresentationSubmitted());
			ps.setInt(11, changedReimbursement.getRId());

			return ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}

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

	public Reimbursement createReimbursement(Reimbursement r) {
		String sql = "insert into reimbursements values (default, ?, ?::status, ?, ?, ?, ?, ?, ?, ?, ?::reimbursement_type, ?::grade_format, ?, ?, ?, ?) returning *";

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, r.getUserId());
			ps.setString(2, r.getStatus());
			ps.setString(3, r.getDescription());
			ps.setDouble(4, r.getrCost());
			ps.setString(5, r.getrLocation());
			ps.setDate(6, r.getStartDate());
			ps.setDate(7, r.getEndDate());
			ps.setTime(8, r.getStartTime());
			ps.setTime(9, r.getEndTime());
			ps.setString(10, r.getrType());
			ps.setString(11, r.getGradeFormat());
			ps.setString(12, r.getPassingGrade());
			ps.setString(13, r.getGradeReceived());
			ps.setBoolean(14, r.isPresentationSubmitted());
			ps.setDouble(15, r.getReimbursementAmount());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Reimbursement(rs.getInt("r_id"), rs.getInt("user_id"), rs.getString("status"),
						rs.getString("description"), rs.getInt("r_cost"), rs.getString("r_location"),
						rs.getDate("start_date"), rs.getDate("end_date"), rs.getTime("start_time"),
						rs.getTime("end_time"), rs.getString("r_type"), rs.getString("grade_format"),
						rs.getString("passing_grade"), rs.getString("grade_received"),
						rs.getBoolean("presentation_submitted"), rs.getFloat("reimbursement_amount"));
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
						rs.getString("description"), rs.getInt("r_cost"), rs.getString("r_location"),
						rs.getDate("start_date"), rs.getDate("end_date"), rs.getTime("start_time"),
						rs.getTime("end_time"), rs.getString("r_type"), rs.getString("grade_format"),
						rs.getString("passing_grade"), rs.getString("grade_received"),
						rs.getBoolean("presentation_submitted"), rs.getFloat("reimbursement_amount"));
				reimbursements.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}
	
	public List<Reimbursement> getAllReimbursementsForUser(int id) {
		List<Reimbursement> reimbursements = new ArrayList<>();
		String sql = "select * from reimbursements where user_id = ?";

		try (Connection conn = cu.getConnection();) {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("r_id"), rs.getInt("user_id"), rs.getString("status"),
						rs.getString("description"), rs.getInt("r_cost"), rs.getString("r_location"),
						rs.getDate("start_date"), rs.getDate("end_date"), rs.getTime("start_time"),
						rs.getTime("end_time"), rs.getString("r_type"), rs.getString("grade_format"),
						rs.getString("passing_grade"), rs.getString("grade_received"),
						rs.getBoolean("presentation_submitted"), rs.getFloat("reimbursement_amount"));
				reimbursements.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}

	public int updateReimbursement(Reimbursement changedR) {
		String sql = "update reimbursements set user_id = ?, status = ?, description = ?, r_cost = ?, "
				+ "datetime = ?, r_type = ?, grade_format = ?, passing_grade = ?, grade_received = ?, "
				+ "presentation_submitted = ?, reimbursement_amount = ? where r_id = ?";

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, changedR.getUserId());
			ps.setString(2, changedR.getStatus());
			ps.setString(3, changedR.getDescription());
			ps.setDouble(4, changedR.getrCost());
			ps.setString(5, changedR.getrLocation());
			ps.setDate(6, changedR.getStartDate());
			ps.setDate(7, changedR.getEndDate());
			ps.setTime(8, changedR.getStartTime());
			ps.setTime(9, changedR.getEndTime());
			ps.setString(6, changedR.getrType());
			ps.setString(7, changedR.getGradeFormat());
			ps.setString(8, changedR.getPassingGrade());
			ps.setString(9, changedR.getGradeReceived());
			ps.setBoolean(10, changedR.isPresentationSubmitted());
			ps.setDouble(11, changedR.getReimbursementAmount());
			ps.setInt(12, changedR.getrId());

			return ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}

package dev.llewellyn.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dev.llewellyn.models.User;
import dev.llewellyn.utils.ConnectionUtil;

public class UserDAO {

	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	public User createUser(User u) {
		String sql = "insert into users values (default, ?, ?, ?, ?, ?, ?) returning *";

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getPass());
			ps.setDouble(5, u.getAvailableAmount());
			ps.setBoolean(6, u.isFinanceManager());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new User(rs.getInt("u_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("email"), rs.getString("pass"), rs.getDouble("available_amount"),
						rs.getBoolean("is_finance_manager"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUserById(int id) {
		String sql = "select * from users where u_id = ?";

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new User(rs.getInt("u_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("email"), rs.getString("pass"), rs.getDouble("available_amount"),
						rs.getBoolean("is_finance_manager"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUserByEmail(String email) {
		String sql = "select * from users where email = ?";

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new User(rs.getInt("u_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("email"), rs.getString("pass"), rs.getDouble("available_amount"),
						rs.getBoolean("is_finance_manager"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int updateUser(int id, double newAmount) {
		String sql = "update users set available_amount = ?, where u_id = ?";

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, newAmount);
			ps.setInt(2, id);

			return ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}

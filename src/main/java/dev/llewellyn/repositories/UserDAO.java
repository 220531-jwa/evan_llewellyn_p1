package dev.llewellyn.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			ps.setInt(5, u.getAvailableAmount());
			ps.setBoolean(6, u.isFinanceManager());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new User(rs.getInt("u_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("email"), rs.getString("pass"), rs.getInt("available_amount"),
						rs.getBoolean("is_finance_manager"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		String sql = "select * from users";

		try (Connection conn = cu.getConnection();) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User c = new User(rs.getInt("u_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("email"), rs.getString("pass"), rs.getInt("available_amount"),
						rs.getBoolean("is_finance_manager"));
				users.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public User getUserById(int id) {
		String sql = "select * from users where id = ?";

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new User(rs.getInt("u_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("email"), rs.getString("pass"), rs.getInt("available_amount"),
						rs.getBoolean("is_finance_manager"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package kr.ac.kopo.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.MemberInfoType;

public class MemberDAO {

	public void insertMember(MemberVO m) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TBL_MEMBER(USER_ID, PASSWORD, USERNAME, EMAIL) ");
		sql.append("VALUES(?, ?, ?, ?)");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUsername());
			pstmt.setString(4, m.getEmail());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MemberVO selectMemberLogin(String id, String password) {
		MemberVO m = new MemberVO();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("  FROM TBL_MEMBER ");
		sql.append(" WHERE USER_ID  = ? ");
		sql.append("   AND PASSWORD = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				m.setUserId(rs.getString("USER_ID"));
				m.setPassword(rs.getString("PASSWORD"));
				m.setUsername(rs.getString("USERNAME"));
				m.setEmail(rs.getString("EMAIL"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}

	public boolean selectMemberRegistration(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT USER_ID ");
		sql.append("  FROM TBL_MEMBER ");
		sql.append(" WHERE USER_ID  = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public String selectId(String username, String email) {
		MemberVO m = new MemberVO();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT USER_ID ");
		sql.append("  FROM TBL_MEMBER ");
		sql.append(" WHERE USERNAME  = ? ");
		sql.append("   AND EMAIL = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, username);
			pstmt.setString(2, email);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				return rs.getString("USER_ID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String selectPwd(MemberVO m) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT PASSWORD ");
		sql.append("  FROM TBL_MEMBER ");
		sql.append(" WHERE USER_ID  = ? ");
		sql.append("   AND USERNAME = ? ");
		sql.append("   AND EMAIL = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUsername());
			pstmt.setString(3, m.getEmail());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				return rs.getString("PASSWORD");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean selectIdCheck(String userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 1 ");
		sql.append("  FROM TBL_MEMBER ");
		sql.append(" WHERE USER_ID = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void deleteMember(MemberVO m) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM TBL_MEMBER ");
		sql.append("WHERE USER_ID = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, m.getUserId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateMemberInfo(MemberVO m, MemberInfoType type) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE TBL_MEMBER ");
		switch (type) {
		case USERNAME:
			sql.append("   SET USERNAME = ? ");
			break;
		case EMAIL:
			sql.append("   SET EMAIL = ? ");
			break;
		case PASSWORD:
			sql.append("   SET PASSWORD = ? ");
		}
		sql.append(" WHERE USER_ID = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			switch (type) {
			case USERNAME:
				pstmt.setString(1, m.getUsername());
				break;
			case EMAIL:
				pstmt.setString(1, m.getEmail());
				break;
			case PASSWORD:
				pstmt.setString(1, m.getPassword());
				break;
			}
			pstmt.setString(2, m.getUserId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

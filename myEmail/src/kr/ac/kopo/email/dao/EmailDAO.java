package kr.ac.kopo.email.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.email.vo.EmailVO;
import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.EmailType;

public class EmailDAO {
	private List<EmailVO> list;

	public EmailDAO() {
		list = new ArrayList<>();
	}

	public List<EmailVO> selectAll(String loginId, EmailType type) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT NO, USER_ID, SENDER, RECEIVER, TITLE, TO_CHAR(CREATED_AT, 'YYYY-MM-DD HH24:MI:SS') AS CREATED_AT ");

		switch (type) {
		case RECEIVED_MAIL:
			sql.append("  FROM TBL_RECEIVED_MAIL ");
			break;
		case SEND_MAIL:
			sql.append("  FROM TBL_SEND_MAIL ");
			break;
		case BIN_MAIL:
			sql.append("  FROM TBL_BIN ");
			break;
		default:
			break;
		}
		sql.append(" WHERE USER_ID = ? ");
		sql.append(" ORDER BY CREATED_AT DESC ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, loginId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("NO");
				String sender = rs.getString("SENDER");
				String receiver = rs.getString("RECEIVER");
				String title = rs.getString("TITLE");
				String createdAt = rs.getString("CREATED_AT");
				EmailVO e = new EmailVO(no, sender, receiver, title, createdAt);
				list.add(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public EmailVO selectEmail(String loginId, int no, EmailType type) {
		EmailVO email = null;
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT SENDER, RECEIVER, TITLE, CONTENTS, TO_CHAR(CREATED_AT, 'YYYY-MM-DD HH24:MI:SS') AS CREATED_AT ");

		switch (type) {
		case RECEIVED_MAIL:
			sql.append("  FROM TBL_RECEIVED_MAIL ");
			break;
		case SEND_MAIL:
			sql.append("  FROM TBL_SEND_MAIL ");
			break;
		case BIN_MAIL:
			sql.append("  FROM TBL_BIN ");
			break;
		}
		sql.append(" WHERE NO = ? ");
		sql.append("   AND USER_ID = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, no);
			pstmt.setString(2, loginId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String sender = rs.getString("SENDER");
				String receiver = rs.getString("RECEIVER");
				String title = rs.getString("TITLE");
				String contents = rs.getString("CONTENTS");
				String createdAt = rs.getString("CREATED_AT");

				email = new EmailVO(no, sender, receiver, title, contents, createdAt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}

	public void insertMail(EmailVO email) {
		insertReceivedMail(email);
		insertSendMail(email);
	}

	public void insertMail(EmailVO email, String createdAt) {
		insertReceivedMail(email, createdAt);
		insertSendMail(email, createdAt);
	}

	private void insertReceivedMail(EmailVO email) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TBL_RECEIVED_MAIL(NO, USER_ID, SENDER, RECEIVER, TITLE, CONTENTS) ");
		sql.append("VALUES(SEQ_RECEIVED_MAIL_NO.NEXTVAL, ?, ?, ?, ?, ?)");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, email.getReceiver());
			pstmt.setString(2, email.getSender());
			pstmt.setString(3, email.getReceiver());
			pstmt.setString(4, email.getTitle());
			pstmt.setString(5, email.getContents());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertReceivedMail(EmailVO email, String createdAt) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TBL_RECEIVED_MAIL(NO, USER_ID, SENDER, RECEIVER, TITLE, CONTENTS, CREATED_AT) ");
		sql.append("VALUES(SEQ_RECEIVED_MAIL_NO.NEXTVAL, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, email.getReceiver());
			pstmt.setString(2, email.getSender());
			pstmt.setString(3, email.getReceiver());
			pstmt.setString(4, email.getTitle());
			pstmt.setString(5, email.getContents());
			pstmt.setString(6, createdAt);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertSendMail(EmailVO email) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TBL_SEND_MAIL(NO, USER_ID, SENDER, RECEIVER, TITLE, CONTENTS) ");
		sql.append("VALUES(SEQ_SEND_MAIL_NO.NEXTVAL, ?, ?, ?, ?, ?) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, email.getSender());
			pstmt.setString(2, email.getSender());
			pstmt.setString(3, email.getReceiver());
			pstmt.setString(4, email.getTitle());
			pstmt.setString(5, email.getContents());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertSendMail(EmailVO email, String createdAt) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TBL_SEND_MAIL(NO, USER_ID, SENDER, RECEIVER, TITLE, CONTENTS, CREATED_AT) ");
		sql.append("VALUES(SEQ_SEND_MAIL_NO.NEXTVAL, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, email.getSender());
			pstmt.setString(2, email.getSender());
			pstmt.setString(3, email.getReceiver());
			pstmt.setString(4, email.getTitle());
			pstmt.setString(5, email.getContents());
			pstmt.setString(6, createdAt);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertBinMail(String loginId, int no, EmailType type) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TBL_BIN(NO, USER_ID, SENDER, RECEIVER, TITLE, CONTENTS, CREATED_AT) ");
		sql.append("SELECT * ");

		switch (type) {
		case RECEIVED_MAIL:
			sql.append("  FROM TBL_RECEIVED_MAIL ");
			break;
		case SEND_MAIL:
			sql.append("  FROM TBL_SEND_MAIL ");
			break;
		}
		sql.append(" WHERE USER_ID = ? ");
		sql.append("   AND NO = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, loginId);
			pstmt.setInt(2, no);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int deleteMail(String loginId, int no, EmailType type) {
		int result = 0;
		StringBuilder sql = new StringBuilder();

		switch (type) {
		case RECEIVED_MAIL:
			insertBinMail(loginId, no, type);
			sql.append("DELETE FROM TBL_RECEIVED_MAIL ");
			break;
		case SEND_MAIL:
			insertBinMail(loginId, no, type);
			sql.append("DELETE FROM TBL_SEND_MAIL ");
			break;
		case BIN_MAIL:
			sql.append("DELETE FROM TBL_BIN ");
			break;
		}
		sql.append("WHERE USER_ID = ? ");
		sql.append("  AND NO = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, loginId);
			pstmt.setInt(2, no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void deleteAll(String loginId) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM TBL_BIN ");
		sql.append("WHERE USER_ID = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, loginId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

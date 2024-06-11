package kr.ac.kopo.email.service;

import java.util.List;

import kr.ac.kopo.email.dao.EmailDAO;
import kr.ac.kopo.email.vo.EmailVO;
import kr.ac.kopo.member.service.MemberServiceFactory;
import kr.ac.kopo.util.EmailType;

public class EmailService {

	private EmailDAO eDAO;

	public EmailService() {
		eDAO = new EmailDAO();
	}

	public List<EmailVO> showMailList(String loginId, EmailType type) {
		return eDAO.selectAll(loginId, type);
	}

	public EmailVO showEmailDetail(String loginId, int no, EmailType type) {
		return eDAO.selectEmail(loginId, no, type);
	}

	public boolean writeMail(EmailVO email) {
		if (!checkReceiver(email.getReceiver()))
			return false;
		else
			eDAO.insertMail(email);
		return true;
	}

	private boolean checkReceiver(String receiverId) {
		return MemberServiceFactory.getInstance().checkId(receiverId);
	}

	public int deleteMail(String loginId, int no, EmailType type) {
		return eDAO.deleteMail(loginId, no, type);
	}

	public void clearBin(String loginId) {
		eDAO.deleteAll(loginId);
	}

	public void recoverMail(String loginId, EmailVO email, EmailType type) {
		eDAO.insertMail(email, email.getCreatedAt());
		eDAO.deleteMail(loginId, email.getNo(), type);
	}

}

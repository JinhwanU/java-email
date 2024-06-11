package kr.ac.kopo.member.service;

import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.util.MemberInfoType;

public class MemberService {

	private MemberDAO mDAO;

	public MemberService() {
		mDAO = new MemberDAO();
	}

	public MemberVO checkLogin(String id, String password) {
		MemberVO m = mDAO.selectMemberLogin(id, password);
		if (m.getUserId() == null) {
			System.out.println("아이디 혹은 비밀번호가 틀렸습니다");
			return null;
		}
		m.setPassword(null);
		return m;
	}

	public boolean checkValid(MemberVO m, String passwordCheck) {
		if (!checkNull(m)) {
			return false;
		}
		if (!m.getPassword().equals(passwordCheck)) {
			System.out.println("비밀번호가 일치하지 않습니다");
			return false;
		}
		if (!mDAO.selectMemberRegistration(m.getUserId())) {
			System.out.println("이미 존재하는 아이디입니다");
			return false;
		}
		return true;
	}

	public boolean checkNull(MemberVO m) {
		if (m.getUserId().strip().length() == 0) {
			System.out.println("아이디를 다시 입력하세요");
			return false;
		}
		if (m.getPassword().strip().length() == 0) {
			System.out.println("비밀번호를 다시 입력하세요");
			return false;
		}
		if (m.getUsername().strip().length() == 0) {
			System.out.println("이름을 다시 입력하세요");
			return false;
		}
		if (m.getEmail().strip().length() == 0) {
			System.out.println("이메일을 다시 입력하세요");
			return false;
		}
		return true;
	}

	public void registerMember(MemberVO m) {
		mDAO.insertMember(m);
	}

	public String findId(String username, String email) {
		return mDAO.selectId(username, email);
	}

	public String findPwd(MemberVO m) {
		return mDAO.selectPwd(m);
	}

	public void withdrawMember(MemberVO m) {
		mDAO.deleteMember(m);
	}

	public boolean checkId(String userId) {
		return mDAO.selectIdCheck(userId);
	}

	public void updateMember(MemberVO member, MemberInfoType type) {
		mDAO.updateMemberInfo(member, type);
		
	}

}

package kr.ac.kopo.member.ui;

import kr.ac.kopo.member.vo.MemberVO;

public class LogoutUI extends BaseUI {

	private MemberVO loginMember;

	public LogoutUI(MemberVO loginMember) {
		super();
		this.loginMember = loginMember;
	}

	@Override
	public void execute() throws Exception {
		loginMember = null;
		System.out.println("로그아웃 완료");
		new InitUI().execute();
	}
}

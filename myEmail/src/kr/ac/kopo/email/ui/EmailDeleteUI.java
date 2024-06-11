package kr.ac.kopo.email.ui;

import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.util.EmailType;

public class EmailDeleteUI extends BaseUI {

	private MemberVO loginMember;
	private int no;
	private EmailType type;

	public EmailDeleteUI(MemberVO loginMember, int no, EmailType type) {
		super();
		this.loginMember = loginMember;
		this.no = no;
		this.type = type;
	}

	@Override
	public void execute() throws Exception {
		int failCheck = eService.deleteMail(loginMember.getUserId(), no, type);

		if (failCheck == 0)
			System.out.println("메일 삭제 실패");
		else
			System.out.println("메일 삭제 성공");

		new EmailUI(loginMember, type).execute();
	}

}

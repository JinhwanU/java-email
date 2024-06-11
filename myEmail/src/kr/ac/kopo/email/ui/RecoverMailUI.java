package kr.ac.kopo.email.ui;

import kr.ac.kopo.email.vo.EmailVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.util.EmailType;

public class RecoverMailUI extends BaseUI {

	private MemberVO loginMember;
	private EmailVO email;
	private EmailType type;

	public RecoverMailUI(MemberVO loginMember, EmailVO email, EmailType type) {
		super();
		this.loginMember = loginMember;
		this.email = email;
		this.type = type;
	}

	@Override
	public void execute() throws Exception {
		eService.recoverMail(loginMember.getUserId(), email, type);
		System.out.println("메일 복원 성공");
		new EmailUI(loginMember, type).execute();
	}

}

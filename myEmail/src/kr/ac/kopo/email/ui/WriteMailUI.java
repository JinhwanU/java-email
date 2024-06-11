package kr.ac.kopo.email.ui;

import kr.ac.kopo.email.vo.EmailVO;
import kr.ac.kopo.member.vo.MemberVO;

public class WriteMailUI extends BaseUI {

	private MemberVO loginMember;
	private String receiver;

	public WriteMailUI(MemberVO loginMember, String receiver) {
		super();
		this.loginMember = loginMember;
		this.receiver = receiver;
	}

	public WriteMailUI(MemberVO loginMember) {
		super();
		this.loginMember = loginMember;
		this.receiver = null;
	}

	@Override
	public void execute() throws Exception {
		EmailVO email = new EmailVO();
		System.out.println("--------------------------------------------------------------");
		System.out.println("\t\t\t메일 쓰기");
		System.out.println("--------------------------------------------------------------");

		email.setTitle(scanStr("메일 제목 : "));
		if (receiver == null)
			email.setReceiver(scanStr("받는이 : "));
		else
			email.setReceiver(receiver);
		email.setContents(scanStr("내용 : "));
		email.setSender(loginMember.getUserId());
		email.setUserId(loginMember.getUserId());
		if (!eService.writeMail(email))
			System.out.println("메일 전송 실패 : 존재하지 않는 회원(받는이)");
		else
			System.out.println("메일 전송 성공");
		System.out.println();

	}
}
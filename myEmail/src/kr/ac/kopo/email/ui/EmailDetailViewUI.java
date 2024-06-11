package kr.ac.kopo.email.ui;

import kr.ac.kopo.email.vo.EmailVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.util.EmailType;

public class EmailDetailViewUI extends BaseUI {

	private int no;

	private MemberVO loginMember;
	private EmailType type;

	public EmailDetailViewUI(int no, MemberVO loginMember, EmailType type) {
		super();
		this.no = no;
		this.loginMember = loginMember;
		this.type = type;
	}

	private void showDetail(EmailVO email) {
		System.out.println("--------------------------------------------------------------");
		System.out.println("제목 : " + email.getTitle());
		System.out.println("--------------------------------------------------------------");
		System.out.println("보낸시간 : " + email.getCreatedAt());
		System.out.println("--------------------------------------------------------------");
		System.out.println("보낸이 : " + email.getSender());
		System.out.println("받는이 : " + email.getReceiver());
		System.out.println("--------------------------------------------------------------");
		System.out.println("내용 : " + email.getContents());
		System.out.println("--------------------------------------------------------------");
		System.out.println();
	}

	private int choiceMenu() {
		switch (type) {
		case RECEIVED_MAIL:
		case SEND_MAIL:
			System.out.println("1. 답장하기");
			System.out.println("2. 삭제하기");
			break;
		case BIN_MAIL:
			System.out.println("1. 복원하기");
			System.out.println("2. 영구삭제");
			break;
		}
		System.out.println("0. 뒤로가기");
		return scanInt("항목을 선택하세요 : ");
	}

	@Override
	public void execute() throws Exception {
		IEmailUI ui = null;
		EmailVO email = eService.showEmailDetail(loginMember.getUserId(), no, type);

		showDetail(email);
		int choice = choiceMenu();
		switch (choice) {
		case 0:
			ui = new EmailUI(loginMember, type);
			break;
		case 1:
			if (type == EmailType.BIN_MAIL)
				ui = new RecoverMailUI(loginMember, email, type);
			else
				ui = new WriteMailUI(loginMember, email.getReceiver());
			break;
		case 2:
			ui = new EmailDeleteUI(loginMember, email.getNo(), type);
			break;
		}
		try {
			ui.execute();
		} catch (Exception e) {
			System.out.println("잘못선택하셨습니다");
			new EmailDetailViewUI(no, loginMember, type).execute();
		}

	}
}

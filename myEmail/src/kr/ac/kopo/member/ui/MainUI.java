package kr.ac.kopo.member.ui;

import kr.ac.kopo.email.ui.EmailUI;
import kr.ac.kopo.email.ui.IEmailUI;
import kr.ac.kopo.email.ui.WriteMailUI;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.util.EmailType;

public class MainUI extends BaseUI {

	private MemberVO loginMember;

	public MainUI(MemberVO loginMember) {
		super();
		this.loginMember = loginMember;
	}

	private int choiceMenu() {
		System.out.println("--------------------------------------------------------------");
		System.out.println("\t\t\t메인화면");
		System.out.println("--------------------------------------------------------------");
		System.out.println("1. 받은 메일함");
		System.out.println("2. 보낸 메일함");
		System.out.println("3. 메일 쓰기");
		System.out.println("4. 휴지통");
		System.out.println("5. 회원정보 수정");
		System.out.println("6. 회원 탈퇴");
		System.out.println("7. 로그아웃");
		System.out.println("--------------------------------------------------------------");
		return scanInt("항목을 선택하세요 : ");
	}

	@Override
	public void execute() throws Exception {
		while (loginMember.getUserId() != null) {
			IEmailUI ui = null;
			int type = choiceMenu();
			switch (type) {
			case 1:
				ui = new EmailUI(loginMember, EmailType.RECEIVED_MAIL);
				break;
			case 2:
				ui = new EmailUI(loginMember, EmailType.SEND_MAIL);
				break;
			case 3:
				ui = new WriteMailUI(loginMember);
				break;
			case 4:
				ui = new EmailUI(loginMember, EmailType.BIN_MAIL);
				break;
			case 5:
				ui = new MemberUpdateUI(loginMember);
				break;
			case 6:
				ui = new WithdrawalUI(loginMember);
				break;
			case 7:
				ui = new LogoutUI(loginMember);
				break;
				
			}
			if (ui != null)
				ui.execute();
			else
				System.out.println("잘못선택하셨습니다");
		}

	}
}

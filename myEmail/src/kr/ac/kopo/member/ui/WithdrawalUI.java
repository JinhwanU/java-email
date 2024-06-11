package kr.ac.kopo.member.ui;

import kr.ac.kopo.member.vo.MemberVO;

public class WithdrawalUI extends BaseUI {

	private MemberVO loginMember;

	public WithdrawalUI(MemberVO loginMember) {
		super();
		this.loginMember = loginMember;
	}

	private int choiceMenu() {
		System.out.println("--------------------------------------------------------------");
		System.out.println("\t\t\t회원탈퇴");
		System.out.println("--------------------------------------------------------------");
		System.out.println("아이디 : " + loginMember.getUserId());
		System.out.println("이름 : " + loginMember.getUsername());
		System.out.println("이메일 : " + loginMember.getEmail());
		System.out.println("--------------------------------------------------------------");
		return scanInt("정말 탈퇴하시겠습니까?([1] 예 [0] 뒤로가기)");
	}

	@Override
	public void execute() throws Exception {
		boolean check = true;
		while (check) {
			int choice = choiceMenu();
			switch (choice) {
			case 0:
				new MainUI(loginMember).execute();
				break;
			case 1:
				mService.withdrawMember(loginMember);
				System.out.println("회원탈퇴 성공");
				new InitUI().execute();
				break;
			default:
				System.out.println("잘못선택하셨습니다");
				break;
			}
		}
	}
}

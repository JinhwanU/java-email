package kr.ac.kopo.member.ui;

import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.util.MemberInfoType;

public class MemberUpdateUI extends BaseUI {

	private MemberVO loginMember;

	public MemberUpdateUI(MemberVO loginMember) {
		super();
		this.loginMember = loginMember;
	}

	private void printInfo() {
		System.out.println("--------------------------------------------------------------");
		System.out.println("\t\t\t" + loginMember.getUserId() + " 회원 정보");
		System.out.println("--------------------------------------------------------------");
		System.out.println("이름 : " + loginMember.getUsername());
		System.out.println("이메일 : " + loginMember.getEmail());
		System.out.println("--------------------------------------------------------------");
		System.out.println();
	}

	private int choiceMenu() {
		System.out.println("1. 이름");
		System.out.println("2. 이메일");
		System.out.println("3. 비밀번호");
		System.out.println("0. 뒤로가기");
		return scanInt("수정할 항목을 선택하세요 : ");
	}

	@Override
	public void execute() throws Exception {
		printInfo();
		int choice = choiceMenu();
		switch (choice) {
		case 0:
			new MainUI(loginMember).execute();
			break;
		case 1:
			loginMember.setUsername(scanStr("변경할 이름 : "));
			mService.updateMember(loginMember, MemberInfoType.USERNAME);
			System.out.println("이름 수정완료");
			break;
		case 2:
			loginMember.setEmail(scanStr("변경할 이메일 : "));
			mService.updateMember(loginMember, MemberInfoType.EMAIL);
			System.out.println("이메일 수정완료");
			break;
		case 3:
			loginMember.setPassword(scanStr("변경할 패스워드 : "));
			mService.updateMember(loginMember, MemberInfoType.PASSWORD);
			System.out.println("패스워드 수정완료");
			break;
		default:
			System.out.println("잘못선택하셨습니다");
			break;
		}

	}

}

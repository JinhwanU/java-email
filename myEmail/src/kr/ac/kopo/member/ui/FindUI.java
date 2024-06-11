package kr.ac.kopo.member.ui;

import kr.ac.kopo.member.vo.MemberVO;

public class FindUI extends BaseUI {

	private int choiceMenu() {

		System.out.println("--------------------------------------------------------------");
		System.out.println("1. 다시 입력");
		System.out.println("2. 아이디 찾기");
		System.out.println("3. 비밀번호 찾기");
		System.out.println("4. 뒤로가기");
		System.out.println("--------------------------------------------------------------");
		return scanInt("항목을 선택하세요 : ");
	}

	@Override
	public void execute() throws Exception {
		while (true) {
			int type = choiceMenu();
			switch (type) {
			case 1:
				new LoginUI().execute();
				break;
			case 2:
				findId();
				break;
			case 3:
				findPwd();
				break;
			case 4:
				new InitUI().execute();
				break;
			default:
				System.out.println("잘못선택하셨습니다");
			}
		}
	}

	private void findId() {
		String username = scanStr("이름 : ");
		String email = scanStr("이메일 : ");

		String id = mService.findId(username, email);

		if (id == null)
			System.out.println("일치하는 회원정보가 없습니다");
		else
			System.out.println("아이디 찾기 성공 : " + id);
	}

	private void findPwd() {
		String id = scanStr("아이디 : ");
		String username = scanStr("이름 : ");
		String email = scanStr("이메일 : ");

		MemberVO m = new MemberVO(id, null, username, email);
		String password = mService.findPwd(m);

		if (password == null)
			System.out.println("일치하는 회원정보가 없습니다");
		else
			System.out.println("비밀번호 찾기 성공 : " + password);
	}
}

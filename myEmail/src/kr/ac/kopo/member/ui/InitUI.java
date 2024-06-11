package kr.ac.kopo.member.ui;

import kr.ac.kopo.email.ui.IEmailUI;

public class InitUI extends BaseUI {

	private int choiceMenu() {

		System.out.println("--------------------------------------------------------------");
		System.out.println("\t\t\tE-mail 시스템");
		System.out.println("--------------------------------------------------------------");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 종료");
		System.out.println("--------------------------------------------------------------");

		return scanInt("항목을 선택하세요 : ");
	}

	@Override
	public void execute() throws Exception {
		while (true) {
			IEmailUI ui = null;
			int type = choiceMenu();
			switch (type) {
			case 1:
				ui = new LoginUI();
				break;
			case 2:
				ui = new RegistrationUI();
				break;
			case 3:
				ui = new ExitUI();
				break;
			}

			if (ui != null) {
				ui.execute();
			} else {
				System.out.println("잘못선택하셨습니다");
			}
		}
	}

}

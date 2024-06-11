package kr.ac.kopo.member.ui;

import kr.ac.kopo.member.vo.MemberVO;

public class LoginUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		MemberVO loginMember;
		String id = scanStr("아이디 : ");
		String password = scanStr("패스워드 : ");
		loginMember = mService.checkLogin(id, password);
		if (loginMember == null) {
			FindUI ui = new FindUI();
			ui.execute();
		} else {
			System.out.println("로그인 성공 - " + loginMember.getUserId()+" (" + loginMember.getUsername() + ")");
			MainUI main = new MainUI(loginMember);
			main.execute();
		}
	}
}

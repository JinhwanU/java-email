package kr.ac.kopo.member.ui;

import kr.ac.kopo.member.vo.MemberVO;

public class RegistrationUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		MemberVO m = new MemberVO();
		while (true) {
			m.setUserId(scanStr("아이디 : "));
			m.setPassword(scanStr("패스워드 : "));
			String passwordCheck = scanStr("패스워드 확인 : ");
			m.setUsername(scanStr("이름 : "));
			m.setEmail(scanStr("이메일 : "));

			if (mService.checkValid(m, passwordCheck))
				break;
		}
		mService.registerMember(m);
		System.out.println("회원가입 성공");
	}

}

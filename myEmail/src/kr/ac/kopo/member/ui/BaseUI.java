package kr.ac.kopo.member.ui;

import java.util.Scanner;

import kr.ac.kopo.email.ui.IEmailUI;
import kr.ac.kopo.member.service.MemberService;
import kr.ac.kopo.member.service.MemberServiceFactory;

public abstract class BaseUI implements IEmailUI {

	private Scanner sc;
	protected MemberService mService;

	public BaseUI() {
		sc = new Scanner(System.in);
		mService = MemberServiceFactory.getInstance();
	}

	public String scanStr(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}

	public int scanInt(String msg) {
		while (true) {
			try {
				int num = Integer.parseInt(scanStr(msg));
				return num;
			} catch (NumberFormatException e) {
				System.out.println("숫자로만 입력하세요");
			}
		}
	}
}

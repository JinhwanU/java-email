package kr.ac.kopo.email.ui;

import java.util.Scanner;

import kr.ac.kopo.email.service.EmailService;
import kr.ac.kopo.email.service.EmailServiceFactory;

public abstract class BaseUI implements IEmailUI {

	private Scanner sc;
	protected EmailService eService;

	public BaseUI() {
		sc = new Scanner(System.in);
		eService = EmailServiceFactory.getInstance();
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

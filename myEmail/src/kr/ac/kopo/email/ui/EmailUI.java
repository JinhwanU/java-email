package kr.ac.kopo.email.ui;

import java.util.List;

import kr.ac.kopo.email.vo.EmailVO;
import kr.ac.kopo.member.ui.MainUI;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.util.EmailType;

public class EmailUI extends BaseUI {

	private MemberVO loginMember;
	private EmailType type;
	private List<EmailVO> list;

	public EmailUI(MemberVO loginMember, EmailType type) {
		super();
		this.loginMember = loginMember;
		this.type = type;
	}

	private void receivedMail() {
		System.out.println("--------------------------------------------------------------");
		System.out.println("\t\t\t받은 메일함");
		System.out.println("--------------------------------------------------------------");
		System.out.println("번호\t보낸이\t날짜\t\t\t제목");
		System.out.println("--------------------------------------------------------------");

		list = eService.showMailList(loginMember.getUserId(), type);
		if (list.isEmpty())
			System.out.println("이메일이 존재하지 않습니다");
		else {
			for (EmailVO email : list) {
				System.out.println(email.getNo() + "\t" + email.getSender() + "\t" + email.getCreatedAt() + "\t"
						+ email.getTitle());
			}
		}
		System.out.println();
		list.clear();
	}

	private void sendMail() {
		System.out.println("--------------------------------------------------------------");
		System.out.println("\t\t\t보낸 메일함");
		System.out.println("--------------------------------------------------------------");
		System.out.println("번호\t받는이\t날짜\t\t\t제목");
		System.out.println("--------------------------------------------------------------");

		list = eService.showMailList(loginMember.getUserId(), type);
		if (list.isEmpty())
			System.out.println("이메일이 존재하지 않습니다");
		else {
			for (EmailVO email : list) {
				System.out.println(email.getNo() + "\t" + email.getReceiver() + "\t" + email.getCreatedAt() + "\t"
						+ email.getTitle());
			}
		}
		System.out.println();
		list.clear();
	}

	private void binMail() {
		System.out.println("--------------------------------------------------------------");
		System.out.println("\t\t\t휴지통");
		System.out.println("--------------------------------------------------------------");
		System.out.println("번호\t보낸이\t받는이\t날짜\t\t\t제목");
		System.out.println("--------------------------------------------------------------");

		list = eService.showMailList(loginMember.getUserId(), type);
		if (list.isEmpty())
			System.out.println("이메일이 존재하지 않습니다");
		else {
			for (EmailVO email : list) {
				System.out.println(email.getNo() + "\t" + email.getSender() + "\t" + email.getReceiver() + "\t"
						+ email.getCreatedAt() + "\t" + email.getTitle());
			}
		}
		System.out.println();
		list.clear();
	}

	private void choiceMenu() throws Exception {
		IEmailUI ui = null;
		int no = scanInt("메일을 선택하세요([0] 뒤로가기) : ");
		switch (no) {
		case 0:
			ui = new MainUI(loginMember);
			break;
		default:
			ui = new EmailDetailViewUI(no, loginMember, type);
			break;
		}
		try {
			ui.execute();
		} catch (Exception e) {
			System.out.println("존재하지 않는 메일번호입니다");
			new EmailUI(loginMember, type).execute();
		}
	}

	private void choiceBinMenu() throws Exception {
		int no = scanInt("메일을 선택하세요([0] 뒤로가기, [-1] 휴지통 비우기) : ");
		switch (no) {
		case -1:
			eService.clearBin(loginMember.getUserId());
			System.out.println("휴지통 비우기 성공");
			break;
		case 0:
			break;
		default:
			try {
				new EmailDetailViewUI(no, loginMember, type).execute();
			} catch (Exception e) {
				System.out.println("존재하지 않는 메일번호입니다");
				new EmailUI(loginMember, type).execute();
			}
			break;
		}
	}

	@Override
	public void execute() throws Exception {
		switch (type) {
		case RECEIVED_MAIL:
			receivedMail();
			choiceMenu();
			break;
		case SEND_MAIL:
			sendMail();
			choiceMenu();
			break;
		case BIN_MAIL:
			binMail();
			choiceBinMenu();
			break;
		}
	}
}

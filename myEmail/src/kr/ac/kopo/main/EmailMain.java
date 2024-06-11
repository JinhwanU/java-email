
package kr.ac.kopo.main;
import kr.ac.kopo.member.ui.InitUI;

public class EmailMain {
	public static void main(String[] args) {

		InitUI ui = new InitUI();
		try {
			ui.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package kr.ac.kopo.member.service;

public class MemberServiceFactory {

	private static MemberService service;

	public static MemberService getInstance() {
		if (service == null)
			service = new MemberService();
		return service;
	}
}

package kr.ac.kopo.email.vo;

public class EmailVO {

	private int no;
	private String userId;
	private String sender;
	private String receiver;
	private String title;
	private String contents;
	private String createdAt;

	public EmailVO() {
		super();
	}

	public EmailVO(int no, String title, String createdAt) {
		super();
		this.no = no;
		this.title = title;
		this.createdAt = createdAt;
	}
	
	public EmailVO(int no, String sender, String receiver, String title, String createdAt) {
		super();
		this.no = no;
		this.sender = sender;
		this.receiver = receiver;
		this.title = title;
		this.createdAt = createdAt;
	}

	public EmailVO(int no, String sender, String receiver, String title, String contents, String createdAt) {
		super();
		this.no = no;
		this.sender = sender;
		this.receiver = receiver;
		this.title = title;
		this.contents = contents;
		this.createdAt = createdAt;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "EmailVO [no=" + no + ", userId=" + userId + ", sender=" + sender + ", receiver=" + receiver + ", title="
				+ title + ", contents=" + contents + ", createdAt=" + createdAt + "]";
	}

}

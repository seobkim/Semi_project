package review.model.vo;

public class ReviewAndMember {
	
	private String title;
	private int memberNum;
	private String memberName;
	private String memberAddress;
	private String memberPhone;
	private int boardNo;
	
	public ReviewAndMember() {}
	
	public ReviewAndMember(String title, int memberNum, String memberName, String memberAddress, String memberPhone,
			int boardNo) {
		super();
		this.title = title;
		this.memberNum = memberNum;
		this.memberName = memberName;
		this.memberAddress = memberAddress;
		this.memberPhone = memberPhone;
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public void add(ReviewAndMember reviewAndMember) {
		// TODO Auto-generated method stub
		
	}
	

}

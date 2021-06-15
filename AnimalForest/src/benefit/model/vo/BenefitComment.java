package benefit.model.vo;

import java.sql.Date;

public class BenefitComment {

	private int commentNo;
	private int boardNo;
	private int memberNo;
	private int categoryNo;
	private String contents;
	private Date CDT;
	private Date MDT;
	private String memberId;
	
	public String getMemberId() {
		return memberId;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getMDT() {
		return MDT;
	}

	public void setMDT(Date mDT) {
		MDT = mDT;
	}

	public BenefitComment() {
		
	}
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getCDT() {
		return CDT;
	}
	public void setCDT(Date cDT) {
		CDT = cDT;
	}
}

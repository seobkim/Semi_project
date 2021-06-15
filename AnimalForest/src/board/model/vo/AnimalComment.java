package board.model.vo;

import java.sql.Date;

public class AnimalComment {

	private int commentNo;
	private int memberNum;
	private int boardNo;
	private String content;
	private Date cdt;
	private Date mdt;
	private String memberName;

	public AnimalComment() {
	}

	public AnimalComment(int commentNo, int memberNum, int boardNo, String content, Date cdt, Date mdt,
			String memberName) {
		super();
		this.commentNo = commentNo;
		this.memberNum = memberNum;
		this.boardNo = boardNo;
		this.content = content;
		this.cdt = cdt;
		this.mdt = mdt;
		this.memberName = memberName;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCdt() {
		return cdt;
	}

	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}

	public Date getMdt() {
		return mdt;
	}

	public void setMdt(Date mdt) {
		this.mdt = mdt;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	

}

package review.model.vo;

import java.sql.Date;

public class ReviewComment {
	
	private int comment_No;
	private int board_No;
	private int member_Num;
	private String content;
	private Date cdt;
	private String memberName;
	private String memberid;

	public ReviewComment() {
		
	}

	public ReviewComment(int comment_No, int board_No, int member_Num, String content, Date cdt) {
		super();
		this.comment_No = comment_No;
		this.board_No = board_No;
		this.member_Num = member_Num;
		this.content = content;
		this.cdt = cdt;
	}


	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getComment_No() {
		return comment_No;
	}



	public void setComment_No(int comment_No) {
		this.comment_No = comment_No;
	}


	public int getBoard_No() {
		return board_No;
	}



	public void setBoard_No(int board_No) {
		this.board_No = board_No;
	}



	public int getMember_Num() {
		return member_Num;
	}



	public void setMember_Num(int member_Num) {
		this.member_Num = member_Num;
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

	public void setMember_Id(String memberid) {
		this.setMemberid(memberid);
		
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	

	
}

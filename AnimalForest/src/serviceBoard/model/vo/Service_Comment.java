package serviceBoard.model.vo;

import java.sql.Date;

public class Service_Comment {
	private int comment_No;
	private int member_Num;
	private int board_No;
	private String content;
	private Date cdt;
	private Date mdt;
	
	private String member_Name;

	public Service_Comment() {
	}


	public String getMember_Name() {
		return member_Name;
	}


	public void setMember_Name(String member_Name) {
		this.member_Name = member_Name;
	}


	public int getComment_No() {
		return comment_No;
	}

	public void setComment_No(int comment_No) {
		this.comment_No = comment_No;
	}

	public int getMember_Num() {
		return member_Num;
	}

	public void setMember_Num(int member_Num) {
		this.member_Num = member_Num;
	}

	public int getBoard_No() {
		return board_No;
	}

	public void setBoard_No(int board_No) {
		this.board_No = board_No;
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
}

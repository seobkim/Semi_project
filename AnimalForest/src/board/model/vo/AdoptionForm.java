package board.model.vo;

import java.sql.Date;

public class AdoptionForm {

	private int memberNum;
	private int boardNo;
	private String content;
	private String job;
	private String phone;
	private String email;
	private String address;
	private String detailAdress;
	private Date cdt;
	private String memberName;
	

	public AdoptionForm() {
	}

	public AdoptionForm(int memberNum, int boardNo, String content, String job, String phone, String email,
			String address, String detailAdress, Date cdt, String memberName) {
		super();
		this.memberNum = memberNum;
		this.boardNo = boardNo;
		this.content = content;
		this.job = job;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.detailAdress = detailAdress;
		this.cdt = cdt;
		this.memberName = memberName;
	}
	

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
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

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAdress() {
		return detailAdress;
	}

	public void setDetailAdress(String detailAdress) {
		this.detailAdress = detailAdress;
	}

	public Date getCdt() {
		return cdt;
	}

	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}

}

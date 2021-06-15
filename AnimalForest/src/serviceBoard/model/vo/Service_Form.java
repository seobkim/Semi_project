package serviceBoard.model.vo;

import java.sql.Date;

public class Service_Form {
	private int member_Num;
	private String member_Name;
	private int board_No;
	private String content;
	private String job;
	private String phone;
	private String email;
	private String address;
	private String detail_Address;
	private Date cdt;
	private Date mdt;
	
	public Service_Form() {
	}
	
	public String getMember_Name() {
		return member_Name;
	}


	public void setMember_Name(String member_Name) {
		this.member_Name = member_Name;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getDetail_Address() {
		return detail_Address;
	}

	public void setDetail_Address(String detail_Address) {
		this.detail_Address = detail_Address;
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

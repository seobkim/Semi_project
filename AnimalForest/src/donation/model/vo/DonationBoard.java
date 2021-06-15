package donation.model.vo;

import java.sql.Date;

public class DonationBoard {
	
	public DonationBoard() {
		
	}
	
	private DonationFile dFile;
	
	private String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private int board_No; 
	private int member_Num;
	private String title;
	private String content;
	private int full_Amount;
	private char flag;
	private Date cdt;
	private Date endDate;
	private int grp1_No;
	private int grp2_No;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFull_Amount() {
		return full_Amount;
	}
	public void setFull_Amount(int full_Amount) {
		this.full_Amount = full_Amount;
	}
	public char getFlag() {
		return flag;
	}
	public void setFlag(char flag) {
		this.flag = flag;
	}
	public Date getCdt() {
		return cdt;
	}
	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}
	public int getGrp1_No() {
		return grp1_No;
	}
	public void setGrp1_No(int grp1_No) {
		this.grp1_No = grp1_No;
	}
	public int getGrp2_No() {
		return grp2_No;
	}
	public void setGrp2_No(int grp2_No) {
		this.grp2_No = grp2_No;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public DonationFile getdFile() {
		return dFile;
	}
	public void setdFile(DonationFile dFile) {
		this.dFile = dFile;
	}


}

package donation.model.vo;

import java.sql.Date;

public class DonationHistory {

	private int member_Num;
	private int board_No;
	private Date donaton_Date;
	private int donation_Amount;
	private int count ;
	private int full_Amount;
	private int avg;
	
	public int getAvg() {
		return avg;
	}
	public void setAvg(int avg) {
		this.avg = avg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getFull_Amount() {
		return full_Amount;
	}
	public void setFull_Amount(int full_Amount) {
		this.full_Amount = full_Amount;
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
	public Date getDonaton_Date() {
		return donaton_Date;
	}
	public void setDonaton_Date(Date donaton_Date) {
		this.donaton_Date = donaton_Date;
	}
	public int getDonation_Amount() {
		return donation_Amount;
	}
	public void setDonation_Amount(int donation_Amount) {
		this.donation_Amount = donation_Amount;
	}

}

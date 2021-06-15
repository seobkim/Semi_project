package board.model.vo;

import java.sql.Date;

public class AdoptionSurvey {

	private int memberNum;
	private int boardNo;
	private String exprienceYn;
	private String petYn;
	private int memberFamily;
	private String homeType;
	private String adoptionReason;
	private String adoptionCare;
	private int expectedCost;
	private Date cdt;
	
	public AdoptionSurvey() {
	}

	public AdoptionSurvey(int memberNum, int boardNo, String exprienceYn, String petYn, int memberFamily, String homeType,
			String adoptionReason, String adoptionCare, int expectedCost, Date cdt) {
		super();
		this.memberNum = memberNum;
		this.boardNo = boardNo;
		this.exprienceYn = exprienceYn;
		this.petYn = petYn;
		this.memberFamily = memberFamily;
		this.homeType = homeType;
		this.adoptionReason = adoptionReason;
		this.adoptionCare = adoptionCare;
		this.expectedCost = expectedCost;
		this.cdt = cdt;
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

	public String getExprienceYn() {
		return exprienceYn;
	}

	public void setExprienceYn(String exprienceYn) {
		this.exprienceYn = exprienceYn;
	}

	public String getPetYn() {
		return petYn;
	}

	public void setPetYn(String petYn) {
		this.petYn = petYn;
	}

	public int getMemberFamily() {
		return memberFamily;
	}

	public void setMemberFamily(int memberFamily) {
		this.memberFamily = memberFamily;
	}

	public String getHomeType() {
		return homeType;
	}

	public void setHomeType(String homeType) {
		this.homeType = homeType;
	}

	public String getAdoptionReason() {
		return adoptionReason;
	}

	public void setAdoptionReason(String adoptionReason) {
		this.adoptionReason = adoptionReason;
	}

	public String getAdoptionCare() {
		return adoptionCare;
	}

	public void setAdoptionCare(String adoptionCare) {
		this.adoptionCare = adoptionCare;
	}

	public int getExpectedCost() {
		return expectedCost;
	}

	public void setExpectedCost(int expectedCost) {
		this.expectedCost = expectedCost;
	}

	public Date getCdt() {
		return cdt;
	}

	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}

}

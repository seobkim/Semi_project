package board.model.vo;

public class BoardAndMember {
	
	private String title;
	private int memberNum;
	private String memberName;
	private String memberAddress;
	private String memberPhone;
	private int boardNo;
	private AnimalFile aFile;
	private String fileName;
	private int fCnt;
	private boolean fFlag;
	
	public BoardAndMember() {}

	
	public BoardAndMember(String title, int memberNum, String memberName, String memberAddress, String memberPhone,
			int boardNo, AnimalFile aFile, String fileName) {
		super();
		this.title = title;
		this.memberNum = memberNum;
		this.memberName = memberName;
		this.memberAddress = memberAddress;
		this.memberPhone = memberPhone;
		this.boardNo = boardNo;
		this.aFile = aFile;
		this.fileName = fileName;
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

	public AnimalFile getaFile() {
		return aFile;
	}

	public void setaFile(AnimalFile aFile) {
		this.aFile = aFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public int getfCnt() {
		return fCnt;
	}


	public void setfCnt(int fCnt) {
		this.fCnt = fCnt;
	}


	public boolean isfFlag() {
		return fFlag;
	}


	public void setfFlag(boolean fFlag) {
		this.fFlag = fFlag;
	}
	
	
	

}

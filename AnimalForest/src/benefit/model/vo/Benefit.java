package benefit.model.vo;

import java.sql.Date;

public class Benefit {

	private String fileName;
	
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private int boardNo;
	private int memberNo;
	private int categoryNo;
	private String title;
	private String contents;
	private Date endDate;
	private Date CDT;
	private Date MDT;
	
	public Benefit() {
		
	}

	public Date getCDT() {
		return CDT;
	}

	public void setCDT(Date cDT) {
		CDT = cDT;
	}

	public Date getMDT() {
		return MDT;
	}

	public void setMDT(Date mDT) {
		MDT = mDT;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}


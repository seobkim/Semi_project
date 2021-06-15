package review.model.vo;

import java.sql.Date;
import java.util.ArrayList;

public class Review {

	private int board_No;
	private int member_Num;
	private String title;
	private String content;
	private Date adoptionTime;
	

	private Date cdt;
	private String fileName;
	private ArrayList<ReviewComment> comments;
	private ArrayList<File> reviewFiles;
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Review() {
	}

	public Review(int board_No, int member_Num, String title, String content, Date adoptionTime, Date cdt) {
		super();
		this.board_No = board_No;
		this.member_Num = member_Num;
		this.title = title;
		this.content = content;
		this.adoptionTime = adoptionTime;
		this.cdt = cdt;

	}

	public Review(ArrayList<ReviewComment> comments) {
		super();
		this.comments = comments;
	}
	
	public ArrayList<File> getReviewFiles() {
		return reviewFiles;
	}

	public void setReviewFiles(ArrayList<File> reviewFiles) {
		this.reviewFiles = reviewFiles;
	}

	public ArrayList<ReviewComment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<ReviewComment> comments) {
		this.comments = comments;
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

	public Date getAdoptionTime() {
		return adoptionTime;
	}

	public void setAdoptionTime(Date adoptionTime) {
		this.adoptionTime = adoptionTime;
	}

	public Date getCdt() {
		return cdt;
	}

	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}

}

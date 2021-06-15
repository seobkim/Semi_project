package review.model.vo;

import java.sql.Blob;
import java.sql.Date;

public class File {

	private int fileNo;
	private int boardNo;
	private String fileName;
	private int fileSize;
	private Blob files;
	private Date cdt;

	public File() {}

	public File(int fileNo, int boardNo, String fileName, int fileSize, Blob files, Date cdt) {
		super();
		this.fileNo = fileNo;
		this.boardNo = boardNo;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.files = files;
		this.cdt = cdt;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public Blob getFiles() {
		return files;
	}

	public void setFiles(Blob files) {
		this.files = files;
	}

	public Date getCdt() {
		return cdt;
	}

	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}

}

package board.model.vo;

import java.sql.Blob;
import java.sql.Date;

public class AnimalFile {

	private int fileNO;
	private String fileName;
	private int boardNo;
	

	public AnimalFile(int fileNO, String fileName, int boardNo) {
		super();
		this.fileNO = fileNO;
		this.fileName = fileName;
		this.boardNo = boardNo;
	}
	public int getFileNO() {
		return fileNO;
	}
	public void setFileNO(int fileNO) {
		this.fileNO = fileNO;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	


}

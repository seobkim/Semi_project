package benefit.model.vo;

import java.sql.Blob;

public class BenefitFile {

	private int fileNo;
	private String fileName;
	private int boardNo;
	
	/*public BenefitFile(int fileNo, String fileName, int boardNo) {
		super();
		this.fileNo = fileNo;
		this.fileName = fileName;
		this.boardNo = boardNo;
	}*/

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
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

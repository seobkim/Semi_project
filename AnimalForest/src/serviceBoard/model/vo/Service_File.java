package serviceBoard.model.vo;

import java.sql.Blob;
import java.sql.Date;

public class Service_File {
	private int file_No;
	private int board_No;
	private String file_Name;
	private long file_Size;
	private Blob files;
	private Date cdt;

	public Service_File() {
	}

	public Service_File(int file_No, int board_No, String file_Name, long file_Size, Blob files, Date cdt) {
		super();
		this.file_No = file_No;
		this.board_No = board_No;
		this.file_Name = file_Name;
		this.file_Size = file_Size;
		this.files = files;
		this.cdt = cdt;
	}

	public int getFile_No() {
		return file_No;
	}

	public void setFile_No(int file_No) {
		this.file_No = file_No;
	}

	public int getBoard_No() {
		return board_No;
	}

	public void setBoard_No(int board_No) {
		this.board_No = board_No;
	}

	public String getFile_Name() {
		return file_Name;
	}

	public void setFile_Name(String file_Name) {
		this.file_Name = file_Name;
	}

	public long getFile_Size() {
		return file_Size;
	}

	public void setFile_Size(long file_Size) {
		this.file_Size = file_Size;
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

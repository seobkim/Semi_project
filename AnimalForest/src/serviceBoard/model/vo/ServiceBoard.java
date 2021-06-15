package serviceBoard.model.vo;

import java.sql.Date;
import java.util.ArrayList;

public class ServiceBoard {
	private int board_No;
	private int member_Num;
	private String title;
	private String content;
	private String flag;
	private Date deadLine;
	private Date service_Fr;
	private Date service_To;
	private int volunteer;
	private int qualfication;
	private Date cdt;
	private Date mdt;
	private ArrayList<Service_Comment>clist;
	private Service_File sFile;
	private String fileName;
	private int count;
	
	public ServiceBoard() {
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Service_File getsFile() {
		return sFile;
	}



	public void setsFile(Service_File sFile) {
		this.sFile = sFile;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public ArrayList<Service_Comment> getClist() {
		return clist;
	}

	public void setClist(ArrayList<Service_Comment> clist) {
		this.clist = clist;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public Date getService_Fr() {
		return service_Fr;
	}

	public void setService_Fr(Date service_Fr) {
		this.service_Fr = service_Fr;
	}

	public Date getService_To() {
		return service_To;
	}

	public void setService_To(Date service_To) {
		this.service_To = service_To;
	}

	public int getVolunteer() {
		return volunteer;
	}

	public void setVolunteer(int volunteer) {
		this.volunteer = volunteer;
	}

	public int getQualfication() {
		return qualfication;
	}

	public void setQualfication(int qualfication) {
		this.qualfication = qualfication;
	}

	public Date getCdt() {
		return cdt;
	}

	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}

	public Date getMdt() {
		return mdt;
	}

	public void setMdt(Date mdt) {
		this.mdt = mdt;
	}
	
}

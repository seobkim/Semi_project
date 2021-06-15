package serviceBoard.model.vo;

import java.sql.Date;

public class ServiceBoardAndMember {
	//멤버에서 가져오는 매개변수
	private int memberNum;
	private String memberName;
	private String memberAddress;
	private String memberPhone;
	private String memberId;
	private char memberType;
	
	//ServiceBoard에서 가져오는 매개변수
	private int board_No;
	private Date deadLine;
	private String content;
	private Date cdt;
	private Date mdt;
	private String flag;
	private Date service_Fr;
	private Date service_To;
	private int volunteer;
	private int qualfication;
	private String title;
	private Service_File sFile;
	private String fileName;
	private int count;
	
	//form에서 가져오는 매개변수
	private String job;
	private String email;
	private String detail_Address;
	
	public ServiceBoardAndMember() {
		
	}
	
	

	public char getMemberType() {
		return memberType;
	}



	public void setMemberType(char memberType) {
		this.memberType = memberType;
	}



	public String getMemberId() {
		return memberId;
	}



	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public String getJob() {
		return job;
	}



	public void setJob(String job) {
		this.job = job;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getDetail_Address() {
		return detail_Address;
	}



	public void setDetail_Address(String detail_Address) {
		this.detail_Address = detail_Address;
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



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
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



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
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

	public int getBoard_No() {
		return board_No;
	}

	public void setBoard_No(int board_No) {
		this.board_No = board_No;
	}
	
	
	
	
	
}

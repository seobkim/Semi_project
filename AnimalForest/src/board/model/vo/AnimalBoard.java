package board.model.vo;

import java.util.ArrayList;
import java.util.Date;

public class AnimalBoard {

	private int boardNo;
	private int memberNum;
	private int grp1No;
	private int grp2No;
	private String title;
	private String content;
	private char flag;
	private char privacyYn;
	private String feature;
	private String color;
	private int weight;
	private Date birthDate;
	private String findPlace;
	private Date cdt;
	private int selectMemberNum;
	private Date mdt;
	private String neutralizationyn;
	private String gender;
	private ArrayList<AnimalComment> comments = null;
	private AnimalFile aFile;
	private String fileName;

	public AnimalBoard() {
	}

	public AnimalBoard(int boardNo, int memberNum, int grp1No, int grp2No, String title, String content, char flag,
			char privacyYn, String feature, String color, int weight, Date birthDate, String findPlace, Date cdt,
			int selectMemberNum, Date mdt, String neutralizationyn, String gender, ArrayList<AnimalComment> comments,
			AnimalFile aFile, String fileName) {
		super();
		this.boardNo = boardNo;
		this.memberNum = memberNum;
		this.grp1No = grp1No;
		this.grp2No = grp2No;
		this.title = title;
		this.content = content;
		this.flag = flag;
		this.privacyYn = privacyYn;
		this.feature = feature;
		this.color = color;
		this.weight = weight;
		this.birthDate = birthDate;
		this.findPlace = findPlace;
		this.cdt = cdt;
		this.selectMemberNum = selectMemberNum;
		this.mdt = mdt;
		this.neutralizationyn = neutralizationyn;
		this.gender = gender;
		this.comments = comments;
		this.aFile = aFile;
		this.fileName = fileName;
	}




	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	public int getGrp1No() {
		return grp1No;
	}

	public void setGrp1No(int grp1No) {
		this.grp1No = grp1No;
	}

	public int getGrp2No() {
		return grp2No;
	}

	public void setGrp2No(int grp2No) {
		this.grp2No = grp2No;
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

	public char getFlag() {
		return flag;
	}

	public void setFlag(char flag) {
		this.flag = flag;
	}

	public char getPrivacyYn() {
		return privacyYn;
	}

	public void setPrivacyYn(char privacyYn) {
		this.privacyYn = privacyYn;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFindPlace() {
		return findPlace;
	}

	public void setFindPlace(String findPlace) {
		this.findPlace = findPlace;
	}

	public Date getCdt() {
		return cdt;
	}

	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}

	public int getSelectMemberNum() {
		return selectMemberNum;
	}

	public void setSelectMemberNum(int selectMemberNum) {
		this.selectMemberNum = selectMemberNum;
	}

	public Date getMdt() {
		return mdt;
	}

	public void setMdt(Date mdt) {
		this.mdt = mdt;
	}

	public String getNeutralizationyn() {
		return neutralizationyn;
	}

	public void setNeutralizationyn(String neutralizationyn) {
		this.neutralizationyn = neutralizationyn;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public ArrayList<AnimalComment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<AnimalComment> comments) {
		this.comments = comments;
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
}

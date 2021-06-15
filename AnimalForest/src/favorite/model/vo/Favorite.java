package favorite.model.vo;

import java.sql.Date;

public class Favorite {
	private int boardNo;
	private int memberNo;
	private String grp1_nm;
	private String grp2_nm;
	private String title;
	private String content;
	private String feature;
	private String color;
	private char gender;
	private char net_yn;
	private int selNum;
	private Date cdt;
	
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
	public String getGrp1_nm() {
		return grp1_nm;
	}
	public void setGrp1_nm(String grp1_nm) {
		this.grp1_nm = grp1_nm;
	}
	public String getGrp2_nm() {
		return grp2_nm;
	}
	public void setGrp2_nm(String grp2_nm) {
		this.grp2_nm = grp2_nm;
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
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public char getNet_yn() {
		return net_yn;
	}
	public void setNet_yn(char net_yn) {
		this.net_yn = net_yn;
	}
	public int getSelNum() {
		return selNum;
	}
	public void setSelNum(int selNum) {
		this.selNum = selNum;
	}
	public Date getCdt() {
		return cdt;
	}
	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}
	
}

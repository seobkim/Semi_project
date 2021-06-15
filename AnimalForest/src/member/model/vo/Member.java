package member.model.vo;

import java.sql.Blob;
import java.sql.Date;

public class Member {
	private int memberNum;
	private String memberId;
	private	Blob memberPhoto;
    private String memberPhotoName;
	private String memberPwd;
	private String memberName;
	private char memberType;
	private String memberNick;
	private String memberEmail;
	private String memberPost;
	private String memberAddress;
	private String memberAddressDetail;
	private String memberPhone;
	private String memberRegNum;
	private Date memberRegDate;
	private int adoption_success;
	private int adoption_yet;

	public Member() {}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Blob getMemberPhoto() {
		return memberPhoto;
	}

	public void setMemberPhoto(Blob memberPhoto) {
		this.memberPhoto = memberPhoto;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public char getMemberType() {
		return memberType;
	}

	public void setMemberType(char memberType) {
		this.memberType = memberType;
	}

	public String getMemberNick() {
		return memberNick;
	}

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPost() {
		return memberPost;
	}

	public void setMemberPost(String memberPost) {
		this.memberPost = memberPost;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberAddressDetail() {
		return memberAddressDetail;
	}

	public void setMemberAddressDetail(String memberAddressDetail) {
		this.memberAddressDetail = memberAddressDetail;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberRegNum() {
		return memberRegNum;
	}

	public void setMemberRegNum(String memberRegNum) {
		this.memberRegNum = memberRegNum;
	}

	public Date getMemberRegDate() {
		return memberRegDate;
	}

	public void setMemberRegDate(Date memberRegDate) {
		this.memberRegDate = memberRegDate;
	}

	public int getAdoption_success() {
		return adoption_success;
	}

	public void setAdoption_success(int adoption_success) {
		this.adoption_success = adoption_success;
	}

	public int getAdoption_yet() {
		return adoption_yet;
	}

	public void setAdoption_yet(int adoption_yet) {
		this.adoption_yet = adoption_yet;
	}

	public String getMemberPhotoName() {
		return memberPhotoName;
	}

	public void setMemberPhotoName(String memberPhotoName) {
		this.memberPhotoName = memberPhotoName;
	}


	
}//c

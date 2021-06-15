package note.model.vo;

import java.sql.Date;

public class Note {
	private int noteNo;
	private int senderNum;
	private int receiverNum;
	private String senderId;
	private String receiverId;
	private String noteTitle;
	private String noteContents;
	private Date noteDate;
	public int getNoteNo() {
		return noteNo;
	}
	public void setNoteNo(int noteNo) {
		this.noteNo = noteNo;
	}
	public int getSenderNum() {
		return senderNum;
	}
	public void setSenderNum(int senderNum) {
		this.senderNum = senderNum;
	}
	public int getReceiverNum() {
		return receiverNum;
	}
	public void setReceiverNum(int receiverNum) {
		this.receiverNum = receiverNum;
	}
	
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getNoteTitle() {
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
	public String getNoteContents() {
		return noteContents;
	}
	public void setNoteContents(String noteContents) {
		this.noteContents = noteContents;
	}
	public Date getNoteDate() {
		return noteDate;
	}
	public void setNoteDate(Date noteDate) {
		this.noteDate = noteDate;
	}
	
}

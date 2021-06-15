package note.model.vo;

import java.util.ArrayList;

public class NotePageData {
	private ArrayList<Note> pageList = null;
	private String pageNavi = null;
	
	public NotePageData() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Note> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<Note> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}

package board.model.vo;

import java.util.ArrayList;

public class PageData {

	private ArrayList<BoardAndMember> pageList = null;
	private String pageNavi = null;

	public PageData() {
	}

	public ArrayList<BoardAndMember> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<BoardAndMember> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}

}

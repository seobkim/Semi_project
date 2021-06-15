package everyboard.model.vo;

import java.util.ArrayList;


public class BoardPageData {
	private ArrayList<Board> pageList = null;
	private String pageNavi = null;
	
	public BoardPageData() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Board> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<Board> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}

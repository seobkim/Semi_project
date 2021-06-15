package serviceBoard.model.vo;

import java.util.ArrayList;

public class PageData {
	
	private ArrayList<ServiceBoardAndMember> pageList = null;
	private String pageNavi = null;

	public PageData() {
	}

	public ArrayList<ServiceBoardAndMember> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<ServiceBoardAndMember> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}

package review.model.vo;

import java.util.ArrayList;

public class PageData {

	private ArrayList<Review> pageList = null;
	private String pageNavi = null;

	public PageData() {
	}

	public ArrayList<Review> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<Review> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}

}

package benefit.model.vo;

import java.util.ArrayList;


public class PageDate {

	private ArrayList<Benefit> pageList = null;
	private String pageNavi = null;
	
	public PageDate() {
		
	}
	
	public ArrayList<Benefit> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<Benefit> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
}

package favorite.model.vo;

import java.util.ArrayList;

public class FavoritePageData {
	private ArrayList<Favorite> pageList = null;
	private String pageNavi = null;
	
	public FavoritePageData() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Favorite> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<Favorite> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}

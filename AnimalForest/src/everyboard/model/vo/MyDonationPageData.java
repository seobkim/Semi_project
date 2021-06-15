package everyboard.model.vo;

import java.util.ArrayList;

public class MyDonationPageData {
	private ArrayList<MyDonation> pageList = null;
	private String pageNavi = null;
	
	public MyDonationPageData() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<MyDonation> getPageList() {
		return pageList;
	}
	public void setPageList(ArrayList<MyDonation> pageList) {
		this.pageList = pageList;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
}

package donation.model.vo;
import java.util.ArrayList;


public class PageData {
	private ArrayList<DonationBoard> pageList = null;
	private String pageNavi = null;
	
	public PageData() {
		
	}

	public ArrayList<DonationBoard> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<DonationBoard> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}

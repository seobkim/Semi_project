package everyboard.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import everyboard.model.dao.BoardDao;
import everyboard.model.vo.Board;
import everyboard.model.vo.BoardPageData;
import everyboard.model.vo.MyDonation;
import everyboard.model.vo.MyDonationPageData;
import note.model.dao.NoteDao;

public class BoardService {
	private ConnectionFactory factory;
	public BoardService() {
		// TODO Auto-generated constructor stub
		factory = ConnectionFactory.getConnection();
	}
	
	public MyDonationPageData myDonationHistory(int memberNum, int currentPage) {
		Connection conn = null;
		MyDonationPageData pd = new MyDonationPageData();
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		ArrayList<MyDonation> dlist = null;
		
		try {
			conn = factory.createConnection();
			dlist = new BoardDao().myDonationHistory(conn, memberNum, currentPage, recordCountPerPage);
			pd.setPageList(dlist);
			pd.setPageNavi(new BoardDao().getDonationPageNavi(conn, memberNum, currentPage, recordCountPerPage, naviCountPerPage));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		
		return pd;
	}
	public BoardPageData myWriteHistory(int memberNum, int currentPage) {
		// TODO Auto-generated method stub
		Connection conn = null;
		BoardPageData pd = new BoardPageData();
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		ArrayList<Board> blist = null;
		
		try {
			conn = factory.createConnection();
			blist = new BoardDao().myWriteHistory(conn, memberNum, currentPage, recordCountPerPage);
			pd.setPageList(blist);
			pd.setPageNavi(new BoardDao().getWritePageNavi(conn, memberNum, currentPage, recordCountPerPage, naviCountPerPage));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		
		return pd;
	}
}

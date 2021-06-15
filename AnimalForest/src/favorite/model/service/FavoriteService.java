package favorite.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import everyboard.model.dao.BoardDao;
import everyboard.model.vo.Board;
import everyboard.model.vo.BoardPageData;
import favorite.model.dao.FavoriteDao;
import favorite.model.vo.Favorite;
import favorite.model.vo.FavoritePageData;

public class FavoriteService {
	private ConnectionFactory factory;
	public FavoriteService() {
		// TODO Auto-generated constructor stub
		factory = ConnectionFactory.getConnection();
	}
	public FavoritePageData myFavoriteList(int memberNum, int currentPage) {
		// TODO Auto-generated method stub
		Connection conn = null;
		FavoritePageData pd = new FavoritePageData();
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		ArrayList<Favorite> flist = null;
		
		try {
			conn = factory.createConnection();
			flist = new FavoriteDao().myFavoriteList(conn, memberNum, currentPage, recordCountPerPage);
			pd.setPageList(flist);
			pd.setPageNavi(new FavoriteDao().getPageNavi(conn, memberNum, currentPage, recordCountPerPage, naviCountPerPage));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		
		return pd;
	}
	public ArrayList<Integer> deleteFavorite(Favorite favorite) {
		// TODO Auto-generated method stub
		Connection conn = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		int result = 0;
		int cnt = 0;
		try {
			conn = factory.createConnection();
			result = new FavoriteDao().deleteFavorite(conn, favorite);
			if(result > 0) {
				factory.commit(conn);
			}else {
				factory.rollback(conn);
			}
			cnt = new FavoriteDao().favoriteCnt(conn, favorite);
			list.add(result);
			list.add(cnt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			factory.close(conn);
		}
		return list;
	}
	public ArrayList<Integer> insertFavorite(Favorite favorite) {
		// TODO Auto-generated method stub
		Connection conn = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		int result = 0;
		int cnt = 0;
		try {
			conn = factory.createConnection();
			result = new FavoriteDao().insertFavorite(conn, favorite);
			if(result > 0) {
				factory.commit(conn);
			}else {
				factory.rollback(conn);
			}
			cnt = new FavoriteDao().favoriteCnt(conn, favorite);
			list.add(result);
			list.add(cnt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			factory.close(conn);
		}
		return list;
	}
}

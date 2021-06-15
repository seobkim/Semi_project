package favorite.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import everyboard.model.vo.Board;
import favorite.model.vo.Favorite;

public class FavoriteDao {

	public ArrayList<Favorite> myFavoriteList(Connection conn, int memberNum, int currentPage, int recordCountPerPage) {
		// TODO Auto-generated method stub
		ArrayList<Favorite> flist = new ArrayList<Favorite>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query =	"SELECT  * " 
					 +	"FROM    ( "
					 +	"            SELECT  A.BOARD_NO, B.MEMBER_NUM, G1.GRP1_NAME AS GRP1_NM, G2.GRP2_NAME AS GRP2_NM, " 
					 +	"                    A.TITLE, A.CONTENT, A.FEATURE, A.COLOR, A.GENDER, A.NEUTRALIZATION_YN, A.SELECT_MEMBER_NUM, " 
					 +	"                    A.CDT, FLOOR((ROW_NUMBER() OVER(ORDER BY B.CDT DESC)-1)/?) AS PAGE_NUM " 
					 +	"            FROM    ANIMAL_BOARD A, FAVORITE_ANIMAL B, ANIMAL_GRP1 G1, ANIMAL_GRP2 G2 " 
					 +	"            WHERE   A.BOARD_NO = B.BOARD_NO " 
					 +	"            AND     A.GRP1_NO = G2.GRP1_NO(+) " 
					 + 	"            AND     A.GRP2_NO = G2.GRP2_NO(+) " 
					 + 	"            AND     G1.GRP1_NO = G2.GRP1_NO " 
					 +	"            AND     B.MEMBER_NUM = ?) "
					 +	"WHERE   PAGE_NUM + 1 = ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recordCountPerPage);
			pstmt.setInt(2, memberNum);
			pstmt.setInt(3, currentPage);
			rset = pstmt.executeQuery();
			Favorite favorite = null;
			while(rset.next()) {
				favorite = new Favorite();
				favorite.setBoardNo(rset.getInt("BOARD_NO"));
				favorite.setMemberNo(rset.getInt("MEMBER_NUM"));
				favorite.setGrp1_nm(rset.getString("GRP1_NM"));
				favorite.setGrp2_nm(rset.getString("GRP2_NM"));
				favorite.setTitle(rset.getString("TITLE"));
				favorite.setContent(rset.getString("CONTENT"));
				favorite.setFeature(rset.getString("FEATURE"));
				favorite.setColor(rset.getString("COLOR"));
				char emptyString = ' ';
				favorite.setGender(rset.getString("GENDER")==null ? emptyString : rset.getString("GENDER").charAt(0));
				favorite.setNet_yn(rset.getString("NEUTRALIZATION_YN") == null ? emptyString : rset.getString("NEUTRALIZATION_YN").charAt(0));
				favorite.setCdt(rset.getDate("CDT"));
				flist.add(favorite);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flist;
	}

	public int totalCount(Connection conn, int memberNum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query =  "SELECT  COUNT(0) AS TOTALCOUNT " 
					 +	"FROM    ANIMAL_BOARD A, FAVORITE_ANIMAL B, ANIMAL_GRP1 G1, ANIMAL_GRP2 G2 "
					 +	"WHERE   A.BOARD_NO = B.BOARD_NO " 
					 + 	"AND     A.GRP1_NO = G2.GRP1_NO(+) " 
					 +	"AND     A.GRP2_NO = G2.GRP2_NO(+) " 
					 +	"AND     G1.GRP1_NO = G2.GRP1_NO " 
					 +	"AND     B.MEMBER_NUM = ?";			
			

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNum);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return recordTotalCount;
	}

	public String getPageNavi(Connection conn, int memberNum, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		int recordTotalCount = totalCount(conn, memberNum);

		int pageTotalCount = 0; // �쟾泥� �럹�씠吏��쓽 媛쒖닔

		// 留뚮뱾 �쟾泥� �럹�씠吏��쓽 媛쒖닔
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		// �쁽�옱 �럹�씠吏�瑜� 湲곗��쑝濡� �꽕鍮꾧쾶�씠�뀡�쓣 援ы빐�빞 �븯誘�濡�
		// �쁽�옱 �럹�씠吏� �젙蹂대�� �솗�씤�빐�꽌 0蹂대떎�뒗 �겕怨� �쟾泥�
		// �럹�씠吏� �닔蹂대떎�뒗 �옉�� �쐞移섏뿉 �엳�뒗吏� �솗�씤
		// (�삤瑜섎갑吏��슜)
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		String callServlet = "/favoriteList";
		
		// 1 2 3 4 5
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = (startNavi) + naviCountPerPage - 1;

		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		boolean needPrev = true;
		boolean needNext = true;

		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}
		// 紐⑤뱺 以�鍮꾨뒗 �걹�궓
		StringBuilder sb = new StringBuilder();
		if (needPrev) {
			sb.append("<a href = '"+callServlet+"?currentPage=" + (startNavi - 1) + "'><</a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href = '"+callServlet+"?currentPage=" + i + "'><b> " + i + " </b></a>");
			} else {
				sb.append("<a href = '"+callServlet+"?currentPage=" + i + "'> " + i + " </a>");
			}
		}
		if (needNext) {
			sb.append("<a href = '"+callServlet+"?currentPage=" + (endNavi+1)
							+ "'>></a>");
		}
		return sb.toString();
	}

	public int deleteFavorite(Connection conn, Favorite favorite) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		String query = "DELETE "
					 + "FROM    FAVORITE_ANIMAL " 
					 + "WHERE   MEMBER_NUM = ? " 
					 + "AND     BOARD_NO = ?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, favorite.getMemberNo());
			pstmt.setInt(2, favorite.getBoardNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(pstmt);
		}
		
		return result;
	}

	public int insertFavorite(Connection conn, Favorite favorite) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		String query = "INSERT INTO FAVORITE_ANIMAL "
					 + "VALUES(?, ?, SYSDATE) ";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, favorite.getMemberNo());
			pstmt.setInt(2, favorite.getBoardNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(pstmt);
		}
		
		return result;
	}
	
	public int favoriteCnt(Connection conn, Favorite favorite) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query 	= "SELECT COUNT(0) AS CNT "
						+ "FROM FAVORITE_ANIMAL "
						+ "WHERE BOARD_NO = ?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, favorite.getBoardNo());
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt("CNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(pstmt);
		}
		
		return result;
	}

}

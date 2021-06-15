package everyboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import everyboard.model.vo.Board;
import everyboard.model.vo.MyDonation;
import everyboard.model.vo.MyDonationPageData;

public class BoardDao {

	public ArrayList<Board> myWriteHistory(Connection conn, int memberNum, int currentPage, int recordCountPerPage) {
		// TODO Auto-generated method stub
		ArrayList<Board> blist = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query =	"SELECT	A.* " 
					 +	"FROM    (SELECT BOARD_NO, CATEGORY_NO, MEMBER_NUM, TITLE, CONTENT, CDT, " 
					 +	"				 FLOOR((ROW_NUMBER() OVER(ORDER BY CDT DESC)-1)/?) AS PAGE_NUM "
					 +	"         FROM	 TABLE(FN_GET_MY_BOARD(?))) A " 
					 +	"WHERE   A.PAGE_NUM + 1 = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recordCountPerPage);
			pstmt.setInt(2, memberNum);
			pstmt.setInt(3, currentPage);
			rset = pstmt.executeQuery();
			Board board = null;
			while(rset.next()) {
				board = new Board();
				board.setBoardNo(rset.getInt("BOARD_NO"));
				board.setCategoryNum(rset.getInt("CATEGORY_NO"));
				board.setMemberNum(rset.getInt("MEMBER_NUM"));
				board.setTitle(rset.getString("TITLE"));
				board.setContent(rset.getString("CONTENT"));
				board.setCdt(rset.getDate("CDT"));
				blist.add(board);
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
		return blist;
	}

	public int writeTotalCount(Connection conn, int memberNum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query =  "SELECT COUNT(0) AS TOTALCOUNT "
					 + 	"FROM 	TABLE(FN_GET_MY_BOARD(?)) ";			
			

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

	public String getWritePageNavi(Connection conn, int memberNum, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		int recordTotalCount = writeTotalCount(conn, memberNum);

		int pageTotalCount = 0; // 전체 페이지의 개수

		// 만들 전체 페이지의 개수
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		// 현재 페이지를 기준으로 네비게이션을 구해야 하므로
		// 현재 페이지 정보를 확인해서 0보다는 크고 전체
		// 페이지 수보다는 작은 위치에 있는지 확인
		// (오류방지용)
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		String callServlet = "/writeList";
		
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
		// 모든 준비는 끝남
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

	public ArrayList<MyDonation> myDonationHistory(Connection conn, int memberNum, int currentPage,
			int recordCountPerPage) {
		// TODO Auto-generated method stub
		ArrayList<MyDonation> dlist = new ArrayList<MyDonation>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT  A.* " 
					 + "FROM    ( " 
					 + "            SELECT  C.BOARD_NO, C.TITLE, C.CONTENT, C.FULL_AMOUNT, B.DONATION_AMOUNT, B.DONATION_DATE, "
					 + "					(SELECT SUM(DONATION_AMOUNT) FROM DONATION_HISTORY WHERE BOARD_NO = C.BOARD_NO) AS SUM_AMOUNT, " 
					 + "                    FLOOR((ROW_NUMBER() OVER(ORDER BY B.DONATION_DATE DESC)-1)/?) AS PAGE_NUM " 
					 + "            FROM    MEMBER A, DONATION_HISTORY B, DONATION_BOARD C " 
					 + "            WHERE   A.MEMBER_NUM = B.MEMBER_NUM " 
					 + "            AND     B.BOARD_NO = C.BOARD_NO"
					 + "			AND		A.MEMBER_NUM = ?) A " 
					 + "WHERE   A.PAGE_NUM + 1 = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recordCountPerPage);
			pstmt.setInt(2, memberNum);
			pstmt.setInt(3, currentPage);
			rset = pstmt.executeQuery();
			MyDonation myDonation = null;
			while (rset.next()) {
				myDonation = new MyDonation();
				myDonation.setBoardNo(rset.getInt("BOARD_NO"));
				myDonation.setTitle(rset.getString("TITLE"));
				myDonation.setContent(rset.getString("CONTENT"));
				myDonation.setFullAmount(rset.getInt("FULL_AMOUNT"));
				myDonation.setSumAmount(rset.getInt("SUM_AMOUNT"));
				myDonation.setDonationAmount(rset.getInt("DONATION_AMOUNT"));			
				myDonation.setDonationDate(rset.getDate("DONATION_DATE"));
				dlist.add(myDonation);
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
		return dlist;
	}

	public String getDonationPageNavi(Connection conn, int memberNum, int currentPage, int recordCountPerPage,
			int naviCountPerPage) {
		int recordTotalCount = donationTotalCount(conn, memberNum);

		int pageTotalCount = 0; // 전체 페이지의 개수

		// 만들 전체 페이지의 개수
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		// 현재 페이지를 기준으로 네비게이션을 구해야 하므로
		// 현재 페이지 정보를 확인해서 0보다는 크고 전체
		// 페이지 수보다는 작은 위치에 있는지 확인
		// (오류방지용)
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		String callServlet = "/myDonationList";
		
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
		// 모든 준비는 끝남
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

	private int donationTotalCount(Connection conn, int memberNum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query =  "SELECT COUNT(0) AS TOTALCOUNT "
					 + 	"FROM 	DONATION_HISTORY "
					 + 	"WHERE	MEMBER_NUM = ?";			
			

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
}

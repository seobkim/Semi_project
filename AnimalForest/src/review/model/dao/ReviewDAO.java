package review.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import review.model.vo.Review;
import review.model.vo.ReviewAndMember;
import review.model.vo.ReviewComment;

public class ReviewDAO {

	public ArrayList<Review> reviewList(Connection conn, int currentPage, int recordCountPerPage) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Review> list = new ArrayList<Review>();
		/*
		 * int start = 1; int end = 10; try { pstmt = conn.prepareStatement(query);
		 * pstmt.setInt(1, start); pstmt.setInt(2, end); rset = pstmt.executeQuery();
		 */

		// 일반 사용자 쿼리
		String query = "SELECT * FROM (SELECT REVIEW_BOARD.*,ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) AS NUM FROM REVIEW_BOARD) JOIN  REVIEW_FILE USING(BOARD_NO) WHERE NUM BETWEEN ? AND ?";
		// 관리자 쿼리
		String query1 = "SELECT * FROM REVIEW_BOARD";

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<Review>();

			while (rset.next()) {
				Review review = new Review();
				review.setBoard_No(rset.getInt("BOARD_NO"));
				review.setMember_Num(rset.getInt("MEMBER_NUM"));
				review.setTitle(rset.getString("TITLE"));
				review.setContent(rset.getString("CONTENT"));
				review.setAdoptionTime(rset.getDate("adoption_time"));
				review.setCdt(rset.getDate("CDT"));
				review.setFileName(rset.getString("FILE_NAME"));
				list.add(review);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception

			}
		}

		return list;
	}

	public int reviewWrite(Connection conn, Review review) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO REVIEW_BOARD VALUES(REVIEW_BOARD_SEQ.NEXTVAL,?,?,?,?,SYSDATE)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, review.getMember_Num());
			pstmt.setString(2, review.getTitle());
			pstmt.setString(3, review.getContent());
			pstmt.setDate(4, review.getAdoptionTime());
			System.out.println(review.getMember_Num());
			System.out.println(review.getTitle());
			System.out.println(review.getContent());
			System.out.println(review.getAdoptionTime());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}//잘모르겠습니다.

	public Review reviewSelect(Connection conn, int board_No) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		Review review = null;
		ResultSet rset = null;
		String query = "SELECT * FROM REVIEW_BOARD WHERE BOARD_NO=?";
		// 선생님이걸 쓸수있나요? 따로 만들어야 하나요?
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board_No);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				review = new Review();
				review.setBoard_No(rset.getInt("BOARD_NO"));
				review.setMember_Num(rset.getInt("MEMBER_NUM"));
				review.setTitle(rset.getString("TITLE"));
				review.setContent(rset.getString("CONTENT"));
				review.setAdoptionTime(rset.getDate("ADOPTION_TIME"));
				review.setCdt(rset.getDate("CDT"));
				

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return review;
	}

	public int reviewDelete(Connection conn, int board_No) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM REVIEW_BOARD WHERE BOARD_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board_No);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	public int reviewModify(Connection conn, Review review, int board_No) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "UPDATE REVIEW_BOARD SET TITLE=?, CONTENT=? WHERE BOARD_NO=?";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, review.getTitle());
			pstmt.setString(2, review.getContent());
			pstmt.setInt(3, board_No);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int ReviewCommentWrite(Connection conn, String comment, int board_No, int member_Num) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "INSERT INTO REVIEW_COMMENT VALUES (REVIEW_COMMENT_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, sysdate)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board_No);
			pstmt.setInt(2, member_Num);
			pstmt.setString(3, comment);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public ArrayList<review.model.vo.File> reviewFileList(Connection conn, int board_No){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<review.model.vo.File> rList = null;
		
		String query ="select * from review_file where board_no =?";
		
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, board_No);
					rset = pstmt.executeQuery();
					
					rList = new ArrayList<review.model.vo.File>();

					while (rset.next()) {
						review.model.vo.File rFile = new review.model.vo.File();
						rFile.setFileNo(rset.getInt("FILE_NO"));
						rFile.setBoardNo(rset.getInt("BOARD_NO"));
						rFile.setFileName(rset.getString("FILE_NAME"));
						rFile.setFileSize(rset.getInt("FILE_SIZE"));
						rFile.setFiles(rset.getBlob("FILES"));
						rFile.setCdt(rset.getDate("CDT"));
						rList.add(rFile);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						rset.close();
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return rList;

	}

	public ArrayList<ReviewComment> reviewComment(Connection conn, int board_No) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ReviewComment> cmtList = null;

		String query = "select * from member join review_comment using (member_Num) where board_no =?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board_No);
			rset = pstmt.executeQuery();

			cmtList = new ArrayList<ReviewComment>();

			while (rset.next()) {
				ReviewComment aComment = new ReviewComment();
				aComment.setComment_No(rset.getInt("COMMENT_NO"));
				aComment.setMember_Num(rset.getInt("member_num"));
				aComment.setContent(rset.getString("CONTENT"));
				aComment.setMember_Id(rset.getString("MEMBER_ID"));
				aComment.setBoard_No(rset.getInt("board_No"));
				aComment.setCdt(rset.getDate("cdt"));
				cmtList.add(aComment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cmtList;
	}

	public int ReviewCommentDelete(Connection conn, int comment_No) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM REVIEW_COMMENT WHERE COMMENT_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comment_No);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public int ReviewCommentModify(Connection conn, int comment_No, int board_No, String comment) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "UPDATE REVIEW_COMMENT SET CONTENT =? WHERE COMMENT_NO =? AND BOARD_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment);
			pstmt.setInt(2, comment_No);
			pstmt.setInt(3, board_No);
			// System.out.println(comment);
			// System.out.println(comment_No);
			// System.out.println(board_No);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<ReviewAndMember> memberListAllSearch(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ReviewAndMember> memberList = null;
		String query = "select * from member right join review_board using (member_num) where TITLE LIKE '%" + search
				+ "%'";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			memberList = new ArrayList<ReviewAndMember>();

			while (rset.next()) {
				ReviewAndMember reviewAndMember = new ReviewAndMember();
				reviewAndMember.setMemberNum(rset.getInt("MEMBER_NUM"));
				reviewAndMember.setMemberName(rset.getString("MEMBER_NAME"));
				reviewAndMember.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				reviewAndMember.setMemberPhone(rset.getString("MEMBER_PHONE"));
				reviewAndMember.setBoardNo(rset.getInt("board_no"));
				reviewAndMember.setTitle(rset.getString("TITLE"));
				memberList.add(reviewAndMember);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberList;

	}

	public ArrayList<Review> ReviewListAll(Connection conn, String search, int currentPage, int recordCountPerPage) {
		ArrayList<Review> list = new ArrayList<Review>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT REVIEW_BOARD.*,ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) AS NUM FROM REVIEW_BOARD WHERE TITLE LIKE '%"
				+ search + "%') WHERE NUM BETWEEN ? AND ?";
		// start와 end를 reviewList 메소드처럼 바꿀거에요
		// int start = 1;
		// int end = 10;
		// 이렇게 하면 1하고 10으로 고정이 되는게 아니라 currentPage값에 따라서 계속 바뀔거에요
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Review review = new Review();
			
				review.setBoard_No(rset.getInt("board_no"));
				review.setMember_Num(rset.getInt("member_num"));
				review.setTitle(rset.getString("TITLE"));
				review.setContent(rset.getString("CONTENT"));
				review.setAdoptionTime(rset.getDate("ADOPTION_TIME"));
				review.setCdt(rset.getDate("CDT"));
			
				list.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// reviewListAll 메소드를 호출한 service에 리턴해줍니다.
		return list;
	}

	public int reviewPicWrite(Connection conn,int boardNo, FileInputStream fis, String imgName, File f) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "INSERT INTO REVIEW_FILE VALUES(REVIEW_FILE_SEQ.NEXTVAL,?,?,?,?,SYSDATE)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, imgName);
			pstmt.setInt(3, (int) f.length());
			pstmt.setBinaryStream(4, fis, (int) f.length());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public int selectReviewBoardNo(Connection conn, Review review) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordNo = 0;
		String query = "SELECT BOARD_NO FROM REVIEW_BOARD WHERE TITLE =? AND CONTENT = ?";
	
		

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, review.getTitle());
			pstmt.setString(2, review.getContent());
			rset = pstmt.executeQuery();

			if (rset.next()) {
				recordNo = rset.getInt("BOARD_NO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				// rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recordNo;
	}
	
	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM REVIEW_BOARD";
		// 게시글 총 갯수를 알아오는 쿼리

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				// rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recordTotalCount;
	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {

		int recordTotalCount = totalCount(conn);

		int pageTotalCount = 0; // 전체 페이지의 개수
		// 전체 게시물 갯수 124개, 10개씩 페이지를 만들면
		// 페이지 갯수는 13개
		// 저 변수값이 왜 영일까요..

		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}

		// 현재 페이지를 기준으로 네비게이션을 구해야하므로
		// 현재 페이지 정보를 확인해서 0보다는 크고 전체
		// 페이지 수보다는 작은 위치에 있는지 확인
		// (오류방지용)
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}

		// 1 2 3 4 5 6
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		// ex)currentPage = 8, naviCountPerPage = 5
		// ((8 - 1) / 5) * 5 + 1 = 6;

		// currentPage = 42, naviCountPerPage = 5
		// 41 42 43 44 45
		// ((42-1)/5) * 5 + 1 = 41;

		int endNavi = startNavi + naviCountPerPage - 1;

		// (오류방지용)
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		// < 1 2 3 4 5 >

		// '<' 모양과 '>' 모양을 준비하기 위해
		// 필요한 변수
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
			sb.append("<a href='/review?currentPage=" + (startNavi - 1) + "'>< </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/review?currentPage=" + i + "'><b>" + i + "</b></a>");
			} else {
				sb.append("<a href='/review?currentPage=" + i + "'> " + i + " </a>");
			}
		}
		if (needNext) {
			sb.append("<a href='/review?currentPage=" + (endNavi + 1) + "'> ></a>");
		}
		return sb.toString();
	}

	public String getSearchPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage,
			String search) {

		int recordTotalCount = searchTotalCount(conn, search);

		int pageTotalCount = 0;

		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}

		// 오류방지용
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}

		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;

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

		StringBuilder sb = new StringBuilder();

		if (needPrev) {
			sb.append("<a href='/reviewsearch?search=" + search + "&currentPage=" + (startNavi - 1) + "'>< </a>");

		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/reviewsearch?search=" + search + "&currentPage=" + i + "'><b>" + i + "</b></a>");
			} else {
				sb.append("<a href='/reviewsearch?search=" + search + "&currentPage=" + i + "'> " + i + " </a>");
			}
		}
		if (needNext) {
			sb.append("<a href='/reviewsearch?search=" + search + "&currentPage=" + (endNavi + 1) + "'> ></a>");
		}
		return sb.toString();
	}

	public int searchTotalCount(Connection conn, String search) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;

		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM REVIEW_BOARD WHERE SUBJECT LIKE '%" + search + "%'";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recordTotalCount;
	}

	// 선생님 잘모르겠습니다 ..;;;;;;;;

	public ArrayList<Review> reviewSearchList(Connection conn, int currentPage, int recordCountPerPage, String search) {

		ArrayList<Review> list = new ArrayList<Review>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT REVIEW_BOARD.*, ROW_NUMBER() OVER(ORDER BY REVIEW_NO DESC) "
				+ "AS NUM FROM REVIEW_BOARD WHERE SUBJECT LIKE '%" + search + "%') WHERE NUM BETWEEN ? AND ?";

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Review review = new Review();
				review.setBoard_No(rset.getInt("BOARD_NO"));
				review.setTitle(rset.getString("TITLE"));
				review.setContent(rset.getString("CONTENT"));
				review.setAdoptionTime(rset.getDate("ADOPTIONTIME"));
				review.setCdt(rset.getDate("SYSDATE"));

				list.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}

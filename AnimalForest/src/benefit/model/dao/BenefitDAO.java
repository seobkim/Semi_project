package benefit.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import benefit.model.vo.Benefit;
import benefit.model.vo.BenefitComment;
import benefit.model.vo.BenefitFile;


public class BenefitDAO {

	public ArrayList<Benefit> BenefitList(Connection conn, int categoryNo) {
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		ArrayList<Benefit> benefitList = new ArrayList<Benefit>();
		String query = "SELECT * FROM BENEFIT_BOARD JOIN BENEFIT_FILE USING (BOARD_NO) WHERE CATEGORY_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Benefit benefit = new Benefit();
				BenefitFile bFile = new BenefitFile();
				benefit.setBoardNo(rset.getInt("BOARD_NO"));
				benefit.setMemberNo(rset.getInt("MEMBER_NUM"));
				benefit.setCategoryNo(rset.getInt("CATEGORY_NO"));
				benefit.setTitle(rset.getString("TITLE"));
				benefit.setContents(rset.getString("CONTENT"));
				benefit.setEndDate(rset.getDate("END"));
				benefit.setCDT(rset.getDate("CDT"));
				benefit.setMDT(rset.getDate("MDT"));
				/*bFile.setFileNo(rset.getInt("FILE_NO"));
				bFile.setBoardNo(rset.getInt("BOARD_NO"));*/
				bFile.setFileName(rset.getString("FILE_NAME"));
				benefit.setFileName(rset.getString("FILE_NAME"));
				benefitList.add(benefit);
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
		return benefitList;
		
	}
	
	public ArrayList<Benefit> searchTitle(Connection conn, int currentPage, int recordCountPerPage, String search, int categoryNo) {
		ArrayList<Benefit> list = new ArrayList<Benefit>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int start = currentPage * recordCountPerPage - (recordCountPerPage  - 1);
		int end = currentPage * recordCountPerPage;
		String query = "SELECT *  FROM (SELECT BENEFIT_BOARD .*, ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) AS  NUM FROM BENEFIT_BOARD)  WHERE CATEGORY_NO = ? AND TITLE LIKE '%"+search+"%'  AND NUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Benefit benefit = new Benefit();
				benefit.setBoardNo(rset.getInt("BOARD_NO"));
				benefit.setMemberNo(rset.getInt("MEMBER_NUM"));
				benefit.setCategoryNo(rset.getInt("CATEGORY_NO"));
				benefit.setTitle(rset.getString("TITLE"));
				benefit.setContents(rset.getString("CONTENT"));
				benefit.setEndDate(rset.getDate("END"));
				benefit.setCDT(rset.getDate("CDT"));
				benefit.setMDT(rset.getDate("MDT"));
				list.add(benefit);
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
		return list;
		
	}
	public int insertBenefit(Connection conn, int categoryNo, Benefit benefit, String imgName, FileInputStream fis, File f) {
		/*PreparedStatement pstmt = null;*/
		CallableStatement cstmt = null;
		int result = 0;
		String query = "{CALL BENEFITPIC(?,?,?,?,?,?)}";
		/*String query = "INSERT INTO BENEFIT_BOARD VALUES(BENEFIT_BOARD_SEQ.NEXTVAL,0,?,?,?,?,SYSDATE, SYSDATE)";*/
		try {
			/*pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setString(4, endDate);
			result = pstmt.executeUpdate();*/
			cstmt = conn.prepareCall(query);
			cstmt.setInt(1, categoryNo);
			cstmt.setString(2, benefit.getTitle());
			cstmt.setString(3, benefit.getContents());
			cstmt.setDate(4, benefit.getEndDate());
			cstmt.setString(5, imgName);
			cstmt.setBlob(6, fis,(int)f.length());
			result = cstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public Benefit modifyBenefit(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Benefit benefit = null;
		String query = "SELECT * FROM BENEFIT_BOARD WHERE BOARD_NO = ?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, boardNo);
				rset = pstmt.executeQuery();
				if (rset.next()) {
					benefit = new Benefit();
					benefit.setBoardNo(rset.getInt("BOARD_NO"));
					benefit.setMemberNo(rset.getInt("MEMBER_NUM"));
					benefit.setCategoryNo(rset.getInt("CATEGORY_NO"));
					benefit.setTitle(rset.getString("TITLE"));
					benefit.setContents(rset.getString("CONTENT"));
					benefit.setEndDate(rset.getDate("END"));
					benefit.setCDT(rset.getDate("CDT"));
					benefit.setMDT(rset.getDate("MDT"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
		
		return benefit;
	}
	public int deleteBenefit(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM BENEFIT_BOARD WHERE BOARD_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
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

	public String getSearchPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage, String search, int categoryNo) {
		int recordTotalCount = searchTotalCount(conn, search);
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		if(currentPage < 1) {
			currentPage = 1;
		} else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		int startNavi = ((currentPage -1)/naviCountPerPage) * naviCountPerPage +1;
		int endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi ==1) {
			needPrev = false;
		}if (endNavi == pageTotalCount) {
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();
		if (needPrev) {
			sb.append("<a href='/searchTitle?search="+search+"&currentPage=" + (startNavi -1) + "&categoryNo="+categoryNo+"'>< </a>");
		} 
		for (int i = startNavi; i<=endNavi; i++) {
			if (i == currentPage) {
	            sb.append("<a href='/searchTitle?search="+search+"&currentPage=" + i + "&categoryNo="+categoryNo+"'><b>" + i + "</b></a>");
	         } else {
	            sb.append("<a href='/searchTitle?search="+search+"&currentPage=" + i + "&categoryNo="+categoryNo+"'>" + i + "</a>");
	         }
		}
		if (needNext) {
			sb.append("<a href='/searchTitle?search="+search+"&currentPage=" + (endNavi + 1) + "&categoryNo="+categoryNo+"'>></a>");
		}
		return sb.toString();
	}

	private int searchTotalCount(Connection conn, String search) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Benefit benefitPost(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		Benefit benefit = null;
		ResultSet rset = null;
		String query = "SELECT * FROM BENEFIT_BOARD JOIN BENEFIT_FILE USING (BOARD_NO) WHERE BOARD_NO = ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				benefit = new Benefit();
				benefit.setBoardNo(rset.getInt("BOARD_NO"));
				benefit.setMemberNo(rset.getInt("MEMBER_NUM"));
				benefit.setCategoryNo(rset.getInt("CATEGORY_NO"));
				benefit.setTitle(rset.getString("TITLE"));
				benefit.setContents(rset.getString("CONTENT"));
				benefit.setEndDate(rset.getDate("END"));
				benefit.setCDT(rset.getDate("CDT"));
				benefit.setMDT(rset.getDate("MDT"));
				/*bFile.setFileNo(rset.getInt("FILE_NO"));
				bFile.setBoardNo(rset.getInt("BOARD_NO"));*/
				benefit.setFileName(rset.getString("FILE_NAME"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return benefit;
	}

	public int modifyBenefitCommit(Connection conn, int categoryNo, String title, String contents, String endDate, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE BENEFIT_BOARD SET CATEGORY_NO = ?, TITLE= ?, CONTENT = ?, END = ? WHERE BOARD_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			pstmt.setString(2, title);
			pstmt.setString(3, contents);
			pstmt.setString(4, endDate);
			pstmt.setInt(5, boardNo);
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
	public int insertComment(Connection conn, String contents, String memberId, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="INSERT INTO BENEFIT_COMMENT VALUES(BENEFIT_COMMENT_SEQ.NEXTVAL, ?, 0, ?, SYSDATE, SYSDATE)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, contents);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	public int modifyComment(Connection conn, int boardNo, int commentNo, String comment) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE BENEFIT_COMMENT SET CONTENT = ? WHERE COMMENT_NO = ? AND BOARD_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment);
			pstmt.setInt(2, commentNo);
			pstmt.setInt(3, boardNo);
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
	public int deleteComment(Connection conn, int commentNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM BENEFIT_COMMENT WHERE COMMENT_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<BenefitComment> commentList(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BenefitComment> list = new ArrayList<BenefitComment>();
		String query = "SELECT * FROM BENEFIT_COMMENT  join member using(member_num)WHERE BOARD_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				BenefitComment cmt = new BenefitComment();
				cmt.setCommentNo(rset.getInt("COMMENT_NO"));
				cmt.setBoardNo(rset.getInt("BOARD_NO"));
				cmt.setContents(rset.getString("CONTENT"));
				cmt.setCDT(rset.getDate("CDT"));
				cmt.setMemberId(rset.getString("MEMBER_ID"));
				list.add(cmt);
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
		return list;
	}

	public ArrayList<Benefit> selectList(Connection conn, int currentPage, int recordCountPerPage, int categoryNo) {
		ArrayList<Benefit> list = new ArrayList<Benefit>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT BENEFIT_BOARD.*,ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) AS NUM FROM BENEFIT_BOARD)WHERE NUM BETWEEN ? AND ?"; 
		
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Benefit benefit = new Benefit();
				benefit.setBoardNo(rset.getInt("BOARD_NO"));
				benefit.setMemberNo(rset.getInt("MEMBER_NUM"));
				benefit.setCategoryNo(rset.getInt("CATEGORY_NO"));
				benefit.setTitle(rset.getString("TITLE"));
				benefit.setContents(rset.getString("CONTENT"));
				benefit.setEndDate(rset.getDate("END"));
				benefit.setCDT(rset.getDate("CDT"));
				benefit.setMDT(rset.getDate("MDT"));
				list.add(benefit);
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
		return list;
	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage, int categoryNo) {
		int recordTotalCount = totalCount(conn);
		int pageTotalCount = 0;
		// 전체 게시물 갯수 124개 , 10개 씩 페이지를 만들면 13개

		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = (recordTotalCount / recordCountPerPage) + 1;
		} else {
			pageTotalCount = (recordTotalCount / recordCountPerPage);
		}
		
		//현재 페이지를 기준으로 네비게이션을 구해야하므로 현재 페이지 정보를 확인해서 0보다는 크고 전체
		//페이지 수보다는 작은 위치에 있는지 확인(오류 방지용)
		if(currentPage<1) {
			currentPage=1;
		}
		else if(currentPage>pageTotalCount) {
			currentPage=pageTotalCount;
		}
		
		int startNavi=((currentPage-1)/naviCountPerPage)*naviCountPerPage+1;
		int endNavi= startNavi+naviCountPerPage-1;
		
		if(endNavi>pageTotalCount) {
			endNavi=pageTotalCount;
		}
		//<1 2 3 4 5>
		//'<' '>'모양을 준비하기 위해 필요한 변수
		
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
	         sb.append("<a href = '/benefitList?currentPage=" + (startNavi - 1) + "&categoryNo="+categoryNo+"'>< </a>");
	      }
	      for (int i = startNavi; i <= endNavi; i++) {
	         if (i == currentPage) {
	            sb.append("<a href='/benefitList?currentPage=" + i + "&categoryNo="+categoryNo+"'><b>" + i + "</b></a>");
	         } else {
	            sb.append("<a href='/benefitList?currentPage=" + i + "&categoryNo="+categoryNo+"'>" + i + "</a>");
	         }
	      }
	      if (needNext) {
	         sb.append("<a href='/benefitList?currentPage=" + (endNavi + 1) + "&categoryNo="+categoryNo+"'>></a>");
	      }
	      return sb.toString();
	   }

	private int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM BENEFIT_BOARD";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return recordTotalCount;
	}

}

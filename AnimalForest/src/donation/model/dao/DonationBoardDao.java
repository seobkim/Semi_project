package donation.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import donation.model.vo.DonationBoard;
import donation.model.vo.DonationFile;
import donation.model.vo.DonationHistory;
import donation.model.vo.animalGrp;
import donation.model.vo.animalGrp2;

public class DonationBoardDao {
	
	public ArrayList<DonationBoard> donationBoardList(Connection conn,int currentPage, int recordCountPerPage, char id) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		ArrayList<DonationBoard> list = new ArrayList<DonationBoard>();
		String fquery=null;
		//관리자 쿼리
		String query = "SELECT * FROM (SELECT DONATION_BOARD.*,ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC)AS NUM FROM DONATION_BOARD)A JOIN DONATION_FILE B ON A.BOARD_NO=B.DONATION_NO WHERE NUM BETWEEN ? AND ? ORDER BY 1 DESC";
		
		//일반 사용자쿼리
		String query1= "SELECT * FROM (SELECT DONATION_BOARD.*,ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC)AS NUM FROM DONATION_BOARD)A JOIN DONATION_FILE B ON A.BOARD_NO=B.DONATION_NO WHERE FLAG =1 and NUM BETWEEN ? AND ? ORDER BY 1 DESC";
		
		
		if(id=='0'||id=='2') {//관리자
			fquery=query;
		}
		else if(id=='1') {//일반 사용자
			fquery=query1;
		}
		
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		
		try {
			pstmt = conn.prepareStatement(fquery);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				DonationBoard board= new DonationBoard();
				DonationFile dfile = new DonationFile();
				board.setBoard_No(rset.getInt("BOARD_NO"));
				board.setMember_Num(rset.getInt("MEMBER_NUM"));
				board.setTitle(rset.getString("TITLE"));
				board.setContent(rset.getString("CONTENT"));
				board.setFull_Amount(rset.getInt("FULL_AMOUNT"));
				board.setFlag(rset.getString("FLAG").charAt(0));
				board.setCdt(rset.getDate("CDT"));
				board.setEndDate(rset.getDate("ENDDATE"));
				board.setGrp1_No(rset.getInt("GRP1_NO"));
				board.setGrp2_No(rset.getInt("GRP2_NO"));
				board.setFileName(rset.getString("FILE_NAME"));
				dfile.setFileName(rset.getString("FILE_NAME"));
				board.setdFile(dfile);
				list.add(board);
			}
			
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				rset.close();
				pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}
		
		return list;
	}

	public int donationWrite(Connection conn, DonationBoard donation,String imgName,FileInputStream fis, File f) {
		// TODO Auto-generated method stub
		
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;
		int result = 0;
//		String query="INSERT INTO DONATION_BOARD VALUES(DONATION_BOARD_SEQ.NEXTVAL,?,?,?,?,DEFAULT,SYSDATE,?,?,?)";
		String query="{CALL PICPROC(?,?,?,?,?,?,?,?,?)}";
		try {
			/*pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, donation.getMember_Num());
			pstmt.setString(2,donation.getTitle());
			pstmt.setString(3,donation.getContent());
			pstmt.setInt(4, donation.getFull_Amount());
			pstmt.setDate(5,donation.getEndDate());
			pstmt.setInt(6, donation.getGrp2_No());
			pstmt.setInt(7, donation.getGrp1_No());
			
			result = pstmt.executeUpdate();*/
			
			cstmt = conn.prepareCall(query);
			cstmt.setInt(1, donation.getMember_Num());
			cstmt.setString(2,donation.getTitle());
			cstmt.setString(3,donation.getContent());
			cstmt.setInt(4, donation.getFull_Amount());
			cstmt.setDate(5, donation.getEndDate());
			cstmt.setInt(6, donation.getGrp2_No());
			cstmt.setInt(7, donation.getGrp1_No());
			cstmt.setString(8, imgName);
			cstmt.setBlob(9, fis,(int)f.length());
			
			result = cstmt.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				cstmt.close();
				pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}
		return result;
	}
	
	public DonationBoard donationSelect(Connection conn, int boardNo) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;
		DonationBoard board = null;
		ResultSet rset= null;
		String query = "SELECT * FROM DONATION_BOARD a join donation_file b on a.board_no=b.donation_no WHERE BOARD_NO=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				board= new DonationBoard();
				board.setBoard_No(rset.getInt("BOARD_NO"));
				board.setMember_Num(rset.getInt("MEMBER_NUM"));
				board.setTitle(rset.getString("TITLE"));
				board.setContent(rset.getString("CONTENT"));
				board.setFull_Amount(rset.getInt("FULL_AMOUNT"));
				board.setFlag(rset.getString("FLAG").charAt(0));
				board.setCdt(rset.getDate("CDT"));
				board.setEndDate(rset.getDate("ENDDATE"));
				board.setGrp1_No(rset.getInt("GRP1_NO"));
				board.setGrp2_No(rset.getInt("GRP2_NO"));
				board.setFileName(rset.getString("FILE_NAME"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}
		
		
		return board;
	}

	public int donationDelete(Connection conn, int boardNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result =0;
		String query = "DELETE FROM DONATION_BOARD WHERE BOARD_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}
		
		
		return result;
	}

	public ArrayList<animalGrp> animalList(Connection conn) {
		// TODO Auto-generated method stub
		Statement stmt =null;
		ResultSet rset = null;
		
		ArrayList<animalGrp> list = new ArrayList<animalGrp>();
		String query="select distinct * from ANIMAL_GRP1";
	
		
		try {
			stmt = conn.createStatement();
			rset=stmt.executeQuery(query);
			
			
			while(rset.next()) {
				animalGrp animal = new animalGrp();
				animal.setGrp1No(rset.getInt("GRP1_NO"));
				animal.setGrp1Name(rset.getString("GRP1_NAME"));
				
				list.add(animal);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}
		
		
		return list;
	}

	public ArrayList<animalGrp2> animalList2(Connection conn) {
		// TODO Auto-generated method stub
		Statement stmt =null;
		ResultSet rset = null;
		
		ArrayList<animalGrp2> list = new ArrayList<animalGrp2>();
		String query="select * from ANIMAL_GRP2 join ANIMAL_GRP1 using(GRP1_NO)";  
	
		
		try {
			stmt = conn.createStatement();
			rset=stmt.executeQuery(query);
			
			
			while(rset.next()) {
				animalGrp2 animal = new animalGrp2();
				animal.setGrp1No(rset.getInt("GRP1_NO"));
				animal.setGrp2No(rset.getInt("GRP2_No"));
				animal.setGrp1Name(rset.getString("GRP1_NAME"));
				animal.setGrp2Name(rset.getString("GRP2_NAME"));
				
				list.add(animal);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}
		
		
		return list;
	}

	public int dontionUpdate(Connection conn, DonationBoard dboard) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0 ;
		String query = "UPDATE DONATION_BOARD SET TITLE=?,CONTENT=?,FULL_AMOUNT=?,ENDDATE=?,GRP2_NO=?,GRP1_NO=? WHERE BOARD_NO=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, dboard.getTitle());
			pstmt.setString(2, dboard.getContent());
			pstmt.setInt(3, dboard.getFull_Amount());
			pstmt.setDate(4, dboard.getEndDate());
			pstmt.setInt(5, dboard.getGrp2_No());
			pstmt.setInt(6, dboard.getGrp1_No());
			pstmt.setInt(7, dboard.getBoard_No());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}
		return result;
	}

	public int donationPicWrite(Connection conn, FileInputStream fis, String imgName,File f) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result=0;
	
		
		String query = "INSERT INTO DONATION_FILE VALUES(DONATION_FILE_SEQ.NEXTVAL,?,NULL,?,SYSDATE,DONATION_BOARD_SEQ.CURRVAL)";
		
		try {
			
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, imgName);
			pstmt.setBinaryStream(2, fis,(int)f.length());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}
		
		return result;
	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage,char id) {
		// TODO Auto-generated method stub
		
		int recordTotalCount = totalCount(conn,id);
		int pageTotalCount = 0;
		// 전체 게시물 갯수 124개 , 10개 씩 페이지를 만들면 13개
		
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = (recordTotalCount / recordCountPerPage) + 1;
		} else {
			pageTotalCount = (recordTotalCount / recordCountPerPage);
		}
		
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
	         sb.append("<a href = 'donationBoard?currentPage=" + (startNavi - 1) + "'>< </a>");
	      }
	      for (int i = startNavi; i <= endNavi; i++) {
	         if (i == currentPage) {
	            sb.append("<a href='donationBoard?currentPage=" + i + "'><b>" + i + "</b></a>");
	         } else {
	            sb.append("<a href='donationBoard?currentPage=" + i + "'>" + i + "</a>");
	         }
	      }
	      if (needNext) {
	         sb.append("<a href='donationBoard?currentPage=" + (endNavi + 1) + "'>></a>");
	      }
	      return sb.toString();
		
		
	}
	public int totalCount(Connection conn,char id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query =null;
		if(id=='2'|| id=='0') {
			query = "SELECT COUNT(*) AS TOTALCOUNT FROM DONATION_BOARD";
		}else if(id=='1') {
			query = "SELECT COUNT(*) AS TOTALCOUNT FROM DONATION_BOARD where flag=1";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}

		return recordTotalCount;

	}
	public int totalSelectCount(Connection conn,String val) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM DONATION_BOARD WHERE TITLE like '%' || ? || '%'";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, val);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}

		return recordTotalCount;

	}


	public ArrayList<DonationBoard> donationSelectName(Connection conn, int currentPage, int recordCountPerPage,
			String val) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		ArrayList<DonationBoard> list = new ArrayList<DonationBoard>();
		
		//일반 사용자 쿼리
		String query = "SELECT * FROM (SELECT DONATION_BOARD.*,ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC)AS NUM FROM DONATION_BOARD where donation_board.title like '%' || ? || '%')A JOIN DONATION_FILE B ON A.BOARD_NO=B.DONATION_NO WHERE NUM BETWEEN ? AND ? ORDER BY 1 DESC";
		
		//관리자 쿼리
		String query1= "SELECT * FROM DONATION_BOARD";
		
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, val);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				DonationBoard board= new DonationBoard();
				DonationFile dfile = new DonationFile();
				board.setBoard_No(rset.getInt("BOARD_NO"));
				board.setMember_Num(rset.getInt("MEMBER_NUM"));
				board.setTitle(rset.getString("TITLE"));
				board.setContent(rset.getString("CONTENT"));
				board.setFull_Amount(rset.getInt("FULL_AMOUNT"));
				board.setCdt(rset.getDate("CDT"));
				board.setEndDate(rset.getDate("ENDDATE"));
				board.setGrp1_No(rset.getInt("GRP1_NO"));
				board.setGrp2_No(rset.getInt("GRP2_NO"));
				board.setFileName(rset.getString("FILE_NAME"));
				dfile.setFileName(rset.getString("FILE_NAME"));
				board.setdFile(dfile);
				list.add(board);
			}
			
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				rset.close();
				pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}
		
		return list;
	}

	public String getSelectPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage,
			String val) {
		// TODO Auto-generated method stub
		int recordTotalCount = totalSelectCount(conn, val);
		int pageTotalCount = 0;
		// 전체 게시물 갯수 124개 , 10개 씩 페이지를 만들면 13개
		
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = (recordTotalCount / recordCountPerPage) + 1;
		} else {
			pageTotalCount = (recordTotalCount / recordCountPerPage);
		}
		
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
	         sb.append("<a href = 'donationBoard?currentPage=" + (startNavi - 1) + "'>< </a>");
	      }
	      for (int i = startNavi; i <= endNavi; i++) {
	         if (i == currentPage) {
	            sb.append("<a href='donationSelectName?currentPage=" + i + "'><b>" + i + "</b></a>");
	         } else {
	            sb.append("<a href='donationSelectName?currentPage=" + i + "'>" + i + "</a>");
	         }
	      }
	      if (needNext) {
	         sb.append("<a href='donationSelectName?currentPage=" + (endNavi + 1) + "'>></a>");
	      }
	      return sb.toString();
		
	}

	public int insertDonation(Connection conn, DonationHistory dhistory) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result =0;
		String query = "INSERT INTO DONATION_HISTORY VALUES(?,?,SYSDATE,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dhistory.getMember_Num());
			pstmt.setInt(2, dhistory.getBoard_No());
			pstmt.setInt(3, dhistory.getDonation_Amount());
			
			result= pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}
		
		return result;
	}

	public DonationHistory selectHistory(Connection conn, int boardNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		DonationHistory result =null;
		ResultSet rset = null; 
		String query = "SELECT COUNT(*) as count ,SUM(DONATION_AMOUNT) as sum FROM DONATION_HISTORY WHERE board_no=?";
		
		try {				
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
		
			
			rset= pstmt.executeQuery();
			
			if(rset.next()) {
				result=new DonationHistory();
				result.setCount(rset.getInt("COUNT"));
				result.setFull_Amount(rset.getInt("SUM"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}
		
		return result;
	}

	public int flagModi(Connection conn, char flag, int boardNo) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;
		
		int result=0;
		String val=null;
		String query= "UPDATE DONATION_BOARD SET FLAG=? WHERE BOARD_NO=?";
		
		if(flag=='0') {
			val="1";
		}
		else if(flag=='1') {
			val="0";
		}
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, val);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	

	

	

}

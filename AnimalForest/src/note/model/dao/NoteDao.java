package note.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import note.model.vo.Note;

public class NoteDao {

	public ArrayList<Note> selectReceiveNoteList(Connection conn, int reciverNum, int currentPage, int recordCountPerPage) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = 	"SELECT	A.* " 
					 +	"FROM    (SELECT A.NOTE_NO, A.SENDER_NUM, A.RECEIVER_NUM, A.NOTE_TITLE, " 
					 +  "                A.NOTE_CONTENTS, A.NOTE_DATE, "  
					 +	"                B.MEMBER_ID AS RECEIVER_ID, C.MEMBER_ID AS SENDER_ID, " 
					 +	"                FLOOR((ROW_NUMBER() OVER(ORDER BY A.NOTE_NO DESC)-1)/?) AS PAGE_NUM, " 
					 +	"                (ROW_NUMBER() OVER(ORDER BY A.NOTE_NO DESC) -1) AS NUM " 
					 +	"        FROM	NOTE A, MEMBER B, MEMBER C "  
					 +	"        WHERE	A.RECEIVER_NUM =  ? " 
					 +	"        AND     A.RECEIVER_NUM = B.MEMBER_NUM(+) " 
					 +	"        AND     A.SENDER_NUM = C.MEMBER_NUM(+)) A " 
					 +	"WHERE   A.PAGE_NUM + 1 = ? ";
		ArrayList<Note> nList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recordCountPerPage);
			pstmt.setInt(2, reciverNum);
			pstmt.setInt(3, currentPage);
			rset = pstmt.executeQuery();
			nList = new ArrayList<Note>();
			Note note = null;
			while(rset.next()) {
				note = new Note();
				note.setNoteNo(rset.getInt("NOTE_NO"));
				note.setSenderNum(rset.getInt("SENDER_NUM"));
				note.setReceiverNum(rset.getInt("RECEIVER_NUM"));
				note.setNoteTitle(rset.getString("NOTE_TITLE"));
				note.setNoteContents(rset.getString("NOTE_CONTENTS"));
				note.setNoteDate(rset.getDate("NOTE_DATE"));
				note.setReceiverId(rset.getString("RECEIVER_ID"));
				note.setSenderId(rset.getString("SENDER_ID"));
				nList.add(note);
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
		return nList;
	}
	
	public int reciveTotalCount(Connection conn, int Num, int type) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query = "";
		if(type == 1) {
			query = "SELECT 	COUNT(0) AS TOTALCOUNT "
					 + "FROM 	NOTE "
					 + "WHERE	RECEIVER_NUM = ?";
		}else if(type == 2) {
			query = "SELECT 	COUNT(0) AS TOTALCOUNT "
					 + "FROM 	NOTE "
					 + "WHERE	SENDER_NUM = ?";			
		}
		

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Num);
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

	public String getPageNavi(Connection conn, int Num, int currentPage, int recordCountPerPage, int naviCountPerPage, int type) {
		int recordTotalCount = reciveTotalCount(conn, Num, type);

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
		
		String callServlet = "";
		if(type == 1) {
			callServlet = "/receiveNoteList";
		}else if(type == 2) {
			callServlet = "/sendNoteList";
		}
		
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

	public Note selectOneNote(Connection conn, int noteNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = 	"SELECT A.NOTE_NO, A.SENDER_NUM, A.RECEIVER_NUM, A.NOTE_TITLE, "
					 + 	"       A.NOTE_CONTENTS, A.NOTE_DATE, " 
					 +	"       B.MEMBER_ID AS RECEIVER_ID, C.MEMBER_ID AS SENDER_ID " 
					 + 	"FROM	NOTE A, MEMBER B, MEMBER C " 
					 +	"WHERE	A.NOTE_NO = ? "
					 + 	"AND    A.RECEIVER_NUM = B.MEMBER_NUM(+) "
					 + 	"AND    A.SENDER_NUM = C.MEMBER_NUM(+) ";
		Note note = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noteNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				note = new Note();
				note.setNoteNo(rset.getInt("NOTE_NO"));
				note.setSenderNum(rset.getInt("SENDER_NUM"));
				note.setReceiverNum(rset.getInt("RECEIVER_NUM"));
				note.setNoteTitle(rset.getString("NOTE_TITLE"));
				note.setNoteContents(rset.getString("NOTE_CONTENTS"));
				note.setNoteDate(rset.getDate("NOTE_DATE"));
				note.setReceiverId(rset.getString("RECEIVER_ID"));
				note.setSenderId(rset.getString("SENDER_ID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return note;
	}

	public int deleteNote(Connection conn, int noteNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		String query = "DELETE FROM NOTE WHERE NOTE_NO = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noteNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public int replyNote(Connection conn, Note note) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		String query =	"INSERT INTO NOTE "
					 + 	"SELECT NOTE_SEQ.NEXTVAL, B.MEMBER_NUM AS SENDER_NUM, C.MEMBER_NUM AS RECEIVER_NUM, "
					 + 	"		?, ?, SYSDATE "
					 + 	"FROM	DUAL A, MEMBER B, MEMBER C "
					 + 	"WHERE	B.MEMBER_ID = ? "
					 + 	"AND	C.MEMBER_ID = ? ";
		
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, note.getNoteTitle());
			pstmt.setString(2, note.getNoteContents());
			pstmt.setString(3, note.getSenderId());
			pstmt.setString(4, note.getReceiverId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Note> selectSendNoteList(Connection conn, int senderNum, int currentPage, int recordCountPerPage) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = 	"SELECT	A.* " 
					 +	"FROM    (SELECT A.NOTE_NO, A.SENDER_NUM, A.RECEIVER_NUM, A.NOTE_TITLE, " 
					 +  "                A.NOTE_CONTENTS, A.NOTE_DATE, "  
					 +	"                B.MEMBER_ID AS RECEIVER_ID, C.MEMBER_ID AS SENDER_ID, " 
					 +	"                FLOOR((ROW_NUMBER() OVER(ORDER BY A.NOTE_NO DESC)-1)/?) AS PAGE_NUM, " 
					 +	"                (ROW_NUMBER() OVER(ORDER BY A.NOTE_NO DESC) -1) AS NUM " 
					 +	"        FROM	 NOTE A, MEMBER B, MEMBER C "  
					 +	"        WHERE	 A.SENDER_NUM =  ? " 
					 +	"        AND     A.RECEIVER_NUM = B.MEMBER_NUM(+) " 
					 +	"        AND     A.SENDER_NUM = C.MEMBER_NUM(+)) A " 
					 +	"WHERE   A.PAGE_NUM + 1 = ? ";
		ArrayList<Note> nList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recordCountPerPage);
			pstmt.setInt(2, senderNum);
			pstmt.setInt(3, currentPage);
			rset = pstmt.executeQuery();
			nList = new ArrayList<Note>();
			Note note = null;
			while(rset.next()) {
				note = new Note();
				note.setNoteNo(rset.getInt("NOTE_NO"));
				note.setSenderNum(rset.getInt("SENDER_NUM"));
				note.setReceiverNum(rset.getInt("RECEIVER_NUM"));
				note.setNoteTitle(rset.getString("NOTE_TITLE"));
				note.setNoteContents(rset.getString("NOTE_CONTENTS"));
				note.setNoteDate(rset.getDate("NOTE_DATE"));
				note.setReceiverId(rset.getString("RECEIVER_ID"));
				note.setSenderId(rset.getString("SENDER_ID"));
				nList.add(note);
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
		return nList;
	}

}

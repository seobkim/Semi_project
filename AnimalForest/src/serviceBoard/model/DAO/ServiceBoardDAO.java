package serviceBoard.model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import member.model.vo.Member;
import serviceBoard.model.vo.ServiceBoard;
import serviceBoard.model.vo.ServiceBoardAndMember;
import serviceBoard.model.vo.Service_Comment;
import serviceBoard.model.vo.Service_Form;

public class ServiceBoardDAO {

	public int insertServiceContent(ServiceBoard serviceBoard, Connection conn) {

		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO SERVICE_BOARD VALUES(SERVICE_BOARD_SEQ.NEXTVAL,?,?,?,DEFAULT,?,?,?,?,?,SYSDATE,SYSDATE)";

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, 4);
			pstmt.setString(2, serviceBoard.getTitle());
			pstmt.setString(3, serviceBoard.getContent());
			pstmt.setDate(4, serviceBoard.getDeadLine());
			pstmt.setDate(5, serviceBoard.getService_Fr());
			pstmt.setDate(6, serviceBoard.getService_To());
			pstmt.setInt(7, serviceBoard.getVolunteer());
			pstmt.setInt(8, serviceBoard.getQualfication());
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

	public int insertServiceContent(Connection conn, ServiceBoard serviceBoard, String imgName, FileInputStream fis,
			File f, int memberNum) {
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "{CALL SERVICEPIC(?,?,?,?,?,?,?,?,?,?)}";

		try {
			cstmt = conn.prepareCall(query);
			cstmt.setInt(1, memberNum);
			cstmt.setString(2, serviceBoard.getTitle());
			cstmt.setString(3, serviceBoard.getContent());
			cstmt.setDate(4, serviceBoard.getDeadLine());
			cstmt.setDate(5, serviceBoard.getService_Fr());
			cstmt.setDate(6, serviceBoard.getService_To());
			cstmt.setInt(7, serviceBoard.getVolunteer());
			cstmt.setInt(8, serviceBoard.getQualfication());
			cstmt.setString(9, imgName);
			cstmt.setBlob(10, fis, (int) f.length());
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

	public int deleteServiceContent(int board_No, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM SERVICE_BOARD WHERE BOARD_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board_No);
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

	public ArrayList<ServiceBoard> selectContentList(Connection conn, char memberType) {
		ArrayList<ServiceBoard> sList = new ArrayList<ServiceBoard>();
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select * from SERVICE_BOARD";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				ServiceBoard serviceBoard = new ServiceBoard();
				serviceBoard.setBoard_No(rset.getInt("BOARD_NO"));
				serviceBoard.setMember_Num(rset.getInt("MEMBER_NUM"));
				serviceBoard.setTitle(rset.getString("TITLE"));
				serviceBoard.setContent(rset.getString("CONTENT"));
				serviceBoard.setFlag(rset.getString("FLAG"));
				serviceBoard.setDeadLine(rset.getDate("DEADLINE"));
				serviceBoard.setService_Fr(rset.getDate("SERVICE_FR"));
				serviceBoard.setService_To(rset.getDate("SERVICE_TO"));
				serviceBoard.setVolunteer(rset.getInt("VOLUNTEER"));
				serviceBoard.setQualfication(rset.getInt("QUALFICATION"));
				serviceBoard.setCdt(rset.getDate("CDT"));
				serviceBoard.setMdt(rset.getDate("MDT"));
				sList.add(serviceBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sList;
	}

	public ServiceBoard selectContentOne(Connection conn, int board_No, int count) {
		ServiceBoard serviceBoard = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM SERVICE_BOARD RIGHT JOIN SERVICE_FILE USING(BOARD_NO) WHERE BOARD_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board_No);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				serviceBoard = new ServiceBoard();
				serviceBoard.setBoard_No(rset.getInt("BOARD_NO"));
				serviceBoard.setMember_Num(rset.getInt("MEMBER_NUM"));
				serviceBoard.setTitle(rset.getString("TITLE"));
				serviceBoard.setContent(rset.getString("CONTENT"));
				serviceBoard.setFlag(rset.getString("FLAG"));
				serviceBoard.setDeadLine(rset.getDate("DEADLINE"));
				serviceBoard.setService_Fr(rset.getDate("SERVICE_FR"));
				serviceBoard.setService_To(rset.getDate("SERVICE_TO"));
				serviceBoard.setVolunteer(rset.getInt("VOLUNTEER"));
				serviceBoard.setQualfication(rset.getInt("QUALFICATION"));
				serviceBoard.setCdt(rset.getDate("CDT"));
				serviceBoard.setMdt(rset.getDate("MDT"));
				serviceBoard.setFileName(rset.getString("FILE_NAME"));
				serviceBoard.setCount(count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return serviceBoard;
	}

	public int modifyContent(Connection conn, int board_No, ServiceBoard serviceBoard) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE SERVICE_BOARD SET TITLE=?,CONTENT=?,FLAG=?,DEADLINE=?,SERVICE_FR=?,SERVICE_TO=?,VOLUNTEER=?,QUALFICATION=?,MDT=SYSDATE WHERE BOARD_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, serviceBoard.getTitle());
			pstmt.setString(2, serviceBoard.getContent());
			pstmt.setString(3, serviceBoard.getFlag());
			pstmt.setDate(4, serviceBoard.getDeadLine());
			pstmt.setDate(5, serviceBoard.getService_Fr());
			pstmt.setDate(6, serviceBoard.getService_To());
			pstmt.setInt(7, serviceBoard.getVolunteer());
			pstmt.setInt(8, serviceBoard.getQualfication());
			pstmt.setInt(9, board_No);
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

	public ArrayList<ServiceBoardAndMember> searchContentList(int currentPage, int recordCountPerPage, char memberType,
			Connection conn, String search) {
		ArrayList<ServiceBoardAndMember> serviceBoardAndMemberList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		String query1 = "SELECT MEMBER_TYPE, title, member_name,member_num,MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO, SERVICE_FR, SERVICE_TO, VOLUNTEER, QUALFICATION, FILE_NAME , CDT, MDT, DEADLINE, CONTENT, FLAG from (SELECT MEMBER_TYPE, TITLE, MEMBER_NAME, MEMBER_NUM, MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO, SERVICE_FR, SERVICE_TO, VOLUNTEER, QUALFICATION, ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) AS NUM ,FILE_NAME , SERVICE_BOARD.CDT, MDT, DEADLINE, CONTENT, FLAG FROM member join SERVICE_BOARD USING (MEMBER_NUM) JOIN SERVICE_FILE USING (BOARD_NO)) WHERE TITLE LIKE '%"
				+ search + "%' AND NUM BETWEEN ? AND ?";
		String query2 = "SELECT MEMBER_TYPE, title, member_name,member_num,MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO, SERVICE_FR, SERVICE_TO, VOLUNTEER, QUALFICATION, FILE_NAME , CDT, MDT, DEADLINE, CONTENT, FLAG from (SELECT MEMBER_TYPE, TITLE, MEMBER_NAME, MEMBER_NUM, MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO, SERVICE_FR, SERVICE_TO, VOLUNTEER, QUALFICATION, ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) AS NUM ,FILE_NAME , SERVICE_BOARD.CDT, MDT, DEADLINE, CONTENT, FLAG FROM member join SERVICE_BOARD USING (MEMBER_NUM) JOIN SERVICE_FILE USING (BOARD_NO)) WHERE TITLE LIKE '%"
				+ search + "%' AND FLAG LIKE '1' AND NUM BETWEEN ? AND ?";
		try {
			if (memberType == '0') {
				pstmt = conn.prepareStatement(query1);
			} else {
				pstmt = conn.prepareStatement(query2);
			}
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			serviceBoardAndMemberList = new ArrayList<ServiceBoardAndMember>();

			while (rset.next()) {
				ServiceBoardAndMember serviceBoardAndMember = new ServiceBoardAndMember();
				serviceBoardAndMember.setMemberType(rset.getString("MEMBER_TYPE").charAt(0));
				serviceBoardAndMember.setBoard_No(rset.getInt("BOARD_NO"));
				serviceBoardAndMember.setMemberNum(rset.getInt("MEMBER_NUM"));
				serviceBoardAndMember.setFileName(rset.getString("FILE_NAME"));
				serviceBoardAndMember.setTitle(rset.getString("TITLE"));
				serviceBoardAndMember.setContent(rset.getString("CONTENT"));
				serviceBoardAndMember.setFlag(rset.getString("FLAG"));
				serviceBoardAndMember.setDeadLine(rset.getDate("DEADLINE"));
				serviceBoardAndMember.setService_Fr(rset.getDate("SERVICE_FR"));
				serviceBoardAndMember.setService_To(rset.getDate("SERVICE_TO"));
				serviceBoardAndMember.setVolunteer(rset.getInt("VOLUNTEER"));
				serviceBoardAndMember.setQualfication(rset.getInt("QUALFICATION"));
				serviceBoardAndMember.setCdt(rset.getDate("CDT"));
				serviceBoardAndMember.setMdt(rset.getDate("MDT"));
				serviceBoardAndMemberList.add(serviceBoardAndMember);
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
		return serviceBoardAndMemberList;
	}

	public ServiceBoardAndMember selectMemberOne(Connection conn, int board_No) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER RIGHT JOIN SERVICE_BOARD USING (MEMBER_NUM) WHERE BOARD_NO =?";
		ServiceBoardAndMember serviceBoardAndMember = new ServiceBoardAndMember();

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board_No);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				serviceBoardAndMember.setMemberNum(rset.getInt("MEMBER_NUM"));
				serviceBoardAndMember.setMemberName(rset.getString("MEMBER_NAME"));
				serviceBoardAndMember.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				serviceBoardAndMember.setMemberPhone(rset.getString("MEMBER_PHONE"));
				serviceBoardAndMember.setBoard_No(rset.getInt("BOARD_NO"));
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
		return serviceBoardAndMember;
	}

	public ServiceBoardAndMember selectMemberOneByForm(Connection conn, int board_No, int memberNum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT MEMBER_NAME, JOB, PHONE, ADDRESS, DETAIL_ADDRESS, CONTENT, EMAIL, MEMBER_NUM, BOARD_NO FROM MEMBER RIGHT JOIN SERVICE_FORM USING (MEMBER_NUM) WHERE BOARD_NO=? AND MEMBER_NUM = ?";
		ServiceBoardAndMember serviceBoardAndMember = null;

		try {
			serviceBoardAndMember = new ServiceBoardAndMember();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board_No);
			pstmt.setInt(2, memberNum);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				serviceBoardAndMember.setMemberName(rset.getString("MEMBER_NAME"));
				serviceBoardAndMember.setJob(rset.getString("JOB"));
				serviceBoardAndMember.setMemberPhone(rset.getString("PHONE"));
				serviceBoardAndMember.setEmail(rset.getString("EMAIL"));
				serviceBoardAndMember.setMemberAddress(rset.getString("ADDRESS"));
				serviceBoardAndMember.setDetail_Address(rset.getString("DETAIL_ADDRESS"));
				serviceBoardAndMember.setContent(rset.getString("CONTENT"));
				serviceBoardAndMember.setBoard_No(board_No);
				serviceBoardAndMember.setMemberNum(memberNum);
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
		return serviceBoardAndMember;
	}

	public int insertServiceComment(Connection conn, int board_No, int memberNum, String content, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO SERVICE_COMMENT VALUES(SERVICE_COMMENT_SEQ.NEXTVAL,?,?,?,SYSDATE,SYSDATE)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNum);
			pstmt.setInt(2, board_No);
			pstmt.setString(3, content);
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

	public ArrayList<Service_Comment> selectCommentList(Connection conn, int board_No) {
		ArrayList<Service_Comment> list = new ArrayList<Service_Comment>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SERVICE_COMMENT RIGHT JOIN MEMBER USING(MEMBER_NUM) WHERE BOARD_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board_No);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Service_Comment comment = new Service_Comment();
				comment.setComment_No(rset.getInt("COMMENT_NO"));
				comment.setMember_Num(rset.getInt("MEMBER_NUM"));
				comment.setMember_Name(rset.getString("MEMBER_NAME"));
				comment.setBoard_No(rset.getInt("BOARD_NO"));
				comment.setContent(rset.getString("CONTENT"));
				comment.setCdt(rset.getDate("CDT"));
				comment.setMdt(rset.getDate("MDT"));
				list.add(comment);
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

	public int deleteServiceComment(Connection conn, int comment_No) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM SERVICE_COMMENT WHERE COMMENT_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comment_No);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int modifyComment(Connection conn, String content, int comment_No, int board_No) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE SERVICE_COMMENT SET CONTENT=? WHERE COMMENT_NO=? AND BOARD_NO =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, content);
			pstmt.setInt(2, comment_No);
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

	public int submitForm(Connection conn, Service_Form serviceForm) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO SERVICE_FORM VALUES(?,?,?,?,?,?,?,?,SYSDATE,SYSDATE)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, serviceForm.getMember_Num());
			pstmt.setInt(2, serviceForm.getBoard_No());
			pstmt.setString(3, serviceForm.getContent());
			pstmt.setString(4, serviceForm.getJob());
			pstmt.setString(5, serviceForm.getPhone());
			pstmt.setString(6, serviceForm.getEmail());
			pstmt.setString(7, serviceForm.getAddress());
			pstmt.setString(8, serviceForm.getDetail_Address());
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

	public ArrayList<ServiceBoardAndMember> selectFormList(Connection conn, int board_No) {
		ArrayList<ServiceBoardAndMember> formList = new ArrayList<ServiceBoardAndMember>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SERVICE_FORM RIGHT JOIN MEMBER USING(MEMBER_NUM) WHERE BOARD_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board_No);
			rset = pstmt.executeQuery();
			formList = new ArrayList<ServiceBoardAndMember>();

			while (rset.next()) {
				ServiceBoardAndMember serviceBoardAndMember = new ServiceBoardAndMember();
				serviceBoardAndMember.setMemberNum(rset.getInt("MEMBER_NUM"));
				serviceBoardAndMember.setMemberName(rset.getString("MEMBER_NAME"));
				serviceBoardAndMember.setBoard_No(board_No);
				serviceBoardAndMember.setContent(rset.getString("CONTENT"));
				serviceBoardAndMember.setJob(rset.getString("JOB"));
				serviceBoardAndMember.setJob(rset.getString("PHONE"));
				serviceBoardAndMember.setEmail(rset.getString("EMAIL"));
				serviceBoardAndMember.setMemberAddress(rset.getString("ADDRESS"));
				serviceBoardAndMember.setDetail_Address(rset.getString("DETAIL_ADDRESS"));
				serviceBoardAndMember.setCdt(rset.getDate("CDT"));
				serviceBoardAndMember.setMdt(rset.getDate("MDT"));
				formList.add(serviceBoardAndMember);
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
		return formList;
	}

	public Service_Form selectOneForm(Connection conn, int memberNum, int board_No) {
		Service_Form serviceForm = new Service_Form();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SERVICE_FORM WHERE MEMBER_NUM=? AND BOARD_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNum);
			pstmt.setInt(2, board_No);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				serviceForm.setBoard_No(rset.getInt("BOARD_NO"));
				serviceForm.setMember_Num(rset.getInt("MEMBER_NUM"));
				serviceForm.setContent(rset.getString("CONTENT"));
				serviceForm.setJob(rset.getString("JOB"));
				serviceForm.setPhone(rset.getString("PHONE"));
				serviceForm.setEmail(rset.getString("EMAIL"));
				serviceForm.setAddress(rset.getString("ADDRESS"));
				serviceForm.setDetail_Address(rset.getString("DETAIL_ADDRESS"));
				serviceForm.setCdt(rset.getDate("CDT"));
				serviceForm.setMdt(rset.getDate("MDT"));
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
		return serviceForm;
	}

	public ServiceBoard selectBoardOneByForm(Connection conn, int board_No, int memberNum) {
		ServiceBoard serviceBoard = new ServiceBoard();
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM SERVICE_BOARD WHERE MEMBER_NUM=? AND BOARD_NO =?";
		ResultSet rset = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNum);
			pstmt.setInt(2, board_No);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				serviceBoard.setBoard_No(rset.getInt("BOARD_NO"));
				serviceBoard.setMember_Num(rset.getInt("MEMBER_NUM"));
				serviceBoard.setTitle(rset.getString("TITLE"));
				serviceBoard.setContent(rset.getString("CONTENT"));
				serviceBoard.setFlag(rset.getString("FLAG"));
				serviceBoard.setDeadLine(rset.getDate("DEADLINE"));
				serviceBoard.setService_Fr(rset.getDate("SERVICE_FR"));
				serviceBoard.setService_To(rset.getDate("SERVICE_TO"));
				serviceBoard.setVolunteer(rset.getInt("VOLUNTEER"));
				serviceBoard.setQualfication(rset.getInt("QUALFICATION"));
				serviceBoard.setCdt(rset.getDate("CDT"));
				serviceBoard.setMdt(rset.getDate("MDT"));
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
		return serviceBoard;
	}

	public int deleteServiceForm(Connection conn, int board_No, int member_Num) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM SERVICE_FORM WHERE MEMBER_NUM =? AND BOARD_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, member_Num);
			pstmt.setInt(2, board_No);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ServiceBoardAndMember selectMemberOne(Connection conn, String memberName) {
		ServiceBoardAndMember serviceBoardAndMember = new ServiceBoardAndMember();
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM MEMBER RIGHT JOIN SERVICE_BOARD USING (MEMBER_NUM) WHERE MEMBER_NAME=?";
		ResultSet rset = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberName);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				serviceBoardAndMember.setMemberNum(rset.getInt("MEMBER_NUM"));
				serviceBoardAndMember.setMemberName(rset.getString("MEMBER_NAME"));
				serviceBoardAndMember.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				serviceBoardAndMember.setMemberPhone(rset.getString("MEMBER_ADDRESS"));
				serviceBoardAndMember.setBoard_No(rset.getInt("BOARD_NO"));
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
		return serviceBoardAndMember;
	}

	public ArrayList<ServiceBoardAndMember> selectContentList(Connection conn, int currentPage, int recordCountPerPage,
			char memberType) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ServiceBoardAndMember> serviceBoardAndMemberList = null;

		/*
		 * String query =
		 * "SELECT * FROM (SELECT SERVICE_BOARD.*, ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) AS NUM FROM SERVICE_BOARD) WHERE NUM BETWEEN ? AND ?"
		 * ;
		 */
		String query1 = "select MEMBER_TYPE, title, member_name,member_num,MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO, SERVICE_FR, SERVICE_TO, VOLUNTEER, QUALFICATION, FILE_NAME , CDT, MDT, DEADLINE, CONTENT, FLAG from (SELECT MEMBER_TYPE, TITLE, MEMBER_NAME, MEMBER_NUM, MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO, SERVICE_FR, SERVICE_TO, VOLUNTEER, QUALFICATION, ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) AS NUM ,FILE_NAME , SERVICE_BOARD.CDT, MDT, DEADLINE, CONTENT, FLAG FROM member join SERVICE_BOARD USING (MEMBER_NUM) JOIN SERVICE_FILE USING (BOARD_NO)) WHERE NUM BETWEEN ? AND ?";
		// admin 일때 ServiceBoardList
		// String query2 = "select MEMBER_TYPE,title,
		// member_name,member_num,MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO, SERVICE_FR,
		// SERVICE_TO, VOLUNTEER, QUALFICATION, FILE_NAME , CDT, MDT, DEADLINE, CONTENT,
		// FLAG from (SELECT MEMBER_TYPE, TITLE, MEMBER_NAME, MEMBER_NUM,
		// MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO, SERVICE_FR, SERVICE_TO, VOLUNTEER,
		// QUALFICATION, ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) AS NUM ,FILE_NAME ,
		// SERVICE_BOARD.CDT, MDT, DEADLINE, CONTENT, FLAG FROM member join
		// SERVICE_BOARD USING (MEMBER_NUM) JOIN SERVICE_FILE USING (BOARD_NO)) WHERE
		// MEMBER_TYPE NOT LIKE '0' AND FLAG LIKE '1' AND NUM BETWEEN ? AND ?";
		String query2    = "select MEMBER_TYPE, title, member_name,member_num,MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO, SERVICE_FR, SERVICE_TO, VOLUNTEER, QUALFICATION, FILE_NAME , CDT, MDT, DEADLINE, CONTENT, FLAG from (SELECT MEMBER_TYPE, TITLE, MEMBER_NAME, MEMBER_NUM, MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO, SERVICE_FR, SERVICE_TO, VOLUNTEER, QUALFICATION, ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) AS NUM ,FILE_NAME , SERVICE_BOARD.CDT, MDT, DEADLINE, CONTENT, FLAG FROM member join SERVICE_BOARD USING (MEMBER_NUM) JOIN SERVICE_FILE USING (BOARD_NO)) WHERE FLAG = '1' AND NUM BETWEEN ? AND ?";

		// admin이 아닌사람일때 ServiceBoardList

		// String query = "select title, member_name,member_num,MEMBER_ADDRESS,
		// MEMBER_PHONE, BOARD_NO, DEADLINE, CONTENT, SERVICE_FR, SERVICE_TO, VOLUNTEER,
		// QUALFICATION, FILE_NAME from (SELECT title,
		// member_name,member_num,MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO, DEADLINE,
		// CONTENT, SERVICE_FR, SERVICE_TO, VOLUNTEER, QUALFICATION, FILE_NAME,
		// ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) AS NUM ,FILE_NAME FROM member join
		// SERVICE_BOARD USING (MEMBER_NUM) JOIN SERVICE_FILE USING (BOARD_NO)) WHERE
		// NUM BETWEEN ? AND ?";

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;

		try {
			if (memberType == '0') {
				pstmt = conn.prepareStatement(query1);
			} else {
				pstmt = conn.prepareStatement(query2);
			}
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			serviceBoardAndMemberList = new ArrayList<ServiceBoardAndMember>();

			while (rset.next()) {
				ServiceBoardAndMember serviceBoardAndMember = new ServiceBoardAndMember();
				serviceBoardAndMember.setMemberType(rset.getString("MEMBER_TYPE").charAt(0));
				serviceBoardAndMember.setMemberName(rset.getString("MEMBER_NAME"));
				serviceBoardAndMember.setMemberNum(rset.getInt("MEMBER_NUM"));
				serviceBoardAndMember.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				serviceBoardAndMember.setMemberPhone(rset.getString("MEMBER_PHONE"));

				serviceBoardAndMember.setTitle(rset.getString("TITLE"));
				serviceBoardAndMember.setBoard_No(rset.getInt("BOARD_NO"));
				serviceBoardAndMember.setDeadLine(rset.getDate("DEADLINE"));
				serviceBoardAndMember.setContent(rset.getString("CONTENT"));
				serviceBoardAndMember.setCdt(rset.getDate("CDT"));
				serviceBoardAndMember.setMdt(rset.getDate("MDT"));
				serviceBoardAndMember.setFlag(rset.getString("FLAG"));
				serviceBoardAndMember.setService_Fr(rset.getDate("SERVICE_FR"));
				serviceBoardAndMember.setService_To(rset.getDate("SERVICE_TO"));
				serviceBoardAndMember.setVolunteer(rset.getInt("VOLUNTEER"));
				serviceBoardAndMember.setQualfication(rset.getInt("QUALFICATION"));
				serviceBoardAndMember.setFlag(rset.getString("FLAG"));
				serviceBoardAndMember.setFileName(rset.getString("FILE_NAME"));
				serviceBoardAndMemberList.add(serviceBoardAndMember);
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
		return serviceBoardAndMemberList;
	}

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM SERVICE_BOARD";// 寃뚯떆湲� 珥� 媛��닔瑜� �븣�븘�삤�뒗 荑쇰━

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
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recordTotalCount;
	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		int recordTotalCount = totalCount(conn);
		int pageTotalCount = 0;
		// 전체 게시물 갯수 124개, 10개씩 페이지를 만들면
				// 페이지 갯수는 13개

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

		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		boolean needPrev = true;// true �씪 �븣 �몴�떆
		boolean needNext = true;// true �씪 �븣 �몴�떆

		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;// 留� �뮘�씪寃쎌슦�뿏 洹� �뮘濡� �뜑 吏꾪뻾�븷 �닔 �뾾�쑝�땲 false ���엯
		}
		StringBuilder sb = new StringBuilder();
		if (needPrev) {
			sb.append("<a href='/serviceBoard?currentPage" + (startNavi - 1) + "'>< </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/serviceBoard?currentPage=" + i + "'><b>" + i + "</b></a>"); // �쁽�옱 �럹�씠吏� 湲��옄
																									// 吏꾪븯寃� �몴�떆
			} else {
				sb.append("<a href='/serviceBoard?currentPage=" + i + "'>" + i + "</a>");
			}
		}
		if (needNext) {
			sb.append("<a href='/serviceBoard?currentPage=" + (endNavi + 1) + "'>></a>");
		}
		return sb.toString();
	}

	public Member selectMemberOnee(Connection conn, int memberNum) {
		PreparedStatement pstmt = null;
		Member member = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER WHERE MEMBER_NUM=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNum);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberNum(rset.getInt("MEMBER_NUM"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberType(rset.getString("MEMBER_TYPE").charAt(0));
				member.setMemberNick(rset.getString("MEMBER_NICK"));
				member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPost(rset.getString("MEMBER_POST"));
				member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				member.setMemberAddressDetail(rset.getString("MEMBER_ADDRESS_DETAIL"));
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberRegNum(rset.getString("MEMBER_REGNUM"));
				member.setMemberRegDate(rset.getDate("MEMBER_REG_DATE"));
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
		return member;
	}

	public int countForm() {
		int count = 0;

		return count;
	}

	public int countForm(Connection conn, int board_No) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int countForm = 0;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM SERVICE_FORM WHERE BOARD_NO =?";// 寃뚯떆湲� 珥� 媛��닔瑜� �븣�븘�삤�뒗
																							// 荑쇰━

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board_No);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				countForm = rset.getInt("TOTALCOUNT");
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
		return countForm;
	}

}

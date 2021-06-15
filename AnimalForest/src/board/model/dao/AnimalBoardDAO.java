package board.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.model.vo.AdoptionForm;
import board.model.vo.AdoptionSurvey;
import board.model.vo.AnimalBoard;
import board.model.vo.AnimalBoardGrp;
import board.model.vo.AnimalBoardGrp2;
import board.model.vo.AnimalComment;
import board.model.vo.BoardAndMember;
import member.model.vo.Member;

public class AnimalBoardDAO {

	public ArrayList<AnimalBoard> AnimalBoardListAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AnimalBoard> animalBoardList = null;
		String query = "SELECT * FROM animal_board";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			animalBoardList = new ArrayList<AnimalBoard>();

			while (rset.next()) {
				AnimalBoard animalBoard = new AnimalBoard();
				animalBoard.setBoardNo(rset.getInt("board_no"));
				animalBoard.setMemberNum(rset.getInt("member_num"));
				animalBoard.setGrp1No(rset.getInt("grp1_no"));
				animalBoard.setGrp2No(rset.getInt("grp2_no"));
				animalBoard.setTitle(rset.getString("TITLE"));
				animalBoard.setContent(rset.getString("CONTENT"));
				animalBoard.setFlag(rset.getString("FLAG").charAt(0));
				animalBoard.setPrivacyYn(rset.getString("privacy_yn").charAt(0));
				animalBoard.setFeature(rset.getString("feature"));
				animalBoard.setColor(rset.getString("color"));
				animalBoard.setGender(rset.getString("gender"));
				animalBoard.setNeutralizationyn(rset.getString("Neutralization_yn"));
				animalBoard.setWeight(rset.getInt("weight"));
				animalBoard.setBirthDate(rset.getDate("BIRTH_DATE"));
				animalBoard.setFindPlace(rset.getString("find_place"));
				animalBoard.setSelectMemberNum(rset.getInt("Select_Member_Num"));
				animalBoard.setCdt(rset.getDate("CDT"));
				animalBoard.setMdt(rset.getDate("mdt"));
				animalBoardList.add(animalBoard);
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
		return animalBoardList;
	}

	public int animalBoardWrite(Connection conn, AnimalBoard animalBoard, int memberNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO ANIMAL_BOARD VALUES(ANIMAL_COMMENT_SEQ.NEXTVAL,?,?,?,?,?,1,0,?,?,?,?,?,?,?,null,SYSDATE,SYSDATE)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNum);
			pstmt.setInt(2, animalBoard.getGrp1No());
			pstmt.setInt(3, animalBoard.getGrp2No());

			// pstmt.setString(3, title);
			pstmt.setString(4, animalBoard.getTitle());
			pstmt.setString(5, animalBoard.getContent());
			pstmt.setString(6, animalBoard.getFeature());
			pstmt.setString(7, animalBoard.getColor());
			pstmt.setString(8, animalBoard.getGender());
			pstmt.setString(9, animalBoard.getNeutralizationyn());
			pstmt.setInt(10, animalBoard.getWeight());
			pstmt.setDate(11, (Date) animalBoard.getBirthDate());
			pstmt.setString(12, animalBoard.getFindPlace());
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

	public int AnimalBoardDelete(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "DELETE FROM animal_Board WHERE board_no=?";

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

	public AnimalBoard AnimalBoardSelectOne(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		AnimalBoard animalBoard = null;
		String query = "SELECT * FROM ANIMAL_BOARD JOIN ANIMAL_FILE USING (BOARD_NO) WHERE BOARD_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				animalBoard = new AnimalBoard();
				animalBoard.setBoardNo(rset.getInt("board_no"));
				animalBoard.setMemberNum(rset.getInt("member_num"));
				animalBoard.setGrp1No(rset.getInt("grp1_no"));
				animalBoard.setGrp2No(rset.getInt("grp2_no"));
				animalBoard.setTitle(rset.getString("TITLE"));
				animalBoard.setContent(rset.getString("CONTENT"));
				animalBoard.setFlag(rset.getString("FLAG").charAt(0));
				animalBoard.setPrivacyYn(rset.getString("privacy_yn").charAt(0));
				animalBoard.setFeature(rset.getString("feature"));
				animalBoard.setColor(rset.getString("color"));
				animalBoard.setGender(rset.getString("gender"));
				animalBoard.setNeutralizationyn(rset.getString("Neutralization_yn"));
				animalBoard.setWeight(rset.getInt("weight"));
				animalBoard.setBirthDate(rset.getDate("BIRTH_DATE"));
				animalBoard.setFindPlace(rset.getString("find_place"));
				animalBoard.setSelectMemberNum(rset.getInt("Select_Member_Num"));
				animalBoard.setCdt(rset.getDate("CDT"));
				animalBoard.setMdt(rset.getDate("mdt"));
				animalBoard.setFileName(rset.getString("File_name"));
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
		return animalBoard;
	}

	public ArrayList<AnimalBoard> AnimalBoardListAll(Connection conn, String search) {
		ArrayList<AnimalBoard> list = new ArrayList<AnimalBoard>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM ANIMAL_BOARD WHERE TITLE LIKE '%" + search + "%'";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				AnimalBoard animalBoard = new AnimalBoard();
				animalBoard.setBoardNo(rset.getInt("board_no"));
				animalBoard.setMemberNum(rset.getInt("member_num"));
				animalBoard.setGrp1No(rset.getInt("grp1_no"));
				animalBoard.setGrp2No(rset.getInt("grp2_no"));
				animalBoard.setTitle(rset.getString("TITLE"));
				animalBoard.setContent(rset.getString("CONTENT"));
				animalBoard.setFlag(rset.getString("FLAG").charAt(0));
				animalBoard.setPrivacyYn(rset.getString("privacy_yn").charAt(0));
				animalBoard.setFeature(rset.getString("feature"));
				animalBoard.setColor(rset.getString("color"));
				animalBoard.setGender(rset.getString("gender"));
				animalBoard.setNeutralizationyn(rset.getString("Neutralization_yn"));
				animalBoard.setWeight(rset.getInt("weight"));
				animalBoard.setBirthDate(rset.getDate("BIRTH_DATE"));
				animalBoard.setFindPlace(rset.getString("find_place"));
				animalBoard.setSelectMemberNum(rset.getInt("Select_Member_Num"));
				animalBoard.setCdt(rset.getDate("CDT"));
				animalBoard.setMdt(rset.getDate("mdt"));
				list.add(animalBoard);
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

	public int AnimalBoardWriteComment(Connection conn, String comment, int boardNo, String MemberName, int memberNum) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "INSERT INTO animal_comment VALUES (ANIMAL_COMMENT_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, sysdate)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNum);
			pstmt.setInt(2, boardNo);
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

	public int AnimalBoardModify(Connection conn, AnimalBoard animalBoard, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "UPDATE animal_Board SET GRP1_NO=?, GRP2_NO=?, Title=?, Content=?, Feature=?, Color=?, Gender=?, Neutralization_yn= ?, weight =?, Birth_Date = ?, Find_Place = ?, MDT = SYSDATE WHERE board_No=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, animalBoard.getGrp1No());
			pstmt.setInt(2, animalBoard.getGrp2No());
			// pstmt.setString(3, title);
			pstmt.setString(3, animalBoard.getTitle());
			pstmt.setString(4, animalBoard.getContent());
			pstmt.setString(5, animalBoard.getFeature());
			pstmt.setString(6, animalBoard.getColor());
			pstmt.setString(7, animalBoard.getGender());
			pstmt.setString(8, animalBoard.getNeutralizationyn());
			pstmt.setInt(9, animalBoard.getWeight());
			pstmt.setDate(10, (Date) animalBoard.getBirthDate());
			pstmt.setString(11, animalBoard.getFindPlace());
			pstmt.setInt(12, boardNo);
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

	public Member memeberSelectOne(Connection conn, int memberNum) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		String query = "SELECT * FROM member WHERE member_num = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNum);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				member = new Member();
				member.setMemberNum(rset.getInt("MEMBER_NUM"));
				member.setMemberId(rset.getString("MEMBER_ID"));
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
				// member.setAdoption_success(rset.getInt("ADOPTION_SUCCESS"));
				// member.setAdoption_yet(rset.getInt("ADOPTION_YET"));
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
		return member;

	}

//	public ArrayList<BoardAndMember> memberListAll(Connection conn) {
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		ArrayList<BoardAndMember> memberList = null;
//		String query = "select * from member right join animal_board using (member_num)";
//
//		try {
//			pstmt = conn.prepareStatement(query);
//			rset = pstmt.executeQuery();
//			memberList = new ArrayList<BoardAndMember>();
//
//			while (rset.next()) {
//				BoardAndMember boardAndMember = new BoardAndMember();
//				boardAndMember.setMemberNum(rset.getInt("MEMBER_NUM"));
//				boardAndMember.setMemberName(rset.getString("MEMBER_NAME"));
//				boardAndMember.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
//				boardAndMember.setMemberPhone(rset.getString("MEMBER_PHONE"));
//				boardAndMember.setBoardNo(rset.getInt("board_no"));
//				boardAndMember.setTitle(rset.getString("TITLE"));
//				memberList.add(boardAndMember);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				pstmt.close();
//				rset.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return memberList;
//	} 원본입니다.
	
	

//	public ArrayList<BoardAndMember> memberListAllSearch(Connection conn, String search) { 서치하는거 예비용
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		ArrayList<BoardAndMember> memberList = null;
//		String query = "select * from member right join animal_board using (member_num) where TITLE LIKE '%" + search
//				+ "%'";
//		try {
//			pstmt = conn.prepareStatement(query);
//			rset = pstmt.executeQuery();
//			memberList = new ArrayList<BoardAndMember>();
//
//			while (rset.next()) {
//				BoardAndMember boardAndMember = new BoardAndMember();
//				boardAndMember.setMemberNum(rset.getInt("MEMBER_NUM"));
//				boardAndMember.setMemberName(rset.getString("MEMBER_NAME"));
//				boardAndMember.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
//				boardAndMember.setMemberPhone(rset.getString("MEMBER_PHONE"));
//				boardAndMember.setBoardNo(rset.getInt("board_no"));
//				boardAndMember.setTitle(rset.getString("TITLE"));
//				memberList.add(boardAndMember);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				pstmt.close();
//				rset.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return memberList;
//
//	}

	public ArrayList<AnimalComment> animalComment(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AnimalComment> cmtList = null;

		String query = "select comment_no, member_num, board_no, content, cdt, mdt, member_name from member join animal_comment using (member_Num) where board_no =?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			cmtList = new ArrayList<AnimalComment>();

			while (rset.next()) {
				AnimalComment aComment = new AnimalComment();
				aComment.setCommentNo(rset.getInt("COMMENT_NO"));
				aComment.setMemberNum(rset.getInt("member_num"));
				aComment.setContent(rset.getString("CONTENT"));
				aComment.setBoardNo(rset.getInt("board_No"));
				aComment.setCdt(rset.getDate("cdt"));
				aComment.setMdt(rset.getDate("mdt"));
				aComment.setMemberName(rset.getString("member_name"));
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

	public int AnimalBoardDeleteComment(Connection conn, int commentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM ANIMAL_COMMENT WHERE COMMENT_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int modifyAnimalBoardComment(Connection conn, int commentNo, int boardNo, String comment) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "UPDATE animal_comment SET CONTENT =? WHERE COMMENT_NO =? AND Board_No = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment);
			pstmt.setInt(2, commentNo);
			pstmt.setInt(3, boardNo);
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

	public int insertAdoptionSurvey(Connection conn, AdoptionSurvey adoptionSurvey) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO Adoption_Surbey VALUES(?,?,?,?,?,?,?,?,?,sysdate)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, adoptionSurvey.getMemberNum());
			pstmt.setInt(2, adoptionSurvey.getBoardNo());
			pstmt.setString(3, adoptionSurvey.getExprienceYn());
			pstmt.setString(4, adoptionSurvey.getPetYn());
			pstmt.setInt(5, adoptionSurvey.getMemberFamily());
			pstmt.setString(6, adoptionSurvey.getHomeType());
			pstmt.setString(7, adoptionSurvey.getAdoptionReason());
			pstmt.setString(8, adoptionSurvey.getAdoptionCare());
			pstmt.setInt(9, adoptionSurvey.getExpectedCost());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int insertAdoptionForm(Connection conn, AdoptionForm adoptionForm) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO adoption_Form VALUES(?,?,?,?,?,?,?,null,sysdate)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, adoptionForm.getMemberNum());
			pstmt.setInt(2, adoptionForm.getBoardNo());
			pstmt.setString(3, adoptionForm.getContent());
			pstmt.setString(4, adoptionForm.getJob());
			pstmt.setString(5, adoptionForm.getPhone());
			pstmt.setString(6, adoptionForm.getEmail());
			pstmt.setString(7, adoptionForm.getAddress());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {

		int recordTotalCount = totalCount(conn);

		int pageTotalCount = 0; // 전체 페이지의 개수
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
			sb.append("<a href='/boardList?currentPage=" + (startNavi - 1) + "'>< </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/boardList?currentPage=" + i + "'><b>" + i + "</b></a>");
			} else {
				sb.append("<a href='/boardList?currentPage=" + i + "'> " + i + " </a>");
			}
		}
		if (needNext) {
			sb.append("<a href='/boardList?currentPage=" + (endNavi + 1) + "'> ></a>");
		}
		return sb.toString();
	}
	
	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM animal_board";
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
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recordTotalCount;
	}

	public ArrayList<BoardAndMember> memberListAll(Connection conn, int currentPage, int recordCountPerPage, char typeNum, int myMemberNum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BoardAndMember> animalBoardList = null;
//		String query = "SELECT TITLE, MEMBER_NAME, MEMBER_NUM, MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO FROM (SELECT TITLE, MEMBER_NAME, MEMBER_NUM, MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO,ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) AS NUM FROM member right join animal_board USING (MEMBER_NUM)) WHERE NUM BETWEEN ? AND ? ";
		//전체 조회용쿼리(관리자)
		String adminquery = "SELECT  TITLE, MEMBER_NAME, MEMBER_NUM, MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO, FILE_NAME, F_CNT, F_FLAG " 
						  + "FROM( " 
						  +	"     SELECT TITLE, MEMBER_NAME, MEMBER_NUM, MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO, FILE_NAME, F_CNT, F_FLAG, " 
						  +	"            ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) AS NUM " 
						  +	"    FROM ( " 
						  +	"        SELECT  B.TITLE, A.MEMBER_NAME, A.MEMBER_NUM, A.MEMBER_ADDRESS, A.MEMBER_PHONE, B.BOARD_NO, C.FILE_NAME, SUM(CASE WHEN D.MEMBER_NUM IS NOT NULL THEN 1 ELSE 0 END) AS F_CNT, " 
						  +	"                (CASE WHEN E.MEMBER_NUM IS NULL THEN 'F' ELSE 'T' END) AS F_FLAG " 
						  +	"        FROM    MEMBER A " 
						  + "        JOIN    ANIMAL_BOARD B ON A.MEMBER_NUM = B.MEMBER_NUM " 
						  +	"        JOIN    ANIMAL_FILE C ON B.BOARD_NO = C.BOARD_NO " 
						  +	"        LEFT JOIN FAVORITE_ANIMAL D ON B.BOARD_NO = D.BOARD_NO " 
						  +	"        LEFT JOIN FAVORITE_ANIMAL E ON B.BOARD_NO = E.BOARD_NO AND ? = E.MEMBER_NUM " 
						  +	"        GROUP BY B.TITLE, A.MEMBER_NAME, A.MEMBER_NUM, A.MEMBER_ADDRESS, A.MEMBER_PHONE, B.BOARD_NO, C.FILE_NAME, (CASE WHEN E.MEMBER_NUM IS NULL THEN 'F' ELSE 'T' END) " 
						  + "    ) " 
						  +	") " 
						  + "WHERE NUM BETWEEN ? AND ?";
		//사용자 조회용 쿼리
		String query1 	= "SELECT  TITLE, MEMBER_NAME, MEMBER_NUM, MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO, FILE_NAME, F_CNT, F_FLAG " 
				  	 	+ "FROM( " 
				  	 	+	"     SELECT TITLE, MEMBER_NAME, MEMBER_NUM, MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO, FILE_NAME, F_CNT, F_FLAG, " 
				  	 	+	"            ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) AS NUM " 
				  	 	+	"    FROM ( " 
				  	 	+	"        SELECT  B.TITLE, A.MEMBER_NAME, A.MEMBER_NUM, A.MEMBER_ADDRESS, A.MEMBER_PHONE, B.BOARD_NO, C.FILE_NAME, SUM(CASE WHEN D.MEMBER_NUM IS NOT NULL THEN 1 ELSE 0 END) AS F_CNT, " 
				  	 	+	"                (CASE WHEN E.MEMBER_NUM IS NULL THEN 'F' ELSE 'T' END) AS F_FLAG " 
				  	 	+	"        FROM    MEMBER A " 
				  	 	+ 	"        JOIN    ANIMAL_BOARD B ON A.MEMBER_NUM = B.MEMBER_NUM " 
				  	 	+	"        JOIN    ANIMAL_FILE C ON B.BOARD_NO = C.BOARD_NO " 
				  	 	+	"        LEFT JOIN FAVORITE_ANIMAL D ON B.BOARD_NO = D.BOARD_NO " 
				  	 	+	"        LEFT JOIN FAVORITE_ANIMAL E ON B.BOARD_NO = E.BOARD_NO AND ? = E.MEMBER_NUM "
				  	 	+   " 		 WHERE B.FLAG = 1"
				  	 	+	"        GROUP BY B.TITLE, A.MEMBER_NAME, A.MEMBER_NUM, A.MEMBER_ADDRESS, A.MEMBER_PHONE, B.BOARD_NO, C.FILE_NAME, (CASE WHEN E.MEMBER_NUM IS NULL THEN 'F' ELSE 'T' END) " 
				  	 	+   "    ) " 
				  	 	+	") " 
				  	 	+ 	"WHERE NUM BETWEEN ? AND ?";
		// 	start와 end는 일종의 공식
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			if(typeNum=='0') {
				pstmt = conn.prepareStatement(adminquery);
			}
			else {
				pstmt = conn.prepareStatement(query1);
			}
			pstmt.setInt(1, myMemberNum);
			pstmt.setInt(2,start);
			pstmt.setInt(3,end);
			rset = pstmt.executeQuery();
			animalBoardList = new ArrayList<BoardAndMember>();
			while (rset.next()) {
				BoardAndMember boardAndMember = new BoardAndMember();
				boardAndMember.setMemberNum(rset.getInt("MEMBER_NUM"));
				boardAndMember.setMemberName(rset.getString("MEMBER_NAME"));
				boardAndMember.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				boardAndMember.setMemberPhone(rset.getString("MEMBER_PHONE"));
				boardAndMember.setBoardNo(rset.getInt("BOARD_NO"));
				boardAndMember.setTitle(rset.getString("TITLE"));
				boardAndMember.setFileName(rset.getString("FILE_NAME"));
				boardAndMember.setfFlag(rset.getString("F_FLAG").equals("T") ? true : false);
				boardAndMember.setfCnt(rset.getInt("F_CNT"));
				animalBoardList.add(boardAndMember);
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
		return animalBoardList;
	}

	public int AnimalBoardWrite(Connection conn, AnimalBoard animalBoard, String imgName, FileInputStream fis, File f,
			int memberNum) {
		
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query="{CALL ANIMALPIC(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
			cstmt.setInt(1, memberNum);
			cstmt.setInt(2, animalBoard.getGrp1No());
			cstmt.setInt(3, animalBoard.getGrp2No());
			cstmt.setString(4, animalBoard.getTitle());
			cstmt.setString(5, animalBoard.getContent());
			cstmt.setString(6, animalBoard.getFeature());
			cstmt.setString(7, animalBoard.getColor());
			cstmt.setString(8, animalBoard.getGender());
			cstmt.setString(9, animalBoard.getNeutralizationyn());
			cstmt.setInt(10, animalBoard.getWeight());
			cstmt.setDate(11, (Date) animalBoard.getBirthDate());
			cstmt.setString(12, animalBoard.getFindPlace());
			cstmt.setString(13, imgName);
			cstmt.setBlob(14, fis,(int)f.length());
			result = cstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public ArrayList<AnimalBoardGrp> animalList(Connection conn) {
				Statement stmt =null;
				ResultSet rset = null;
				
				ArrayList<AnimalBoardGrp> list = new ArrayList<AnimalBoardGrp>();
				String query="select distinct * from ANIMAL_GRP1";
			
				
				try {
					stmt = conn.createStatement();
					rset=stmt.executeQuery(query);
					
					
					while(rset.next()) {
						AnimalBoardGrp animal = new AnimalBoardGrp();
						animal.setGrp1No(rset.getInt("GRP1_NO"));
						animal.setGrp1Name(rset.getString("GRP1_NAME"));
						
						list.add(animal);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				return list;
	}

	public ArrayList<AnimalBoardGrp2> animalList2(Connection conn) {
				Statement stmt =null;
				ResultSet rset = null;
				
				ArrayList<AnimalBoardGrp2> list = new ArrayList<AnimalBoardGrp2>();
				String query="select * from ANIMAL_GRP2 join ANIMAL_GRP1 using(GRP1_NO)";  
			
				
				try {
					stmt = conn.createStatement();
					rset=stmt.executeQuery(query);
					while(rset.next()) {
						AnimalBoardGrp2 animal = new AnimalBoardGrp2();
						animal.setGrp1No(rset.getInt("GRP1_NO"));
						animal.setGrp2No(rset.getInt("GRP2_No"));
						animal.setGrp1name(rset.getString("GRP1_NAME"));
						animal.setGrp2name(rset.getString("GRP2_NAME"));
						
						list.add(animal);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
						
				return list;
	}

	public AnimalBoard animalFileName(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		AnimalBoard animalBoard = null;
		String query = "SELECT FILE_NAME, BOARD_NO FROM ANIMAL_BOARD JOIN ANIMAL_FILE USING (BOARD_NO) WHERE BOARD_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
			
			if (rset.next()) {
				animalBoard = new AnimalBoard();
				animalBoard.setBoardNo(rset.getInt("board_no"));
				animalBoard.setFileName(rset.getString("File_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return animalBoard;
	}

	public ArrayList<AdoptionForm> selectAdoptionFormList(Connection conn, int boardNo) {
		ArrayList<AdoptionForm> formList = new ArrayList<AdoptionForm>();
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      String query = "SELECT * FROM adoption_form join member using(member_num) WHERE BOARD_NO=?";

	      try {
	         pstmt = conn.prepareStatement(query);
	         pstmt.setInt(1, boardNo);
	         rset = pstmt.executeQuery();
	         formList = new ArrayList<AdoptionForm>();

	         while (rset.next()) {
	        	 AdoptionForm adoptionForm = new AdoptionForm();
	        	 adoptionForm.setMemberNum(rset.getInt("MEMBER_NUM"));
	        	 adoptionForm.setBoardNo(boardNo);
	        	 adoptionForm.setContent(rset.getString("CONTENT"));
	        	 adoptionForm.setJob(rset.getString("JOB"));
	        	 adoptionForm.setPhone(rset.getString("PHONE"));
	        	 adoptionForm.setEmail(rset.getString("EMAIL"));
	        	 adoptionForm.setAddress(rset.getString("ADDRESS"));
	        	 adoptionForm.setDetailAdress(rset.getString("DETAILADRESS"));
	        	 adoptionForm.setCdt(rset.getDate("CDT"));
	        	 adoptionForm.setMemberName(rset.getString("member_name"));
	            formList.add(adoptionForm);
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

	public BoardAndMember selectMemberOneByForm(Connection conn, int boardNo) {
		Statement stmt = null;
	      ResultSet rset = null;
	      String query = "SELECT * FROM MEMBER JOIN Adoption_Form USING (MEMBER_NUM)";
	      BoardAndMember boardAndMember = new BoardAndMember();

	      try {
	         stmt = conn.createStatement();
	         rset = stmt.executeQuery(query);
	         if (rset.next()) {
	        	 boardAndMember.setMemberNum(rset.getInt("MEMBER_NUM"));
	        	 boardAndMember.setMemberName(rset.getString("MEMBER_NAME"));
	        	 boardAndMember.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
	        	 boardAndMember.setMemberPhone(rset.getString("MEMBER_PHONE"));
	        	 boardAndMember.setBoardNo(rset.getInt("BOARD_NO"));
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
	      return boardAndMember;
	}

	public AdoptionForm selectOneForm(Connection conn, int boardNo, int memberNum) {
			PreparedStatement pstmt = null;
	        ResultSet rset = null;
	      String query = "select * from adoption_form join member using (member_num) where member_num = ? and board_no = ?";
	      AdoptionForm animalForm = new AdoptionForm();

	      try {
	    	  	pstmt = conn.prepareStatement(query);
		         pstmt.setInt(1, memberNum);
		         pstmt.setInt(2, boardNo);
		         rset = pstmt.executeQuery();
	         if (rset.next()) {
	        	 animalForm.setMemberNum(memberNum);
	        	 animalForm.setBoardNo(boardNo);
	        	 animalForm.setContent(rset.getString("content"));
	        	 animalForm.setJob(rset.getString("JOB"));
	        	 animalForm.setPhone(rset.getString("PHONE"));
	        	 animalForm.setEmail(rset.getString("EMAIL"));
	        	 animalForm.setAddress(rset.getString("ADDRESS"));
	        	 animalForm.setDetailAdress(rset.getString("DETAILADRESS"));
	        	 animalForm.setCdt(rset.getDate("CDT"));   
	        	 animalForm.setMemberName(rset.getString("Member_name"));
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
	      return animalForm;
	}

	public AdoptionSurvey selectOneSurvey(Connection conn, int boardNo, int memberNum) {
		PreparedStatement pstmt = null;
        ResultSet rset = null;
      String query = "select * from adoption_surbey join member using (member_num) where member_num = ? and board_no = ?";
      AdoptionSurvey animalSurvey = new AdoptionSurvey();

      try {
    	  	pstmt = conn.prepareStatement(query);
	         pstmt.setInt(1, memberNum);
	         pstmt.setInt(2, boardNo);
	         rset = pstmt.executeQuery();
         if (rset.next()) {
        	 animalSurvey.setMemberNum(memberNum);
        	 animalSurvey.setBoardNo(boardNo);
        	 animalSurvey.setExprienceYn(rset.getString("EXPRIENCE_YN"));
        	 animalSurvey.setPetYn(rset.getString("PET_YN"));
        	 animalSurvey.setMemberFamily(rset.getInt("member_family"));
        	 animalSurvey.setHomeType(rset.getString("home_type"));
        	 animalSurvey.setAdoptionReason(rset.getString("adopt_reason"));
        	 animalSurvey.setAdoptionCare(rset.getString("adopt_care"));
        	 animalSurvey.setExpectedCost(rset.getInt("expected_cost"));
        	 animalSurvey.setCdt(rset.getDate("CDT"));   
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
      return animalSurvey;
	}

	public ArrayList<BoardAndMember> memberListAllSearch(Connection conn, int currentPage, int recordCountPerPage,
			String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BoardAndMember> animalBoardList = null;
//		String query = "SELECT TITLE, MEMBER_NAME, MEMBER_NUM, MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO FROM (SELECT TITLE, MEMBER_NAME, MEMBER_NUM, MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO,ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) AS NUM FROM member right join animal_board USING (MEMBER_NUM)) WHERE NUM BETWEEN ? AND ? ";
		String query = "SELECT TITLE, MEMBER_NAME, MEMBER_NUM, MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO,FILE_NAME,FLAG FROM (SELECT TITLE,FLAG, MEMBER_NAME, MEMBER_NUM, MEMBER_ADDRESS, MEMBER_PHONE, BOARD_NO,ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) AS NUM ,FILE_NAME FROM member join animal_board USING (MEMBER_NUM) JOIN ANIMAL_FILE USING (BOARD_NO)) WHERE TITLE LIKE '%" + search + "%' AND NUM BETWEEN ? AND ? AND FLAG = 1";
		// start와 end는 일종의 공식
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			animalBoardList = new ArrayList<BoardAndMember>();

			while (rset.next()) {
				BoardAndMember boardAndMember = new BoardAndMember();
				boardAndMember.setMemberNum(rset.getInt("MEMBER_NUM"));
				boardAndMember.setMemberName(rset.getString("MEMBER_NAME"));
				boardAndMember.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				boardAndMember.setMemberPhone(rset.getString("MEMBER_PHONE"));
				boardAndMember.setBoardNo(rset.getInt("board_no"));
				boardAndMember.setTitle(rset.getString("TITLE"));
				boardAndMember.setFileName(rset.getString("file_name"));
				animalBoardList.add(boardAndMember);
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
		return animalBoardList;
	}

	public AdoptionForm adoptFindNum(Connection conn, int boardNo, int memberNum) {
		PreparedStatement pstmt = null;
        ResultSet rset = null;
      String query = "select * from adoption_form where board_no = ? and member_num = ?";
      AdoptionForm animalForm = new AdoptionForm();

      try {
    	  	pstmt = conn.prepareStatement(query);
	         pstmt.setInt(1, boardNo);
	         pstmt.setInt(2, memberNum);
	         rset = pstmt.executeQuery();
         if (rset.next()) {
        	 animalForm.setMemberNum(memberNum);
        	 animalForm.setBoardNo(boardNo);
        	 animalForm.setContent(rset.getString("content"));
        	 animalForm.setJob(rset.getString("JOB"));
        	 animalForm.setPhone(rset.getString("PHONE"));
        	 animalForm.setEmail(rset.getString("EMAIL"));
        	 animalForm.setAddress(rset.getString("ADDRESS"));
        	 animalForm.setDetailAdress(rset.getString("DETAILADRESS"));
        	 animalForm.setCdt(rset.getDate("CDT"));   
        	 animalForm.setMemberName(rset.getString("Member_name"));
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
      return animalForm;
	}

	public int selectAdoptionForm(Connection conn, int memberNum, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "UPDATE ANIMAL_BOARD SET SELECT_MEMBER_NUM =?, FLAG = 0 WHERE BOARD_NO =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNum);
			pstmt.setInt(2, boardNo);
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
	

	
}

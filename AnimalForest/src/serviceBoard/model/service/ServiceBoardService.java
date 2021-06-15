package serviceBoard.model.service;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import member.model.vo.Member;
import serviceBoard.model.DAO.ServiceBoardDAO;
import serviceBoard.model.vo.PageData;
import serviceBoard.model.vo.ServiceBoard;
import serviceBoard.model.vo.ServiceBoardAndMember;
import serviceBoard.model.vo.Service_Comment;
import serviceBoard.model.vo.Service_Form;

public class ServiceBoardService {
	private ConnectionFactory factory;

	public ServiceBoardService() {
		factory = ConnectionFactory.getConnection();
	}

	public int insertServiceContent(ServiceBoard serviceBoard) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new ServiceBoardDAO().insertServiceContent(serviceBoard, conn);

			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}// 글 작성 메소드
	
	public int insertServiceContent(ServiceBoard serviceBoard, String imgName, FileInputStream fis, File f,
			int memberNum) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new ServiceBoardDAO().insertServiceContent(conn,serviceBoard,imgName,fis,f,memberNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn);
		}
		if(result > 0) {
			factory.commit(conn);
		}else {
			factory.rollback(conn);
		}
		return result;
	}
	
	public int deleteServiceContent(int board_No) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ServiceBoardDAO().deleteServiceContent(board_No, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}

		if (result > 0) {
			factory.commit(conn);
		} else {
			factory.rollback(conn);
		}
		return result;
	}// 글 삭제 메소드

	public ServiceBoard selectContentOne(int board_No) {
		Connection conn = null;
		ServiceBoard serviceBoard = null;
		ArrayList<Service_Comment> cmtList = null;
		int count = 0;

		try {
			conn = factory.createConnection();
			count = new ServiceBoardDAO().countForm(conn,board_No);
			serviceBoard = new ServiceBoardDAO().selectContentOne(conn, board_No, count);
			cmtList = new ServiceBoardDAO().selectCommentList(conn, board_No);
			serviceBoard.setClist(cmtList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return serviceBoard;
	}

	public int modifyContent(ServiceBoard serviceBoard, int board_No) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ServiceBoardDAO().modifyContent(conn, board_No, serviceBoard);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		if (result > 0) {
			factory.commit(conn);
		} else {
			factory.rollback(conn);
		}
		return result;
	}

	public PageData searchContentList(String search, char memberType, int currentPage) {
		Connection conn = null;
		int naviCountPerPage = 5; //페이지 네이게이션 최대 개수 
		int recordCountPerPage = 6; //한페이지에 게시글 출력개수
		ArrayList<ServiceBoardAndMember>serviceBoardAndMemberList = null;
		PageData pd = new PageData();

		try {
			conn = factory.createConnection();
			serviceBoardAndMemberList = new ServiceBoardDAO().searchContentList(currentPage,recordCountPerPage, memberType, conn, search);
			pd.setPageList(serviceBoardAndMemberList);
			pd.setPageNavi(new ServiceBoardDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}

	public ServiceBoardAndMember selectMemberOne(int board_No) {
		Connection conn = null;
		ServiceBoardAndMember serviceBoardAndMember = null;

		try {
			conn = factory.createConnection();
			serviceBoardAndMember = new ServiceBoardDAO().selectMemberOne(conn, board_No);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return serviceBoardAndMember;
	}
	
	public Member selectMemberOnee(int memberNum) {
		Member member = null;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			member = new ServiceBoardDAO().selectMemberOnee(conn,memberNum);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		
		return member;
	}

	public ServiceBoardAndMember selectMemberOneByForm(int board_No, int memberNum) {
		Connection conn = null;
		ServiceBoardAndMember serviceBoardAndMember = null;
		try {
			conn = factory.createConnection();
			serviceBoardAndMember = new ServiceBoardDAO().selectMemberOneByForm(conn, board_No,memberNum);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return serviceBoardAndMember;
	}

	public int insertServiceComment(String content, int board_No, int memberNum, String memberId) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ServiceBoardDAO().insertServiceComment(conn, board_No, memberNum, content, memberId);

			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	public int deleteServiceComment(int comment_No) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ServiceBoardDAO().deleteServiceComment(conn, comment_No);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	public int modifyComment(String content, int comment_No, int board_No) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ServiceBoardDAO().modifyComment(conn, content, comment_No, board_No);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	public int submitForm(Service_Form serviceForm) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new ServiceBoardDAO().submitForm(conn, serviceForm);

			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	public ArrayList<ServiceBoardAndMember> selectFormList(int board_No) {
		ArrayList<ServiceBoardAndMember> formList = null;
		Connection conn = null;

		try {
			conn = factory.createConnection();
			formList = new ServiceBoardDAO().selectFormList(conn, board_No);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return formList;
	}

	public Service_Form selectOneForm(int memberNum, int board_No) {
		Service_Form serviceForm = null;
		Connection conn = null;

		try {
			conn = factory.createConnection();
			serviceForm = new ServiceBoardDAO().selectOneForm(conn, memberNum, board_No);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return serviceForm;
	}

	public ServiceBoard selectBoardOneByForm(int board_No, int memberNum) {
		Connection conn = null;
		ServiceBoard serviceBoard = null;

		try {
			conn = factory.createConnection();
			serviceBoard = new ServiceBoardDAO().selectBoardOneByForm(conn, board_No, memberNum);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return serviceBoard;
	}

	public int deleteServiceForm(int board_No, int member_Num) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new ServiceBoardDAO().deleteServiceForm(conn, board_No, member_Num);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn);
		}
		
		if(result > 0) {
			factory.commit(conn);
		}else { 
			factory.rollback(conn);
		}
		
		return result;
	}

	public ServiceBoardAndMember selectMemberOne(String memberName) {
		ServiceBoardAndMember serviceBoardAndMember = null;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			serviceBoardAndMember = new ServiceBoardDAO().selectMemberOne(conn, memberName);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return serviceBoardAndMember ;
	}

	public PageData selectContentList1(int currentPage, char memberType) {
		Connection conn = null;
		int naviCountPerPage = 5; //페이지 네이게이션 최대 개수 
		int recordCountPerPage = 6; //한페이지에 게시글 출력개수 
		ArrayList<ServiceBoardAndMember>serviceBoardAndMemberList = null;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			serviceBoardAndMemberList = new ServiceBoardDAO().selectContentList(conn,currentPage,recordCountPerPage, memberType);
			pd.setPageList(serviceBoardAndMemberList);
			pd.setPageNavi(new ServiceBoardDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}

}

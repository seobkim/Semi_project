package board.model.service;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import board.model.dao.AnimalBoardDAO;
import board.model.vo.AdoptionForm;
import board.model.vo.AdoptionSurvey;
import board.model.vo.AnimalBoard;
import board.model.vo.AnimalBoardGrp;
import board.model.vo.AnimalBoardGrp2;
import board.model.vo.AnimalComment;
import board.model.vo.BoardAndMember;
import board.model.vo.PageData;
import common.ConnectionFactory;
import member.model.vo.Member;

public class AnimalBoardService {

	private ConnectionFactory factory;

	public AnimalBoardService() {
		// TODO Auto-generated constructor stub
		factory = ConnectionFactory.getConnection();
	}

	public ArrayList<AnimalBoard> AnimalBoardListAll() {

		ArrayList<AnimalBoard> animalList = null;
		Connection conn = null;

		try {
			conn = factory.createConnection();
			animalList = new AnimalBoardDAO().AnimalBoardListAll(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return animalList;

	}

	public int animalBoardWrite(AnimalBoard animalBoard, int memberNum) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new AnimalBoardDAO().animalBoardWrite(conn, animalBoard , memberNum);

			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int AnimalBoardDelete(int boardNo) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new AnimalBoardDAO().AnimalBoardDelete(conn, boardNo);

			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public AnimalBoard AnimalBoardSelectOne(int boardNo) {
		Connection conn = null;
		AnimalBoard animalBoard = null;
		ArrayList<AnimalComment> cmtList = null;

		try {
			conn = factory.createConnection();
			animalBoard = new AnimalBoardDAO().AnimalBoardSelectOne(conn, boardNo);
			
//			animalBoardd = new AnimalBoardDAO().animalFileName(conn, boardNo);
//			fileName = animalBoardd.getFileName();
			
			cmtList = new AnimalBoardDAO().animalComment(conn, boardNo);
			animalBoard.setComments(cmtList);
//			animalBoard.setFileName(fileName);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return animalBoard;

	}

	public ArrayList<AnimalBoard> animalBoardSearchList(String search) {
		ArrayList<AnimalBoard> animalList = null;
		Connection conn = null;

		try {
			conn = factory.createConnection();
			animalList = new AnimalBoardDAO().AnimalBoardListAll(conn, search);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return animalList;
	}

	public int AnimalBoardWriteComment(String comment, int boardNo, String MemberName, int memberNum) {

		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new AnimalBoardDAO().AnimalBoardWriteComment(conn, comment, boardNo, MemberName, memberNum);

			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public int AnimalBoardModify(AnimalBoard animalBoard, int boardNo) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new AnimalBoardDAO().AnimalBoardModify(conn, animalBoard, boardNo );

			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Member memeberSelectOne(int memberNum) {
		Connection conn = null;
		Member member = null;

		try {
			conn = factory.createConnection();
			member = new AnimalBoardDAO().memeberSelectOne(conn, memberNum);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}

//	public ArrayList<BoardAndMember> memberListAll() {
//		ArrayList<BoardAndMember> memberList = null;
//		Connection conn = null;
//
//		try {
//			conn = factory.createConnection();
//			memberList = new AnimalBoardDAO().memberListAll(conn);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return memberList;
//	} 위에가 원본파일입니다.
	

	
	

//	public ArrayList<BoardAndMember> memberListAllSearch(String search) { 서치하는거 예비용 
//		ArrayList<BoardAndMember> animalList = null;
//		Connection conn = null;
//
//		try {
//			conn = factory.createConnection();
//			animalList = new AnimalBoardDAO().memberListAllSearch(conn, search);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return animalList;
//	}
	
	

	public int AnimalBoardDeleteComment(int commentNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new AnimalBoardDAO().AnimalBoardDeleteComment(conn, commentNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result > 0) {
			factory.commit(conn);
		} else {
			factory.rollback(conn);
		}

		return result;
	}

	public int modifyAnimalBoardComment(int commentNo, int boardNo, String comment) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new AnimalBoardDAO().modifyAnimalBoardComment(conn, commentNo, boardNo, comment);
			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int insertAdoptionSurvey(AdoptionSurvey adoptionSurvey) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new AnimalBoardDAO().insertAdoptionSurvey(conn, adoptionSurvey);

			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertAdoptionForm(AdoptionForm adoptionForm) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new AnimalBoardDAO().insertAdoptionForm(conn, adoptionForm);

			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public PageData memberListAll(int currentPage, char typeNum, int myMemberNum) { // 수정중
		// SELECT * FROM NOTICE
		Connection conn = null;
		int recordCountPerPage = 6;
		int naviCountPerPage = 5; // 1 2 3 4 5
		PageData pd = new PageData();

		ArrayList<BoardAndMember> animalBoardList = null;

		try {
			conn = factory.createConnection();
			animalBoardList = new AnimalBoardDAO().memberListAll(conn, currentPage, recordCountPerPage,typeNum, myMemberNum);

			pd.setPageList(animalBoardList);
			pd.setPageNavi(new AnimalBoardDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pd;
	}

	public int AnimalBoardWrite(AnimalBoard animalBoard, String imgName, FileInputStream fis, File f, int memberNum) {
		Connection conn= null;
		int result = 0 ;
		
		try {
			conn=factory.createConnection();
			result = new AnimalBoardDAO().AnimalBoardWrite(conn,animalBoard,imgName,fis,f,memberNum);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<AnimalBoardGrp> animalList() {
		Connection conn = null;
		ArrayList<AnimalBoardGrp> list=new ArrayList<AnimalBoardGrp>();
		
		try {
			conn= factory.createConnection();
			AnimalBoardDAO dao = new AnimalBoardDAO();
			list= dao.animalList(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	public ArrayList<AnimalBoardGrp2> animalList2() {
		Connection conn = null;
		ArrayList<AnimalBoardGrp2> list=new ArrayList<AnimalBoardGrp2>();
		
		try {
			conn= factory.createConnection();
			AnimalBoardDAO dao = new AnimalBoardDAO();
			list= dao.animalList2(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	public ArrayList<AdoptionForm> selectAdoptionFormList(int boardNo) {
		
		Connection conn = null;
		ArrayList<AdoptionForm> AdoptionFormList = null;
		
		try {
			conn= factory.createConnection();
			AdoptionFormList= new AnimalBoardDAO().selectAdoptionFormList(conn, boardNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return AdoptionFormList;
	}

	public BoardAndMember selectMemberOneByForm(int boardNo) {
		      Connection conn = null;
		      BoardAndMember boardAndMember = null;
		      try {
		         conn = factory.createConnection();
		         boardAndMember = new AnimalBoardDAO().selectMemberOneByForm(conn, boardNo);
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         factory.close(conn);
		      }
		      return boardAndMember;
		   }

	public AdoptionForm selectOneForm(int boardNo, int memberNum) {
		Connection conn = null;
		AdoptionForm adoptionForm = null;
	      try {
	         conn = factory.createConnection();
	         adoptionForm = new AnimalBoardDAO().selectOneForm(conn, boardNo,memberNum);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         factory.close(conn);
	      }
	      return adoptionForm;
	}

	public AdoptionSurvey selectOneSurvey(int boardNo, int memberNum) {
		Connection conn = null;
		AdoptionSurvey adoptionSurvey = null;
	      try {
	         conn = factory.createConnection();
	         adoptionSurvey = new AnimalBoardDAO().selectOneSurvey(conn, boardNo,memberNum);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         factory.close(conn);
	      }
	      return adoptionSurvey;
	}

	public PageData memberListAllSearch(int currentPage, String search) {
		// SELECT * FROM NOTICE
				Connection conn = null;
				int recordCountPerPage = 6;
				int naviCountPerPage = 5; // 1 2 3 4 5
				PageData pd = new PageData();

				ArrayList<BoardAndMember> animalBoardList = null;

				try {
					conn = factory.createConnection();
					animalBoardList = new AnimalBoardDAO().memberListAllSearch(conn, currentPage, recordCountPerPage,search);
					pd.setPageList(animalBoardList);
					pd.setPageNavi(new AnimalBoardDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return pd;
	}

	public AdoptionForm adoptFindNum(int boardNo, int memberNum) {
		Connection conn = null;
		AdoptionForm adoptionForm = null;
	      try {
	         conn = factory.createConnection();
	         adoptionForm = new AnimalBoardDAO().adoptFindNum(conn, boardNo,memberNum);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         factory.close(conn);
	      }
	      return adoptionForm;
	}

	public int selectAdoptionForm(int memberNum, int boardNo) {
		Connection conn= null;
		int result = 0 ;
		
		try {
			conn=factory.createConnection();
			result = new AnimalBoardDAO().selectAdoptionForm(conn,memberNum,boardNo);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


}

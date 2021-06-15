package review.model.service;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;

import review.model.dao.ReviewDAO;
import review.model.vo.PageData;
import review.model.vo.Review;
import review.model.vo.ReviewAndMember;
import review.model.vo.ReviewComment;

public class ReviewService {

	private ConnectionFactory factory;

	public ReviewService() {
		factory = ConnectionFactory.getConnection();
	}

	public PageData ReviewList(int currentPage) {

		Connection conn = null;
		int recordCountPerPage = 6;
		int naviCountPerPage = 5; // 1 2 3 4 5
		PageData pd = new PageData();
		ArrayList<Review> list = new ArrayList<Review>();

		try {
			conn = factory.createConnection();
			list = new ReviewDAO().reviewList(conn, currentPage, recordCountPerPage);
		//여기서 확인하는게 맞을까요?
			// 네  확인이 되었으면 reviewList 메소드 처럼 ㅏ꿔 줘야야해요 아까 메소드명이 뭐였죠? 아뇨
			// 아뇨 reveiwServie의 reviewList메소드는 고치는게 아니라 
			// 아까 reviewListAll 메소드를 호출하던 reviewService의 메소드를 고쳐야되요  네확인했습니
			pd.setPageList(list);
			pd.setPageNavi(new ReviewDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
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


	public Review reviewSelect(int board_No) {
		// TODO Auto-generated method stub

		Connection conn = null;
		Review review = null;
		ArrayList<ReviewComment> rcList= null;
		ArrayList<review.model.vo.File> fileList = null;
		
		try {
			conn = factory.createConnection();
			review = new ReviewDAO().reviewSelect(conn, board_No);
			rcList = new ReviewDAO().reviewComment(conn, board_No);
			fileList = new ReviewDAO().reviewFileList(conn, board_No);
			
			review.setComments(rcList);
			review.setReviewFiles(fileList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}

		return review;
	}



	public int reviewDelete(int board_No) {
		// TODO Auto-generated method stub

		Connection conn = null;

		int result = 0;

		try {
			conn = factory.createConnection();
			result = new ReviewDAO().reviewDelete(conn, board_No);
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

	public int ReviewModify(Review review, int board_No) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new ReviewDAO().reviewModify(conn, review, board_No);

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

	public int ReviewCommentDelete(int comment_No) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ReviewDAO().ReviewCommentDelete(conn, comment_No);
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

	public int ReviewCommentModify(int comment_No, int board_No, String comment) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ReviewDAO().ReviewCommentModify(conn, comment_No, board_No, comment);
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

	public int ReviewCommentWrite(String comment, int board_No, int member_Num) {

		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new ReviewDAO().ReviewCommentWrite(conn, comment, board_No, member_Num);

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
	
	
	
	
	
/*	public ArrayList<ReviewAndMember> memberListAllSearch(int currentPage, String search) {
		ArrayList<ReviewAndMember> reviewList = null;
		Connection conn = null;
		
		int recordCountPerPage = 6;
		int naviCountPerPage = 5; // 1 2 3 4 5
		
		PageData pd = new PageData();

		
		
		try {
			conn = factory.createConnection();
			reviewList = new ReviewDAO().memberListAllSearch(conn, search);

			pd.setPageList(reviewList);
			pd.setPageNavi(new ReviewDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return reviewList;
	}
*/

/*	public PageData memberListAll(int currentPage) { // 수정중
		// SELECT * FROM NOTICE
		Connection conn = null;
		int recordCountPerPage = 6;
		int naviCountPerPage = 5; // 1 2 3 4 5
		PageData pd = new PageData();

		ArrayList<ReviewAndMember> reviewList = null;

		try {
			conn = factory.createConnection();
			reviewList = new ReviewDAO().memberListAll(conn, currentPage, recordCountPerPage);

			pd.setPageList(reviewList);
			pd.setPageNavi(new ReviewDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
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
	}*/

	// 이게 반환형입니다. 지금리뷰리스트랑 페이지네비를 담고 있는 페이지데이타를 리턴해줘야하는데
	// 어레이리스트리뷰가 반환형이라 그렇지 못하고 있어요
	// 반환형과 리턴값을 확인해보세요 이게 그 얘기였습니다 네..;;ㅎ
	public PageData reviewSearchList(String search,  int currentPage, int recordCountPerPage) {
		ArrayList<Review> reviewList = null;
		Connection conn = null;
		int naviCountPerPage = 5; // 1 2 3 4 5
		PageData pd = new PageData();

		try {
			conn = factory.createConnection();
			reviewList = new ReviewDAO().ReviewListAll(conn, search, currentPage, recordCountPerPage);
			pd.setPageList(reviewList);
			pd.setPageNavi(new ReviewDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
			// reviewsLIst 변수에 디비에서 가져온 데이터를 저장해주었어요
			// service의 reviewList 메소드를 보면 pageNavi String과 reviewList 데이터를 
			// 모두 저장하기 위해서 pageData 클래스를 사용할거에요 확인해보세요 
			// 네 여기를 ㄱ reviewList 메소드와 똑같이 바꿔주셔야되요 
			// 선생님 맞나요?
			// 네 맞는데 반환형도 보셔야되요 최종적으로 
			// reviewService에 reviewList를 보시면 반환형이 이렇게 되어있을겁니다
			// 리턴값도요 확인해보세요! 
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
	

	public int reviewWrite(Review review) {

		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new ReviewDAO().reviewWrite(conn, review);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public int reviewPicWrite(int boardNo, String imgName, FileInputStream fis, File f) {

		Connection conn= null;
		int result = 0;
		
		try {
			conn=factory.createConnection();
			result= new ReviewDAO().reviewPicWrite(conn,boardNo,fis,imgName,f);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result>0) {
			factory.commit(conn);
		}else {
			factory.rollback(conn);
		}
		return result;
	}
	
	public int selectReviewBoardNo(Review review) {
		Connection conn= null;
		int result = 0;
		
		try {
			conn=factory.createConnection();
			result= new ReviewDAO().selectReviewBoardNo(conn,review);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
/*	public PageData selectNoticeList(int currentPage) {
		// SELECT * FROM NOTICE
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5; // 1 2 3 4 5
		PageData pd = new PageData();
		
		ArrayList<Review> reviewList = null;
		
		try {
			conn = factory.createConnection();
			reviewList = new ReviewDAO().selectReviewList(conn, currentPage, recordCountPerPage);
			
			pd.setPageList(reviewList);
			pd.setPageNavi(new ReivewDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
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
	}*/
	// 화면 부분 에서 페이지 네비가 안뜹니다 뜨면 그이후 부터는 오류 보면서 하겠는데 어디서 잘못된건지모르겠습니다
	//선생님이 해주신거 참고하면서 하려고 했는데 잘안됩니다
	
	public PageData reviewSearchList(int currentPage, String search) {
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			//pd.setPageList(new ReviewDAO().reviewSearchList(conn, currentPage, recordCountPerPage, search));
			pd.setPageNavi(new ReviewDAO().getSearchPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage, search));
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
	
	
	
	
	
	
	
	
	

}

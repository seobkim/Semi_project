package benefit.model.service;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.jsp.tagext.PageData;

import benefit.model.dao.BenefitDAO;
import benefit.model.vo.Benefit;
import benefit.model.vo.BenefitComment;
import benefit.model.vo.PageDate;
import common.ConnectionFactory;

public class BenefitService {

	private ConnectionFactory factory;

	public BenefitService() {
		factory = ConnectionFactory.getConnection();
	}

	public ArrayList<Benefit> BenefitList(int categoryNo) {
		Connection conn = null;
		ArrayList<Benefit> benefitList = null;
		try {
			conn = factory.createConnection();
			benefitList = new BenefitDAO().BenefitList(conn, categoryNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (benefitList != null) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		}
		return benefitList;
	}

	public int insertBenefit(int categoryNo, Benefit benefit, String imgName, FileInputStream fis, File f) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new BenefitDAO().insertBenefit(conn, categoryNo, benefit, imgName, fis, f);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return result;
	}

	public Benefit modifyBenefit( int boardNo) {
		Connection conn = null;
		Benefit benefit = null;
		try {
			conn = factory.createConnection();
			benefit = new BenefitDAO().modifyBenefit(conn, boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return benefit;
	}

	public int deleteBenefit(int boardNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new BenefitDAO().deleteBenefit(conn, boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(result >0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		}
		return result;
	}


	public Benefit benefitPost(int boardNo) {
		// TODO Auto-generated method stub

		Connection conn = null;
		Benefit benefit = null;
		try {
			conn = factory.createConnection();
			benefit = new BenefitDAO().benefitPost(conn, boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (benefit != null) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		}

		return benefit;
	}

	public int modifyBenefitCommit(int categoryNo, String title, String contents, String endDate, int boardNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new BenefitDAO().modifyBenefitCommit(conn, categoryNo, title, contents, endDate, boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		}
		return result;
	}
	public int insertComment(String contents, String memberId, int boardNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new BenefitDAO().insertComment(conn, contents, memberId, boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(result>0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		}
		return result;
	}

	public int modifyComment(int boardNo, int commentNo, String comment) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new BenefitDAO().modifyComment(conn, boardNo, commentNo, comment);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result > 0) {
			factory.commit(conn);
		} else {
			factory.rollback(conn);
		}
		return result;
	}

	public int deleteComment(int commentNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new BenefitDAO().deleteComment(conn, commentNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} if (result > 0) {
			factory.commit(conn);
		} else {
			factory.rollback(conn);
		}
		return result;
	}

	public ArrayList<BenefitComment> commentList(int boardNo) {
		Connection conn = null;
		ArrayList<BenefitComment> list = null;
		try {
			conn = factory.createConnection();
			list = new BenefitDAO().commentList(conn, boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (list != null) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		}
		return list;
	}

	public PageDate selectList(int currentPage, int categoryNo) {
		Connection conn = null;
		ArrayList<Benefit> list = null;
		int recordCountPerPage=10;
		int naviCountPerPage=5;
		PageDate pd = new PageDate();
		try {
			conn=factory.createConnection();
			list = new BenefitDAO().selectList(conn,currentPage,recordCountPerPage, categoryNo);
			pd.setPageList(list);
			pd.setPageNavi(new BenefitDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage, categoryNo));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(list != null) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		}
		
		return pd;
	}
	public PageDate searchTitle(int currentPage, String search, int categoryNo) {
		Connection conn = null;
		ArrayList<Benefit> list = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		PageDate pd = new PageDate();
		try {
			conn = factory.createConnection();
			list = new BenefitDAO().searchTitle(conn, currentPage, recordCountPerPage, search, categoryNo);
			pd.setPageList(list);
			pd.setPageNavi(new BenefitDAO().getSearchPageNavi(conn, recordCountPerPage, currentPage, naviCountPerPage, 
					search, categoryNo));
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return pd;
	}
}

package donation.model.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import donation.model.dao.DonationBoardDao;
import donation.model.vo.DonationBoard;
import donation.model.vo.DonationFile;
import donation.model.vo.DonationHistory;
import donation.model.vo.PageData;
import donation.model.vo.animalGrp;
import donation.model.vo.animalGrp2;



public class DonationBoardService {
	
	private ConnectionFactory factory;
	
	public DonationBoardService() {
		factory=ConnectionFactory.getConnection();
	}

	public PageData donationBoardList(int currentPage, char id) {
		// TODO Auto-generated method stub
		
		Connection conn=null;
		ArrayList<DonationBoard> list = new ArrayList<DonationBoard>();
		int recordCountPerPage=6;
		int naviCountPerPage=5;
		PageData pd = new PageData();
		try {
			conn=factory.createConnection();
			list = new DonationBoardDao().donationBoardList(conn,currentPage,recordCountPerPage,id);
			pd.setPageList(list);
			pd.setPageNavi(new DonationBoardDao().getPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage,id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pd!=null) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return pd;
	}
	
	public DonationBoard donationSelect(int boardNo) {
		// TODO Auto-generated method stub
		
		Connection conn =null;
		DonationBoard board = null;
		
		try {
			conn=factory.createConnection();
			board= new DonationBoardDao().donationSelect(conn,boardNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(board!=null) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return board;
	}
	
	public int donationWrite(DonationBoard donation,String imgName,FileInputStream fis, File f) {
		// TODO Auto-generated method stub
		
		Connection conn= null;
		int result = 0 ;
		
		try {
			conn=factory.createConnection();
			result = new DonationBoardDao().donationWrite(conn,donation,imgName,fis,f);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(result>0) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
		
		
		
		return result;
	}

	public int donationDelete(int boardNo) {
		// TODO Auto-generated method stub
		
		Connection conn= null;
		
		int result = 0 ;
		
		try {
			conn=factory.createConnection();
			result = new DonationBoardDao().donationDelete(conn,boardNo);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(result>0) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return result;
	}

	public ArrayList<animalGrp> animalList() {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		ArrayList<animalGrp> list=new ArrayList<animalGrp>();
		
		try {
			conn= factory.createConnection();
			DonationBoardDao dao = new DonationBoardDao();
			list= dao.animalList(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(list!=null) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
		return list;
	}

	public ArrayList<animalGrp2> animalList2() {
		Connection conn = null;
		ArrayList<animalGrp2> list=new ArrayList<animalGrp2>();
		
		try {
			conn= factory.createConnection();
			DonationBoardDao dao = new DonationBoardDao();
			list= dao.animalList2(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(list!=null) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
		return list;
	}

	public int donationUpdate(DonationBoard dboard) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		int result = 0;
		
		try {
			conn= factory.createConnection();
			result = new DonationBoardDao().dontionUpdate(conn,dboard);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(result>0) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return result;
	}

	public int donationPicWrite(FileInputStream fis,String imgName,File f) {
		// TODO Auto-generated method stub
		Connection conn= null;
		int result = 0;
		
		try {
			conn=factory.createConnection();
			result= new DonationBoardDao().donationPicWrite(conn,fis,imgName,f);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(result>0) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return result;
	}

	

	public PageData donationSelectName(String val, int currentPage) {
		// TODO Auto-generated method stub
		Connection conn=null;
		ArrayList<DonationBoard> list = new ArrayList<DonationBoard>();
		int recordCountPerPage=6;
		int naviCountPerPage=5;
		PageData pd = new PageData();
		try {
			conn=factory.createConnection();
			list = new DonationBoardDao().donationSelectName(conn,currentPage,recordCountPerPage,val);
			pd.setPageList(list);
			pd.setPageNavi(new DonationBoardDao().getSelectPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage,val));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(pd!=null) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return pd;
	}

	public int insertDonation(DonationHistory dhistory) {
		// TODO Auto-generated method stub
		Connection conn= null;
		int result = 0;
		
		try {
			conn=factory.createConnection();
			result= new DonationBoardDao().insertDonation(conn,dhistory);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(result>0) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return result;
	}

	public DonationHistory selectHistory(int boardNo) {
		Connection conn= null;
		DonationHistory result = null;
		
		try {
			conn=factory.createConnection();
			result= new DonationBoardDao().selectHistory(conn,boardNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(result !=null) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return result;
	}

	public int flagModi(char flag, int boardNo) {
		// TODO Auto-generated method stub
		Connection conn = null;
		int result =0;
		
		try {
			conn=factory.createConnection();
			result= new DonationBoardDao().flagModi(conn,flag,boardNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
	}
	
	


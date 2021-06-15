package main.mode.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import benefit.model.vo.Benefit;
import board.model.vo.AnimalBoard;
import donation.model.vo.DonationBoard;
import review.model.vo.Review;
import serviceBoard.model.vo.ServiceBoard;

public class MainDao {

	public AnimalBoard selectOneAnimal(Connection conn) {
		// TODO Auto-generated method stub
		Statement stmt =null;
		AnimalBoard animalBoard=null;
		ResultSet rset = null;
		String query = "SELECT * FROM(SELECT ANIMAL_BOARD.*,ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC)AS NUM FROM animal_board where FLAG=1)A JOIN ANIMAL_FILE B USING(BOARD_NO) WHERE NUM=1";
		
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(query);
			if(rset.next()) {
				animalBoard=new AnimalBoard();
				animalBoard.setBoardNo(rset.getInt("BOARD_NO"));
				animalBoard.setMemberNum(rset.getInt("MEMBER_NUM"));
				animalBoard.setTitle(rset.getString("TITLE"));
				animalBoard.setContent(rset.getString("CONTENT"));
				animalBoard.setFileName(rset.getString("FILE_NAME"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return animalBoard;
	}

	public Review selectOneReview(Connection conn) {
		Statement stmt =null;
		Review reviewBoard=null;
		ResultSet rset = null;
		String query = "SELECT * FROM(SELECT review_BOARD.*,ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC)AS NUM FROM review_board)A JOIN review_FILE B USING(BOARD_NO) WHERE NUM=1";
		
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(query);
			if(rset.next()) {
				reviewBoard=new Review();
				reviewBoard.setBoard_No(rset.getInt("BOARD_NO"));
				reviewBoard.setMember_Num(rset.getInt("MEMBER_NUM"));
				reviewBoard.setTitle(rset.getString("TITLE"));
				reviewBoard.setContent(rset.getString("CONTENT"));
				reviewBoard.setFileName(rset.getString("FILE_NAME"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reviewBoard;
	}

	public DonationBoard selectOneDonation(Connection conn) {
		// TODO Auto-generated method stub
		Statement stmt =null;
		DonationBoard dBoard=null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT DONATION_BOARD.*,ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC)AS NUM FROM DONATION_BOARD WHERE FLAG=1)A JOIN DONATION_FILE B ON A.BOARD_NO=B.DONATION_NO WHERE NUM =1 ORDER BY 1 DESC";
		
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(query);
			
			if(rset.next()) {
				dBoard=new DonationBoard();
				dBoard.setBoard_No(rset.getInt("BOARD_NO"));
				dBoard.setMember_Num(rset.getInt("MEMBER_NUM"));
				dBoard.setTitle(rset.getString("TITLE"));
				dBoard.setContent(rset.getString("CONTENT"));
				dBoard.setFileName(rset.getString("FILE_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dBoard;
	}

	public ServiceBoard selectOneService(Connection conn) {
		// TODO Auto-generated method stub
		Statement stmt =null;
		ServiceBoard serviceBoard=null;
		ResultSet rset = null;
		String query = "SELECT * FROM(SELECT SERVICE_BOARD.*,ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC)AS NUM FROM service_board)A JOIN SERVICE_FILE B USING(BOARD_NO) WHERE NUM=1";
		
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(query);
			if(rset.next()) {
				serviceBoard=new ServiceBoard();
				serviceBoard.setBoard_No(rset.getInt("BOARD_NO"));
				serviceBoard.setMember_Num(rset.getInt("MEMBER_NUM"));
				serviceBoard.setTitle(rset.getString("TITLE"));
				serviceBoard.setContent(rset.getString("CONTENT"));
				serviceBoard.setFileName(rset.getString("FILE_NAME"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serviceBoard;
	}

	public Benefit selectOneBenefit(Connection conn) {
		// TODO Auto-generated method stub
		Statement stmt =null;
		Benefit benefitBoard=null;
		ResultSet rset = null;
		String query = "SELECT * FROM(SELECT BENEFIT_BOARD.*,ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC)AS NUM FROM BENEFIT_board)A JOIN BENEFIT_FILE B USING(BOARD_NO) WHERE NUM=1";
		
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(query);
			if(rset.next()) {
				benefitBoard=new Benefit();
				benefitBoard.setBoardNo(rset.getInt("BOARD_NO"));
				benefitBoard.setMemberNo(rset.getInt("MEMBER_NUM"));
				benefitBoard.setTitle(rset.getString("TITLE"));
				benefitBoard.setContents(rset.getString("CONTENT"));
				benefitBoard.setFileName(rset.getString("FILE_NAME"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return benefitBoard;
	}

}

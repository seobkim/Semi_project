package main.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import benefit.model.vo.Benefit;
import board.model.vo.AnimalBoard;
import common.ConnectionFactory;
import donation.model.vo.DonationBoard;
import main.mode.dao.MainDao;
import review.model.vo.Review;
import serviceBoard.model.vo.ServiceBoard;

public class MainService {

	private ConnectionFactory factory;

	public MainService() {
		// TODO Auto-generated constructor stub
		factory = ConnectionFactory.getConnection();
	}

	public AnimalBoard selectOneAnimal() {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		AnimalBoard animalBoard = null;
		
		try {
			conn=factory.createConnection();
			animalBoard = new MainDao().selectOneAnimal(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return animalBoard;
	}

	public Review selectOneReview() {
		// TODO Auto-generated method stub
		Connection conn = null;
		Review review = null;
		
		try {
			conn=factory.createConnection();
			review = new MainDao().selectOneReview(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return review;
	}

	public DonationBoard selectOneDonation() {
		// TODO Auto-generated method stub
		Connection conn = null;
		DonationBoard Dboard = null;
		
		try {
			conn=factory.createConnection();
			Dboard = new MainDao().selectOneDonation(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Dboard;
	}

	public ServiceBoard selectOneService() {
		// TODO Auto-generated method stub
		Connection conn = null;
		ServiceBoard sBoard = null;
		
		try {
			conn=factory.createConnection();
			sBoard = new MainDao().selectOneService(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sBoard;
	}

	public Benefit selectoneBenefit() {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		Benefit bBoard = null;
		
		try {
			conn = factory.createConnection();
			bBoard= new MainDao().selectOneBenefit(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bBoard;
	}
}
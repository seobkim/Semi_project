package note.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import note.model.dao.NoteDao;
import note.model.vo.Note;
import note.model.vo.NotePageData;

public class NoteService {
	private ConnectionFactory factory;

	public NoteService() {
		// TODO Auto-generated constructor stub
		factory = ConnectionFactory.getConnection();
	}

	public NotePageData selectReceiveNoteList(int reciverNum, int currentPage, int type) {
		// TODO Auto-generated method stub
		Connection conn = null;
		ArrayList<Note> nList = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		NotePageData pd = new NotePageData();
		try {
			conn = factory.createConnection();
			nList = new NoteDao().selectReceiveNoteList(conn, reciverNum, currentPage, recordCountPerPage);
			pd.setPageList(nList);
			pd.setPageNavi(new NoteDao().getPageNavi(conn, reciverNum, currentPage, recordCountPerPage, naviCountPerPage, type));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}

	public Note selectOneNote(int noteNo) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Note note = null;
		
		try {
			conn = factory.createConnection();
			note = new NoteDao().selectOneNote(conn, noteNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		
		return note;
	}

	public int deleteNote(int noteNo) {
		// TODO Auto-generated method stub
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new NoteDao().deleteNote(conn, noteNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			factory.close(conn);
		}
		
		if(result > 0) {
			factory.commit(conn);
			factory.close(conn);
		}else {
			factory.rollback(conn);
			factory.close(conn);
		}
		return result;
	}

	public int replyNote(Note note) {
		// TODO Auto-generated method stub
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new NoteDao().replyNote(conn, note);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			factory.close(conn);
		}
		
		if(result > 0) {
			factory.commit(conn);
			factory.close(conn);
		}else {
			factory.rollback(conn);
			factory.close(conn);
		}
		
		return result;
	}

	public NotePageData selectSendNoteList(int senderNum, int currentPage, int type) {
		// TODO Auto-generated method stub
		Connection conn = null;
		ArrayList<Note> nList = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		NotePageData pd = new NotePageData();
		try {
			conn = factory.createConnection();
			nList = new NoteDao().selectSendNoteList(conn, senderNum, currentPage, recordCountPerPage);
			pd.setPageList(nList);
			pd.setPageNavi(new NoteDao().getPageNavi(conn, senderNum, currentPage, recordCountPerPage, naviCountPerPage, type));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}
}

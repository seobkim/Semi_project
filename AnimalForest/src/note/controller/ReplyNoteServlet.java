package note.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import note.model.service.NoteService;
import note.model.vo.Note;

/**
 * Servlet implementation class ReplyNoteServlet
 */
@WebServlet("/replyNote")
public class ReplyNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyNoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		Note note = new Note();
		note.setNoteTitle(request.getParameter("title"));
		note.setNoteContents(request.getParameter("contents"));
		note.setReceiverId(request.getParameter("receiverId"));
		note.setSenderId(request.getParameter("senderId"));
		int result = new NoteService().replyNote(note);
		
		response.sendRedirect("/receiveNoteList");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

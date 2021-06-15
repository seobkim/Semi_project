package note.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import note.model.service.NoteService;
import note.model.vo.Note;

/**
 * Servlet implementation class NoteContentServlet
 */
@WebServlet("/noteContent")
public class NoteContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoteContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int noteNo = Integer.parseInt(request.getParameter("noteNo")); 
		Note note = new NoteService().selectOneNote(noteNo);
		
		if(note != null) {
			RequestDispatcher view = request.getRequestDispatcher("/views/note/noteDetail.jsp");
			request.setAttribute("note", note);
			request.setAttribute("type", Integer.parseInt(request.getParameter("type")));
			view.forward(request, response);
		}else {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package note.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import note.model.service.NoteService;
import note.model.vo.NotePageData;

/**
 * Servlet implementation class SendNoteListServlet
 */
@WebServlet("/sendNoteList")
public class SendNoteListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendNoteListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		int senderNum = member.getMemberNum();
		
		int currentPage = 0;

		if(request.getParameter("currentPage") == null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		NotePageData pd = new NoteService().selectSendNoteList(senderNum, currentPage, 2);
		if(pd!=null) {
			RequestDispatcher view = request.getRequestDispatcher("/views/note/noteList.jsp");
			request.setAttribute("pd", pd);
			request.setAttribute("type", "2");
			request.setAttribute("currentPage", currentPage);
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

package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.AnimalBoardService;
import member.model.vo.Member;

/**
 * Servlet implementation class AnimalBoardDeleteServlet
 */
@WebServlet("/deleteAnimalBoard")
public class AnimalBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalBoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int findNum = Integer.parseInt(request.getParameter("memberNum"));
		HttpSession session = request.getSession();
		int memberNum = ((Member) session.getAttribute("member")).getMemberNum();
		char memberType = ((Member) session.getAttribute("member")).getMemberType();
		
		if(findNum == memberNum || memberType=='0') {
			int result = new AnimalBoardService().AnimalBoardDelete(boardNo);
			if ( result > 0) {
				response.sendRedirect("/boardList");
			} else {
				response.sendRedirect("/views/animalBoard/animalBoardError.html");
			}
		}
			else {
            RequestDispatcher rd = request.getRequestDispatcher("/views/animalBoard/msg.jsp");
            request.setAttribute("msg", "작성자만 삭제가능합니다.");
            request.setAttribute("loc", "/animalBoardDetail?boardNo="+ boardNo);
            rd.forward(request, response);
            return;
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

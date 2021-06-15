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
import board.model.vo.AdoptionForm;
import board.model.vo.AnimalBoard;
import member.model.vo.Member;

/**
 * Servlet implementation class AnimalBoardDetailToForm
 */
@WebServlet("/AnimalBoardDetailToForm")
public class AnimalBoardDetailToFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalBoardDetailToFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int boardNo  = Integer.parseInt(request.getParameter("boardNo"));

		HttpSession session = request.getSession();
		String memberName = ((Member) session.getAttribute("member")).getMemberName();
		int memberNum = ((Member) session.getAttribute("member")).getMemberNum();
		AdoptionForm adoptionForm = new AnimalBoardService().adoptFindNum(boardNo,memberNum);
		int getmemberNum = adoptionForm.getMemberNum();
		
		//int memberNum = adoptionForm.getMemberNum();
		if(getmemberNum!=memberNum) {
		request.setAttribute("boardNo", boardNo);
		request.setAttribute("memberName", memberName);
		request.getRequestDispatcher("/views/animalBoard/animalAdoptionForm.jsp").forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/views/animalBoard/msg.jsp");
			request.setAttribute("msg", "입양신청은 한게시물당 한번만 가능합니다.");
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

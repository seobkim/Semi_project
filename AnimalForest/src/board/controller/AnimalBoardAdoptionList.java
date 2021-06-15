package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.AnimalBoardService;
import board.model.vo.AdoptionForm;
import board.model.vo.BoardAndMember;
import member.model.vo.Member;

/**
 * Servlet implementation class AnimalBoardAdoptionList
 */
@WebServlet("/AnimalBoardAdoptionList")
public class AnimalBoardAdoptionList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalBoardAdoptionList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		int memberNum = ((Member) session.getAttribute("member")).getMemberNum();
		int findNum = Integer.parseInt(request.getParameter("memberNum"));
		
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		ArrayList<AdoptionForm> adoptionList = new AnimalBoardService().selectAdoptionFormList(boardNo);
		BoardAndMember boardAndMember = new AnimalBoardService().selectMemberOneByForm(boardNo);
		
	      if(adoptionList!=null && memberNum == findNum) {
	         RequestDispatcher view = request.getRequestDispatcher("/views/animalBoard/AdoptionFormList.jsp");      
	         request.setAttribute("adoptionList", adoptionList);
	         request.setAttribute("boardAndMember", boardAndMember);
	         view.forward(request, response);
	      }else {
	    	  RequestDispatcher rd = request.getRequestDispatcher("/views/animalBoard/msg.jsp");
	            request.setAttribute("msg", "작성자만 조회가능합니다.");
	            request.setAttribute("loc", "/animalBoardDetail?boardNo="+ boardNo);
	            rd.forward(request, response);
	            return;  
	      }
	      //response.sendRedirect("/views/animalBoard/animalBoardError.html");
		AnimalBoardService animalBoardService = new AnimalBoardService();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

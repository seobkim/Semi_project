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
import board.model.vo.AnimalBoard;
import board.model.vo.AnimalBoardGrp;
import board.model.vo.AnimalBoardGrp2;
import member.model.vo.Member;

/**
 * Servlet implementation class AnimalBoardModifyServlet
 */
@WebServlet("/modifyAnimalBoard")
public class AnimalBoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalBoardModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		AnimalBoard animalboard = new AnimalBoardService().AnimalBoardSelectOne(boardNo);
		HttpSession session = request.getSession();
		int memberNum = ((Member) session.getAttribute("member")).getMemberNum();
		int findNum = animalboard.getMemberNum();
		ArrayList<AnimalBoardGrp> list = new ArrayList<AnimalBoardGrp>();
		ArrayList<AnimalBoardGrp2> list2 = new ArrayList<AnimalBoardGrp2>();
	
		list = new AnimalBoardService().animalList();
		list2= new AnimalBoardService().animalList2();
		
		if(animalboard != null && memberNum == findNum) {
			request.setAttribute("animalboard", animalboard);
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			RequestDispatcher view = request.getRequestDispatcher("/views/animalBoard/animalBoardModify.jsp");
			view.forward(request, response);
		}
		else {
            RequestDispatcher rd = request.getRequestDispatcher("/views/animalBoard/msg.jsp");
            request.setAttribute("msg", "작성자만 수정가능합니다.");
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

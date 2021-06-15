package board.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.AnimalBoardService;
import board.model.vo.AnimalBoard;
import member.model.vo.Member;

/**
 * Servlet implementation class AnimalBoardModifyCommitServlet
 */
@WebServlet("/modifyAnimalBoardCommit")
public class AnimalBoardModifyCommitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalBoardModifyCommitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// View에서 넘어온 값 변수 저장

		String MemberName = null;
		AnimalBoard animalBoard = new AnimalBoard();
		
		animalBoard.setTitle(request.getParameter("title"));
		animalBoard.setFindPlace(request.getParameter("findplace"));
		animalBoard.setGrp1No(Integer.parseInt(request.getParameter("grp1")));
		animalBoard.setFeature(request.getParameter("feature"));
		animalBoard.setNeutralizationyn(request.getParameter("neutralizationyn"));
		animalBoard.setContent(request.getParameter("content"));
		animalBoard.setWeight(Integer.parseInt(request.getParameter("weight")));
		animalBoard.setGender(request.getParameter("gender"));
		animalBoard.setColor(request.getParameter("color"));
		animalBoard.setBirthDate(Date.valueOf(request.getParameter("birthdate")));
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int grp1=Integer.parseInt(request.getParameter("grp1"));
		if(grp1==1) {
			animalBoard.setGrp2No(Integer.parseInt(request.getParameter("grp1-1")));
		}
		else if(grp1==2) {
			animalBoard.setGrp2No(Integer.parseInt(request.getParameter("grp2-1")));
		}
		else {
			animalBoard.setGrp2No(1);
		}
		int result = new AnimalBoardService().AnimalBoardModify(animalBoard,boardNo);
		if( result > 0 ) {
			response.sendRedirect("/animalBoardDetail?boardNo="+boardNo);
		} else {
			response.sendRedirect("/views/notice/noticeError.html");
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

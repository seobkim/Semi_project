package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.AnimalBoardService;

/**
 * Servlet implementation class AnimalBoardCommentModifyServlet
 */
@WebServlet("/modifyAnimalBoardComment")
public class AnimalBoardCommentModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnimalBoardCommentModifyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String comment = request.getParameter("modComment");
		int boardNo = Integer.parseInt(request.getParameter("modBoardNo"));
		int commentNo = Integer.parseInt(request.getParameter("modCommentNo"));

		int result = new AnimalBoardService().modifyAnimalBoardComment(commentNo, boardNo, comment);
		if (result > 0) {
			response.sendRedirect("/animalBoardDetail?boardNo=" + boardNo);
		} else {
			response.sendRedirect("/views/animalBoard/animalBoardError.html");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

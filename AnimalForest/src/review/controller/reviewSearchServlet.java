package review.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.model.vo.PageData;
import review.model.service.ReviewService;
import review.model.vo.Review;
import review.model.vo.ReviewAndMember;




@WebServlet("/reviewSearch")
public class reviewSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public reviewSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String search = request.getParameter("search");
		int currentPage = 0;
		int recordCountPerPage = 6;
		if (request.getParameter("currentPage")==null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		// 여섯개씩 보여줄거면 6으로 바꾸셔야합니당
		// 오류 메시지를 보면 리턴값이 페이지데이타인데 저장하려고하는게 어레이리스트 리뷰로 되어 있어서
		// 오류가 났어요 어떻게 고치면 될까요? 맞아요 ㅎㅎ 
		// ㅔ페이지 데이타가 이름이 같아서 발생한 문제네요 원래 다 달라야되는데
		// 리뷰도 페이지 데이타 보드도 페이지데이타..다 페이지데이타...ㅠㅠ 이제 소스는 다고쳐서 실행을 해볼까요 네
		PageData reviewList = new ReviewService().reviewSearchList(search, currentPage, recordCountPerPage); 
		//ArrayList<ReviewAndMember> memberList = new ReviewService().memberListAllSearch(search);
		
		request.setAttribute("review", reviewList); // 맨처음
		// 리뷰서치페이지로는 리뷰리스트로 데이터를 보내주고 있어서 아무것도 안나온거에요 
		// 리뷰서치페이지에 이엘태그로 리뷰를 받아오고 있으니까 리뷰로 바꿔주면 데이터가 나오겠죠?
		//request.setAttribute("memberList", memberList);
		RequestDispatcher view = request.getRequestDispatcher("/views/review/reviewSearch.jsp");
		view.forward(request, response);
		// 확인중이신가요? 
		//제가 무슨짓을햇나 생각중이엇습니다 ㅠㅠ 리뷰서치페이지가 없어요.. 그거 만들어주세요 리뷰올페이지처럼 
		//네 앍이제 어떻게 해야하죠?
		
		
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package review.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import member.model.vo.Member;
import review.model.service.ReviewService;
import review.model.vo.Review;
import serviceBoard.model.service.ServiceBoardService;


@WebServlet("/reviewWrite")
public class ReviewWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReviewWriteServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//request.setCharacterEncoding("utf-8");
		String imgpath=request.getSession().getServletContext().getRealPath("img"); //저장될 위치
		
		int size = 1024 *1024*5;
		MultipartRequest multi = new MultipartRequest(request, imgpath, size,"utf-8",new DefaultFileRenamePolicy());
		
		
		Review review= new Review();
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		int memberNum = member.getMemberNum();
		
		review.setMember_Num(memberNum);
		review.setTitle(multi.getParameter("title"));
		review.setContent(multi.getParameter("content"));
		review.setAdoptionTime(Date.valueOf(multi.getParameter("adoptionTime")));
		
		
	
		String imgName=multi.getFile("file").getName();
		File f = new File(imgpath+"/"+imgName);
		FileInputStream fis = new FileInputStream(f);
		
	
		int result = new ReviewService().reviewWrite(review);
		int boardNo = new ReviewService().selectReviewBoardNo(review);

			if (result > 0) {
				int result2 =  new ReviewService().reviewPicWrite(boardNo,imgName,fis,f);
				if ( result2 > 0) response.sendRedirect("/review");
			}else {
				response.sendRedirect("/views/review/ReviewError.html");
			}
	}
		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
	
	

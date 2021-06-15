package member.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberModifyServlet
 */
@WebServlet("/mupdate")
public class MemberModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       request.setCharacterEncoding("UTF-8");
	      

		    int size = 1024*1024*5;
		    
		    String imgpath = request.getSession().getServletContext().getRealPath("memberProfile"); //저장될위치 
		    		
		    MultipartRequest multi = new MultipartRequest(request,imgpath,size,"utf-8");
	       
		    Member member= new Member();
		    
	       	member.setMemberType(multi.getParameter("type").charAt(0));
		    member.setMemberId(multi.getParameter("userId"));
		    member.setMemberNick(multi.getParameter("nickName"));
		    member.setMemberPwd(multi.getParameter("userPwd"));
		    member.setMemberName(multi.getParameter("userName"));
		    member.setMemberEmail(multi.getParameter("email"));
		    member.setMemberPhone(multi.getParameter("phone"));
		    member.setMemberPost(multi.getParameter("postcode"));
		    member.setMemberAddress(multi.getParameter("addr"));
		    member.setMemberAddressDetail(multi.getParameter("addr2"));
		    
		    String imgName = multi.getFile("fileName").getName();
			 
		    member.setMemberPhotoName(imgName);
			  
		    File f = new File(imgpath +"/"+imgName);
		    FileInputStream fis = new FileInputStream(f);
		   
		    int result = new MemberService().updateMember(member,imgName,fis,f);	
		    if (result > 0) {
		    	  HttpSession session = request.getSession();
		    	   session.setAttribute("member", member);
		    	   response.sendRedirect("/views/member/memberModifySuccess.jsp");
		    	
		    	
		    }else {
		    	response.sendRedirect("/views/member/memberError.html");	    	
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

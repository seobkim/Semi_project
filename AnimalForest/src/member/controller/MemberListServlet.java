package member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class membeerListServlet
 */
@WebServlet("/memberlist")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ArrayList<Member> list = new MemberService().selectMemberList();
	
    //System.out.println(list);
     
		if(!list.isEmpty()) {
			RequestDispatcher view =request.getRequestDispatcher("/views/member/memberList.jsp");
			
			request.setAttribute("list", list);
			view.forward(request, response);
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

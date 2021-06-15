package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

/**
 * Servlet implementation class DupldChkServlet
 */
@WebServlet("/dupid.lo")
public class DupldChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DupldChkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 MemberService mservice = new MemberService();
	 int result = mservice.dupldChk(request.getParameter("userId"));
	 PrintWriter out = response.getWriter();
	 if(result>0) {
		 out.append("fail"); 
		 //만약 dubldChk()의 결과값이 0이상이면 fail
	 }else {
		 out.append("ok");
		 //결과 값이 0 보다 크지 않으면 ok를 담아서 보낸다 
	 }
	 out.flush();
	 out.close();
	 
	}

}

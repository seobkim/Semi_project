package member.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/memberinsert")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
       
	    int size = 1024*1024*5;
	    
	    String imgpath = request.getSession().getServletContext().getRealPath("memberProfile"); //���옣�맆�쐞移� 
	    		
	    MultipartRequest multi = new MultipartRequest(request,imgpath,size,"utf-8");
	  
	    Member member = new Member();
	    
	    member.setMemberType(multi.getParameter("type").charAt(0));
	    member.setMemberId(multi.getParameter("userId"));
	    member.setMemberNick(multi.getParameter("nickName"));
	    member.setMemberPwd(multi.getParameter("userPwd"));
	    member.setMemberName(multi.getParameter("userName"));
	    member.setMemberRegNum(multi.getParameter("regNum"));
	    member.setMemberEmail(multi.getParameter("email"));
	    member.setMemberPhone(multi.getParameter("phone"));
	    member.setMemberPost(multi.getParameter("postcode"));
	    member.setMemberAddress(multi.getParameter("addr"));
	    member.setMemberAddressDetail(multi.getParameter("addr2"));
	  
	    String imgName = multi.getFile("fileName").getName();
	 
	  
	    File f = new File(imgpath +"/"+imgName);
	    FileInputStream fis = new FileInputStream(f);
	    
	    int result = new MemberService().insertMember(member,imgName,fis,f);
	    
	    if (result > 0) {
	    	response.sendRedirect("/views/member/joinSuccess.jsp");
	    	
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

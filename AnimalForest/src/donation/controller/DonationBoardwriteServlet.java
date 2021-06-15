package donation.controller;


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

import donation.model.service.DonationBoardService;

import donation.model.vo.DonationBoard;
import member.model.vo.Member;

/**
 * Servlet implementation class DonationWriteServlet
 */
@WebServlet("/donationWrite")
public class DonationBoardwriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonationBoardwriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		int size = 1024 *1024*5;
		HttpSession session = request.getSession();
		Member member=(Member)session.getAttribute("member");
		String imgpath=request.getSession().getServletContext().getRealPath("img"); //저장될 위치
		
		MultipartRequest multi = new MultipartRequest(request,imgpath,size,"utf-8");
		DonationBoard donation= new DonationBoard();
		
		donation.setTitle(multi.getParameter("title"));
		donation.setMember_Num(member.getMemberNum());
		donation.setContent(multi.getParameter("content"));
		donation.setFull_Amount(Integer.parseInt(multi.getParameter("fullAccount")));
		
		
		
		
		String imgName=multi.getFile("file").getName();
		
		
		File f = new File(imgpath+"/"+imgName);
		
		FileInputStream fis = new FileInputStream(f);
		
		System.out.println(f);
		System.out.println(fis);
		
		
		donation.setEndDate(Date.valueOf(multi.getParameter("endDate")));
		donation.setGrp1_No(Integer.parseInt(multi.getParameter("grp1")));
		
		int grp1=Integer.parseInt(multi.getParameter("grp1"));
		
		
		if(grp1==1) {
			donation.setGrp2_No(Integer.parseInt(multi.getParameter("grp1-1")));
		}
		else if(grp1==2) {
			donation.setGrp2_No(Integer.parseInt(multi.getParameter("grp2-1")));
		}
		else {
			donation.setGrp2_No(1);
		}
		
		int result = new DonationBoardService().donationWrite(donation,imgName,fis,f);
		
//		int result2 = new DonationBoardService().donationPicWrite(fis,imgName,f);
		
		if(result>0) {
			response.sendRedirect("/donationBoard");
		}
		else {
			response.sendRedirect("/semi/error.html");
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

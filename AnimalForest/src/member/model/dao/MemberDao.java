package member.model.dao;


import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import common.ConnectionFactory;
import member.model.vo.Member;

public class MemberDao {
	
	public ArrayList<Member> selectMemberList(Connection conn){
	
		ArrayList<Member> list = null;
		Member member=null;
		Statement stmt = null;
		ResultSet rset =null;
	    String query="SELECT * FROM MEMBER WHERE MEMBER_TYPE='1'";
	   
	    		//"SELECT * FROM MEMBER WHERE MEMBER_TYPE='1'";
	    try {
			stmt = conn.createStatement();
			rset=stmt.executeQuery(query);
			list=new ArrayList<Member>();
		
			while(rset.next()) {
				member = new Member();
				//System.out.println(list);
				member.setMemberNum(rset.getInt("MEMBER_NUM"));
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPhoto(rset.getBlob("MEMBER_PHOTO"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberType(rset.getString("MEMBER_TYPE").charAt(0));
				member.setMemberNick(rset.getString("MEMBER_NICK"));
				member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPost(rset.getString("MEMBER_POST"));
				member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				member.setMemberAddressDetail(rset.getString("MEMBER_ADDRESS_DETAIL"));
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberRegNum(rset.getString("MEMBER_REGNUM"));
				member.setMemberRegDate(rset.getDate("MEMBER_REG_DATE"));
			
				list.add(member);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(rset);
			ConnectionFactory.close(stmt);
		}
	    		
		return list;
		
	}
	public ArrayList<Member> memberCenterList(Connection conn,int currentPage, int recordCountPerPage){
		//보호소 전체리스트 
		ArrayList<Member> list = null;
		Member member=null;
		/*Statement stmt = null;*/
		PreparedStatement pstmt= null;
		ResultSet rset =null;
	    String query="SELECT * FROM(SELECT Member.*, ROW_NUMBER() OVER(ORDER BY MEMBER_NUM) AS NUM FROM MEMBER) \n" + 
	    		"WHERE MEMBER_NUM BETWEEN ? AND ? AND MEMBER_TYPE='2'";
	   
	    		//"SELECT * FROM MEMBER WHERE MEMBER_TYPE='1'";
	    int start = currentPage*recordCountPerPage - (recordCountPerPage-1) ;
	    int end = currentPage*recordCountPerPage;
	    try {
			/*stmt = conn.createStatement();
			rset=stmt.executeQuery(query);*/
	    	pstmt = conn.prepareStatement(query);
	    	pstmt.setInt(1, start);
	    	pstmt.setInt(2, end);
	    	rset=pstmt.executeQuery();
			list=new ArrayList<Member>();
		
			while(rset.next()) {
				member = new Member();
				//System.out.println(list);
				member.setMemberNum(rset.getInt("MEMBER_NUM"));
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPhoto(rset.getBlob("MEMBER_PHOTO"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
			/*	member.setMemberType(rset.getString("MEMBER_TYPE").charAt(0));*/
			/*	member.setMemberNick(rset.getString("MEMBER_NICK"));*/
             	member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPost(rset.getString("MEMBER_POST"));
				member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
			/*	member.setMemberAddressDetail(rset.getString("MEMBER_ADDRESS_DETAIL"));*/
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberRegNum(rset.getString("MEMBER_REGNUM"));
				member.setMemberRegDate(rset.getDate("MEMBER_REG_DATE"));
			
				list.add(member);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(rset);
			ConnectionFactory.close(pstmt);
		}
	    		
		return list;
		
	}
	
	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null; 
		int recordTotalCount = 0;
		String query= "SELECT COUNT(*) AS TOTALCOUNT FROM MEMBER";
		//개시글 총 갯수를 알아오는 쿼리 
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		return recordTotalCount; 
	}

	public String getPageNavi(Connection conn,int currentPage, int recordCountPerPage, 
			int naviCountPerPage) {
		
		int recordTotalCount = totalCount(conn); 
		
		int pageTotalCount = 0 ; 
	
		
		if(recordTotalCount % recordCountPerPage > 0) {
			
			pageTotalCount = recordTotalCount / recordCountPerPage +1; 
					
		}else {
			pageTotalCount = recordTotalCount / recordCountPerPage ;
		}
		//오류방지용 
		if(currentPage <1 ) {
			currentPage = 1; 
		}else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount; 
		} 
		
		int startNavi = ((currentPage-1)/naviCountPerPage)* naviCountPerPage+1;
		 //시작하는페이지 Navi 번호구하기 
		int endNavi = startNavi + naviCountPerPage -1 ; 
		//끝나는 페이지 Navi 번호구하기 
		//오류방지용 
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount; 
		}
	
		//< 1 2 3 4 5 >// 모양
		boolean needPrev = true;
		boolean needNext = true; 
		
		if(startNavi==1) {
			needPrev = false; 
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		StringBuilder sb = new StringBuilder(); 
		//클래스사용 
		if(needPrev) {
			sb.append("<a href='/membercenterlist?currentPage="+(startNavi-1)+"'><</a>");
		}
		for(int i =startNavi; i<=endNavi; i++) {
			if(i==currentPage) {
				sb.append("<a href='/membercenterlist?currentPage="+i+"'><b>"+i+"</b></a>");
			}else {
				sb.append("<a href='/membercenterlist?currentPage="+i+"'>"+i+"</a>");
			}
		}
		if(needNext) {
			sb.append("<a href='/membercenterlist?currentPage="+(endNavi+1)+"'>></a>");
		}
		return sb.toString();
	}
	
	public Member selectLogin(Connection conn, String userId, String userPwd) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PWD=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				member = new Member();
				member.setMemberNum(rset.getInt("MEMBER_NUM"));
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPhoto(rset.getBlob("MEMBER_PHOTO"));
				member.setMemberPhotoName(rset.getString("MEMBER_PHOTONAME"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberType(rset.getString("MEMBER_TYPE").charAt(0));
				member.setMemberNick(rset.getString("MEMBER_NICK"));
				member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPost(rset.getString("MEMBER_POST"));
				member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				member.setMemberAddressDetail(rset.getString("MEMBER_ADDRESS_DETAIL"));
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberRegNum(rset.getString("MEMBER_REGNUM"));
				member.setMemberRegDate(rset.getDate("MEMBER_REG_DATE"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		 ConnectionFactory.close(rset);
		 ConnectionFactory.close(pstmt);
			
		}
		return member;
	}

	public int insertMember(Connection conn, Member member,String imgName,FileInputStream fis, File f) {

		PreparedStatement pstmt = null;
		int result = 0;
        String query = "INSERT INTO	MEMBER VALUES(MEMBER_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE)";
        
        try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setBinaryStream(2, fis,(int)f.length());
	        pstmt.setString(3,imgName);
			pstmt.setString(4, member.getMemberPwd());
			pstmt.setString(5, member.getMemberName());
			pstmt.setString(6, String.valueOf(member.getMemberType()));
			pstmt.setString(7, member.getMemberNick());
			pstmt.setString(8, member.getMemberEmail());
			pstmt.setString(9, member.getMemberPost());
			pstmt.setString(10, member.getMemberAddress());
			pstmt.setString(11, member.getMemberAddressDetail());
			pstmt.setString(12, member.getMemberPhone());
			pstmt.setString(13, member.getMemberRegNum());
			
			System.out.println(result);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		
	          ConnectionFactory.close(pstmt);
		}

		return result;

	}
	
	public Member searchId(Connection conn, String userName,String phone,String email) {
		Member member = null;
		PreparedStatement pstmt = null; 
		ResultSet rset= null; 
		String query="SELECT * FROM MEMBER WHERE MEMBER_NAME =? AND MEMBER_PHONE =? AND MEMBER_EMAIL=?"; 
		
		
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setString(2, phone);
			pstmt.setString(3,email);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member();
				//System.out.println(rset.getString("MEMBER_ID"));
				member.setMemberNum(rset.getInt("MEMBER_NUM"));
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPhoto(rset.getBlob("MEMBER_PHOTO"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberType(rset.getString("MEMBER_TYPE").charAt(0));
				member.setMemberNick(rset.getString("MEMBER_NICK"));
				member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPost(rset.getString("MEMBER_POST"));
				member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				member.setMemberAddressDetail(rset.getString("MEMBER_ADDRESS_DETAIL"));
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberRegNum(rset.getString("MEMBER_REGNUM"));
				member.setMemberRegDate(rset.getDate("MEMBER_REG_DATE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			 ConnectionFactory.close(rset);
			 ConnectionFactory.close(pstmt);
			
		}
		
		return member;
	}
	
	public Member searchPwd(Connection conn,String userId,String userName, String phone) {
		Member member  = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER WHERE MEMBER_ID =? AND MEMBER_NAME =? AND MEMBER_PHONE=?"; 
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userName);
			pstmt.setString(3, phone);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member();
				System.out.println(rset.getString("MEMBER_PWD"));
				member.setMemberNum(rset.getInt("MEMBER_NUM"));
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPhoto(rset.getBlob("MEMBER_PHOTO"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberType(rset.getString("MEMBER_TYPE").charAt(0));
				member.setMemberNick(rset.getString("MEMBER_NICK"));
				member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPost(rset.getString("MEMBER_POST"));
				member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				member.setMemberAddressDetail(rset.getString("MEMBER_ADDRESS_DETAIL"));
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberRegNum(rset.getString("MEMBER_REGNUM"));
				member.setMemberRegDate(rset.getDate("MEMBER_REG_DATE"));
							
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				rset.close();
				pstmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return member;
	}
	public int updateMember(Connection conn, Member member,String imgName,FileInputStream fis,File f) {
		PreparedStatement pstmt =null;
		int result = 0 ; 
		String query="UPDATE MEMBER SET MEMBER_TYPE=?,MEMBER_PHOTO=?,MEMBER_PHOTONAME=?, MEMBER_NICK=?,MEMBER_PWD=?,MEMBER_NAME=?, MEMBER_EMAIL=?, MEMBER_POST=?,MEMBER_ADDRESS=?,MEMBER_ADDRESS_DETAIL=?,MEMBER_PHONE=? WHERE MEMBER_ID=?";
			  
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, String.valueOf(member.getMemberType()));
			pstmt.setBinaryStream(2, fis,(int)f.length());
	        pstmt.setString(3,imgName);
	        pstmt.setString(4, member.getMemberNick());
			pstmt.setString(5, member.getMemberPwd());
            pstmt.setString(6, member.getMemberName());
            pstmt.setString(7, member.getMemberEmail());
            pstmt.setString(8, member.getMemberPost());
            pstmt.setString(9, member.getMemberAddress());
            pstmt.setString(10, member.getMemberAddressDetail());
            pstmt.setString(11, member.getMemberPhone());
            pstmt.setString(12, member.getMemberId());
           
            
            
            result = pstmt.executeUpdate();
           
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		
			 ConnectionFactory.close(pstmt);
		}
		
		return result;
		
	}
	
	public int deleteMember(Connection conn, String MemberId) {
	       PreparedStatement pstmt = null;
	       int result = 0; 
	       String query="DELETE FROM MEMBER WHERE MEMBER_ID =? ";
	     
	       try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,MemberId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			 ConnectionFactory.close(pstmt);
		}
	       
	      return result;
		
	}
	
	public int dupldChk(Connection conn,String userId) { //아이디중복체크
		PreparedStatement pstmt =  null; 
		ResultSet rset = null; 
		int result=0;
		String query = "SELECT COUNT(*)FROM MEMBER WHERE MEMBER_ID=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt(1);
						//rset 첫번째컬럼값 갖고오기  	
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			ConnectionFactory.close(rset);
			ConnectionFactory.close(pstmt);
		}
		
		
		
		
		return result;
		
	}
	
public ArrayList<Member> selectCenterList(Connection conn) { //보호소 
		
		ArrayList<Member> list = null;
		Member member=null;
		Statement stmt = null;
		ResultSet rset =null;
		
		String query = "SELECT  A.MEMBER_NUM, A.MEMBER_ID, A.MEMBER_PWD, A.MEMBER_NAME, "
					 + "		A.MEMBER_TYPE, A.MEMBER_NICK, A.MEMBER_EMAIL, A.MEMBER_POST, "
					 + "        A.MEMBER_ADDRESS, A.MEMBER_ADDRESS_DETAIL, A.MEMBER_PHONE, "
					 + "		A.MEMBER_REGNUM, A.MEMBER_REG_DATE, " 
					 + "        SUM(CASE WHEN B.BOARD_NO IS NOT NULL AND B.SELECT_MEMBER_NUM IS NULL THEN 1 ELSE 0 END) AS ADOPTION_YET, " 
					 + "        SUM(CASE WHEN B.BOARD_NO IS NOT NULL AND B.SELECT_MEMBER_NUM IS NOT NULL THEN 1 ELSE 0 END) AS ADOPTION_SUCCESS " 
					 + "FROM    MEMBER A, ANIMAL_BOARD B " 
					 + "WHERE   A.MEMBER_NUM = B.MEMBER_NUM(+) "
					 + "AND		A.MEMBER_TYPE = '2' " 
					 + "GROUP BY A.MEMBER_NUM, A.MEMBER_ID,A.MEMBER_PWD, A.MEMBER_NAME, "
					 + "		A.MEMBER_TYPE, A.MEMBER_NICK, A.MEMBER_EMAIL, A.MEMBER_POST, " 
					 + "		A.MEMBER_ADDRESS, A.MEMBER_ADDRESS_DETAIL, A.MEMBER_PHONE, "
					 + "		A.MEMBER_REGNUM, A.MEMBER_REG_DATE " 
					 + "ORDER BY 1";
		try {
			
			stmt= conn.createStatement();
			rset=stmt.executeQuery(query);
			list=new ArrayList<Member>();
			
			while(rset.next()) {
				member=new Member();
				member.setMemberNum(rset.getInt("MEMBER_NUM"));
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberType(rset.getString("MEMBER_TYPE").charAt(0));
				member.setMemberNick(rset.getString("MEMBER_NICK"));
				member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPost(rset.getString("MEMBER_POST"));
				member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				member.setMemberAddressDetail(rset.getString("MEMBER_ADDRESS_DETAIL"));
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberRegNum(rset.getString("MEMBER_REGNUM"));
				member.setMemberRegDate(rset.getDate("MEMBER_REG_DATE"));
				member.setAdoption_success(rset.getInt("ADOPTION_SUCCESS"));
				member.setAdoption_yet(rset.getInt("ADOPTION_YET"));
				list.add(member);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			  ConnectionFactory.close(rset);
	          ConnectionFactory.close(stmt);
		}
			
		
		return list;
	}
}//c 

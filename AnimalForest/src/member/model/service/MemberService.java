package member.model.service;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import member.model.dao.MemberDao;
import member.model.vo.Member;
import member.model.vo.PageData;
import sun.font.CreatedFontTracker;

public class MemberService {

	private ConnectionFactory factory;
	
	public MemberService() {
		factory=ConnectionFactory.getConnection();
	}
	
	public ArrayList<Member> selectMemberList(){ //회원리스트 
		ArrayList<Member> list= null;
		Connection conn = null; 
		
		try {
			conn=factory.createConnection();
			list = new MemberDao().selectMemberList(conn);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public PageData memberCenterList(int currentPage){ 
//보호소리스트 
		ArrayList<Member> list = null;
		Connection conn = null;
		int recordCountPerPage=10;
		int naviCountPerPage = 5;
		PageData pd = new PageData();
		
		try {
			conn=factory.createConnection();
			list = new MemberDao().memberCenterList(conn,currentPage,recordCountPerPage);
			pd.setPageList(list);
			pd.setPageNavi(new MemberDao().getPageNavi(conn, currentPage, 
					recordCountPerPage, naviCountPerPage));
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pd;
		
	}
	
	public int  insertMember(Member member,String imgName,FileInputStream fis,File f) { //회원가입 
		
		 Connection conn = null;
		 int result = 0;
		 try {
			conn=factory.createConnection();
			result=new MemberDao().insertMember(conn, member,imgName,fis,f);
					} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if(result > 0) {
			 factory.commit(conn);
			 
		 }else {
			 factory.rollback(conn);
		 }
		
		 return result;
		
	}
	
	public Member selectLogin(String userId,String userPwd) { //로그인 
		
		Connection conn= null;
		Member member= null;
		
		try {
			conn=factory.createConnection();
			member= new MemberDao().selectLogin(conn,userId,userPwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return member;
	}
	public Member searchId(String userName,String phone,String email) { //아이디찾기 
		Connection conn  =null; 
		Member member = new Member();
		
		try {
			conn= factory.createConnection();
			member = new MemberDao().searchId(conn,userName,phone,email);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
		return member;
	}
	public int updateMember(Member member,String imgName,FileInputStream fis,File f) { //회원정보수정
		Connection conn = null;
		int result = 0;
		
		try {
			conn=factory.createConnection();
			
			result = new MemberDao().updateMember(conn,member,imgName,fis,f);
			if(result > 0) {
		        factory.commit(conn);
			}else {
				factory.rollback(conn);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result; 
	}
	
	public Member searchPwd(String userId, String userName, String phone) { //비번찾기 
		Connection conn  =null; 
		Member member = new Member();
		
		try {
			conn= factory.createConnection();
			member = new MemberDao().searchPwd(conn,userId,userName,phone);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
		return member;
	}
		
		

	public int deleteMember(String memberId) {
		Connection conn = null; 
		int result= 0 ; 
		
		try {
			conn=factory.createConnection();
			result = new MemberDao().deleteMember(conn,memberId);
			
			if(result > 0) {
				factory.commit(conn);
			}else{
				factory.rollback(conn);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public int dupldChk(String userId) { //아이디중복체크 
		Connection conn = null; 
		int result = new MemberDao().dupldChk(conn,userId);
		return result;
		
	}

	public ArrayList<Member> selectCenterList() {	//보호소 리스트 출력용(남훈)
		Connection conn = null;
		ArrayList<Member> list = null;
		
		try {
			conn=factory.createConnection();
			list = new MemberDao().selectCenterList(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
}

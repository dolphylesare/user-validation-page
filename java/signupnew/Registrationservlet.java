//package signupnew;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Servlet implementation class Registrationservlet
// */
//@WebServlet("/Register")
//public class Registrationservlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	      String uname = request.getParameter("name");
//	      String uemail =request.getParameter("uemail");
//	      String upwd =request.getParameter("pass");
//	      String umobile =request.getParameter("contact");
//	      Object RequestDispatcher = null;
//	      Connection con = null;
//	      
//	     try{
//	    	 Class.forName("com.mysql.cj.jdbc.Driver");
//	    	 Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/signup","root","dol@2002");
//	    	 PreparedStatement pst = con1.prepareStatement("insert into users(uname,upwd,uemail,umobile)values(?,?,?,?)");
//	    	 pst.setString(1, uname);
//	    	 pst.setString(2, upwd);
//	    	 pst.setString(2, uemail);
//	    	 pst.setString(2, umobile);
//	    	 
//	    	 int rowCount =pst.executeUpdate();
//	    	 RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
//	    	 if (rowCount > 0) {
//	    		 request.setAttribute("status","success");
//	    		 
//	    	 } else {
//		    		 request.setAttribute("status","failed");
//	    	 }
//	    	 dispatcher.forward(request,response);
//	    	 
//	     }catch(Exception e) {
//	    	 e.printStackTrace();
//	    	 ;
//	     }finally {
//	    	 con.close();
//	    	 
//	     } Catch (SQLException e) {
//	    	 e.printStackTrace();
//	     }
//	}
//}
//	    	 
	    	 
	     package signupnew;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registrationservlet
 */
@WebServlet("/Register")
public class Registrationservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("name");
        String uemail = request.getParameter("uemail");
        String upwd = request.getParameter("pass");
        String umobile = request.getParameter("contact");
        
        Connection con = null;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/signup?useSSL=false", "root", "dol@2002");
            pst = con.prepareStatement("INSERT INTO users (uname, upwd, uemail, umobile) VALUES (?, ?, ?, ?)");
            pst.setString(1, uname);
            pst.setString(2, upwd);
            pst.setString(3, uemail);
            pst.setString(4, umobile);
            
            int rowCount = pst.executeUpdate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
            
            if (rowCount > 0) {
                request.setAttribute("status", "success");
            } else {
                request.setAttribute("status", "failed");
            }
            dispatcher.forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            // You might want to handle the exception more gracefully, e.g., redirect to an error page
        } finally {
            // Close resources in reverse order of opening
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

		
	



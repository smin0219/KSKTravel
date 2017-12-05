import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;




public class DelCourseServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	  System.out.println("!!!!!!!!!!");
	  HttpSession session=request.getSession();  
	
	
	  
	System.out.println("sdddddd");
	  
	  
	          String stuId = ""+session.getValue("login");
	          

	  		String userid = request.getParameter("userid");
			String crscode = request.getParameter("crscode");
	     	String mysJDBCDriver = "com.mysql.jdbc.Driver"; 
	     	String mysURL ="jdbc:mysql://127.0.0.1:3306/cse305";
	      	String mysUserID = "root"; 
	      	String mysPassword = "1234";


	      	System.out.println(userid);
	      	System.out.println(crscode);
	      	
				java.sql.Connection conn=null;
				try 
				{
	            	Class.forName(mysJDBCDriver).newInstance();
	    			java.util.Properties sysprops=System.getProperties();
	    			sysprops.put("user",mysUserID);
	    			sysprops.put("password",mysPassword);
	        
					//connect to the database
	            			conn=java.sql.DriverManager.getConnection(mysURL,sysprops);
	            			System.out.println("Connected successfully to database using JConnect");
	            
	            			java.sql.Statement stmt1=conn.createStatement();
								stmt1.executeUpdate("delete from Transcript where CrsCode='"+crscode+"' and StudId='"+userid+"'");
				} catch(Exception e)
				{
					e.printStackTrace();
				
				}
				finally{
				
					try{conn.close();}catch(Exception ee){};
				}
	 
	   

	  
	  
	  
	  
	  
	  
	  RequestDispatcher view = request.getRequestDispatcher("delcourse.jsp");
      view.forward(request, response);    
    }
  
  
}
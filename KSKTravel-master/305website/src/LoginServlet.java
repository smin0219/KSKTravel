

import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


//		 HttpSession session2=request.getSession(false);
//		 System.out.println("session44442:   "+session2);
//		 
//		 if(session2!=null )
//		 {
//			 
//			 String stuId = "" + session2.getValue("login");
//			 System.out.println("stuIdstuId:   "+stuId);
//			 
//			 ServletContext context= getServletContext();
//				RequestDispatcher rd= context.getRequestDispatcher("/studentinfo");
//				rd.forward(request, response);
//				return;
//		 }
//		
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if((request.getParameter("action")!=null)&&	(request.getParameter("action").trim().equals("logout")))
		{
			 HttpSession session=request.getSession();  
			session.putValue("login","");
			response.sendRedirect("/");
			return;
		}
			String username = request.getParameter("username");
			String userpasswd = request.getParameter("userpasswd");
	     	String mysJDBCDriver = "com.mysql.jdbc.Driver"; 
	   	  	String mysURL ="jdbc:mysql://127.0.0.1:3306/travel_registration_system";
	  	String mysUserID = "root"; 
	  	String mysPassword = "1234";
	    
	  	 HttpSession session=request.getSession();  
		session.putValue("login","");
		if ((username!=null) &&(userpasswd!=null))
		{
			if (username.trim().equals("") || userpasswd.trim().equals(""))
			{
				response.sendRedirect("index.htm");
			}
			else
			{
				// code start here
				java.sql.Connection conn=null;
				try {
	            	Class.forName(mysJDBCDriver).newInstance();
        			java.util.Properties sysprops=System.getProperties();
        			sysprops.put("user",mysUserID);
        			sysprops.put("password",mysPassword);
    
        			//connect to the database
        			conn=java.sql.DriverManager.getConnection(mysURL,sysprops);
        			System.out.println("Connected successfully to database using JConnect");
        
        			conn.setAutoCommit(false);
        			java.sql.Statement stmt1=conn.createStatement();
        			java.sql.ResultSet rs = stmt1.executeQuery("select AccountNo from Customer where Id='" + username + "'");
        			rs.next();
        			String accountNo = rs.getString(1);
        			// java.sql.ResultSet rs = stmt1.executeQuery(" select * from Student where Id='"+username+"' and Pswd='"+userpasswd+"'");
        			rs = stmt1.executeQuery(" select * from Customer where Id='"+username+"' and Pswd='"+userpasswd+"'");
					if (rs.next())
					{
						// login success
						session.putValue("login",username);
//						response.sendRedirect("studentinfo");
						
//						response.sendRedirect("/StudentInfoServlet");
						
						
						System.out.println("RequestDispatcher rd= context.getRequestDispatcher;");
						
						List<Reservation> reservations = new ArrayList<Reservation>();
						
						rs = stmt1.executeQuery(" select r.*, i.*, rp.* from Reservation r, Includes i, ReservationPassenger rp where r.AccountNo='"+accountNo+ "' and r.ResrNo = rp.ResrNo and r.ResrNo = i.ResrNo");
						ResultSetMetaData rsmd = rs.getMetaData();

						int columnsNumber = rsmd.getColumnCount();
						System.out.println("columnsNumber " + columnsNumber);
						while(rs.next()) {
							rsmd = rs.getMetaData();

							columnsNumber = rsmd.getColumnCount();
							System.out.println("columnsNumber " + columnsNumber);
							Reservation res = new Reservation();
//							res.setNumber(rs.getString("r.ResrNo"));
//							res.setDate(rs.getString("r.ResrDate"));
//							res.setFlightnum(rs.getString("i.FlightNo"));
//							res.setSeat(rs.getString("rp.SeatNo"));
//							res.setMeal(rs.getString("rp.Meal"));
							res.setNumber(rs.getString(1));
							res.setDate(rs.getString(2));
							res.setFlightnum(rs.getString(9));
							res.setSeat(rs.getString(15));
							res.setMeal(rs.getString(17));
							
							String airline = rs.getString(8);
							
							java.sql.Statement stmt2=conn.createStatement();
							java.sql.ResultSet rs2 = stmt2.executeQuery(" select a.Name, l.DepTime from Airport a, Leg l where l.AirlineID = '" + airline + "' and l.FlightNo = '" + res.getFlightnum() + "' and l.DepAirportID = a.Id");
							if(rs2.next()) {
								res.setDeparting(rs2.getString(1));
								//res.setDeptime(rs.getString(2));
							}
								
							rs2 = stmt2.executeQuery(" select a.Name from Airport a, Leg l where l.AirlineID = '" + airline + "' and l.FlightNo = '" + res.getFlightnum() + "' and l.ArrAirportID = a.Id");
							if (rs2.next())
								res.setDestination(rs2.getString(1));
							
							rs2 = stmt2.executeQuery("select DepTime from Leg where AirlineID='" + airline + "' and FlightNo='" + rs.getString(9) + "' and LegNo='" + rs.getString(10) + "'");
							rs2.next();
							res.setDeptime(rs2.getString(1));
							
							reservations.add(res);
						}
						
						session.setAttribute("reslist", reservations);
						
						ServletContext context= getServletContext();
						// RequestDispatcher rd= context.getRequestDispatcher("/studentinfo");
						RequestDispatcher rd= context.getRequestDispatcher("/CustomerMain.jsp");
						rd.forward(request, response);		
					}
					else
					{
						System.out.println("profff");
						System.out.println("profff");
						System.out.println("profff");
//						rs = stmt1.executeQuery(" select * from Professor where Id='"+username+"' and Pswd='"+userpasswd+"'");
						rs = stmt1.executeQuery(" select * from Employee where Id='"+username+"' and Pswd='"+userpasswd+"'");
						
						
						if(rs.next())
						{
							session.putValue("login", username);
//							response.sendRedirect("FacultyInformation.jsp");
							rs = stmt1.executeQuery(" select * from Employee where Id='"+username+"' and IsManager="+1);
							if(rs.next()) {
								// Manager
								response.sendRedirect("ManagerMain.jsp");
							} else {
								// Customer Representative
								response.sendRedirect("CustomerRepMain.jsp");
							}
						}
							
						else
						{
							// username or password mistake
							response.sendRedirect("passMistake.jsp");
						}
					}
				} catch(Exception e)
				{
					e.printStackTrace();
				}
				finally{
					try{conn.close();}catch(Exception ee){};
				}
			}
		}
	}
}

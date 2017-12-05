

import java.io.IOException;
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
 * Servlet implementation class RecommendationServlet
 */
@WebServlet("/recommendations")
public class RecommendationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommendationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		HttpSession session=request.getSession(); 
		String username = request.getParameter("username");
		// String userpasswd = request.getParameter("userpasswd");
     	String mysJDBCDriver = "com.mysql.jdbc.Driver"; 
   	  	String mysURL ="jdbc:mysql://127.0.0.1:3306/travel_registration_system";
   	  	String mysUserID = "root"; 
   	  	String mysPassword = "1234";
   	  	
   	  	String accountNo = (String) session.getAttribute("accountno");
   	  	
   	  	java.sql.Connection conn = null;
		try {
			Class.forName(mysJDBCDriver).newInstance();
			java.util.Properties sysprops = System.getProperties();
			sysprops.put("user", mysUserID);
			sysprops.put("password", mysPassword);

			// connect to the database
			conn = java.sql.DriverManager.getConnection(mysURL, sysprops);
			System.out.println("Connected successfully to database using JConnect");
			
			java.sql.Statement stmt1 = conn.createStatement();
			
			java.sql.ResultSet rs = stmt1.executeQuery("SELECT * FROM FlightReservation FR " + 
														"WHERE NOT EXISTS (" + 
														"       SELECT * FROM Reservation R, Includes I" + 
														"       WHERE R.ResrNo = I.ResrNo AND FR.AirlineID = I.AirlineID" + 
														"       AND FR.FlightNo = I.FlightNo AND R.AccountNo =" + accountNo +"" + 
														")" + 
														"ORDER BY FR.ResrCount DESC");
			List<Recommendation> recs = new ArrayList<Recommendation>();
			while(rs.next()) {
				String airlineID = rs.getString(1);
				String flightNo = rs.getString(2);
				java.sql.Statement stmt2 = conn.createStatement();
				java.sql.ResultSet rs2 = stmt2.executeQuery("select Name from Airline where Id='" + airlineID + "'");
				rs2.next();
				String airline = rs2.getString(1);
				
				Recommendation rec = new Recommendation();
				rec.setAirline(airline);
				rec.setFlightNo(flightNo);
				recs.add(rec);
			}
			
			session.setAttribute("recslist", recs);
			
			ServletContext context= getServletContext();
			RequestDispatcher rd= context.getRequestDispatcher("/CustomerRecommendations.jsp");
			rd.forward(request, response);	
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				conn.close();
			} catch (Exception ee) {
			}
			;
		}
	}

}



import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * Servlet implementation class AddReservationServlet
 */
@WebServlet("/reserve")
public class AddReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReservationServlet() {
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
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		HttpSession session = request.getSession();
		
		String depAirportID = request.getParameter("from");
		String arrAirportID = request.getParameter("to");
		String depDate = request.getParameter("depdate");
		String airlineID = request.getParameter("airline");
		String meal = request.getParameter("meal");
		
		String mysJDBCDriver = "com.mysql.jdbc.Driver";

		String mysURL = "jdbc:mysql://127.0.0.1:3306/travel_registration_system";
		String mysUserID = "root";
		String mysPassword = "1234";
		
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
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			
			List<Leg> legs = new ArrayList<Leg>();
			
			java.sql.ResultSet rs = stmt1.executeQuery("select * from Leg where DepAirportID='" + depAirportID + "' and ArrAirportID='" + arrAirportID + "' and cast(DepTime as date)='" + depDate + "' and AirlineID='" + airlineID + "'");
			while(rs.next()) {
				// TODO: Get leg information
				Leg leg = new Leg();
				
				java.sql.Statement stmt2 = conn.createStatement();
				
				java.sql.ResultSet rs2 = stmt2.executeQuery("select Name from Airport where Id='" + depAirportID + "'");
				rs2.next();
				leg.setDepAirport(rs2.getString(1));
				System.out.println("AR depAirport: " + leg.getDepAirport());
				
				rs2 = stmt2.executeQuery("select Name from Airport where Id='" + arrAirportID + "'");
				rs2.next();
				leg.setArrAirport(rs2.getString(1));
				
				rs2 = stmt2.executeQuery("select Name from Airline where Id='" + airlineID + "'");
				rs2.next();
				leg.setAirline(rs2.getString(1));
				
				leg.setDepTime(rs.getString("DepTime"));
				leg.setFlightNo(rs.getString("FlightNo"));
				
				rs2 = stmt2.executeQuery("select Fare from fare where AirlineID='" + airlineID + "' and FlightNo='" + rs.getString("FlightNo") + "'");
				rs2.next();
				leg.setFare(rs2.getString(1));
				
				leg.setLegNo(rs.getString("LegNo"));
				leg.setArrTime(rs.getString("ArrTime"));
				leg.setMeal(meal);
				
				legs.add(leg);
			}
			
			session.setAttribute("leglist", legs);
			
			ServletContext context= getServletContext();
			RequestDispatcher rd= context.getRequestDispatcher("/ReserveResults.jsp");
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

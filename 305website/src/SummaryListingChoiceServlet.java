

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
 * Servlet implementation class SummaryListingChoiceServlet
 */
@WebServlet("/sumlistrevchoice")
public class SummaryListingChoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SummaryListingChoiceServlet() {
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
		
		String choice = request.getParameter("choice");

		
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
			
			if(choice.equals("Flight"))
			{
				List<Flight> flights = new ArrayList<Flight>();
				
				java.sql.ResultSet rs = stmt1.executeQuery("select FlightNo from Flight");
				while(rs.next()) {
					// Get Flight Numbers
					Flight flight = new Flight();
					flight.setFlightNum(rs.getString(1));
					
					flights.add(flight);
				}
				
				session.setAttribute("flightlist", flights);
				
			}
			else if(choice.equals("Destination City"))
			{
				List<Airport> cities = new ArrayList<Airport>();
				
				java.sql.ResultSet rs = stmt1.executeQuery("select City from Airport");
				while(rs.next()) {
					// Get City Names
					Airport city = new Airport();
					city.setCity(rs.getString(1));
					
					cities.add(city);
					
				}
				
				session.setAttribute("citylist", cities);
				
			}
			else if(choice.equals("Customer"))
			{
				List<Customer> customers = new ArrayList<Customer>();
				
				java.sql.ResultSet rs = stmt1.executeQuery("select C.id, P.FirstName, P.LastName from Customer C, Person P where C.id = P.id");
				while(rs.next()) {
					// Get ids and names of customers
					Customer customer = new Customer();
					customer.setID(rs.getString(1));
					customer.setFirstName(rs.getString(2));
					customer.setLastName(rs.getString(3));
					
					customers.add(customer);
					
				}
		
				session.setAttribute("custlist", customers);
				
			}
		
			ServletContext context= getServletContext();
			RequestDispatcher rd= context.getRequestDispatcher("/ManagerSummaryListingChoiceResults.jsp");
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

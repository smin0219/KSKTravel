

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * Servlet implementation class SubmitReservation
 */
@WebServlet("/submitres")
public class SubmitReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitReservation() {
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
		// doGet(request, response);
		
		HttpSession session = request.getSession();
		
		ArrayList<Reservation> reslist = (ArrayList<Reservation>) session.getAttribute("reslist");
		
		String depAirport = request.getParameter("depAirport");
		String arrAirport = request.getParameter("arrAirport");
		String depTime = request.getParameter("depTime");
		String airline = request.getParameter("airline");
		String flightNo = request.getParameter("flightNo");
		String fare = request.getParameter("fare");
		String legNo = request.getParameter("legNo");
		String arrTime = request.getParameter("arrTime");
		String meal = request.getParameter("meal");
		String retDate = request.getParameter("retDate");
		String type = request.getParameter("type");
		
		System.out.println("depAirport param: " + depAirport);
		
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
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
		
			java.sql.Statement stmt1 = conn.createStatement();
			
			// Update Reservation, Include, and ReservationPassenger tables
			java.sql.ResultSet rs = stmt1.executeQuery("select max(ResrNo) from Reservation");
			int maxResrNo;
			if(rs.next()) {
				maxResrNo = rs.getInt(1);
				maxResrNo++;
			} else {
				maxResrNo = 1;
			}
			
			String tType;
			
			if(type.charAt(0) == '1')
				tType = "One-Way";
			else if(type.charAt(0) == '2')
				tType = "Round-Trip";
			else
				tType = "Multi-City";
			
			double bookingFee = Double.parseDouble(fare) * 0.1;
			double totalFare = Double.parseDouble(fare) + bookingFee;
			String username = (String) session.getAttribute("login");
			rs = stmt1.executeQuery("select AccountNo from Customer where Id='" + username + "'");
			rs.next();
			String accountNo = rs.getString(1);
			stmt1.executeUpdate("insert into Reservation values('" + maxResrNo + "','" + dateFormat.format(date) + "','" + bookingFee + "','" + totalFare + "', null, '" + rs.getString(1) + "','" + tType + "')");
			
			rs = stmt1.executeQuery("select Id from Airline where Name='" + airline + "'");
			rs.next();
			String airlineID = rs.getString(1);
			// String airlineID = airline;
			rs = stmt1.executeQuery("select Id from Airport where Name='" + depAirport + "'");
			rs.next();
			String depAirportID = rs.getString(1);
			// String depAirportID = depAirport;
			rs = stmt1.executeQuery("select Id from Airport where Name='" + arrAirport + "'");
			rs.next();
			String arrAirportID = rs.getString(1);
			// String arrAirportID = arrAirport;
			System.out.println("airlineID: " + airlineID);
			System.out.println("depAirportID: " + depAirportID);
			stmt1.executeUpdate("insert into Includes values('" + maxResrNo + "','" + airlineID + "','" + flightNo + "','" + legNo + "','" + dateFormat.format(date) + "')");
			
			rs = stmt1.executeQuery("select NoOfSeats from Flight where FlightNo='" + flightNo + "' and AirlineID='" + airlineID + "'");
			rs.next();
			int noOfSeats = rs.getInt(1);
			Random rand = new Random();
			int seatNum = rand.nextInt((int) Math.floor(noOfSeats / 3)) + 1;
			int part2 = rand.nextInt(6) + 1;
			String letter;
			if(part2 == 1)
				letter = "A";
			else if (part2 == 2)
				letter = "B";
			else if (part2 == 3)
				letter = "C";
			else if (part2 == 4)
				letter = "D";
			else if (part2 == 5)
				letter = "E";
			else
				letter = "F";
			String seat = seatNum + letter;
			
			try {
				stmt1.executeUpdate("insert into Passenger values ('" + username + "','" + accountNo + "')");
			} catch(MySQLIntegrityConstraintViolationException e) {
				
			}
			
			stmt1.executeUpdate("insert into ReservationPassenger values ('" + maxResrNo + "','" + username + "','" + accountNo + "','" + seat + "', 'Economy','" + meal + "')");
			
			Reservation res = new Reservation();
			res.setNumber(maxResrNo + ""); 
			res.setDate(dateFormat.format(date));
			res.setDeparting(depAirport);
			res.setDeptime(depTime);
			res.setDestination(arrAirport);
			res.setFlightnum(flightNo);
			res.setMeal(meal);
			res.setSeat(seat);
			res.setType(tType);
//			if(type.charAt(0) == '1')
//				res.setType("One-Way");
//			else if(type.charAt(0) == '2')
//				res.setType("Round-Trip");
//			else
//				res.setType("Multi-City");
			System.out.println("type: " + type);
			
			reslist.add(res);
			
			if(type.charAt(0) == '2') {
				
				// stmt1.executeUpdate("insert into Includes values('" + maxResrNo + "','" + airlineID + "','" + flightNo + "','" + legNo + "','" + dateFormat.format(date) + "')");
				
//				Reservation res2 = new Reservation();
//				res.setNumber(maxResrNo + "");
//				res.setDate(dateFormat.format(date));
//				res.setDeparting(arrAirport);
//				res.setDeptime(retDate);
//				res.setDestination(depAirport);
//				res.setFlightnum(flightNo);
//				res.setMeal(meal);
//				res.setType("Round-Trip");
//				
//				seatNum = rand.nextInt((int) Math.floor(noOfSeats / 3)) + 1;
//				part2 = rand.nextInt(6) + 1;
//				if(part2 == 1)
//					letter = "A";
//				else if (part2 == 2)
//					letter = "B";
//				else if (part2 == 3)
//					letter = "C";
//				else if (part2 == 4)
//					letter = "D";
//				else if (part2 == 5)
//					letter = "E";
//				else
//					letter = "F";
//				seat = seatNum + letter;
//				res.setSeat(seat);
//				
//				reslist.add(res2);
			}
			
			session.setAttribute("reslist", reslist);
			
			stmt1.close();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				conn.close();
			} catch (Exception ee) {
			}
			;
		}
		
		RequestDispatcher view = request.getRequestDispatcher("CustomerMain.jsp");
		view.forward(request, response);
	}

}

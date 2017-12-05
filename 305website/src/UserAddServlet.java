import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/useradd")
public class UserAddServlet extends HttpServlet {

	public static String join(List<String> list, String delim) {

		StringBuilder sb = new StringBuilder();

		String loopDelim = "";

		for (String s : list) {

			sb.append(loopDelim);
			sb.append(s);

			loopDelim = delim;
		}

		return sb.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("useradd");
		HttpSession session = request.getSession();
		System.out.println(session.getValue("login"));

		List<User> users = new ArrayList<User>();

		User user = new User();

		user.setId(4);
		user.setName("name");

		users.add(user);

		// request.setAttribute("student", student);

		session.setAttribute("users", users);

		String Id = request.getParameter("Id");
		String FirstName = request.getParameter("FirstName");
		String LastName = request.getParameter("LastName");
		String Password1 = request.getParameter("Password1");
		String Email = request.getParameter("Email");
		String Address = request.getParameter("Address");
		String City = request.getParameter("City");
		String State = request.getParameter("State");
		String Zip = request.getParameter("Zip");
		String CCN = request.getParameter("CCN");
		
		String SSN = request.getParameter("SSN");
		String Rate = request.getParameter("HourlyRate");
		
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
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			if (request.getParameter("target").trim().equals("customer") || request.getParameter("target").trim().equals("student")) {
				// Customer
				System.out.println("ADD CUSTOMER");
				
				stmt1.executeUpdate("insert into Person values('" + Id + "','" + FirstName + "','" + LastName + "','"
						+ Address + "','" + City + "','" + State + "','" + Zip + "')");
				
				stmt1.executeUpdate("insert into Customer values('" + Id + "','" + 0 + "','" + CCN + "','" + Email + "','" + dateFormat.format(date) + "','" + 0 + "','" + Password1 + "')");
				// out.print("insert into Student
				// values('"+Id+"','"+Password1+"','"+Name+"','"+request.getParameter("status")+"')");
				stmt1.close();
			} else if (request.getParameter("target").trim().equals("customer_rep")) {
				// Customer Representative
				System.out.println("ADD CUSTOMER REP");
				
				stmt1.executeUpdate("insert into Person values('" + Id + "','" + FirstName + "','" + LastName + "','"
						+ Address + "','" + City + "','" + State + "','" + Zip + "')");
				
				java.sql.ResultSet rs = stmt1.executeQuery("select max(AccountNo) from Customer");
				int maxAccountNo;
				if (rs.next()) {
					maxAccountNo = rs.getInt(1);
					maxAccountNo++;
				} else {
					maxAccountNo = 1;
				}
				
				stmt1.executeUpdate("insert into Employee values('" + Id + "','" + SSN + "','" + maxAccountNo + "','" + dateFormat.format(date) + "','" + Rate + ',' + Password1 + "')");

				System.out.println("Id:		" + Id);

				// out.print("insert into Professor
				// values('"+Id+"','"+Password1+"','"+Name+"','"+request.getParameter("DepID")+"')");;
				stmt1.close();
			} else {
				// Manager
				System.out.println("ADD MANAGER");
				System.out.println("Correct Servlet");
				System.out.println("target: " + request.getParameter("target"));
				
				stmt1.executeUpdate("insert into Person values('" + Id + "','" + FirstName + "','" + LastName + "','"
						+ Address + "','" + City + "','" + State + "','" + Zip + "')");
				
				stmt1.executeUpdate("insert into Employee values('" + Id + "','" + SSN + "','" + 1 + "','" + dateFormat.format(date) + "','" + Rate + ',' + Password1 +"')");
				
				stmt1.close();
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				conn.close();
			} catch (Exception ee) {
			}
			;
		}

		RequestDispatcher view = request.getRequestDispatcher("useradd.jsp");
		view.forward(request, response);
	}

}


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StudentInfoServlet
 */
public class StudentInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
System.out.println("StudentInformation");
System.out.println("StudentInformation");
System.out.println("StudentInformation");
		
		HttpSession session = request.getSession();
		String stuId = "" + session.getValue("login");
		
		System.out.println("stuId:		"+stuId);

		String mysJDBCDriver = "com.mysql.jdbc.Driver";
		String mysURL = "jdbc:mysql://127.0.0.1:3306/cse305";
		String mysUserID = "root";
		String mysPassword = "1234";

		List<DataTypeB> list = new ArrayList<DataTypeB>();
		java.sql.Connection conn = null;
		try {
			Class.forName(mysJDBCDriver).newInstance();
			java.util.Properties sysprops = System.getProperties();
			sysprops.put("user", mysUserID);
			sysprops.put("password", mysPassword);

			// connect to the database
			conn = java.sql.DriverManager.getConnection(mysURL, sysprops);
			System.out
					.println("Connected successfully to database using JConnect");

			java.sql.Statement stmt1 = conn.createStatement();

			java.sql.ResultSet rs = stmt1
					.executeQuery("select Course.CrsCode,Course.CrsName,Course.DeptID,Professor.Name,Transcript.Grade from Course, Professor,Transcript where Course.CrsCode=Transcript.CrsCode and Professor.Id=Course.InsNo and Transcript.StudId='"
							+ stuId + "'");

		

			String strGrade;

			while (rs.next()) {
				strGrade = rs.getString(5);
				if (rs.getString(5).trim().equals("-1")) {
					strGrade = "N/A";
				}

				DataTypeB data = new DataTypeB();
				data.setItem1(rs.getString(1));
				data.setItem2(rs.getString(2));
				data.setItem3(rs.getString(3));
				data.setItem4(rs.getString(4));
				data.setItem5(strGrade);
				
				
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				list.add(data);

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
		
		System.out.println("length:"+list.size());
		session.setAttribute("list2", list);
		RequestDispatcher view = request.getRequestDispatcher("StudentInformation.jsp");
		view.forward(request, response);
	}

}

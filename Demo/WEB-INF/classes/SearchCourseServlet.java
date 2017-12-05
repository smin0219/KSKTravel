import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class SearchCourseServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

//		System.out.println("search!!");
//		
//		System.out.println("useradd");
		HttpSession session = request.getSession();
		System.out.println(session.getValue("login"));

		if (request.getProtocol().compareTo("HTTP/1.0") == 0)
			response.setHeader("Pragma", "no-cache");
		if (request.getProtocol().compareTo("HTTP/1.1") == 0)
			response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		String strCrsCode = request.getParameter("crscode");
		String strCrsName = request.getParameter("crsname");
		String strDept = request.getParameter("dept");
		String strProfName = request.getParameter("profname");
		String strId;

		String mysJDBCDriver = "com.mysql.jdbc.Driver";
		String mysURL = "jdbc:mysql://127.0.0.1:3306/cse305";
		String mysUserID = "root";
		String mysPassword = "1234";

		String stuId = "" + session.getValue("login");

		List<DataTypeA> list = new ArrayList<DataTypeA>();

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

			java.sql.Statement stmt = conn.createStatement();
			java.sql.Statement stmt1 = conn.createStatement();
			java.sql.Statement stmt2 = conn.createStatement();
			java.sql.ResultSet rs, rs1, rs2;
			if (!strProfName.trim().equals("")) {
				rs = stmt.executeQuery("select * from Professor "
						+ "WHERE Name = '" + strProfName + "'");

				while (rs.next()) {
					strId = rs.getString("Id");

					if (!strCrsCode.equals("")) {
						if (!strCrsName.equals("")) {
							if (!strDept.equals("")) {
								rs1 = stmt1
										.executeQuery("select * from Course "
												+ "WHERE CrsCode = '"
												+ strCrsCode + "' "
												+ "AND DeptID = '" + strDept
												+ "' " + "AND CrsName = '"
												+ strCrsName + "' "
												+ "AND InsNo = '" + strId + "'");
							} else {
								rs1 = stmt1
										.executeQuery("select * from Course "
												+ "WHERE CrsCode = '"
												+ strCrsCode + "' "
												+ "AND CrsName = '"
												+ strCrsName + "' "
												+ "AND InsNo = '" + strId + "'");
							}
						} else // CrsName is null
						{
							if (!strDept.equals("")) {
								rs1 = stmt1
										.executeQuery("select * from Course "
												+ "WHERE CrsCode = '"
												+ strCrsCode + "' "
												+ "AND DeptID = '" + strDept
												+ "' " + "AND InsNo = '"
												+ strId + "'");
							} else {
								rs1 = stmt1
										.executeQuery("select * from Course "
												+ "WHERE CrsCode = '"
												+ strCrsCode + "' "
												+ "AND InsNo = '" + strId + "'");
							}
						}
					} else // CrsCode is null
					{
						if (!strCrsName.equals("")) {
							if (!strDept.equals("")) {
								rs1 = stmt1
										.executeQuery("select * from Course "
												+ "WHERE DeptID = '" + strDept
												+ "' " + "AND CrsName = '"
												+ strCrsName + "' "
												+ "AND InsNo = '" + strId + "'");
							} else {
								rs1 = stmt1
										.executeQuery("select * from Course "
												+ "WHERE CrsName = '"
												+ strCrsName + "' "
												+ "AND InsNo = '" + strId + "'");
							}
						} else // CrsName is null
						{
							if (!strDept.equals("")) {
								System.out.println("select * from Course "
										+ "WHERE DeptID = '" + strDept + "' "
										+ "AND InsNo = '" + strId + "'");
								rs1 = stmt1
										.executeQuery("select * from Course "
												+ "WHERE DeptID = '" + strDept
												+ "' " + "AND InsNo = '"
												+ strId + "'");
							} else {
								rs1 = stmt1
										.executeQuery("select * from Course "
												+ "WHERE InsNo = '" + strId
												+ "'");
							}
						}
					}
					while (rs1.next()) {
						strCrsCode = rs1.getString("CrsCode");

						strDept = rs1.getString("DeptID");
						strCrsName = rs1.getString("CrsName");
						strId = rs1.getString("InsNo");

						rs2 = stmt2.executeQuery("select * from Professor "
								+ "WHERE Id = '" + strId + "'");
						if (rs2.next()) {
							strProfName = rs2.getString("Name");
						}

						DataTypeA data = new DataTypeA();
						data.setCrsCode(rs1.getString("CrsCode"));
						data.setCrsName(rs1.getString("CrsName"));
						data.setDeptID(rs1.getString("DeptID"));
						data.setName(rs2.getString("Name"));
						list.add(data);
					}
				}
			} // end of if strProdID is not null
			else {
				if (!strCrsCode.equals("")) {
					if (!strCrsName.equals("")) {
						if (!strDept.equals("")) {
							rs1 = stmt.executeQuery("select * from Course "
									+ "WHERE CrsCode = '" + strCrsCode + "' "
									+ "AND DeptID = '" + strDept + "' "
									+ "AND CrsName = '" + strCrsName + "' ");
						} else {
							rs1 = stmt.executeQuery("select * from Course "
									+ "WHERE CrsCode = '" + strCrsCode + "' "
									+ "AND CrsName = '" + strCrsName + "' ");
						}
					} else // CrsName is null
					{
						if (!strDept.equals("")) {
							rs1 = stmt.executeQuery("select * from Course "
									+ "WHERE CrsCode = '" + strCrsCode + "' "
									+ "AND DeptID = '" + strDept + "' ");
						} else {
							rs1 = stmt.executeQuery("select * from Course "
									+ "WHERE CrsCode = '" + strCrsCode + "' ");
						}
					}
				} else // CrsCode is null
				{
					if (!strCrsName.equals("")) {
						if (!strDept.equals("")) {
							rs1 = stmt.executeQuery("select * from Course "
									+ "WHERE DeptID = '" + strDept + "' "
									+ "AND CrsName = '" + strCrsName + "'");
						} else {
							rs1 = stmt.executeQuery("select * from Course "
									+ "WHERE CrsName = '" + strCrsName + "'");
						}
					} else // CrsName is null
					{
						if (!strDept.equals("")) {
							rs1 = stmt.executeQuery("select * from Course "
									+ "WHERE DeptID = '" + strDept + "'");
						} else {
							rs1 = stmt.executeQuery("select * from Course");
						}
					}
				}
				while (rs1.next()) {
					strCrsCode = rs1.getString("CrsCode");

					strDept = rs1.getString("DeptID");
					strCrsName = rs1.getString("CrsName");
					strId = rs1.getString("InsNo");

					rs2 = stmt2.executeQuery("select * from Professor "
							+ "WHERE Id = '" + strId + "'");
					if (rs2.next()) {
						strProfName = rs2.getString("Name");
					}
					DataTypeA data = new DataTypeA();
					data.setCrsCode(rs1.getString("CrsCode"));
					data.setCrsName(rs1.getString("CrsName"));
					data.setDeptID(rs1.getString("DeptID"));
					data.setName(rs2.getString("Name"));
					list.add(data);
				}
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

		List<User> users = new ArrayList<User>();

	    User user = new User();

	    
	    user.setId(4);
	    user.setName("name");
	  
	    users.add(user);
		
	    session.setAttribute("users", users);
		session.setAttribute("list", list);
		
		for(DataTypeA data : list)
		{
//			System.out.println(data.getCrsCode());
//			System.out.println(data.getCrsName());
//			System.out.println(data.getDeptID());
//			System.out.println(data.getName());
			
		}
		RequestDispatcher view = request.getRequestDispatcher("SearchdCourse.jsp");
		view.forward(request, response);
	}

}
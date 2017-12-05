import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		System.out.println("LoginFilterLoginFilterLoginFilter");

		HttpSession session = request.getSession(false);

		// String id =""+session.getValue("login");
		// System.out.println("iiiiiiiiii"+id);

		// HttpSession session = request.getSession();

		System.out.println("session:   " + session);

		String loginURI = request.getContextPath() + "/";
		String requestURI = request.getRequestURI();
		System.out.println(loginURI);
		System.out.println(requestURI);
		System.out.println("00000");

		boolean ifRoot = loginURI.equals(requestURI);

		System.out.println("ifRoot:    " + ifRoot);

		boolean bool = requestURI.endsWith("login");

		System.out.println("bool:  " + bool);

		if (bool == true || ifRoot == true) {
			chain.doFilter(request, response);
		}

		else {

			if (session == null) {
				System.out.println("111111");
				response.sendRedirect(loginURI);
			} else {
				System.out.println("22222");
				chain.doFilter(request, response);
			}

		}

//		 chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("initial!!!!!!!!");

	}

	// ...
}
package su.sergey.contacts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DoLoginServlet extends HttpServlet {
	public static final String PERFORM_LOGIN_SERVLET_URL = "/j_security_check";
	
    /**
     * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	session.setAttribute(LoginServlet.LOGIN_PERFORMED_ATTRIBUTE, "true");
    	request.getRequestDispatcher(PERFORM_LOGIN_SERVLET_URL).forward(request, response);
    }
}


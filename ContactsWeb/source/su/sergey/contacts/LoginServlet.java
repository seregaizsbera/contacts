package su.sergey.contacts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	public static final String LOGIN_PAGE = "/login.jsp";	
	public static final String LOGIN_PERFORMED_ATTRIBUTE = "loginPerformed";
	public static final String LOGIN_SERVLET_URL = "/login";
	
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	session.removeAttribute(LOGIN_PERFORMED_ATTRIBUTE);
    	session.invalidate();
    	request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);    	
    }
}
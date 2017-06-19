package com.vehiclecontroler.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vehiclecontroler.exception.DatabaseException;
import com.vehiclecontroler.exception.InvalidUserException;
import com.vehiclecontroler.formbean.LoginBean;
import com.vehiclecontroler.formbean.RegistrationBean;
import com.vehiclecontroler.service.Services;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		LoginBean lb = new LoginBean();
		String email=	request.getParameter("email");

		String password=request.getParameter("password");
		lb.setEmail(email);
		lb.setPassword(password);
		System.out.println(lb);
		Services services= new Services();
		try {
			RegistrationBean rb= services.login(lb);
			System.out.println(rb);
			request.getSession().setAttribute("USER", rb);
			response.sendRedirect("home.jsp");
			
			
			
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			request.getSession().setAttribute("FAIL", "PLEASE TRY LATER");
			response.sendRedirect("index.jsp");
			
		} catch (InvalidUserException e) {
			// TODO Auto-generated catch block

			request.getSession().setAttribute("FAIL", "IVALID USER CREDIENTIALS");
			e.printStackTrace();
			
			response.sendRedirect("index.jsp");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

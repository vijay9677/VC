package com.vehiclecontroler.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vehiclecontroler.exception.DatabaseException;
import com.vehiclecontroler.exception.InvalidUserException;
import com.vehiclecontroler.exception.MailException;
import com.vehiclecontroler.service.Services;

/**
 * Servlet implementation class ForgotPasswordAction
 */
@WebServlet("/ForgotPasswordAction")
public class ForgotPasswordAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
String email =	request.getParameter("email");

	System.out.println(email);

	Services services = new Services();
	try {
		Boolean b;
		try {
			b = services.forgotPassword(email);
	
			System.out.println(b);
			
			if(b!=null && b){
				request.getSession().setAttribute("SUCCESS", "PASSWORD SENT TO YOUR EMAIL");
			}
			else{
				request.getSession().setAttribute("FAIL", "UNABLE TO SEND EMAIL");
					
			}

		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.getSession().setAttribute("FAIL", e.getMessage());

		}
		} catch (DatabaseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

		request.getSession().setAttribute("FAIL", "PLEASE TRY LATER");
	
	} catch (InvalidUserException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	

		request.getSession().setAttribute("FAIL", "INVALID EMAIL");
	}
	
	response.sendRedirect("index.jsp");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

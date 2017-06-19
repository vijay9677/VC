package com.vehiclecontroler.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vehiclecontroler.exception.DatabaseException;
import com.vehiclecontroler.exception.RegistrationFailureException;
import com.vehiclecontroler.formbean.RegistrationBean;
import com.vehiclecontroler.service.Services;
import com.vehiclecontroler.util.StringConstants;

/**
 * Servlet implementation class RegistrationAction
 */
@WebServlet("/RegistrationAction")
public class RegistrationAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		
		String email =request.getParameter("email");

	String password=	request.getParameter("password");

String mobNo=		request.getParameter("mobile-number");

String age=		request.getParameter("age");
String fName=		request.getParameter("first-name");
		
RegistrationBean rb = new RegistrationBean();
rb.setAge(Integer.parseInt(age));
rb.setEmail(email);
rb.setMobileNo(mobNo);
rb.setName(fName);
rb.setPassword(password);
System.out.println(">>>>>>>>>>>>"+rb);
HttpSession httpSession= request.getSession();
Services services = new Services();
try {
	services.registerUser(rb);
	
	httpSession.setAttribute("SUCCESS", StringConstants.REGISTRATION_SUCCESS);
	response.sendRedirect("index.jsp");
} catch (DatabaseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	if(e.getMessage().contains("Duplicate entry")){
		httpSession.setAttribute("reg", rb);

		httpSession.setAttribute("FAIL", "EMAIL ALREADY EXIST");
		response.sendRedirect("Registration.jsp");
			
	}else{
	httpSession.setAttribute("FAIL", "FAILED TO REGISTER PLEASE TRY LATER");

	response.sendRedirect("index.jsp");
	}

} catch (RegistrationFailureException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	httpSession.setAttribute("reg", rb);
	
	httpSession.setAttribute("ERR", "REGISTRATION FAILED RE ENTER DATA");
	response.sendRedirect("Registration.jsp");

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

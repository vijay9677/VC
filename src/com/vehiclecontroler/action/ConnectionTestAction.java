package com.vehiclecontroler.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vehiclecontroler.service.Services;
import com.vehiclecontroler.util.StringConstants;

/**
 * Servlet implementation class ConnectionTestAction
 */
@WebServlet("/ConnectionTestAction")
public class ConnectionTestAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionTestAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		Services services= new Services();
	HttpSession session=	request.getSession();
		if(services.testConn()){
			System.out.println("TEST SUCCESS");
			session.setAttribute(StringConstants.TEST_CON_SUCCESS,StringConstants.TEST_SUCCESS_MSG);
		
		}else{
			
			System.out.println("TEST FAILED");

			session.setAttribute(StringConstants.TEST_CON_FAIL,StringConstants.TEST_FAIL_MSG);
			
		}
		response.sendRedirect("home.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

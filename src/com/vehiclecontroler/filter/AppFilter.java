package com.vehiclecontroler.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vehiclecontroler.formbean.RegistrationBean;

/**
 * Servlet Filter implementation class AppFilter
 */
@WebFilter("/*")
public class AppFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AppFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest httpRequest= (HttpServletRequest)request;
		HttpServletResponse	res=(HttpServletResponse) response;
HttpSession session = httpRequest.getSession();
String uri=		httpRequest.getRequestURI();
RegistrationBean user= 	(RegistrationBean)session.getAttribute("USER");

if(uri.contains("/css")||uri.contains("/js")||uri.contains("image")||uri.contains("RegistrationAction")||uri.contains("ForgotPasswordAction")){
	chain.doFilter(request, response);
	
	
}
else	if(uri.contains("index.jsp")||uri.contains("LoginAction")||uri.contains("ForgotPasswordAction")||uri.contains("ForgotPassword.jsp")||uri.contains("Registration.jsp")||uri.contains("contact")){
			if(user!=null && user.getuId()!=null){

res.sendRedirect("home.jsp");
				
			}else{
			chain.doFilter(request, response);
			  session.setMaxInactiveInterval(2*60*60);//TWO HOURS
			
			}
		}else{
			
		System.out.println(user);
			if(user== null || user.getuId()==null){
res.sendRedirect("index.jsp");
			}else{
			chain.doFilter(request, response);
			  session.setMaxInactiveInterval(2*60*60);//TWO HOURS
			}
			
			
			
		}
		
		
		
		// pass the request along the filter chain
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

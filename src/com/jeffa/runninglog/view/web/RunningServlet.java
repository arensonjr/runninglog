package com.jeffa.runninglog.view.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RunningServlet
 */
@WebServlet(description = "Main runninglog servlet", urlPatterns = { "/" })
public class RunningServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		PrintWriter pageWriter = response.getWriter();
		
		pageWriter.println( "Hello, world!" );
		
		Map< String, String[] > parameters = request.getParameterMap();
		for ( Map.Entry< String, String[] > paramPair : parameters.entrySet() ) {
			pageWriter.format( "Param: %s; Value: %s\n", paramPair.getKey(), paramPair.getValue()[0] );
		}
		
		pageWriter.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}



package Grades;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowAverage
 */
@WebServlet("/ShowAverage")
public class ShowAverage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseCollection db;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAverage() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException
    {
        // Do required initialization
    	db = new DatabaseCollection();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");

		// Actual logic goes here.
	    PrintWriter out = response.getWriter(); 
	    
	    //get data from database
		double average = db.getAverage();
		
		printHtml(out, average);
	      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void destroy() 
	   { 
	     // do nothing. 
	   } 
	
	private void printHtml(PrintWriter out, double average){
		out.println("<!DOCTYPE html>");
		out.println("<html lang='en'>");
		out.println("<head>");
		out.println("<title>Grade Book</title>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\" integrity=\"sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		
		//display content in body
		out.println("<div class=\"container\">");
		out.println("<h3>Average:</h3>");
		out.println("<h4>"+String.format("%.2f", average)+"</h4>");
		out.println("<form action=\"index.html\">");
		out.println("<button type=\"submit\" class=\"btn btn-default\">Add A Grade</button>");
		out.println("</form>");
		out.println("</div>");
		
		//library links
		out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>");
		out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\" integrity=\"sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==\" crossorigin=\"anonymous\"></script>");
		out.println("</body>");
		out.println("</html>");
	    
	}
}

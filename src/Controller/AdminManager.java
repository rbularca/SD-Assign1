package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.UserRepository;
import Model.UsersEntity;

/**
 * Servlet implementation class AdminManager
 */
@WebServlet("/admin.jsp")
public class AdminManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserRepository repo = new UserRepository();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		System.out.println("Woof! ");
		response.setContentType("text/html");
	    request.setAttribute("attr", "10");
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

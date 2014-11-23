package Controller;

import Model.UserRepository;
import Model.UsersEntity;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginManager
 */
@WebServlet("/LoginManager")
public class LoginManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserRepository repo = new UserRepository();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		int loginResult = repo.login(request.getParameter("username"), 
							request.getParameter("password"));
		System.out.println(loginResult);
		switch(loginResult)
		{
		case -1: rd = request.getRequestDispatcher("/Error.jsp");
				 rd.forward(request, response);
			     break;
		case 0:  rd = request.getRequestDispatcher("/Normal.jsp");
				 rd.forward(request, response);
				 break;
		case 1:  response.sendRedirect(request.getContextPath() + "/Admin.jsp");
				 break;
		default: rd = request.getRequestDispatcher("/Error.jsp");
				 rd.forward(request, response);
				 break;
		}

	}

}

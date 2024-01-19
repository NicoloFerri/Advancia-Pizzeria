package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DAO;
import model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO userDao = new DAO();

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAO dao = new DAO();
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = dao.selectCredentials(username, password);
		System.out.println(user);

		try {
			if (null != user) {	
				request.setAttribute("listOfIngredienti", dao.getIngredientsListByUser(user));
				request.setAttribute("listOfImpasti", dao.getImpastoListByUser(user));
				request.setAttribute("listOfPizza", dao.getPizzaListByUser(user));
				request.setAttribute("userObj", user.getUserId());
				request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			} else {
				request.setAttribute("loginError", true);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

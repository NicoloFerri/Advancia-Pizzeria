package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAO;
import model.User;

public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DashboardServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAO dao = new DAO();
		String imp = request.getParameter("dough");
		String[] ingredients = request.getParameterValues("ingredient");
		String pizzaName = request.getParameter("pizzaName");
		String userString = request.getParameter("userObj");
		int userId = (int) Integer.parseInt(userString);
		User user = dao.getUserById(userId);
        dao.insertPizza(imp, ingredients, pizzaName, user);
        
		request.setAttribute("userObj", userId);
		request.setAttribute("listOfIngredienti", dao.getIngredientsListByUser(user));
		request.setAttribute("listOfImpasti", dao.getImpastoListByUser(user));
		request.setAttribute("listOfPizza", dao.getPizzaListByUser(user));
		RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
	}

}

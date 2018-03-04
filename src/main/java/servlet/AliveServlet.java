package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.Dao;
import dao.DaoImpl;

/**
 * Servlet implementation class AliveServlet
 */
public class AliveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	Dao dao = new DaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AliveServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("application/json; charset=ISO-8859-1");
		String jsonData = gson.toJson(dao.findAllAlivePeople("alive_people"));
		System.out.println(jsonData);
		out.println(jsonData);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

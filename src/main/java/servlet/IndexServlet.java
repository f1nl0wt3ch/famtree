package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.Dao;
import dao.DaoImpl;
import domain.AlivePeople;
import service.CommonService;
import service.CommonServiceImpl;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommonService service = new CommonServiceImpl();
	Gson gson = new Gson();
	Dao dao = new DaoImpl();
	List<AlivePeople> alives = new ArrayList<AlivePeople>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int totalAlive = Math.round(dao.findAllAlivePeople("alive_people").size()/6);
		request.setAttribute("totalAlive", totalAlive);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AlivePeople alive = new AlivePeople();
		String jsonData = service.handleRequestFromClient(request);
		System.out.println("Data "+ jsonData);
		alive = gson.fromJson(jsonData, AlivePeople.class);
		alives.add(alive);
		dao.insertAlivePeople(alives, "alive_people");
	}

}

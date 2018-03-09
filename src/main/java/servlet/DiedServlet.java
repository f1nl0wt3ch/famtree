package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.Dao;
import dao.DaoImpl;
import domain.AlivePeople;
import domain.DiedPeople;
import service.CommonService;
import service.CommonServiceImpl;

/**
 * Servlet implementation class DiedServlet
 */
public class DiedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	Dao dao = new DaoImpl();
	CommonService service = new CommonServiceImpl();
	List<DiedPeople> dieds = new ArrayList<DiedPeople>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("application/json; charset=ISO-8859-1");
		String jsonData = gson.toJson(dao.findAllDiedPeople());
		out.println(jsonData);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiedPeople died = new DiedPeople();
		String jsonData = service.handleRequestFromClient(request);
		System.out.println("Data "+ jsonData);
		died = gson.fromJson(jsonData, DiedPeople.class);
		dieds.add(died);
		dao.insertDiedPeople(dieds);
	}

}

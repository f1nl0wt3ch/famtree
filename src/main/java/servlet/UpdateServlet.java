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
import domain.AlivePeople;
import service.CommonService;
import service.CommonServiceImpl;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Dao dao = new DaoImpl();
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); 
    CommonService service = new CommonServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           String queryStr = request.getQueryString();
           int id= Integer.parseInt(queryStr);
           AlivePeople people = dao.findAlivePeopleById(id);
           PrintWriter out = response.getWriter();
	   		response.setCharacterEncoding("ISO-8859-1");
	   		response.setContentType("application/json; charset=ISO-8859-1");
	   		String jsonData = gson.toJson(people);
	   		out.println(jsonData);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonData = service.handleRequestFromClient(request);
		AlivePeople people = gson.fromJson(jsonData, AlivePeople.class);
		dao.updateAlivePeople(people);
	}

}

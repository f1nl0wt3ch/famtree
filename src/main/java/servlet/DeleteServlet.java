package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.Dao;
import dao.DaoImpl;
import service.CommonService;
import service.CommonServiceImpl;

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommonService service = new CommonServiceImpl();
	Dao dao = new DaoImpl();
	Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String queryStr = request.getQueryString();
		 int id= Integer.parseInt(queryStr);
         response.addHeader("status", dao.deleteAlivePeople(id, "alive_people")+"");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String jsonData = service.handleRequestFromClient(request);
         int id = gson.fromJson(jsonData, Integer.class);
         response.addHeader("status", dao.deleteAlivePeople(id, "alive_people")+"");
	}

}

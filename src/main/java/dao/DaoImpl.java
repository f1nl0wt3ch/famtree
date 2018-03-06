package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.AlivePeople;
import service.CommonService;
import service.CommonServiceImpl;

public class DaoImpl implements Dao {
	final String USER = "DB_USER";
	final String DRIVER = "DRIVER";
	final String PASSWORD = "DB_PASS";
	final String URL = "URL";
	CommonService service = new CommonServiceImpl();
	String user = service.getValueByKey(USER);
	String pass = service.getValueByKey(PASSWORD);
	String driver = service.getValueByKey(DRIVER);
	String url = service.getValueByKey(URL);

	private Statement sta;
	private ResultSet rs;

	public Connection connectDatabase() {
		Connection con;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			sta = con.createStatement();
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean closeConnection(Connection con) {
		boolean paluu = false;
		try {
			con.close();
			paluu = true;

		} catch (SQLException SQLe) {
			paluu = false;
		}
		return paluu;
	}

	public List<AlivePeople> findAllAlivePeople(String table) {
		List<AlivePeople> peoples = new ArrayList<AlivePeople>();
		Connection con = connectDatabase();
		if(con != null) {
			try {

				String query = "SELECT id, fullname,address, email, phone, imageLink, detail, bornDate FROM " + table+" ORDER BY bornDate";
				rs = sta.executeQuery(query);

				while (rs.next()) {
					AlivePeople people = new AlivePeople();
					people.setId(rs.getInt("id"));
					people.setFullName(rs.getString("fullname"));
					people.setAddress(rs.getString("address"));
					people.setEmail(rs.getString("email"));
					people.setPhone(rs.getString("phone"));
					people.setBornDate(rs.getTimestamp("bornDate"));
					people.setDetail(rs.getString("detail"));
					people.setImageLink(rs.getString("imageLink"));
					peoples.add(people);
				}
			} catch (Exception e) {
				System.out.print("error " + e);
			} finally {
				closeConnection(con);
			}
		}
		return (ArrayList<AlivePeople>) peoples;
	}

	public boolean insertAlivePeople(List<AlivePeople> peoples, String table) {
		Connection con = connectDatabase();
		if(peoples == null) {
			return false;
		} else {
			String query;
			if (peoples.size() == 1) {
				 System.out.println("INSERT AN ALIVE PEOPLE");
			     query = "INSERT INTO "+table+"(fullname, address, email, phone,bornDate, imageLink, detail) VALUES "+ "('"+
						       peoples.get(0).getFullName()+"','"+peoples.get(0).getAddress()+"','"+peoples.get(0).getEmail()+"','"
						       +peoples.get(0).getPhone()+"','"+peoples.get(0).getBornDate()+"','"
						       +peoples.get(0).getImageLink()+"','"+peoples.get(0).getDetail()+"')";
			} else {
				System.out.println("INSERT A LIST OF ALIVE PEOPLE");
				  query = "INSERT INTO "+table+"(fullname, address, email, phone,bornDate, imageLink, detail) VALUES "+ service.formatAlivesToSQL(peoples) ;
			}
			   try {
				 return sta.execute(query);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				closeConnection(con);
			}
		}
	}

	public int updateAlivePeople(AlivePeople ap) {
		return 0;
	}

	public int deleteAlivePeople(int id, String table) {
		String query = "DELETE FROM "+table+" WHERE id="+id;
		Connection con = connectDatabase();
		try {
			return (sta.execute(query))? 1 : 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			closeConnection(con);
		}
	}

}

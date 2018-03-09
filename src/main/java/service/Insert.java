package service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.Dao;
import dao.DaoImpl;
import domain.AlivePeople;

public class Insert {

	public static void main(String[] args) {
		  List<AlivePeople> peoples = new ArrayList<AlivePeople>();
		  AlivePeople alive = new AlivePeople();
		  alive.setFullName("Dinh Ngoc Mun");
		  alive.setBornDate(new Timestamp(System.currentTimeMillis()));
		  alive.setDetail("Sister");
		  alive.setEmail("mun.dinh@yahoo.com");
		  alive.setPhone("0857437373");
		  alive.setImageLink("https://s3.amazonaws.com/aspph-wp-production/app/uploads/2017/03/Ans-.jpg");
		  alive.setAddress("Vietnam");
		  peoples.add(alive);
          Dao dao = new DaoImpl();
          System.out.println(dao.insertAlivePeople(peoples));
          
	}

}

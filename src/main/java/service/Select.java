package service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import dao.Dao;
import dao.DaoImpl;
import domain.AlivePeople;

public class Select {

	public static void main(String[] args) {
        String uri = "/Users/dinhthinh/Desktop/datafamily.xlsx";
        ExcelUtil excel = new ExcelUtil();
        Dao dao = new DaoImpl();
        CommonService service = new CommonServiceImpl();
        System.out.println(service.formatAlivesToSQL(dao.findAllAlivePeople()));
        System.out.println("Dao "+ dao.findAllAlivePeople().size());
        try {
			List<AlivePeople> list = excel.findAllAlivePeopleFromExcel(new FileInputStream(uri));
			for(AlivePeople people : list) {
				System.out.println("Full name "+people.getFullName());
				System.out.println("Address "+people.getAddress());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}

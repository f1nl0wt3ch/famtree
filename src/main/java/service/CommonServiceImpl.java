package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.AlivePeople;
import domain.DiedPeople;

public class CommonServiceImpl implements CommonService {
	private Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
    final String FILE_URI = "";
    ResourceBundle bundle = ResourceBundle.getBundle("properties/config");
	public List<AlivePeople> findAllAlivePeople() {
		try {
			FileInputStream excelFile = new FileInputStream(new File(FILE_URI));
	        Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
		    Iterator<Row> iterator = datatypeSheet.iterator();
		    while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + "--");
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + "--");
                    }

                }
                System.out.println();

            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<DiedPeople> findAllDiedPeople() {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> findAllParamter(String lang) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getValueByKey(String key) {
		return (bundle.getString(key) == null)? null : bundle.getString(key);
	}

	public Timestamp convertStringToTimestamp(String dateStr) {
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		    Date parsedDate = dateFormat.parse(dateStr);
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    return timestamp;
		} catch(Exception e) { //this generic but you can control another types of exception
		    return null;
		}
	}

	public String handleRequestFromClient(HttpServletRequest request) throws IOException {
		BufferedReader reader = request.getReader();
        StringBuilder builderStr = new StringBuilder();
        String line, JSONResponseStr;
        while((line = reader.readLine()) != null){
        	    builderStr.append(line);
        }
        reader.close();
        JSONResponseStr = builderStr.toString();
        return JSONResponseStr;
    }

	public String formatAlivesToSQL(List<AlivePeople> list) {
		StringBuilder builderStr = new StringBuilder();
		for(AlivePeople people : list) {
			builderStr.append("('"+people.getFullName()+"','"+people.getAddress()+"','"+people.getEmail()+"','"+people.getPhone()
			                 +"','"+people.getDetail()+"','"+people.getBornDate()+"'),");
		}
		String str = builderStr.toString().substring(0, builderStr.toString().length()-1);
		return str;
	}

	public String formatDiedToSQL(List<DiedPeople> list) {
		return null;
	}
	
}

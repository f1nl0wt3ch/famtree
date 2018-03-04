package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import domain.AlivePeople;
import domain.DiedPeople;

public class ExcelUtil {

	CommonService service = new CommonServiceImpl();
	public List<AlivePeople> findAllAlivePeopleFromExcel(FileInputStream sourceFile) {
		List<AlivePeople> peoples = new ArrayList<AlivePeople>();
		int row = 0;
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(sourceFile);
			Sheet dataTypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = dataTypeSheet.iterator();
			while (iterator.hasNext()) {
				row++;
				if (row > 1) {
					Row currentRow = iterator.next();
					Iterator<Cell> cellIterator = currentRow.iterator();
					while (cellIterator.hasNext()) {
						Cell currentCell = cellIterator.next();
							AlivePeople people = new AlivePeople();
							people.setFullName(currentCell.getStringCellValue());
							people.setAddress(currentCell.getStringCellValue());
							people.setEmail(currentCell.getStringCellValue());
							people.setPhone(currentCell.getStringCellValue());
							people.setBornDate(service.convertStringToTimestamp(currentCell.getStringCellValue()));
							people.setImageLink(currentCell.getStringCellValue());
							peoples.add(people);
					}

				}

			}
			workbook.close();
			sourceFile.close();
			return peoples;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<DiedPeople> findAllDiedPeopleFromExcel(FileInputStream sourceFile) {
		return null;
	}
}

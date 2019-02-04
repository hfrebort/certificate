package at.ofi.certificate.backend;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

public class ExcelSheetReader {

	private static final SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public static List<String> readsheet(Sheet sheet) {
		List<String> list = new ArrayList<>();
		sheet.spliterator()
				.forEachRemaining(row -> row.spliterator().forEachRemaining(cell -> list.add(cell.toString())));
		return list;
	}
}

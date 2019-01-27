package at.spindi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;

public class excel {

	private static final DataFormatter cellFormatter = new DataFormatter();
	private static final SimpleDateFormat ISO8601dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Stream<List<String>> readsheet(Sheet sheet) {

		return 
			StreamSupport.stream( sheet.spliterator(), false )
			.map( row -> 
			{
				return 
					StreamSupport.stream( row.spliterator(), false )
					.map( cell -> 
					{
						String result = "";
						if ( cell.getCellTypeEnum() == CellType.STRING) {
							result = cell.getStringCellValue();
						}
						else if ( cell.getCellTypeEnum() == CellType.NUMERIC) {
							final Date date = cell.getDateCellValue();
							result = ISO8601dateFormat.format(date);
						}
						else {
							result = cellFormatter.formatCellValue(cell);
						}
						return result;
					})
					.collect(Collectors.toList());
			});
	}
}

package at.ofi.certificate.backend.reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import at.ofi.exceltocertsdb.ColumnMappingType;
import org.apache.poi.ss.util.CellReference;

public class ExcelSheetReader {

	private static final String EMPTY_STRING = "";

	public static Stream<Map<ColumnMappingType,Object>> readRowsUntilEmpty(final Sheet certSheet, final List<ColumnMappingType> columnMapping, int skipRows) {

		return
			java.util.stream.StreamSupport.stream(certSheet.spliterator(), false)
			.skip(skipRows)
			.takeWhile(row -> row.getFirstCellNum() != -1) // short representing the first logical cell in the row, or -1 if the row does not contain any cells.
			.map( row -> readRowDataToMap(row, columnMapping) );
	}
	private static Map<ColumnMappingType, Object> readRowDataToMap(final Row row, final List<ColumnMappingType> columnMapping)  {
		
		final Map<ColumnMappingType, Object> result = new HashMap<ColumnMappingType, Object>();

		for ( ColumnMappingType colMap : columnMapping ) {

			final int colNumber = CellReference.convertColStringToIndex(colMap.getExcelColumn());
			Cell cell = row.getCell(colNumber);

			Object value;

			if ( cell == null ) {
				value = EMPTY_STRING;
			}
			else if ( cell.getCellType() == CellType.BLANK )
			{
				value = EMPTY_STRING;
			}
			else if ( "char".equals(colMap.getType())) {
				value = getStringRepresentation(cell);
			}
			else if ( "int".equals(colMap.getType())) {
				value = Integer.valueOf( (int) cell.getNumericCellValue() );
			}
			else if ( "date".equals(colMap.getType())) {
				java.util.Date d = cell.getDateCellValue();
				value = d;
			}
			else {
				throw new RuntimeException(String.format("unknown type [%s] from database column [%s]", colMap.getType(), colMap.getDatabaseColumn()));
			}

			result.put(colMap, value);
		}
		
		return result;
	}
	private static String getStringRepresentation(Cell cell) {

		String result;

		if ( cell.getCellType() == CellType.NUMERIC) {
			cell.setCellType(CellType.STRING); // https://stackoverflow.com/questions/1072561/how-can-i-read-numeric-strings-in-excel-cells-as-string-not-numbers
			result = cell.getStringCellValue();
		}
		else {
			result = cell.toString();
		}

		return result;
	}
}

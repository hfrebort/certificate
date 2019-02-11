package at.ofi.certificate.backend.dbimport;

import at.ofi.exceltocertsdb.ColumnMappingType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SheetReader {

	//private static final String EMPTY_STRING = "";

	public static Stream<List<Object>> readSheetUntilEmptyRow(final Sheet certSheet, final List<ColumnMappingType> columnMapping, int skipRows) {

		return
			java.util.stream.StreamSupport.stream(certSheet.spliterator(), false)
			.skip(skipRows)
			//.takeWhile(row -> row.getFirstCellNum() != -1) // short representing the first logical cell in the row, or -1 if the row does not contain any cells.
			.map( row -> readRow(row, columnMapping) );
	}
	private static List<Object> readRow(final Row row, final List<ColumnMappingType> columnMapping)  {
		
		final List<Object> result = new ArrayList<>(columnMapping.size());

		for ( ColumnMappingType colMap : columnMapping ) {

			final int colNumber = CellReference.convertColStringToIndex(colMap.getExcelColumn());
			Cell cell = row.getCell(colNumber);

			Object value;

			if ( cell == null ) {
				value = null;
			}
			else if ( cell.getCellType() == CellType.BLANK )
			{
				value = null;
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

			result.add(value);
		}
		
		return result;
	}
	private static String getStringRepresentation(final Cell cell) {

		String result;

		if ( cell.getCellType() == CellType.NUMERIC) {
			if ( DateUtil.isCellDateFormatted(cell) ) {
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY.MM.DD");
				result = sdf.format(cell.getDateCellValue());
			}
			else {
				cell.setCellType(CellType.STRING); // https://stackoverflow.com/questions/1072561/how-can-i-read-numeric-strings-in-excel-cells-as-string-not-numbers
				result = cell.getStringCellValue();
			}
		}
		else {
			result = cell.toString();
		}

		return result;
	}
}

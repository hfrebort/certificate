package at.ofi.certificate.backend.reader;

import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.KeyValue;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import at.ofi.exceltocertsdb.ColumnMappingType;

public class ExcelSheetReader {

	private static final SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public static List<String> readCells(Sheet sheet) {
		List<String> list = new ArrayList<>();
		sheet.spliterator()
				.forEachRemaining(row -> row.spliterator().forEachRemaining(cell -> list.add(cell.toString())));
		return list;
	}

	public static boolean isEmptyRow(Map<ColumnMappingType, Object> rowData) {
		return 
				rowData.values().stream()
				.allMatch( data -> data == null);
	}

	public static Stream<Map<ColumnMappingType,Object>> readRows(Sheet certSheet, List<ColumnMappingType> columnMapping, int skipRows) {
		
		return 
			java.util.stream.StreamSupport.stream(certSheet.spliterator(), false)
			.skip(skipRows)
			.map( row -> readRowDataToMap(row, columnMapping) )
			.takeWhile( rowMap -> rowMap.values().stream().allMatch(i -> i == null) );
	}
	private static Map<ColumnMappingType, Object> readRowDataToMap(Row row, List<ColumnMappingType> columnMapping) {
		
		Map<ColumnMappingType, Object> result = new HashMap<ColumnMappingType, Object>();
		
		for ( ColumnMappingType colMap : columnMapping ) {
			final int colNumber = excelColToNumber(colMap.getExcelColumn());
			final Cell cell = row.getCell(colNumber);

			Object value = null;
			
			if ( cell.getCellType() == CellType.BLANK )
			{
				value = null;
			}
			else if ( colMap.getType() == "char") {
				value = cell.getStringCellValue();
			}
			else if ( colMap.getType() == "int") {
				value = Integer.valueOf( Double.valueOf( cell.getNumericCellValue() ).intValue() );
			}
			else if ( colMap.getType() == "date") {
				value = cell.getDateCellValue();
			}

			result.put(colMap, value);
		}
		
		return result;
		
		/*
		return 
			columnMapping.stream()
			.map( colMap -> {
				
				final int colNumber = excelColToNumber(colMap.getExcelColumn());
				final Cell cell = row.getCell(colNumber);

				Object value = null;
				
				if ( cell.getCellType() == CellType.BLANK )
				{
					value = null;
				}
				else if ( colMap.getType() == "char") {
					value = cell.getStringCellValue();
				}
				else if ( colMap.getType() == "int") {
					value = new Integer( new Double( cell.getNumericCellValue() ).intValue() );
				}
				else if ( colMap.getType() == "date") {
					value = cell.getDateCellValue();
				}
				
				return new AbstractMap.SimpleEntry<ColumnMappingType, Object>(colMap, value);
				
			})
			.filter( entry -> entry != null)
			.collect(Collectors.toMap( e -> e.getKey(), e -> e.getValue() ));
			*/
	}
	
	public static int excelColToNumber(String excelcol) {
		
		int base = 26;
		int number = 0;
		
		for ( int i=0; i < excelcol.length(); ++i ) {
			
			int digitDecimalValue = excelcol.charAt(i) - 'A' + 1;
			number = number * base + digitDecimalValue; 
			
		}
		
		return number;
	}
}

package at.ofi.certificate.backend.reader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import at.ofi.certificate.backend.dbimport.MappingReader;
import at.ofi.certificate.backend.dbimport.SheetReader;
import at.ofi.exceltocertsdb.ColumnMappingType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.junit.Test;

import at.ofi.exceltocertsdb.CertificateSheetType;

public class ReadXlsxWithMappings {

	private static final String SAMPLE_XLS = "src/test/resources/CertsSample.xlsx";
	
	private static final String OUTPUT_XLS = System.getProperty("java.io.tmpdir") + "output_readWithMapping.txt";
	
	@Test
	public void ReadExcelWithMappingsTest() throws Exception {
		System.out.println("Output: " + OUTPUT_XLS);
		CertificateSheetType mappings = MappingReader.read(ClassLoader.getSystemResourceAsStream("ExcelToCertsDB.xml"));
		List<ColumnMappingType> mappingCols = mappings.getColumnMapping();
		
		try (	PrintWriter output = getUTF8writer(OUTPUT_XLS);
				InputStream xlsInput = new FileInputStream(new File(SAMPLE_XLS));
				Workbook wb = WorkbookFactory.create(xlsInput))
		{
			Sheet certSheet = wb.getSheet(mappings.getSheetName());
			int skipRows = 1;
			SheetReader
				.readSheetUntilEmptyRow(certSheet, mappings.getColumnMapping(), skipRows)
				.forEach( dataRow -> {
						for ( int i=0; i < dataRow.size(); ++i) {
							output.printf("%s(%s)\t", mappingCols.get(i).getDatabaseColumn(), String.valueOf(dataRow.get(i)));
						}
						output.println("");
				});			
		}
		catch (Exception e) {
			Assert.fail(
					String.format("Can not load workbook. Type [%s] Msg [%s]", 
							e.getClass().toString(), 
							e.getMessage() ));
			throw(e);
		}
	}
	private static PrintWriter getUTF8writer(String filename) throws FileNotFoundException {
		return new PrintWriter(
				new BufferedWriter(
						new OutputStreamWriter(
								new FileOutputStream(filename), StandardCharsets.UTF_8)));
	}
}

package at.ofi.certificate.backend.reader;

import java.io.*;
import java.nio.charset.StandardCharsets;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.junit.Test;

import at.ofi.exceltocertsdb.CertificateSheetType;

public class ReadXlsxWithMappings {

	private static final String SampleXls = "c:\\temp\\cert\\CertsSample.xlsx";
	
	@Test
	public void ReadExcelWithMappingsTest() throws Exception {
		CertificateSheetType mappings = MappingSheetReader.read(ClassLoader.getSystemResourceAsStream("ExcelToCertsDB.xml")).getValue();
		
		try (	PrintWriter output = getUTF8writer("c:\\temp\\cert\\output_readWithMapping.txt");
				InputStream XlsInput = new FileInputStream(new File(SampleXls));
				Workbook wb = WorkbookFactory.create(XlsInput);)
		{
			Sheet certSheet = wb.getSheet(mappings.getSheetName());
			int skipRows = 1;
			ExcelSheetReader
				.readRowsUntilEmpty(certSheet, mappings.getColumnMapping(), skipRows)
				.forEach( data -> {
					data.forEach( (colMap,value) -> {
						output.printf("%s(%s)\t", colMap.getDatabaseColumn(), value.toString());
					});
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

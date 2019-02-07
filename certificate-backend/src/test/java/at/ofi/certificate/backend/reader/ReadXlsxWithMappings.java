package at.ofi.certificate.backend.reader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import at.ofi.certificate.backend.dbimport.MappingReader;
import at.ofi.certificate.backend.dbimport.ReadCertsFromExcelSheet;
import at.ofi.exceltocertsdb.ColumnMappingType;
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
		CertificateSheetType mappings = MappingReader.read(ClassLoader.getSystemResourceAsStream("ExcelToCertsDB.xml"));
		List<ColumnMappingType> mappingCols = mappings.getColumnMapping();
		
		try (	PrintWriter output = getUTF8writer("c:\\temp\\cert\\output_readWithMapping.txt");
				InputStream XlsInput = new FileInputStream(new File(SampleXls));
				Workbook wb = WorkbookFactory.create(XlsInput))
		{
			Sheet certSheet = wb.getSheet(mappings.getSheetName());
			int skipRows = 1;
			ReadCertsFromExcelSheet
				.readRowsUntilEmpty(certSheet, mappings.getColumnMapping(), skipRows)
				.forEach( dataRow -> {
						for ( int i=0; i < dataRow.size(); ++i) {
							output.printf("%s(%s)\t",
									mappingCols.get(i).getDatabaseColumn(), dataRow.get(i).toString());
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

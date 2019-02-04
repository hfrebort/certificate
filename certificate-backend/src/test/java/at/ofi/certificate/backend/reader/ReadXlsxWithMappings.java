package at.ofi.certificate.backend.reader;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.junit.Assert;
import org.junit.Test;

import at.ofi.certificate.backend.BOCertRow;
import at.ofi.exceltocertsdb.CertificateSheetType;

public class ReadXlsxWithMappings {

	private static final String SampleXls= "c:\\temp\\crt\\CertsSample.xlsx";
	
	@Test
	public void ReadExcelWithMappingstest() throws FileNotFoundException {
		CertificateSheetType mappings = MappingSheetReader.read(ClassLoader.getSystemResourceAsStream("ExcelToCertsDB.xml")).getValue();
		
		try (	InputStream XlsInput = new FileInputStream(new File(SampleXls));
				Workbook wb = XSSFWorkbookFactory.createWorkbook(XlsInput)) {
			
			Sheet certSheet = wb.getSheet(mappings.getSheetName());
			int startRow = 2;
			ExcelSheetReader.readRows(certSheet, mappings.getColumnMapping(), startRow);
			
			
			
		} catch (Exception e) {
			Assert.fail("Can not load workbook");
		}
		
	}

}

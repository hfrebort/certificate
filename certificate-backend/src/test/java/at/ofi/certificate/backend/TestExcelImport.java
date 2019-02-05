package at.ofi.certificate.backend;

import static org.junit.Assert.assertTrue;

import java.io.*;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Stream;


import at.ofi.certificate.backend.reader.ExcelSheetReader;
import at.ofi.certificate.backend.reader.MappingSheetReader;
import at.ofi.exceltocertsdb.CertificateSheetType;
import at.ofi.exceltocertsdb.ColumnMappingType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author ce-harald.frebort
 */
public class TestExcelImport {

	@Test
	public void runImportTest() throws IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

			importCertsToDB.run(
					"ExcelToCertsDB.xml",
					"c:\\temp\\cert\\CertsSample.xlsx",
					"jdbc:mysql://localhost/excelcerts?user=bee&password=0nrg");

	}

}

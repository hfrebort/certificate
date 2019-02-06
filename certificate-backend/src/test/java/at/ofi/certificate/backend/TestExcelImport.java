package at.ofi.certificate.backend;

import static org.junit.Assert.assertTrue;

import java.io.*;
import java.sql.SQLException;

import at.ofi.certificate.backend.dbimport.ImportCertsToDB;
import org.junit.Test;

/**
 * @author ce-harald.frebort
 */
public class TestExcelImport {

	@Test
	public void runImportTest() throws IOException, SQLException {

			ImportCertsToDB.run(
					"ExcelToCertsDB.xml",
					"c:\\temp\\cert\\CertsSample.xlsx",
					"jdbc:mysql://localhost/excelcerts?user=bee&password=0nrg");

	}

}

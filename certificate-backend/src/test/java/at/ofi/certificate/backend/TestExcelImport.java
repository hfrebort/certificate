package at.ofi.certificate.backend;

import static org.junit.Assert.assertTrue;

import java.io.*;
import java.sql.SQLException;

import at.ofi.certificate.backend.dbimport.ImportCertsToDB;
import at.ofi.certificate.backend.dbimport.InsertCertsToDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @author ce-harald.frebort
 */
public class TestExcelImport {

	@Test
	public void runImportTest() throws IOException, SQLException {

		Logger log = LogManager.getLogger();

		log.debug("rönning [runImportTest]...");


		ImportCertsToDB.run(
				"ExcelToCertsDB.xml",
				"c:\\temp\\cert\\CertsSample.xlsx",
					"jdbc:mysql://localhost/excelcerts?user=bee&password=0nrg");

		// adding "&rewriteBatchedStatements=true" to connection string will break the returned rowCount by executeBatch()
	}

}

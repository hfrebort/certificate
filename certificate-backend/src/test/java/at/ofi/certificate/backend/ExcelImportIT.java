package at.ofi.certificate.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import at.ofi.certificate.backend.dbimport.ImportCertsToDB;

public class ExcelImportIT {

   @Test
   public void runImportTest() throws IOException, SQLException {

      Logger log = LogManager.getLogger();

      log.debug("rönning [runImportTest]...");
      FileInputStream fileInputStream = new FileInputStream(new File("src/test/resources/CertsSample.xlsx"));

      ImportCertsToDB.run(
            fileInputStream,
            "jdbc:mysql://localhost/excelcerts?user=bee&password=0nrg");

      // adding "&rewriteBatchedStatements=true" to connection string will break the returned rowCount by executeBatch()
   }

}

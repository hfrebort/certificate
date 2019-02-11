package at.ofi.certificate.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ofi.certificate.backend.dbimport.ImportCertsToDB;

public class ExcelImportIT {

   @Test
   public void runImportTest() throws IOException, SQLException {

      Logger log = LoggerFactory.getLogger(ExcelImportIT.class);

      log.debug("rönning [runImportTest]...");
      FileInputStream fileInputStream = new FileInputStream(new File("src/test/resources/CertsSample.xlsx"));

      ImportCertsToDB.run(
            fileInputStream);

      // adding "&rewriteBatchedStatements=true" to connection string will break the returned rowCount by executeBatch()
   }

}

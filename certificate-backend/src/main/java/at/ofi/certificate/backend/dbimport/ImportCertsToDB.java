package at.ofi.certificate.backend.dbimport;

import static java.lang.ClassLoader.getSystemResourceAsStream;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import at.ofi.exceltocertsdb.CertificateSheetType;

public class ImportCertsToDB {

   private static final Logger LOG = LogManager.getLogger(ImportCertsToDB.class);

   private static final String MAPPING_XML = "ExcelToCertsDB.xml";

   public static void run(InputStream xlsInput, String jdbcConnectionUrl) throws IOException, SQLException {

      CertificateSheetType mappings = MappingReader.read(getSystemResourceAsStream(MAPPING_XML));

      try (Workbook workbook = WorkbookFactory.create(xlsInput);
            Connection connection = DriverManager.getConnection(jdbcConnectionUrl)) {
         LOG.debug("loading sheet [{}]", mappings.getSheetName());
         Sheet certSheet = workbook.getSheet(mappings.getSheetName());

         LOG.debug("loading rows of sheet to stream");
         Stream<List<Object>> dataRowStream =
               SheetReader.readSheetUntilEmptyRow(certSheet, mappings.getColumnMapping(), 1);

         LOG.debug("import started");
         InsertCertsToDB.run(dataRowStream, mappings.getColumnMapping(), connection, "da Spindi wor's");
         LOG.debug("import ended");
      }
   }
}
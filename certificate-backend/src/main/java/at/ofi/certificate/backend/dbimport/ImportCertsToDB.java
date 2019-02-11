package at.ofi.certificate.backend.dbimport;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ofi.exceltocertsdb.CertificateSheetType;

public class ImportCertsToDB {

   private static final Logger LOG = LoggerFactory.getLogger(ImportCertsToDB.class);

   private static final String MAPPING_XML = "ExcelToCertsDB.xml";

   public static void run(InputStream xlsInput) throws IOException, SQLException {

      CertificateSheetType mappings = getMapping();

      try (Workbook workbook = WorkbookFactory.create(xlsInput);
            Connection connection = getConnection()) {
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

   private static Connection getConnection() throws RuntimeException {
      try {
         Context initContext = new InitialContext();
         Context webContext = (Context) initContext.lookup("java:/comp/env");
         DataSource dataSource = (DataSource) webContext.lookup("jdbc/certificate");
         return dataSource.getConnection();
      } catch (Exception e) {
         throw new RuntimeException("Can not provide connection! " + e.getMessage(), e);
      }
   }

   private static CertificateSheetType getMapping() {
      try {
         InputStream systemResourceAsStream = ImportCertsToDB.class.getClassLoader().getResourceAsStream(MAPPING_XML);
         return MappingReader.read(systemResourceAsStream);
      } catch (Exception e) {
         LOG.error("Configuration error!", e);
         throw new RuntimeException("Configuration error!", e);
      }
   }
}

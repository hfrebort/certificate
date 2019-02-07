package at.ofi.certificate.backend.dbimport;

import at.ofi.exceltocertsdb.CertificateSheetType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public class ImportCertsToDB {

    private static Logger log = LogManager.getLogger(ImportCertsToDB.class);

    public static void run(String mappingXml, String certsXls, String JdbcConnectionUrl)
            throws IOException, SQLException {

        CertificateSheetType mappings =
                MappingReader.read(ClassLoader.getSystemResourceAsStream(mappingXml));

        try (InputStream    XlsInput = new FileInputStream(new File(certsXls));
             Workbook       wb       = WorkbookFactory.create(XlsInput);
             Connection     conn     = DriverManager.getConnection(JdbcConnectionUrl);)
        {
            log.debug("loading sheet [{}]", mappings.getSheetName());
            Sheet certSheet = wb.getSheet(mappings.getSheetName());

            log.debug("loading rows of sheet to stream");
            Stream<List<Object>> dataRowStream =
                    SheetReader.readSheetUntilEmptyRow(certSheet, mappings.getColumnMapping(), 1);

            log.debug("import started");
            InsertCertsToDB.run(dataRowStream, mappings.getColumnMapping(), conn, "da Spindi wor's");
            log.debug("import ended");
        }
    }
}

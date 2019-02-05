package at.ofi.certificate.backend;

import at.ofi.certificate.backend.reader.ExcelSheetReader;
import at.ofi.certificate.backend.reader.MappingSheetReader;
import at.ofi.exceltocertsdb.CertificateSheetType;
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

public class importCertsToDB {

    public static void run(String mappingXml, String certsXls, String JdbcConnectionUrl)
            throws IOException, SQLException {

        CertificateSheetType mappings =
                MappingSheetReader.read(ClassLoader.getSystemResourceAsStream(mappingXml)).getValue();

        try (InputStream    XlsInput = new FileInputStream(new File(certsXls));
             Workbook       wb       = WorkbookFactory.create(XlsInput);
             Connection     conn     = DriverManager.getConnection(JdbcConnectionUrl);)
        {
            Sheet certSheet = wb.getSheet(mappings.getSheetName());

            Stream<List<Object>> dataRowStream =
                    ExcelSheetReader.readRowsUntilEmpty(certSheet, mappings.getColumnMapping(), 1);

            insertCertsToDB.run(dataRowStream, mappings.getColumnMapping(), conn);
        }
    }
}

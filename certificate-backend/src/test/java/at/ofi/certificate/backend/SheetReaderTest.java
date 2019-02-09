package at.ofi.certificate.backend;

import at.ofi.certificate.backend.dbimport.MappingReader;
import at.ofi.certificate.backend.dbimport.SheetReader;
import at.ofi.exceltocertsdb.CertificateSheetType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.time.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class SheetReaderTest {
    @Test
    public void readSmallExcel() throws IOException {

        CertificateSheetType mappings = MappingReader.read(
            ClassLoader.getSystemResourceAsStream("1_zwei_3terJänner_mapping.xml"));

        try (InputStream xlsInput = new FileInputStream(new File("src/test/resources/1_zwei_3terJänner.xlsx"));
             Workbook wb = WorkbookFactory.create(xlsInput))
        {
            Sheet certSheet = wb.getSheet(mappings.getSheetName());

            List<List<Object>> data = SheetReader
                .readSheetUntilEmptyRow(certSheet, mappings.getColumnMapping(), 0)
                .collect(Collectors.toList());

            Assert.assertEquals(1, data.size());

            List<Object> row = data.get(0);
            Assert.assertTrue(row.get(0) instanceof Integer);
            Assert.assertEquals(Integer.valueOf(1), row.get(0));

            Assert.assertTrue(row.get(1) instanceof String);
            Assert.assertEquals("zwei", row.get(1));

            Assert.assertTrue(row.get(2) instanceof java.util.Date);
            java.util.Date d = (Date) row.get(2);

            LocalDateTime xlsDate = LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault());

            Assert.assertEquals(LocalDateTime.of(2019,1,3,0,0), xlsDate);
        }
    }
    @Test
    public void readAllColumnsAsStrings() throws IOException {

        CertificateSheetType mappings = MappingReader.read(
            ClassLoader.getSystemResourceAsStream("1_zwei_3terJänner_mapping_allAsStrings.xml"));

        try (InputStream xlsInput = new FileInputStream(new File("src/test/resources/1_zwei_3terJänner.xlsx"));
             Workbook wb = WorkbookFactory.create(xlsInput))
        {
            Sheet certSheet = wb.getSheet(mappings.getSheetName());

            Iterator<List<Object>> data = SheetReader
                .readSheetUntilEmptyRow(certSheet, mappings.getColumnMapping(), 0)
                .iterator();

            Assert.assertTrue( data.hasNext() );

            List<Object> row = data.next();
            Assert.assertTrue(row.get(0) instanceof String);
            Assert.assertEquals("1", row.get(0));

            Assert.assertTrue(row.get(1) instanceof String);
            Assert.assertEquals("zwei", row.get(1));

            Assert.assertTrue(row.get(2) instanceof String);
            Assert.assertEquals("2019.01.03", row.get(2));
        }
    }
}

package at.ofi.certificate.backend.reader;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import at.ofi.certificate.backend.dbimport.MappingReader;
import at.ofi.exceltocertsdb.CertificateSheetType;

public class MappingSheetReaderTest {

   private CertificateSheetType mapping;

   @Test
   public void testReadXmlMapping() throws Exception {
      this.whenRead();
      this.thenObjetcsAreNotNull();
      this.thenSheetNameIs("Auditprogramm - Gültige Zert");
      this.thenColumnsMappingsSizeIs(35);
   }

   private void thenColumnsMappingsSizeIs(int expectedSize) {
      Assert.assertNotNull("ColumnMappings are empty", this.mapping.getColumnMapping());
      Assert.assertEquals(expectedSize, this.mapping.getColumnMapping().size());
   }

   private void thenSheetNameIs(String expected) {
      Assert.assertEquals(expected, this.mapping.getSheetName());
   }

   private void thenObjetcsAreNotNull() {
      Assert.assertNotNull("The certificate sheet is empty", this.mapping);
   }

   private void whenRead() {
      InputStream inputStream = ClassLoader.getSystemResourceAsStream("ExcelToCertsDB.xml");
      this.mapping = MappingReader.read(inputStream);
   }

}

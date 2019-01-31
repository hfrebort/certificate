package at.ofi.certificate.backend.reader;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import at.ofi.exceltocertsdb.CertificateSheetType;

public class MappingSheetReaderTest {

	private MappingSheetReader reader;
	private CertificateSheetType sheet;

	@Test
	public void testReadXmlMapping() throws Exception {
		this.givenAnInstance();
		this.whenRead();
		this.thenObjetcsAreNotNull();
		this.thenSheetNameIs("Auditprogramm - G�ltige Zert");
		this.thenColumnsMappingsSizeIs(35);
	}

	private void thenColumnsMappingsSizeIs(int expectedSize) {
		Assert.assertNotNull("ColumnMappings are empty", this.sheet.getColumnMapping());
		Assert.assertEquals(expectedSize, this.sheet.getColumnMapping().size());
	}

	private void thenSheetNameIs(String expected) {
		Assert.assertEquals(expected, this.sheet.getSheetName());
	}

	private void thenObjetcsAreNotNull() {
		Assert.assertNotNull("The certificate sheet is empty", this.sheet);
	}

	private void whenRead() {
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("ExcelToCertsDB.xml");
		this.sheet = this.reader.readSheet(inputStream).getValue();
	}

	private void givenAnInstance() {
		this.reader = new MappingSheetReader();

	}

}
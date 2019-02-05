package at.ofi.certificate.backend;

import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author ce-harald.frebort
 */
public class TestExcelImport {

	private static final String FILE_NAME = "c:\\temp\\Auditprogramm Muster.xlsx";

	/*
	@Test
	public void readSheet() throws Exception {
		File file = new File(FILE_NAME);

		try (Workbook wb = WorkbookFactory.create(file)) {
			Sheet sheet = wb.getSheet("Auditprogramm - Gültige Zert");
			List<String> data = at.ofi.certificate.backend.reader.ExcelSheetReader.readCells(sheet);
			assertTrue(data.size() > 0);
		} catch (Exception e) {
			Assert.fail("Can not load workbook");
		}
	}

	@Test
	public void writeSheetToFile() throws Exception {
		try (Workbook wb = WorkbookFactory.create(new File(FILE_NAME));
				PrintWriter writer = getUTF8writer("c:\\temp\\JavaXls\\out1.UTF8.txt")) {
			Sheet sheet = wb.getSheet("Auditprogramm - Gültige Zert");
			at.ofi.certificate.backend.reader.ExcelSheetReader.readCells(sheet).forEach(row -> {
				// writer.println(String.join(";", row));
				writer.printf("%d|%s\n", row, String.join(";", row));
			});
		}
	}

	private static PrintWriter getUTF8writer(String filename) throws FileNotFoundException {
		return new PrintWriter(
				new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8)));

	}
	*/
}

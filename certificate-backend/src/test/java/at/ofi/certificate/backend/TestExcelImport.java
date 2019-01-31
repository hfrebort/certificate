package at.ofi.certificate.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Stream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;


/**
 * @author ce-harald.frebort
 */
public class TestExcelImport {

    @Test
    public void readSheet() throws Exception {
    	
    	try (Workbook wb = WorkbookFactory.create(new File ("c:\\temp\\Auditprogramm Muster.xlsx"))) {
    		Sheet sheet = wb.getSheet("Auditprogramm - Gültige Zert");
    		Stream<List<String>> data =  at.ofi.certificate.backend.excel.readsheet(sheet);
    		assertTrue( data.count() > 0 );
    	}
    }
    
    @Test
    public void writeSheetToFile() throws Exception {
    	
    	try (Workbook wb = WorkbookFactory.create(new File ("c:\\temp\\Auditprogramm Muster.xlsx"));
 	    	 PrintWriter writer = getUTF8writer("c:\\temp\\JavaXls\\out1.UTF8.txt")) 
    	{
    		Sheet sheet = wb.getSheet("Auditprogramm - Gültige Zert");		
    		at.ofi.certificate.backend.excel.readsheet(sheet)
	    	.forEach(row -> {
	    		//writer.println(String.join(";", row));
	    		writer.printf("%d|%s\n", row.size(), String.join(";", row));
	    	});
    	}
    }
    
    private static PrintWriter getUTF8writer(String filename) throws FileNotFoundException
    {
    	return 
			new PrintWriter(
   				new BufferedWriter(
   					new OutputStreamWriter(
						new FileOutputStream(filename), 
						StandardCharsets.UTF_8)));
	    		
    }
}

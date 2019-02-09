package at.ofi.certificate.backend.dbimport;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.transform.stream.StreamSource;

import at.ofi.exceltocertsdb.CertificateSheetType;

public class MappingReader {

   public static CertificateSheetType read(InputStream inputStream) {
      try {
         return JAXBContext.newInstance("at.ofi.exceltocertsdb")
               .createUnmarshaller()
               .unmarshal(new StreamSource(inputStream), CertificateSheetType.class)
               .getValue();
      } catch (final Exception e) {
         throw new RuntimeException("Can not read definition file!");
      }
   }

}

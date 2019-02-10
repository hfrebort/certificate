/************************************************************************
 ** COPYRIGHT: Harald Frebort
 **            Pottendorferstrasse 14
 **            A-2483 Weigelsdorf
 **            AUSTRIA
 ** LANGUAGE:  Java, J2SE JDK 1.8
 ************************************************************************/
package at.ofi.certificate.backend;

import static at.ofi.certificate.backend.api.VerificationResult.builder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.ofi.certificate.backend.api.CertificateService;
import at.ofi.certificate.backend.api.VerificationResult;
import at.ofi.certificate.backend.api.VerificationResult.Builder;
import at.ofi.certificate.backend.api.VerificationResult.Status;
import at.ofi.certificate.backend.dbimport.ImportCertsToDB;

/**
 * @author HFrebort
 * @version Feb 10, 2019
 */
public class DefaultCertificateService implements CertificateService {

   private static final Logger LOG = LogManager.getLogger(DefaultCertificateService.class);

   private static final String JDBC_CONNECTION_URL = "jdbc:mysql://5.175.13.247/excelcerts?user=certificate_user&password=%Of92ym5";

   @Override
   public VerificationResult insert(InputStream inputStream) {
      Builder result = builder();
      try {
         ImportCertsToDB.run(inputStream, JDBC_CONNECTION_URL);
      } catch (IOException | SQLException e) {
         result
               .status(Status.ERROR)
               .message("Unable to insert into DB: " + e.getMessage());
         LOG.error("Unable to insert into DB", e);
      }
      return result.build();
   }

   @Override
   public VerificationResult verify(InputStream inputStream) {
      return builder().build();
   }

}

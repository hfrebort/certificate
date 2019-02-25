/************************************************************************
 ** COPYRIGHT: Harald Frebort
 **            Pottendorferstrasse 14
 **            A-2483 Weigelsdorf
 **            AUSTRIA
 ** LANGUAGE:  Java, J2SE JDK 1.8
 ************************************************************************/
package at.ofi.certificate.backend;

import static at.ofi.certificate.backend.api.VerificationResult.builder;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

   private static final Logger LOG = LoggerFactory.getLogger(DefaultCertificateService.class);

   @Override
   public VerificationResult insert(InputStream inputStream) {
      Builder result = builder();
      try {
         ImportCertsToDB.run(inputStream);
      } catch (Exception e) {
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

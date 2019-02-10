/************************************************************************
 ** COPYRIGHT: Harald Frebort
 **            Pottendorferstrasse 14
 **            A-2483 Weigelsdorf
 **            AUSTRIA
 ** LANGUAGE:  Java, J2SE JDK 1.8
 ************************************************************************/
package at.ofi.certificate.backend.api;

import java.io.InputStream;

/**
 * This is the interface definition of the certification service. Use verify for
 * upload verification. Use insert for upload provision.
 * 
 * @author HFrebort
 * @version Feb 9, 2019
 */
public interface CertificateService {

   /**
    * Verify if the given input stream
    * 
    * @return the verification result
    */
   VerificationResult verify(InputStream inputStream);

   /**
    * Insert the input stream into the database
    * 
    * @param inputStream the input stream of the upload
    * @return the verification result
    */
   VerificationResult insert(InputStream inputStream);
}

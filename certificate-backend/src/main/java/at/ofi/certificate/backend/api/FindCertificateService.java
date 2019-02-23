/************************************************************************
 ** COPYRIGHT: Harald Frebort
 **            Pottendorferstrasse 14
 **            A-2483 Weigelsdorf
 **            AUSTRIA
 ** LANGUAGE:  Java, J2SE JDK 1.8
 ************************************************************************/
package at.ofi.certificate.backend.api;

import java.util.List;

/**
 * This is the interface definition to get certificates.
 * 
 * @author HFrebort
 * @version Feb 11, 2019
 */
public interface FindCertificateService {

   /**
    * Find the current valid certificate entries.
    * 
    * @return list of certificate entries
    */
   List<CertificateEntry> findEntries();

   /**
    * Find certificate entries by the given version id
    * 
    * @param versionId the version id to find entries
    * @return list of certificate entries
    */
   List<CertificateEntry> findEntriesByVersionId(String versionId);
}

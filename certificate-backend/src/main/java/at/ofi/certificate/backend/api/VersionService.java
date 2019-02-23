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
 * This is the interface to get versions
 * 
 * @author HFrebort
 * @version Feb 11, 2019
 */
public interface VersionService {

   /**
    * @return list of all current versions
    */
   List<Version> getVersions();
}

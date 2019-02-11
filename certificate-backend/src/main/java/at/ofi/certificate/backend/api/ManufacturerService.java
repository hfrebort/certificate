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
 * This is the interface definition of the manufacturer service.
 * 
 * @author HFrebort
 * @version Feb 11, 2019
 */
public interface ManufacturerService {

   /**
    * Get a distinct list of manufacturer of the current valid version.
    * 
    * @return list of manufacturer
    */
   List<String> getManufacturer();
}

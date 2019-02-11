/************************************************************************
 ** COPYRIGHT: Harald Frebort
 **            Pottendorferstrasse 14
 **            A-2483 Weigelsdorf
 **            AUSTRIA
 ** LANGUAGE:  Java, J2SE JDK 1.8
 ************************************************************************/
package at.ofi.certificate.backend;

import java.util.Collections;
import java.util.List;

import at.ofi.certificate.backend.api.ManufacturerService;

/**
 * This is the default implementation of the {@link ManufacturerService}.
 * 
 * @author HFrebort
 * @version Feb 11, 2019
 */
public class DefaultManufacturer implements ManufacturerService {

   @Override
   public List<String> getManufacturer() {
      return Collections.emptyList();
   }

}

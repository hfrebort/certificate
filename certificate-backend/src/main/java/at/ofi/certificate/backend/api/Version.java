/************************************************************************
 ** COPYRIGHT: Harald Frebort
 **            Pottendorferstrasse 14
 **            A-2483 Weigelsdorf
 **            AUSTRIA
 ** LANGUAGE:  Java, J2SE JDK 1.8
 ************************************************************************/
package at.ofi.certificate.backend.api;

import java.util.Date;

/**
 * @author HFrebort
 * @version Feb 23, 2019
 */
public class Version {

   private Date imported;

   private String importer;

   /**
    * @param imported
    * @param importer
    */
   public Version(Date imported, String importer) {
      this.imported = imported;
      this.importer = importer;
   }

   /**
    * @return the imported
    */
   public Date getImported() {
      return imported;
   }

   /**
    * @return the importer
    */
   public String getImporter() {
      return importer;
   }

}

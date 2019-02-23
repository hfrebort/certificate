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

   private String versionId;

   private Date imported;

   private String importer;

   /**
    * @param imported
    * @param importer
    */
   public Version(final String versionId, Date imported, String importer) {
      this.versionId = versionId;
      this.imported = imported;
      this.importer = importer;
   }

   /**
    * @return the versionId
    */
   public String getVersionId() {
      return versionId;
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

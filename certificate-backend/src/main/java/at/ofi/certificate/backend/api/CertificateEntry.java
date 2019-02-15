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
 * @author HFrebort
 * @version Feb 14, 2019
 */
public class CertificateEntry {

   private String manufacturer;

   private String auditMonthLatest;

   private String auditor;

   private List<String> norms;

   /**
    * @param manufacturer
    * @param auditMonthLatest
    * @param auditor
    * @param norms
    */
   public CertificateEntry(String manufacturer, String auditMonthLatest, String auditor, List<String> norms) {
      this.manufacturer = manufacturer;
      this.auditMonthLatest = auditMonthLatest;
      this.auditor = auditor;
      this.norms = norms;
   }

   /**
    * @return the manufacturer
    */
   public String getManufacturer() {
      return manufacturer;
   }

   /**
    * @return the auditMonthLatest
    */
   public String getAuditMonthLatest() {
      return auditMonthLatest;
   }

   /**
    * @return the auditor
    */
   public String getAuditor() {
      return auditor;
   }

   /**
    * @return the norms
    */
   public List<String> getNorms() {
      return norms;
   }

}

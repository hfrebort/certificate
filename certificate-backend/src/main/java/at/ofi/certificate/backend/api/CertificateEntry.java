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
 * The certificate entry
 * 
 * @author HFrebort
 * @version Feb 14, 2019
 */
public final class CertificateEntry {

   private String certNumber;

   private String manufacturer;

   private String manufacturerContact;

   private String manufacturerLocation;

   private String productDescription;

   private String auditMonthLatest;

   private String auditor;

   private String industry;

   private List<String> norms;

   private CertificateEntry(Builder bulder) {
      this.certNumber = bulder.certNumber;
      this.manufacturer = bulder.manufacturer;
      this.manufacturerContact = bulder.manufacturerContact;
      this.manufacturerLocation = bulder.manufacturerLocation;
      this.productDescription = bulder.productDescription;
      this.auditMonthLatest = bulder.auditMonthLatest;
      this.auditor = bulder.auditor;
      this.industry = bulder.industry;
      this.norms = bulder.norms;
   }

   /**
    * @return the certNumber
    */
   public String getCertNumber() {
      return certNumber;
   }

   /**
    * @return the manufacturer
    */
   public String getManufacturer() {
      return manufacturer;
   }

   /**
    * @return the manufacturerContact
    */
   public String getManufacturerContact() {
      return manufacturerContact;
   }

   /**
    * @return the manufacturerLocation
    */
   public String getManufacturerLocation() {
      return manufacturerLocation;
   }

   /**
    * @return the productDescription
    */
   public String getProductDescription() {
      return productDescription;
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
    * @return the industry
    */
   public String getIndustry() {
      return industry;
   }

   /**
    * @return the norms
    */
   public List<String> getNorms() {
      return norms;
   }

   public static Builder builder() {
      return new Builder();
   }

   public static class Builder {

      private String certNumber;

      private String manufacturer;

      private String manufacturerContact;

      private String manufacturerLocation;

      private String productDescription;

      private String auditMonthLatest;

      private String auditor;

      private String industry;

      private List<String> norms;

      public Builder certNumber(final String certNumber) {
         this.certNumber = certNumber;
         return this;
      }

      public Builder manufacturer(final String manufacturer) {
         this.manufacturer = manufacturer;
         return this;
      }

      public Builder manufacturerContact(final String manufacturerContact) {
         this.manufacturerContact = manufacturerContact;
         return this;
      }

      public Builder manufacturerLocation(final String manufacturerLocation) {
         this.manufacturerLocation = manufacturerLocation;
         return this;
      }

      public Builder productDescription(final String productDescription) {
         this.productDescription = productDescription;
         return this;
      }

      public Builder auditMonthLatest(final String auditMonthLatest) {
         this.auditMonthLatest = auditMonthLatest;
         return this;
      }

      public Builder auditor(final String auditor) {
         this.auditor = auditor;
         return this;
      }

      public Builder industry(final String industry) {
         this.industry = industry;
         return this;
      }

      public Builder norms(final List<String> norms) {
         this.norms = norms;
         return this;
      }

      public CertificateEntry build() {
         return new CertificateEntry(this);
      }
   }
}

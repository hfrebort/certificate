/************************************************************************
 ** COPYRIGHT: Harald Frebort
 **            Pottendorferstrasse 14
 **            A-2483 Weigelsdorf
 **            AUSTRIA
 ** LANGUAGE:  Java, J2SE JDK 1.8
 ************************************************************************/
package at.ofi.certificate.backend.api;

/**
 * This is the verification result.
 * 
 * @author HFrebort
 * @version Feb 9, 2019
 */
public class VerificationResult {

   public enum Status {
      OK, ERROR
   }

   private Status status;

   private String message;

   private VerificationResult(Status status, String message) {
      this.status = status;
      this.message = message;
   }

   /**
    * @return the status
    */
   public Status getStatus() {
      return status;
   }

   /**
    * @return the message
    */
   public String getMessage() {
      return message;
   }

   /**
    * @return the builder
    */
   public static Builder builder() {
      return new Builder();
   }

   public static class Builder {

      private Status status;

      private String message;

      public Builder() {
         status = Status.OK;
      }

      public Builder status(Status status) {
         this.status = status;
         return this;
      }

      public Builder message(String message) {
         this.message = message;
         return this;
      }

      public VerificationResult build() {
         return new VerificationResult(status, message);
      }
   }
}

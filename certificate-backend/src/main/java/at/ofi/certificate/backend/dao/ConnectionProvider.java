/************************************************************************
 ** COPYRIGHT: Harald Frebort
 **            Pottendorferstrasse 14
 **            A-2483 Weigelsdorf
 **            AUSTRIA
 ** LANGUAGE:  Java, J2SE JDK 1.8
 ************************************************************************/
package at.ofi.certificate.backend.dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * @author HFrebort
 * @version Feb 15, 2019
 */
public class ConnectionProvider {

   private DataSource dataSource;

   /**
    * Get the connection
    * 
    * @return the connection
    */
   public Connection getConnection() {
      lazyInitialize();
      try {
         return dataSource.getConnection();
      } catch (Exception e) {
         throw new RuntimeException("Can not provide connection! " + e.getMessage(), e);
      }
   }

   private void lazyInitialize() {
      try {
         Context initContext = new InitialContext();
         Context webContext = (Context) initContext.lookup("java:/comp/env");
         dataSource = (DataSource) webContext.lookup("jdbc/certificate");
      } catch (Exception e) {
         throw new RuntimeException("Can not provide data source! " + e.getMessage(), e);
      }
   }

}

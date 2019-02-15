/************************************************************************
 ** COPYRIGHT: Harald Frebort
 **            Pottendorferstrasse 14
 **            A-2483 Weigelsdorf
 **            AUSTRIA
 ** LANGUAGE:  Java, J2SE JDK 1.8
 ************************************************************************/
package at.ofi.certificate.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.ofi.certificate.backend.api.CertificateEntry;
import at.ofi.certificate.backend.api.FindCertificateService;
import at.ofi.certificate.backend.dao.ConnectionProvider;

/**
 * @author HFrebort
 * @version Feb 14, 2019
 */
public class DefaultFindCertificateService implements FindCertificateService {

   private static final String SELECT_STATEMENT = "select * from cert where fkVersionId in ( select max(id) from version)";

   ConnectionProvider provider = new ConnectionProvider();

   @Override
   public List<CertificateEntry> findEntries() {
      List<CertificateEntry> result = new ArrayList<>();
      Connection connection = provider.getConnection();
      try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATEMENT)) {
         ResultSet resultSet = preparedStatement.executeQuery();
         while (resultSet.next()) {
            result.add(new CertificateEntry(
                  resultSet.getString("manufacturer"),
                  resultSet.getString("auditMonthLatest"),
                  resultSet.getString("auditor"),
                  Arrays.asList(
                        resultSet.getString("n1"),
                        resultSet.getString("n2"),
                        resultSet.getString("n3"),
                        resultSet.getString("n4"),
                        resultSet.getString("n5"),
                        resultSet.getString("n6"),
                        resultSet.getString("n7"),
                        resultSet.getString("n8"),
                        resultSet.getString("n9"),
                        resultSet.getString("n10"))));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return result;
   }

}

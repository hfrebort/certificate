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
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ofi.certificate.backend.api.Version;
import at.ofi.certificate.backend.api.VersionService;
import at.ofi.certificate.backend.dao.ConnectionProvider;

/**
 * @author HFrebort
 * @version Feb 23, 2019
 */
public class DefaultVersionService implements VersionService {

   private static final Logger LOG = LoggerFactory.getLogger(DefaultCertificateService.class);

   private static final String SELECT_STATEMENT = "select * from version";

   private ConnectionProvider provider = new ConnectionProvider();

   @Override
   public List<Version> getVersions() {
      final List<Version> versions = new ArrayList<>();
      final Connection connection = provider.getConnection();
      try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATEMENT)) {
         final ResultSet resultSet = preparedStatement.executeQuery();
         while (resultSet.next()) {
            versions.add(new Version(resultSet.getDate("imported"), resultSet.getString("importedByName")));
         }
      } catch (SQLException e) {
         LOG.error("Unable to get versions!", e);
      }
      return versions;
   }

}

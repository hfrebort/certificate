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
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ofi.certificate.backend.api.CertificateEntry;
import at.ofi.certificate.backend.api.FindCertificateService;
import at.ofi.certificate.backend.dao.ConnectionProvider;

/**
 * The service to load all current valid certificate entries.
 * 
 * @author HFrebort
 * @version Feb 14, 2019
 */
public class DefaultFindCertificateService implements FindCertificateService {

   private static final Logger LOG = LoggerFactory.getLogger(DefaultFindCertificateService.class);

   private static final String SELECT_STATEMENT = "select * from cert where fkVersionId in ( select max(id) from version)";

   private static final String[] NORMS = {"n1", "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9", "n10"};

   ConnectionProvider provider = new ConnectionProvider();

   @Override
   public List<CertificateEntry> findEntries() {
      List<CertificateEntry> result = new ArrayList<>();
      Connection connection = provider.getConnection();
      try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATEMENT)) {
         final ResultSet resultSet = preparedStatement.executeQuery();
         while (resultSet.next()) {
            result.add(CertificateEntry.builder()
                  .manufacturer(resultSet.getString("manufacturer"))
                  .manufacturerContact(resultSet.getString("manufacturerContact"))
                  .manufacturerLocation(resultSet.getString("manufacturerLocation"))
                  .productDescription(resultSet.getString("productDescription"))
                  .auditMonthLatest(resultSet.getString("auditMonthLatest"))
                  .auditor(resultSet.getString("auditor"))
                  .industry(resultSet.getString("industry"))
                  .norms(getNorms((k) -> getNorm(resultSet, k)))
                  .build());
         }
      } catch (SQLException e) {
         LOG.error("Error while find entries!", e);
      }
      return result;
   }

   private List<String> getNorms(final Function<String, String> getNormFunction) {
      return Stream.of(NORMS)
            .map(getNormFunction::apply)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
   }

   private String getNorm(ResultSet resultSet, String columnName) {
      try {
         return resultSet.getString(columnName);
      } catch (SQLException e) {
         LOG.error("Error while get norm column!", e);
      }
      return null;
   }
}

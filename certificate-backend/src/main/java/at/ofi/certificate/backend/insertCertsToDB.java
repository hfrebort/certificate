package at.ofi.certificate.backend;

import at.ofi.exceltocertsdb.ColumnMappingType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class insertCertsToDB {

    public static void run(Stream<List<Object>> data, List<ColumnMappingType> columnMapping, Connection conn)
        throws SQLException {

        final String insertSql =
                  "insert into cert (fkVersionId,"
                + String.join(",", columnMapping.stream()
                        .map( c -> c.getDatabaseColumn()).collect(Collectors.toList()))
                + ") values ("
                + "?," + String.join(",", columnMapping.stream().map( c -> "?").collect(Collectors.toList()))
                + ")";

        try (PreparedStatement insert = conn.prepareStatement(insertSql)) {

            for ( List<Object> row : (Iterable<List<Object>>) data::iterator) {

                insert.clearParameters();
                insert.setInt(1, 1);

                for (int i=0; i < row.size(); ++i) {
                    Object value = row.get(i);
                    int paramIdx = i + 2;

                    if ( value == null) {
                        insert.setNull(paramIdx, getJavaSqlType(columnMapping.get(i).getType()));
                    }
                    if ( value instanceof String ) {
                       insert.setString(paramIdx, (String)value);
                    }
                    else if (value instanceof Integer) {
                        insert.setInt(paramIdx, ((Integer) value).intValue());
                    }
                    else if (value instanceof Date) {
                        java.util.Date d = (Date)value;
                        insert.setDate(paramIdx, new java.sql.Date(d.getTime()));
                    }
                }

                insert.executeUpdate();

            }
        }
    }
    private static int getJavaSqlType(String excelColumType)
    {
        if ( "char".equals(excelColumType)) {
            return Types.VARCHAR;
        }
        else if ( "int".equals(excelColumType)) {
            return Types.INTEGER;
        }
        else if ( "date".equals(excelColumType)) {
            return Types.DATE;
        }

        return Types.VARCHAR;
    }
}

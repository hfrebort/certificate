package at.ofi.certificate.backend.dbimport;

import at.ofi.exceltocertsdb.ColumnMappingType;

import java.sql.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InsertCertsToDB {

    public static void run(Stream<List<Object>> data, List<ColumnMappingType> columnMapping, Connection conn, String nameOfImporter)
        throws SQLException {

        final String insertSql = getSqlForCertInsert(
                "fkVersionId",
                columnMapping.stream().map( c -> c.getDatabaseColumn()).collect(Collectors.toList()));

        try {

            conn.setAutoCommit(false);

            int fkVersionId = getNextVersionId(conn, nameOfImporter);

            try (PreparedStatement insert = conn.prepareStatement(insertSql)) {

                for (final List<Object> row : (Iterable<List<Object>>) data::iterator) {
                    insert.clearParameters();
                    fillStatement(row, columnMapping, fkVersionId, insert);
                    insert.addBatch();
                }

                int[] rowInserted = insert.executeBatch();
                int insertCount = Arrays.stream(rowInserted).sum();
            }

            conn.commit();
        }
        catch (SQLException sex) {
            conn.rollback();
            throw sex;
        }
    }

    private static int getNextVersionId(Connection conn, String importername) throws SQLException {
        String SqlInsertNewVersion = "insert into version (imported, importedByName) values (NOW(),?)";

        int newVersionId = -1;

        try (PreparedStatement newVersionStmt = conn.prepareStatement(
                SqlInsertNewVersion, Statement.RETURN_GENERATED_KEYS)) {
            if ( isNullOrEmpty(importername) ) {
                newVersionStmt.setNull(1, Types.VARCHAR);
            }
            else {
                newVersionStmt.setString(1, importername);
            }

            newVersionStmt.executeUpdate();

            try (ResultSet rs = newVersionStmt.getGeneratedKeys()) {
                if (rs.next()) {
                    newVersionId = rs.getInt(1);
                }
            }
        }

        return newVersionId;
    }

    private static void fillStatement(List<Object> row, List<ColumnMappingType> columnMapping, int fkVersionId, PreparedStatement insert)
            throws SQLException {


        insert.setInt(1, fkVersionId);

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
                Date d = (Date)value;
                insert.setDate(paramIdx, new java.sql.Date(d.getTime()));
            }
        }
    }

    private static String getSqlForCertInsert(String fkVersionIdColumnname, List<String> columnNames) {

        return
          "insert into cert (" + fkVersionIdColumnname + ","
          + String.join(",", columnNames)
          + ") values ("
          + "?," // one question mark for fkVersionId
          + String.join(",", columnNames.stream().map( c -> "?").collect(Collectors.toList()))
          + ")";
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
    private static boolean isNullOrEmpty(final String str) {
        return str == null || str.length() == 0;
    }
}

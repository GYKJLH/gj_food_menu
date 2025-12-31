package org.example.utils;

import java.sql.*;

public class DbTransfer {

    static String SRC_URL = "jdbc:mysql://127.0.0.1:3306/schema?useSSL=false";
    static String SRC_USER = "root";
    static String SRC_PWD = "123456";

    static String DEST_URL = "jdbc:mysql://127.0.0.1:3306/schema?useSSL=false";
    static String DEST_USER = "root";
    static String DEST_PWD = "123456";

    public static void main(String[] args) throws Exception {
        transferTable("table_name   ");
        transferTable("table_name2");
    }

    private static void transferTable(String tableName) throws Exception {
        try (
                Connection srcConn = DriverManager.getConnection(SRC_URL, SRC_USER, SRC_PWD);
                Connection destConn = DriverManager.getConnection(DEST_URL, DEST_USER, DEST_PWD);
                Statement stmt = srcConn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from " + tableName)
        ) {
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();

            StringBuilder sql = new StringBuilder("insert into " + tableName + " values (");
            for (int i = 0; i < colCount; i++) {
                sql.append("?,");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");

            destConn.setAutoCommit(false);

            int batchSize = 1000;
            int total = 0;
            long start = System.currentTimeMillis();

            try (PreparedStatement ps = destConn.prepareStatement(sql.toString())) {
                while (rs.next()) {
                    for (int i = 1; i <= colCount; i++) {
                        ps.setObject(i, rs.getObject(i));
                    }
                    ps.addBatch();
                    total++;

                    if (total % batchSize == 0) {
                        ps.executeBatch();
                        ps.clearBatch();
                        destConn.commit();

                        System.out.println("[" + tableName + "] 已迁移：" + total + " 条");
                    }
                }

                ps.executeBatch();
                destConn.commit();
            }

            System.out.println(
                    "[" + tableName + "] 迁移完成，总数：" + total +
                            "，耗时：" + (System.currentTimeMillis() - start) + " ms"
            );
        }
    }

}


package it.coderit;

import org.apache.commons.io.IOUtils;
import org.hsqldb.Server;
import org.junit.jupiter.api.*;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SQLTest {

    private static Server server;

    @BeforeAll
    static void startUpDb() throws Exception {
        server = new Server();
        server.setDatabaseName(0, "mainDb");
        server.setDatabasePath(0, "mem:mainDb");
        server.setAddress("0.0.0.0");
        server.setSilent(true);
        server.setLogWriter(null);
        server.start();
        System.out.println("JDBC Url:" + jdbcUrl());
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        try (Connection c = DriverManager.getConnection(jdbcUrl(), "SA", "")) {
            final var statement = c.createStatement();
            statement.executeUpdate(IOUtils.resourceToString("/tables.sql", StandardCharsets.UTF_8));
            statement.executeUpdate(IOUtils.resourceToString("/data.sql", StandardCharsets.UTF_8));
        }
    }

    @AfterAll
    static void shutdownSql() {
        server.stop();
        while (!server.isNotRunning()) {
            System.out.println("Waiting db to be stopped");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }

    private static String jdbcUrl() {
        return "jdbc:hsqldb:hsql://" +
                server.getAddress() +
                ":" +
                server.getPort() +
                "/" +
                server.getDatabaseName(0, true);
    }

    @Test
    @Order(1)
    void setUpData() throws Exception {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        try (Connection c = DriverManager.getConnection(jdbcUrl(), "SA", "")) {
        }
    }

    @Test
    @Order(2)
    void countAllEmployees() throws SQLException {
        final var sql = """
                select ...
                """;
        assertEquals(5, singleResult(sql, Integer.class));
    }

    @Test
    @Order(3)
    void countEmployeesOfProduction() throws SQLException {
        final var sql = """
        select ...
        """;
        assertEquals(2, singleResult(sql, Integer.class));
    }

    @Test
    @Order(4)
    void topSalary() throws SQLException {
        final var sql = """
        select ...
        """;
        assertEquals(3000, singleResult(sql, Integer.class));
    }

    @Test
    @Order(5)
    void fullNameOfEmployeeWithLowestSalary() throws SQLException {
        // These piece of doc can be useful:
        // http://www.hsqldb.org/doc/1.8/guide/ch09.html#select-section
        final var sql = """
        select ...
        """;
        assertEquals("Davide Dominici", singleResult(sql, String.class));
    }

    private static Object singleResult(String sql, Class desiredType) throws SQLException {
        Object result = null;
        try (Connection c = DriverManager.getConnection(jdbcUrl(), "SA", "")) {
            final var resultSet = c.createStatement().executeQuery(sql);
            assertEquals(1, resultSet.getMetaData().getColumnCount(), "1 column was expected");
            if(!resultSet.next()){
                return null;
            }
            if(desiredType == Integer.class) {
                result = resultSet.getInt(1);
            }else if(desiredType == String.class) {
                result = resultSet.getString(1);
            }else{
                throw new IllegalArgumentException(desiredType + " not supported");
            }
        }
        return result;
    }


}

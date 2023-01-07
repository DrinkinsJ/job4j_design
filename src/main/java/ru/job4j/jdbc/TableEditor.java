package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private final Properties properties;
    private Connection connection;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    public static void main(String[] args) throws Exception {

        try (TableEditor tableEditor = new TableEditor(getProperties())) {
            tableEditor.createTable("students");
            System.out.println(getTableScheme(tableEditor.connection, "students"));
            tableEditor.addColumn("students", "firstName", "varchar(255)");
            System.out.println(getTableScheme(tableEditor.connection, "students"));
            tableEditor.renameColumn("students", "firstName", "lastName");
            System.out.println(getTableScheme(tableEditor.connection, "students"));
            tableEditor.dropColumn("students", "lastName");
            System.out.println(getTableScheme(tableEditor.connection, "students"));
            tableEditor.dropTable("students");
        }
    }

    private static Properties getProperties() throws Exception {
        Properties prop = new Properties();
        try (InputStream is = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            prop.load(is);
        }
        return prop;
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("db");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "create table if not exists %s(%s);",
                tableName,
                "id serial primary key");
        createStatement(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format(
                "drop table %s",
                tableName);
        createStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "alter table %s add %s %s",
                tableName,
                columnName,
                type);
        createStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "alter table %s drop column %s",
                tableName,
                columnName);
        createStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "alter table %s rename column %s to %s",
                tableName,
                columnName,
                newColumnName);
        createStatement(sql);
    }

    private void createStatement(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
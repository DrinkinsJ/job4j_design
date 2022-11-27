package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {


    private static Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        try (InputStream is = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            prop.load(is);
        }

        TableEditor tableEditor = new TableEditor(prop);
        tableEditor.createTable("students");
        System.out.println(getTableScheme("students"));
        tableEditor.addColumn("students", "course", "integer");
        System.out.println(getTableScheme("students"));
        tableEditor.dropColumn("students", "name");
        System.out.println(getTableScheme("students"));
        tableEditor.renameColumn("students", "course", "age");
        System.out.println(getTableScheme("students"));
        tableEditor.dropTable("students");
    }

    public static String getTableScheme(String tableName) throws Exception {
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
        connection = getConnection();
    }

    private Connection getConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        String url = "jdbc:postgresql://localhost:5432/" + properties.getProperty("db");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "create table if not exists %s(%s, %s);",
                        tableName,
                        "id serial primary key",
                        "name text"
                );
                statement.execute(sql);
            }
        }
    }

    public void dropTable(String tableName) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "drop table %s",
                        tableName
                );
                statement.execute(sql);
            }
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "alter table %s add column %s %s",
                        tableName,
                        columnName,
                        type
                );
                statement.execute(sql);
            }
        }
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "alter table %s drop column %s",
                        tableName,
                        columnName
                );
                statement.execute(sql);
            }
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "alter table %s rename column %s to %s",
                        tableName,
                        columnName,
                        newColumnName
                );
                statement.execute(sql);
            }
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
package ru.job4j.tracker.store;

import ru.job4j.tracker.model.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {

    private Connection cn;

    public SqlTracker() {
        init();
    }

    private void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws SQLException {
        if (cn != null) {
            cn.close();
        }
    }

    private Timestamp setDateTime() {
        long millis = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(millis);
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        return Timestamp.valueOf(localDateTime);
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "insert into items (name, created) values (?, ?);")) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setTimestamp(2, setDateTime());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean replaced = false;
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "update items set name = ?, created = ? where id = ?;")) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setTimestamp(2, setDateTime());
            preparedStatement.setInt(3, id);
            replaced = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return replaced;
    }

    @Override
    public boolean delete(int id) {
        boolean deleted = false;
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "delete from items where id = ?")) {
            preparedStatement.setInt(1, id);
            deleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "select * from items")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getTimestamp("created").toLocalDateTime()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> names = new ArrayList<>();
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "select * from items where name = ?;")) {
            preparedStatement.setString(1, key);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    names.add(setItem(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "select * from items where id = ?;")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    item = setItem(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    private Item setItem(ResultSet resultSet) throws SQLException {
        return new Item(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getTimestamp("created").toLocalDateTime());
    }
}
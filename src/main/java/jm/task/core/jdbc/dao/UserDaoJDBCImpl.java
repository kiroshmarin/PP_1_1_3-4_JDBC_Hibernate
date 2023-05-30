package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String SQL = "CREATE TABLE IF NOT EXISTS User(\n" +
                "  id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,\n" +
                "  name VARCHAR(45),\n" +
                "  lastName VARCHAR(45),\n" +
                "  age TINYINT\n" +
                ");";
        try (Statement ps = Util.getConnection().createStatement()) {
            ps.executeUpdate(SQL);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE IF EXISTS User";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO User(name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement ps = Util.getConnection().prepareStatement(SQL)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM User WHERE id=?";
        try (PreparedStatement ps = Util.getConnection().prepareStatement(SQL)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String SQL = "SELECT * FROM User";
        try (Statement statement = Util.getConnection().createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String SQL = "DELETE FROM User";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}

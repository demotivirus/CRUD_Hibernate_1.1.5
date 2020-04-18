package dao;

import model.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDao implements UserDao{
    private Connection connection = DBHelper.getInstance().getConnection();
    private static UserJdbcDao instance;

    private UserJdbcDao(){}

    public static UserJdbcDao getInstance(){
        if (instance == null)
            return instance = new UserJdbcDao();
        return instance;
    }

    public void addUser(User user) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT into users (name, password, age) values (?, ?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getAge());

            preparedStatement.executeUpdate();
            System.out.println("Add user " + user + " success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(long id){
        User user = null;
        try{
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from users where id = ?");
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                int age = resultSet.getInt("age");
                return new User(id, name, password, age);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return user;
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";
        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            User user;
            while (result.next()) {
                long id = result.getLong("id");
                String name = result.getString("name");
                String password = result.getString("password");
                int age = result.getInt("age");
                user = new User(id, name, password, age);
                users.add(user);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return users;
    }

    public void deleteUser(Long id) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User " + id + " delete");
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void updateUser(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE users SET name = ?, password = ?, age = ? ")
                .append("where id = ?");

        try(PreparedStatement preparedStatement =
                    connection.prepareStatement(sb.toString())) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setLong(4, user.getId());

            preparedStatement.execute();
            System.out.println(user + " update");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public User loginUser(String name, String password){
        User user = null;
        try{
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from users where name = ? and password = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String paramName = resultSet.getString("name");
                String paramPass = resultSet.getString("password");
                int age = resultSet.getInt("age");
                String role = resultSet.getString("role");
                user = new User(paramName, paramPass, age, role);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return user;
    }
}

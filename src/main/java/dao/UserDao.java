package dao;

import model.User;
import java.util.List;

public interface UserDao{
    void addUser(User user);

    User getUser(long id);

    List<User> getAll();

    void deleteUser(Long id);

    void updateUser(User user);

    User loginUser(String name, String password);
}

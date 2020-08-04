package tk.hellojarvis.db.dao;


import tk.hellojarvis.db.entity.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : JarvisHsu
 * @create 2020/07/29 22:56
 */
public interface UserMapper {
    public User loadUser(User user);
    public void deleteAllUsers();


}

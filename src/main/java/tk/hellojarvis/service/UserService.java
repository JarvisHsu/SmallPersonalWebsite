package tk.hellojarvis.service;

import org.apache.ibatis.session.SqlSession;
import tk.hellojarvis.db.config.MyBatisManager;
import tk.hellojarvis.db.dao.UserMapper;
import tk.hellojarvis.db.entity.User;

import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : JarvisHsu
 * @create 2020/08/03 19:44
 */
public class UserService {
    private UserMapper userMapper;

    public UserService() {
        SqlSession sqlSession = MyBatisManager.openSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    public boolean checkUser(User user) {
        boolean bool;
        try {
            bool = userMapper.loadUser(user) != null;
        } finally {
            MyBatisManager.closeSqlSession();
        }
        return bool;
    }

    public void deleteAllUsers() {
        userMapper.deleteAllUsers();
    }
}

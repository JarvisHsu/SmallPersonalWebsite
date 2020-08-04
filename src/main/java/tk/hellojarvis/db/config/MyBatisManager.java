package tk.hellojarvis.db.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : JarvisHsu
 * @create 2020/07/29 17:39
 * <p>
 * * 读取mybatis-config.xml文件 创建数据源
 * * mybatis 有两个重要的API SqlSession 和数据源之间的一次会话(集成了JDBC中Connection的功能)
 * * SqlSessionFactory 采用了工厂模型一个类 这个类主要是创建SqlSession
 * * SqlSessionFactory一定根据mybatis-config.xml文件来创建SqlSession
 */
public class MyBatisManager {
    //定义SqlSessionFactory
    private static SqlSessionFactory sqlSessionFactory;
    //定义mybatis-config.xml文件的位置
    private static String configFilePath = "mybatis-config.xml";
    //线程空间(为每一个线程开辟一个线程空间线程级别线程不同 ThreadLocal的对象不同)
    //同一个线程多次和数据源产生交互，就不用每一个线程内在创建多个SqlSession相同的线程，
    //线程空间肯定是相同的;用了一个线程级别的空间内部存储SqLSession，可以省略相同的线程
    //多次访问数据源再去创建多个SqlSession对象;不同的线程又是不同的SqlSession对象
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public static void setConfigFilePath(String configFilePath) {
        MyBatisManager.configFilePath = configFilePath;
    }

    /**
     * 初始化SqlSessionFactory 1:读取mybatis-config.xml文件
     */
    static {
        try {
            //通过MyBatis的Resources类来读取mybatis-config.xml文件 并转换成文件流
            InputStream inputStream = Resources.getResourceAsStream(configFilePath);
            //SqlSessionFactoryBuilder 是用于构建SqlSessionFactory(调用了build这个方法
            // 通过mybatis-config.xml文件 mybatis-config.xml其实就传递到SqlSessionFactory)
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //现在利用了一个匿名静态块来初始化SqlSessionFactory 但是匿名静态块的执行只是在当前类加载的时候执行一次，
    //后续没有办法再调用以及再执行，如果存在匿名静态块初始化sqlSessionFactory的时候出现问题了，那么需要定义
    //一个备选方案，是一个方法
    private static void reBuilderSqlSessionFactory() {
        try {
            InputStream inputStream = Resources.getResourceAsStream(configFilePath);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession openSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession == null) {
            if (sqlSessionFactory == null) {
                reBuilderSqlSessionFactory();
            }
            sqlSession = sqlSessionFactory.openSession(true);
            threadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    public static void closeSqlSession() {
        if (threadLocal.get() != null) {
            threadLocal.get().close();
            threadLocal.remove();
        }
    }
}

package test;


import test.Dao.IUserDao;
import test.mybatis.User;
import test.mybatis.io.Resources;
import test.mybatis.sqlsession.SqlSession;
import test.mybatis.sqlsession.SqlSessionFactory;
import test.mybatis.sqlsession.SqlSessionFactoryBuilder;


import java.io.InputStream;
import java.util.List;

public class test {
    public static void main(String[] args) throws Exception {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生成SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();
    }
}

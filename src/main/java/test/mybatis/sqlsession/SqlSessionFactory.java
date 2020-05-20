package test.mybatis.sqlsession;

public interface SqlSessionFactory {
    // 用于打开新的Session对象
    SqlSession openSession();
}

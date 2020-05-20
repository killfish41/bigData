package test.mybatis.sqlsession;

/**
 * 自定义mybatis中和数据库交互的核心类
 * 他里面可以创建Dao接口的代理对象
 */
public interface SqlSession {
    //根据参数创建一个代理对象
    <T> T getMapper(Class<T> daoInterfaceClass);
    void close();
}

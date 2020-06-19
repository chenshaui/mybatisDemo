package sqlsession;

import bind.MapperProxy;
import config.Configuration;
import config.MapperStatement;
import executor.Executor;
import executor.SimpleExecutor;
import org.apache.log4j.Logger;
import java.lang.reflect.Proxy;
import java.util.List;


/**
 * @ClassName DefaultSqlSession
 * @Description 封装了所有数据库的操作，所有功能都是基于 Excutor 来实现的，Executor 封装了 JDBC 操作
 * @Author chenshuai
 * @Date 2020/6/18 17:54
 * @Version 1.0
 */
public class DefaultSqlSession implements SqlSession {
    private static Logger log = Logger.getLogger(DefaultSqlSession.class.getClass());
    private final Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        super();
        this.configuration = configuration;
        executor = new SimpleExecutor(configuration);
    }
    @Override
    public <T> T selectOne(String statement, Object parameter) {
        List<T> list = this.selectList(statement, parameter);
        if (list.size() > 1) {
            log.error("传递参数过多！");
            throw new RuntimeException("too many param");
        }
        if (list == null || list.size() == 0) {
            log.info("无参数传入！");
            return null;
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public <T> List<T> selectList(String statement, Object parameter) {
        MapperStatement ms = configuration.getMappedStatement().get(statement);
        return executor.query(ms,parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        //通过动态代理生成了一个实现类，我们重点关注，动态代理的实现，它是一个 InvocationHandler，传入参数是 this，就是 sqlSession 的一个实例。
        MapperProxy mp = new MapperProxy(this);
        //给我一个接口，还你一个实现类
        return (T) Proxy.newProxyInstance(type.getClassLoader(),new Class[]{type},mp);
    }
}

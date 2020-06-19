package bind;


import org.apache.log4j.Logger;
import sqlsession.SqlSession;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @ClassName MapperProxy
 * @Description  将请求转发给 sqlSession
 * @Author chenshuai
 * @Date 2020/6/18 18:12
 * @Version 1.0
 */
public class MapperProxy implements InvocationHandler {
    private static Logger log = Logger.getLogger(MapperProxy.class.getClass());
    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info(method.getDeclaringClass().getName()+"."+method.getName());
        //最终还是将执行方法转给 sqlSession，因为 sqlSession 里面封装了 Executor
        //根据调用方法的类名和方法名以及参数，传给 sqlSession 对应的方法
        if(Collection.class.isAssignableFrom(method.getReturnType())){
            return sqlSession.selectList(method.getDeclaringClass().getName() + "." + method.getName(), args == null ? null : args[0]);
        }else{
            return sqlSession.selectOne(method.getDeclaringClass().getName() + "." + method.getName(), args == null ? null : args[0]);
        }
    }
}

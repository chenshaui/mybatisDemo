package factory;

import sqlsession.SqlSession;

/**
 * @Author chenShuai
 * @Description  创建sqlsession，启动时加载配置信息
 * @Date  2020/6/18  16:49
 * @Param
 * @return
 **/
public interface SqlSessionFactory {
    SqlSession openSession();
}

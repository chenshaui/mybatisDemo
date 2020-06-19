/**
 * Created by ASUS on 2020/6/19.
 */

import entity.User;
import factory.DefaultSqlSessionFactory;
import factory.SqlSessionFactory;
import mapper.UserMapper;
import sqlsession.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Test
 * @Description TODO
 * @Author chenshuai
 * @Date 2020/6/19 9:59
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectById(1);
        List<User> users = mapper.selectAll();
        System.out.println(users);

    }
}

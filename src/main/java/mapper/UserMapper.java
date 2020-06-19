package mapper;

import entity.User;

import java.util.List;

/**
 * @Author chenShuai
 * @Description   mapper接口
 * @Date  2020/6/18  16:35
 * @Param
 * @return
 **/
public interface UserMapper {
    User selectById(int id);
    List<User> selectAll();
}

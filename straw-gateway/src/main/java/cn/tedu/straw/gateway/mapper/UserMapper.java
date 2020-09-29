package cn.tedu.straw.gateway.mapper;

import cn.tedu.straw.commons.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户详情
     *
     * @param username 用户名
     * @return 匹配的用户详情，如果没有匹配的数据，则返回null
     */
    User findByUsername(String username);

}
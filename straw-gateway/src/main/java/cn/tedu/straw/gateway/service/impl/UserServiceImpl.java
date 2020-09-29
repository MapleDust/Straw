package cn.tedu.straw.gateway.service.impl;

import cn.tedu.straw.commons.model.*;
import cn.tedu.straw.gateway.mapper.PermissionMapper;
import cn.tedu.straw.gateway.mapper.UserMapper;
import cn.tedu.straw.gateway.service.IUserService;
import cn.tedu.straw.gateway.vo.UserLoginVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserLoginVO getUserLoginDetails(String username) {
        // 基于参数username调用userMapper.findByUsername()方法查询用户信息
        User user = userMapper.findByUsername(username);
        // 判断查询结果是否为null
        if (user == null) {
            // -- 返回null，表示“用户名不存在”
            return null;
        }

        // 如果还可以执行到此处，表示“找到了与用户名匹配的数据，用户名是存在的”
        // 基于参数username调用permissionMapper.findByUsername()方法查询该用户的权限列表
        List<Permission> permissions = permissionMapper.findByUsername(username);

        // 创建UserLoginVO对象，并封装各属性的值
        UserLoginVO userLoginVO = new UserLoginVO()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setNickname(user.getNickname())
                .setPhone(user.getPhone())
                .setType(user.getType())
                .setLocked(user.getLocked())
                .setEnabled(user.getEnabled())
                .setPermissions(permissions);

        // 返回UserLoginVO对象
        return userLoginVO;
    }

}














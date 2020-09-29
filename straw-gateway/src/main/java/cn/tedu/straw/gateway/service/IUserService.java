package cn.tedu.straw.gateway.service;

import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.gateway.vo.UserLoginVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
public interface IUserService extends IService<User> {

    /**
     * 获取当前尝试登录的用户详情
     *
     * @param username 用户名
     * @return 匹配的用户详情，如果没有匹配的数据，则返回null
     */
    UserLoginVO getUserLoginDetails(String username);

}

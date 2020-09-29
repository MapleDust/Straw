package cn.tedu.straw.gateway.mapper;

import cn.tedu.straw.commons.model.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 查询某用户的权限信息
     *
     * @param username 用户名
     * @return 该用户的权限信息，如果用户数据不存在或未分配用户组（用户角色），则返回的列表中没有权限数据
     */
    List<Permission> findByUsername(String username);

}

package cn.tedu.straw.api.service.impl;

import cn.tedu.straw.api.mapper.RoleMapper;
import cn.tedu.straw.api.service.IRoleService;
import cn.tedu.straw.commons.model.Role;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}

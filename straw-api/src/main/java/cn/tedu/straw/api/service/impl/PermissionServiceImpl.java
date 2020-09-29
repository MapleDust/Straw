package cn.tedu.straw.api.service.impl;

import cn.tedu.straw.api.mapper.PermissionMapper;
import cn.tedu.straw.api.service.IPermissionService;
import cn.tedu.straw.commons.model.Permission;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}

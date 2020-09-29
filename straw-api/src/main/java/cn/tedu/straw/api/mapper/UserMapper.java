package cn.tedu.straw.api.mapper;

import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.commons.vo.TeacherSelectOptionVO;
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
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询老师的数据列表，用于显示前端的下拉菜单
     *
     * @return 老师的数据列表
     */
    List<TeacherSelectOptionVO> findTeacherSelectOptionList();

}

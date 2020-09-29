package cn.tedu.straw.redis.tag.mapper;

import cn.tedu.straw.commons.model.Tag;
import cn.tedu.straw.commons.vo.TagVO;
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
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 查询所有的标签列表
     *
     * @return 所有标签的数据列表
     */
    List<TagVO> findAll();

}

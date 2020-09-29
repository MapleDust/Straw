package cn.tedu.straw.redis.tag.service;

import cn.tedu.straw.commons.model.Tag;
import cn.tedu.straw.commons.vo.TagVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
public interface ITagService extends IService<Tag> {

    /**
     * 查询所有的标签列表
     *
     * @return 所有标签的数据列表
     */
    List<TagVO> getTagList();

}

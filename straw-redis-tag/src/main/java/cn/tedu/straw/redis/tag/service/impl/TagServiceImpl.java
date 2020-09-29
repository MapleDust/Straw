package cn.tedu.straw.redis.tag.service.impl;

import cn.tedu.straw.commons.model.Tag;
import cn.tedu.straw.commons.vo.TagVO;
import cn.tedu.straw.redis.tag.mapper.TagMapper;
import cn.tedu.straw.redis.tag.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public List<TagVO> getTagList() {
        return tagMapper.findAll();
    }

}

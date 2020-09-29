package cn.tedu.straw.redis.tag.mapper;

import cn.tedu.straw.commons.vo.TagVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class TagMapperTests {

    @Autowired
    TagMapper mapper;

    @Test
    void findAll() {
        List<TagVO> tags = mapper.findAll();
        log.debug("tag list size = {}", tags.size());
        for (TagVO tag : tags) {
            log.debug(">>> {}", tag);
        }
    }

}

package cn.tedu.straw.api.mapper;

import cn.tedu.straw.commons.vo.QuestionListItemVO;
import cn.tedu.straw.commons.vo.QuestionVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
public class PermissonMapperTests {
    @Resource
    QuestionMapper mapper;

    @Test
    void findHotQuestion() {
        List<QuestionListItemVO> lists = mapper.findHotList();
        for (QuestionListItemVO list : lists) {
            log.debug(String.valueOf(list));
        }
    }
    @Test
    void findMyQuestion(){
        int page=1;//查询第几页
        int pagesize=3;//每页显示条数
        //应用分页参数
        PageHelper.startPage(page,pagesize);
        List<QuestionVO> myQuestions = mapper.findMyQuestions(13);
        for (QuestionVO myQuestion : myQuestions) {
            log.debug(String.valueOf(myQuestion));
        }
        //得到分页参数
        PageInfo<QuestionVO>questionPageInfo=new PageInfo<>(myQuestions);
        log.debug("pageInfo>>>{}",questionPageInfo);
    }
    @Test
    void a(){
        System.out.println(mapper.findById(1));
    }
}

package cn.tedu.straw.search.schedule;

import cn.tedu.straw.search.repository.Questionrepository;
import cn.tedu.straw.search.service.IQuestionService;
import cn.tedu.straw.search.vo.QuestionVO;
import com.github.pagehelper.PageInfo;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LoadQuestionsSchedule {
    /**
     *
     */
    @Resource
    private ElasticsearchOperations elasticsearchOperations;
    @Resource
    private IQuestionService service;
    @Resource
    private Questionrepository questionrepository;

    @Scheduled(fixedRate = 10*60*1000)
    public void loadQuestions() {
        //清空当前ElasticSearch服务器中所有”问题“的文档：直接将整个索引删除
        elasticsearchOperations.indexOps(QuestionVO.class).delete();
        //循环读取MySql数据库中的数据，并将数据写入到ElasticSearch
        int pageNum = 1;
        PageInfo<QuestionVO> pageInfo = null;
        do {
            //读取Mysql数据库中的数据
            service.getQuestionsFromDataBase(pageNum);
            //页码自增
            pageNum++;
            //将读取到的数据全部写入到ElasticSearch
            questionrepository.saveAll(pageInfo.getList());
        }while (pageInfo.isHasNextPage());
    }
}

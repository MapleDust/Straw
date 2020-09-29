package cn.tedu.straw.search.repository;

import cn.tedu.straw.search.vo.QuestionVO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Questionrepository extends ElasticsearchRepository<QuestionVO,Long> {
}

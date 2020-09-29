package cn.tedu.straw.api.service.impl;

import cn.tedu.straw.api.dto.PostAnswerDTO;
import cn.tedu.straw.api.ex.InsertException;
import cn.tedu.straw.api.mapper.AnswerMapper;
import cn.tedu.straw.api.message.PostAnswerMessage;
import cn.tedu.straw.api.service.IAnswerService;
import cn.tedu.straw.commons.model.Answer;
import cn.tedu.straw.commons.vo.AnswerVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements IAnswerService {
    @Resource
    private AnswerMapper answerMapper;
    @Resource
    private Gson gson;
    @KafkaListener(topics = {"POST_ANSWER"})
    public void a(ConsumerRecord<String,String>record){
        String message=record.value();
        PostAnswerMessage postAnswerMessage=gson.fromJson(message,PostAnswerMessage.class);
        post(
                postAnswerMessage.getPostAnswerDTO(),
                postAnswerMessage.getUserId(),
                postAnswerMessage.getUserNickName()
        );
    }

    @Override
    public void post(PostAnswerDTO postAnswerDTO, Integer userId, String userNickName) {
        // 创建Answer对象
        Answer answer = new Answer()
                // 补全Answer对象的属性值：content		<<<	参数
                .setContent(postAnswerDTO.getContent())
                // 补全Answer对象的属性值：countOfLikes	<<< 0
                .setCountOfLikes(0)
                // 补全Answer对象的属性值：userId		<<< 参数
                .setUserId(userId)
                // 补全Answer对象的属性值：userNickName	<<< 参数
                .setUserNickName(userNickName)
                // 补全Answer对象的属性值：questionId	<<<	参数
                .setQuestionId(postAnswerDTO.getQuestionId())
                // 补全Answer对象的属性值：createdTime	<<< 当前时间
                .setCreatedTime(LocalDateTime.now())
                // 补全Answer对象的属性值：statusOfAccept<<<	0
                .setStatusOfAccept(0);
        // 调用answerMapper.insert(Answer answer)方法插入“答案”数据，获取返回值
        int rows = answerMapper.insert(answer);
        // 判断返回结果是否不为1
        if (rows != 1) {
            // 是：抛出InsertException
            throw new InsertException("发表答案失败！服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public List<AnswerVO> getAnswerList(Integer questionId) {
        return answerMapper.findByQuestionId(questionId);
    }
}
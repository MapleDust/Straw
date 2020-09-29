package cn.tedu.straw.api.service.impl;

import cn.tedu.straw.api.dto.PostQuestionDTO;
import cn.tedu.straw.api.ex.InsertException;
import cn.tedu.straw.api.ex.LoginException;
import cn.tedu.straw.api.ex.QuestionNotFoundException;
import cn.tedu.straw.api.mapper.QuestionMapper;
import cn.tedu.straw.api.mapper.QuestionTagMapper;
import cn.tedu.straw.api.mapper.UserQuestionMapper;
import cn.tedu.straw.api.service.IQuestionService;
import cn.tedu.straw.commons.model.Question;
import cn.tedu.straw.commons.model.QuestionTag;
import cn.tedu.straw.commons.model.UserQuestion;
import cn.tedu.straw.commons.vo.QuestionDetailVO;
import cn.tedu.straw.commons.vo.QuestionListItemVO;
import cn.tedu.straw.commons.vo.QuestionVO;
import cn.tedu.straw.commons.vo.TagVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private QuestionTagMapper questionTagMapper;
    @Resource
    private UserQuestionMapper userQuestionMapper;
    @Resource
    private RestTemplate restTemplate;

    @Override
    @Transactional
    public Integer post(PostQuestionDTO postQuestionDTO, Integer userId, String userNickname) {
        // 创建当前时间对象now
        LocalDateTime now = LocalDateTime.now();

        // 处理标签的id数组的字符串
        String tagIdsString = Arrays.toString(postQuestionDTO.getTagIds());
        tagIdsString = tagIdsString.substring(1, tagIdsString.length() - 1);
        // 创建Question对象
        Question question = new Question()
                // 向Question对象中封装数据：title > 从参数postQuestionDTO获取
                .setTitle(postQuestionDTO.getTitle())
                // 向Question对象中封装数据：content > 从参数postQuestionDTO获取
                .setContent(postQuestionDTO.getContent())
                // 向Question对象中封装数据：userNickName > 参数userNickname
                .setUserNickName(userNickname)
                // 向Question对象中封装数据：userId > 参数userId
                .setUserId(userId)
                // 向Question对象中封装数据：createdTime > now
                .setCreatedTime(now)
                // 向Question对象中封装数据：status > 0
                .setStatus(0)
                // 向Question对象中封装数据：hits > 0
                .setHits(0)
                // 向Question对象中封装数据：isPublic > 1
                .setIsPublic(1)
                // 向Question对象中封装数据：modifiedTime > now
                .setModifiedTime(now)
                // 向Question对象中封装数据：isDelete > 0
                .setIsDelete(0)
                // 向Question对象中封装数据：tagIds > 从参数postQuestionDTO获取，并转换为String
                .setTagIds(tagIdsString);
        // 调用questionMapper.insert(Question question)方法向question表中插入数据，并获取返回值
        int rows = questionMapper.insert(question);
        // 判断返回值是否不为1
        if (rows != 1) {
            // 是：抛出InsertException
            throw new InsertException("发布问题失败！服务器忙，请稍后再次尝试！");
        }

        // 遍历参数postQuestionDTO中的tagIds
        for (Integer tagId : postQuestionDTO.getTagIds()) {
            // 创建QuestionTag对象
            // 向QuestionTag对象中封装数据：questionId > 从以上插入成功的Question对象中获取
            // 向QuestionTag对象中封装数据：tagId > 被遍历到的数组元素
            QuestionTag questionTag = new QuestionTag()
                    .setQuestionId(question.getId())
                    .setTagId(tagId);
            // 调用questionTagMapper.insert(QuestionTag questionTag)向question_tag表中插入数据，并获取返回值
            rows = questionTagMapper.insert(questionTag);
            // 判断返回值是否不为1
            if (rows != 1) {
                // 是：抛出InsertException
                throw new InsertException("发布问题失败！服务器忙，请稍后再次尝试！");
            }
        }

        // 遍历postQuestionDTO中的teacherIds
        for (Integer teacherId : postQuestionDTO.getTeacherIds()) {
            // 创建UserQuestion对象
            // 向UserQuestion对象中封装数据：questionId > 从以上插入成功的Question对象中获取
            // 向UserQuestion对象中封装数据：userId > 被遍历到的数组元素
            // 向UserQuestion对象中封装数据：createdTime > now
            UserQuestion userQuestion = new UserQuestion()
                    .setQuestionId(question.getId())
                    .setUserId(teacherId)
                    .setCreatedTime(now);
            // 调用userQuestionMapper.insert(UserQuestion userQuestion)向user_question表中插入数据，并获取返回值
            rows = userQuestionMapper.insert(userQuestion);
            // 判断返回值是否不为1
            if (rows != 1) {
                // 是：抛出InsertException
                throw new InsertException("发布问题失败！服务器忙，请稍后再次尝试！");
            }
        }

        // 返回Question对象中的id
        return question.getId();
    }

    @Override
    public List<QuestionListItemVO> getHotList() {
        return questionMapper.findHotList();
    }

    @Value("${project.questions.my-list.page-size}")
    private int pageSize;

    @Override
    public PageInfo<QuestionVO> getMyQuestions(Integer userId, Integer page) {
        // 设置分页参数
        PageHelper.startPage(page, pageSize);
        // 执行查询
        List<QuestionVO> questions = questionMapper.findMyQuestions(userId);
        // 遍历查询结果，将数据封装到需要响应的列表中
        for (QuestionVO question : questions) {
            // ===== 获取查询到的“问题”的标签列表 =====
            // 从查询结果中获取当前“问题”的标签的id的字符串
            String tagIdsStr = question.getTagIds();
            // 将“标签”的id的字符串拆分为数组
            String[] tagIdArray = tagIdsStr.split(", ");
            // 准备“标签”的集合
            List<TagVO> tags = new ArrayList<>();
            // 遍历“标签”的id的数组，逐一获取数据
            for (int i = 0; i < tagIdArray.length; i++) {
                // 确定获取数据的url，url中的参数使用 {} 框住数字，数字从1开始顺序编号
                // 以下URL中，原本应该写主机名、端口的位置，需要改为在Eureka Server中注册的应用程序名称
                String url = "http://redis-tag/v1/tags/{1}/simple";
                // 执行远程调用，getForObject()方法的3个参数分别表示：
                // 1. 向哪个路径提交请求
                // 2. 响应的结果的数据类型
                // 3. URL中的参数值，由于方法设计的第3个参数是可变参数，所以，如果URL中有多个占位符，则依次写出各个值，使用逗号隔开即可
                TagVO tag = restTemplate.getForObject(url, TagVO.class, tagIdArray[i]);
                // 将获取的结果放在“标签”的集合中
                tags.add(tag);
            }
            // 基于查询结果封装需要响应的对象
            question.setTags(tags);
        }
        // 将需要响应的数据封装到PageInfo中
        PageInfo<QuestionVO> questionPageInfo = new PageInfo<>(questions);
        // 返回结果
        return questionPageInfo;
    }

    @Override
    public QuestionDetailVO getQuestionDetail(Integer id) {
        // 查询数据
        QuestionDetailVO question = questionMapper.findById(id);

        // 如果数据不存在，则抛出异常
        if (question == null) {
            throw new QuestionNotFoundException("查询问题详情失败！尝试访问的数据不存在，可能已经被删除！");
        }

        // 通过Ribbon远程调用，获取问题的List<Tag>
        String tagIdsStr = question.getTagIds();
        String[] tagIdArray = tagIdsStr.split(", ");
        List<TagVO> tags = new ArrayList<>();
        String url = "http://redis-tag/v1/tags/{1}/simple";
        for (int i = 0; i < tagIdArray.length; i++) {
            TagVO tag = restTemplate.getForObject(url, TagVO.class, tagIdArray[i]);
            tags.add(tag);
        }
        question.setTags(tags);

        // 返回
        return question;
    }
}

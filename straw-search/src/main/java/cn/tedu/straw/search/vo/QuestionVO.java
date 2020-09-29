package cn.tedu.straw.search.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@Document(indexName = "questions")//配置ElasticSearch服务器的索引
@Accessors(chain = true)
public class QuestionVO implements Serializable {
    /**
     * 该属性需要被响应到客户端，以便于实现客户端界面的操作
     * */
    @Id
    private Integer id;
    /**
     * 标题：用于匹配搜索的关键字
     * */
    @Field(type = FieldType.Text,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String title;
    /**
     * 正文：用于匹配搜索关键字
     * */
    @Field(type = FieldType.Text,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String content;
    /**
     * 用于完善页面功能
     * */
    private Integer userId;
    /**
     * 作者昵称：用于完善页面的显示
     * */
    @Field(type = FieldType.Keyword)
    private String userNickName;
    /**
     * 点击量：用于显示点击量
     * */
    private Integer hits;
}

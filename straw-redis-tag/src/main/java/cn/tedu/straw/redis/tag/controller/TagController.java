package cn.tedu.straw.redis.tag.controller;

import cn.tedu.straw.commons.vo.R;
import cn.tedu.straw.redis.tag.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/v1/tags")
public class TagController {

    @Resource
    private RedisUtils redisUtils;

    // http://localhost:8888
    @GetMapping("")
    public R<List<Object>> getTags() {
        // 向Redis中存入的数据的Key，也是取出数据的Key
        String key = "tags";
        // 从Redis中读取标签列表数据
        List<Object> list = redisUtils.getList(key);
        // 响应结果
        return R.ok(list);
    }

    // http://localhost:8888/v1/tags/1
    // http://localhost:8888/v1/tags/2
    @GetMapping("/{id}")
    public R<Object> getTag(@PathVariable("id") Integer id) {
        Object obj = redisUtils.get("tag:" + id);
        return R.ok(obj);
    }

    // http://localhost/redis-tag/v1/tags/1/simple
// http://localhost:8080/v1/tags/2/simple
    @GetMapping("/{id}/simple")
    public Object getSimpleTag(@PathVariable("id") Integer id) {
        return redisUtils.get("tag:" + id);
    }
}

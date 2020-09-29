package cn.tedu.straw.redis.tag.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 向Redis中存入数据
     *
     * @param key   数据的Key
     * @param value 数据的值
     */
    public void set(String key, Object value) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value);
    }

    /**
     * 向Redis中的某个List中添加元素
     *
     * @param key      List的Key
     * @param listItem 元素的值
     * @return 添加元素的位置
     */
    public Long rightPushListItem(String key, Object listItem) {
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        return ops.rightPush(key, listItem);
    }

    /**
     * 删除Redis中的某个数据
     *
     * @param key 被删除的数据的Key
     * @return 操作成功与否
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 从Redis中取出某个数据
     *
     * @param key 数据的Key
     * @return 数据的值
     */
    public Object get(String key) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        return ops.get(key);
    }

    /**
     * 获取Redis中某个List的长度
     *
     * @param key List的Key
     * @return List的长度
     */
    public Long size(String key) {
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        return ops.size(key);
    }

    /**
     * 获取Redis中的某个List的全部数据
     *
     * @param key List的Key
     * @return List的全部数据
     */
    public List<Object> getList(String key) {
        return getList(key, 0, size(key));
    }

    /**
     * 获取Redis中的某个List中的部分数据
     *
     * @param key   List的Key
     * @param start 截取的起始位置
     * @param end   截取的结束位置
     * @return 截取得到的List
     */
    public List<Object> getList(String key, long start, long end) {
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        return ops.range(key, start, end);
    }

}

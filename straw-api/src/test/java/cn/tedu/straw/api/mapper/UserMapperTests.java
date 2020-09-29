package cn.tedu.straw.api.mapper;

import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.commons.vo.TeacherSelectOptionVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class UserMapperTests {

    @Autowired
    UserMapper mapper;

    @Test
    void insert() {
        User user = new User();
        user.setUsername("mybatis");
        user.setPassword("1234");
        int rows = mapper.insert(user);
        log.debug("rows={}", rows);
    }

    @Test
    void deleteById() {
        Integer id = 1;
        int rows = mapper.deleteById(id);
        log.debug("rows={}", rows);
    }

    @Test
    void updateById() {
        User user = new User();
        user.setId(3);
        user.setPassword("888888");
        int rows = mapper.updateById(user);
        log.debug("rows={}", rows);
    }

    @Test
    void selectById() {
        Integer id = 5;
        User user = mapper.selectById(id);
        log.debug("query result >>> {}", user);
    }

    @Test
    void selectByUsername() {
        // 根据用户名查询数据，指定用户名的值
        String username = "root";
        // 创建查询时的条件对象，相当配置SQL语句中的WHERE子句
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // eq方法用于表示 username=?，表示“等于”的判断条件
        queryWrapper.eq("username", username);
        // 执行查询
        User user = mapper.selectOne(queryWrapper); // selectOne() > 查询结果最多只会有1条
        // 测试输出
        log.debug("query result >>> {}", user);
    }

    @Test
    void selectList() {
        // 当查询列表的参数queryWrapper为null时，表示没有WHERE子句
        List<User> users = mapper.selectList(null);
        log.debug("query result count = {}", users.size());
        // 输入 iter 即可生成遍历的for循环，iter=iterator
        for (User user : users) {
            log.debug(">>> {}", user);
        }
    }

    @Test
    void findTeacherSelectOptionList() {
        List<TeacherSelectOptionVO> teachers = mapper.findTeacherSelectOptionList();
        log.debug("query result count = {}", teachers.size());
        for (TeacherSelectOptionVO teacher : teachers) {
            log.debug(">>> {}", teacher);
        }
    }

}

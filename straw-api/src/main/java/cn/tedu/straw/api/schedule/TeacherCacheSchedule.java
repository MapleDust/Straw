package cn.tedu.straw.api.schedule;

import cn.tedu.straw.api.cache.TeacherCache;
import cn.tedu.straw.api.service.IUserService;
import cn.tedu.straw.commons.vo.TeacherSelectOptionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherCacheSchedule {

    @Autowired
    private IUserService userService;

    public static final Object LOCK = new Object();

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void loadTeachersCache() {
        synchronized (LOCK) {
            // 清除可能已经存在的缓存数据
            TeacherCache.getTeachers().clear();
            // 向缓存中存入数据
            List<TeacherSelectOptionVO> teachers = userService.getTeacherSelectOptionList();
            TeacherCache.getTeachers().addAll(teachers);
            TeacherCache.setTeachers(teachers);
        }
    }

}

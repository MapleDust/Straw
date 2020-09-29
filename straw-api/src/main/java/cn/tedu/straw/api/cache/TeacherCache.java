package cn.tedu.straw.api.cache;

import cn.tedu.straw.commons.vo.TeacherSelectOptionVO;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TeacherCache {

    private static List<TeacherSelectOptionVO> teachers = new CopyOnWriteArrayList<>();

    public static List<TeacherSelectOptionVO> getTeachers() {
        return teachers;
    }

    public static void setTeachers(List<TeacherSelectOptionVO> teachers) {
        TeacherCache.teachers = teachers;
    }

}

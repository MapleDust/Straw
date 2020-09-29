package cn.tedu.straw.api.service;

import cn.tedu.straw.api.dto.StudentRegisterDTO;
import cn.tedu.straw.api.ex.ServiceException;
import cn.tedu.straw.api.vo.UserLoginVO;
import cn.tedu.straw.commons.vo.TeacherSelectOptionVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Slf4j
public class UserServiceTests {

    @Autowired
    IUserService service;

    @Test
    void regStudent() {
        try {
            StudentRegisterDTO studentRegisterDTO = new StudentRegisterDTO()
                    .setPhone("13900139002")
                    .setPassword("1234")
                    .setDayOfBirth(LocalDate.now())
                    .setGender(1)
                    .setNickname("Frank")
                    .setInviteCode("JSD2002-525416");
            service.regStudent(studentRegisterDTO);
            log.debug("注册成功！！！");
        } catch (ServiceException e) {
            log.debug("注册失败！！！异常={}", e.getClass().getName());
        }
    }

    @Test
    void getTeacherSelectOptionList() {
        List<TeacherSelectOptionVO> teachers = service.getTeacherSelectOptionList();
        log.debug("query result count = {}", teachers.size());
        for (TeacherSelectOptionVO teacher : teachers) {
            log.debug(">>> {}", teacher);
        }
    }

}

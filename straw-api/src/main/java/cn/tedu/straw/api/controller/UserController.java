package cn.tedu.straw.api.controller;


import cn.tedu.straw.api.cache.TeacherCache;
import cn.tedu.straw.api.dto.StudentRegisterDTO;
import cn.tedu.straw.api.ex.ParameterValidationException;
import cn.tedu.straw.api.service.IUserService;
import cn.tedu.straw.commons.vo.R;
import cn.tedu.straw.commons.vo.TeacherSelectOptionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
@RestController
@RequestMapping("/v1/users")
@Slf4j
public class UserController {

    @Resource
    private IUserService userService;

    //  http://localhost/api/v1/users/student/register?phone=13100131001&password=1234&inviteCode=JSD1912-876840
    // http://localhost:8080/v1/users/student/register?phone=13100131001&password=1234&inviteCode=JSD1912-876840
    @RequestMapping("/student/register")
    public R regStudent(@Valid StudentRegisterDTO studentRegisterDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            log.debug("验证参数格式失败！{}", errorMessage);
            throw new ParameterValidationException(errorMessage);
        }
        userService.regStudent(studentRegisterDTO);
        return R.ok();
    }

    // http://localhost/api/v1/users/teachers
    // http://localhost:8080/v1/users/teachers
    @GetMapping("/teachers")
    public R<List<TeacherSelectOptionVO>> getTeachers() {
        return R.ok(TeacherCache.getTeachers());
    }

}

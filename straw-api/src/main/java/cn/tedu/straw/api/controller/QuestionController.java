package cn.tedu.straw.api.controller;


import cn.tedu.straw.api.cache.QuestionCache;
import cn.tedu.straw.api.dto.PostQuestionDTO;
import cn.tedu.straw.api.ex.*;
import cn.tedu.straw.api.service.IQuestionService;
import cn.tedu.straw.commons.model.Question;
import cn.tedu.straw.commons.security.LoginUserInfo;
import cn.tedu.straw.commons.vo.QuestionDetailVO;
import cn.tedu.straw.commons.vo.QuestionListItemVO;
import cn.tedu.straw.commons.vo.QuestionVO;
import cn.tedu.straw.commons.vo.R;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
@RequestMapping("/v1/questions")
public class QuestionController {

    @Resource
    private IQuestionService questionService;

    // http://localhost/api/v1/questions/post?title=HelloWorld&content=PublicStaticVoidMain&tagIds=2&tagIds=7&tagIds=9&teacherIds=1&teacherIds=5&teacherIds=8
    // http://localhost:8080/v1/questions/post?title=HelloWorld&content=PublicStaticVoidMain&tagIds=2&tagIds=7&tagIds=9&teacherIds=1&teacherIds=5&teacherIds=8&userId=13&userNickName=Alex
    @RequestMapping("/post")
    public R<Integer> post(@Valid PostQuestionDTO postQuestionDTO, BindingResult bindingResult,
                           @AuthenticationPrincipal LoginUserInfo loginUserInfo) {
        System.err.println("LoginUserInfo >>> " + loginUserInfo);
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            throw new ParameterValidationException(errorMessage);
        }
        Integer questionId = questionService.post(
                postQuestionDTO,
                loginUserInfo.getId(),
                loginUserInfo.getNickname()
        );
        return R.ok(questionId);
    }

    @Value("${project.upload.base-dir}")
    private String uploadBaseDir;
    @Value("${project.upload.question-image.max-size}")
    private long imageMaxSize;
    @Value("${project.upload.question-image.types}")
    private List<String> imageTypes;
    @Value("${project.resource-server.name}")
    private String resourceServerName;

    // http://localhost:8080/v1/questions/upload-image
    @PostMapping("/upload-image")
    public R<String> uploadImage(MultipartFile imageFile) {
        // 调用参数imageFile.isEmpty()判断上传的文件是否为空
        if (imageFile.isEmpty()) {
            // 是：抛出FileEmptyException
            throw new FileEmptyException("上传图片失败！请选择有效的图片文件！");
        }

        // 调用参数imageFile.getSize()获取文件大小
        // 判断文件大小是否超出了imageMaxSize值的限制
        if (imageFile.getSize() > imageMaxSize) {
            // 是：抛出FileSizeException
            throw new FileSizeException("上传图片失败！不允许上传超过" + imageMaxSize / 1024 + "KB的图片！");
        }

        // 调用参数imageFile.getContentType()获取文件类型
        // 判断文件类型是否不被imageTypes包含（调用contains()方法）
        if (!imageTypes.contains(imageFile.getContentType())) {
            // 是：抛出FileTypeException
            throw new FileTypeException("上传图片失败！仅支持以下格式的图片：" + imageTypes);
        }

        // 保存上传文件的文件夹：D:/IdeaProjects/straw-upload/2020/08
        // 通过DateTimeFormatter.ofPattern("yyyy/MM").format(LocalDate.now())得到 2020/08
        String subDir = DateTimeFormatter.ofPattern("yyyy/MM").format(LocalDate.now());
        // 创建上传文件夹的File对象：File parent = new File(uploadBaseDir, 以上得到的2020/08);
        File parent = new File(uploadBaseDir, subDir);
        // 调用parent.exists()判断文件夹是否不存在
        if (!parent.exists()) {
            // 是：调用parent.mkdirs()创建文件夹
            parent.mkdirs();
        }

        // 文件名filename：自定义策略，保证文件名不会冲突
        String filename = System.currentTimeMillis() + "-" + System.nanoTime();

        // 调用参数imageFile.getOriginalFilename()获取原始文件名
        String originalFilename = imageFile.getOriginalFilename();
        // 基于原始文件名进行截取，得到扩展名suffix
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 基于以上filename和suffix得到文件全名child
        String child = filename + suffix;

        // 基于parent和child创建文件对象，表示将上传的文件保存到哪里
        try {
            // -- 调用imageFile.transferTo()方法执行保存
            imageFile.transferTo(new File(parent, child));
        } catch (IOException e) {
            // -- 抛出FileIOException
            throw new FileIOException("上传图片失败！上传时出现读写错误，请稍后再次尝试！");
        } catch (IllegalStateException e) {
            // -- 抛出FileStateException
            throw new FileStateException("上传图片失败！原图片状态异常，请检查原图片文件是否存在！");
        }

        // 基于 2020/08 和 文件全名child 得到上传的文件的路径：/2020/08/xxxx.jpg
        String imageUrl = "/" + resourceServerName + "/" + subDir + "/" + child;
        // 返回文件路径
        return R.ok(imageUrl);
    }

    //http://localhost:8080/v1/questions/hot-list
    //http://localhost/api/v1/questions/hot-list
    @RequestMapping("/hot-list")
    public R<List<QuestionListItemVO>> getHotList() {
        return R.ok(QuestionCache.getQuestions());
    }

    // http://localhost/api/v1/questions/my-list?page=1
    @GetMapping("/my-list")
    public R<PageInfo<QuestionVO>> getMyList(@AuthenticationPrincipal LoginUserInfo loginUserInfo, Integer page) {
        if (page == null || page < 1) {
            page = 1;
        }
        PageInfo<QuestionVO> questions = questionService.getMyQuestions(loginUserInfo.getId(), page);
        return R.ok(questions);
    }

    //http://localhost/api/v1/questions/1
    //http://localhost:8080/v1/questions/1

    @GetMapping("/{id}")
    public R<QuestionDetailVO> getQuestionDetail(@PathVariable("id") Integer id){
        return R.ok(questionService.getQuestionDetail(id));
    }
}
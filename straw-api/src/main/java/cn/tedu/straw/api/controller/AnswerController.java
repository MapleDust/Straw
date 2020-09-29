package cn.tedu.straw.api.controller;


import cn.tedu.straw.api.dto.PostAnswerDTO;
import cn.tedu.straw.api.ex.ParameterValidationException;
import cn.tedu.straw.api.message.PostAnswerMessage;
import cn.tedu.straw.api.service.IAnswerService;
import cn.tedu.straw.commons.security.LoginUserInfo;
import cn.tedu.straw.commons.vo.AnswerVO;
import cn.tedu.straw.commons.vo.R;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/v1/answers")
@Slf4j
public class AnswerController {
    @Resource
    private IAnswerService answerService;
    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;
    @Resource
    private Gson gson;

    //http://localhost/api/v1/answers/post?questionId=7&content=Answer7
    @RequestMapping("/post")
    public R<Void> post(@Valid PostAnswerDTO postAnswerDTO,
                        BindingResult bindingResult,
                        @AuthenticationPrincipal LoginUserInfo loginUserInfo) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            throw new ParameterValidationException(message);
        }
        PostAnswerMessage postAnswerMessage=new PostAnswerMessage()
                .setPostAnswerDTO(postAnswerDTO)
                .setUserId(loginUserInfo.getId())
                .setUserNickName(loginUserInfo.getUsername());
        String message=gson.toJson(postAnswerMessage);
        kafkaTemplate.send("POST_ANSWER",message);
        log.debug("kafka{}",kafkaTemplate);
        //answerService.post(postAnswerDTO, loginUserInfo.getId(), loginUserInfo.getNickname());
        return R.ok();
    }

    @GetMapping("")
    public R<List<AnswerVO>> getAnswerList(Integer questionId){
        return R.ok(answerService.getAnswerList(questionId));
    }
}

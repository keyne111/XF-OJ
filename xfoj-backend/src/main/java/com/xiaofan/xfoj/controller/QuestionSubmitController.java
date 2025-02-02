package com.xiaofan.xfoj.controller;

import com.xiaofan.xfoj.common.BaseResponse;
import com.xiaofan.xfoj.common.ErrorCode;
import com.xiaofan.xfoj.common.ResultUtils;
import com.xiaofan.xfoj.exception.BusinessException;
import com.xiaofan.xfoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.xiaofan.xfoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.xiaofan.xfoj.model.entity.QuestionSubmit;
import com.xiaofan.xfoj.model.entity.User;
import com.xiaofan.xfoj.model.vo.QuestionSubmitVO;
import com.xiaofan.xfoj.service.QuestionSubmitService;
import com.xiaofan.xfoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 *
 *
 */
@RestController
@RequestMapping("/questionSubmit_thumb")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return 提交记录的 id
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
                                               HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能提交题目
        final User loginUser = userService.getLoginUser(request);
        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }

    /**
     * 分页获取题目提交
     * （普通用户和管理员都能分页查询，也可以根据编程语言，用户id，题目id，状态值去分页查，
     * 区别：普通用户只能看见自己提交的code，看不见其他人提交的code，管理员能看见自己和其他人提交的code）
     *
     * @param questionSubmitQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
                                                                         HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        // 从数据库中先去查，再去做分页
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        final User loginUser = userService.getLoginUser(request);
        // 返回脱敏信息
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, loginUser));
    }



}

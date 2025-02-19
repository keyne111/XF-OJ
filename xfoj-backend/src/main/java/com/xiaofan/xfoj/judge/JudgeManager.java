package com.xiaofan.xfoj.judge;


import com.xiaofan.xfoj.judge.strategy.DefaultJudgeStrategy;
import com.xiaofan.xfoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.xiaofan.xfoj.judge.strategy.JudgeContext;
import com.xiaofan.xfoj.judge.strategy.JudgeStrategy;
import com.xiaofan.xfoj.judge.codesandbox.model.JudgeInfo;
import com.xiaofan.xfoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}

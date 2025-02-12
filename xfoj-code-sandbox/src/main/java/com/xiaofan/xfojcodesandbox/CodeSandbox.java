package com.xiaofan.xfojcodesandbox;


import com.xiaofan.xfojcodesandbox.model.ExecuteCodeRequest;
import com.xiaofan.xfojcodesandbox.model.ExecuteCodeResponse;
import org.springframework.stereotype.Component;

/**
 * 代码沙箱接口定义
 */
@Component
public interface CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}

package com.xiaofan.xfojcodesandbox;

import com.xiaofan.xfojcodesandbox.model.ExecuteCodeRequest;
import com.xiaofan.xfojcodesandbox.model.ExecuteCodeResponse;
import org.springframework.stereotype.Component;

/**
 * Java 原生代码沙箱实现（直接复用模板方法）
 */
@Component
public class JavaNativeCodeSandbox extends JavaCodeSandboxTemplate {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        return super.executeCode(executeCodeRequest);
    }
}

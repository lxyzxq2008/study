package com.sunroom;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.Callable;

@RestController
@Slf4j
public class AsyncController {

    /**
     * 这种方式是线程异步(就是切换主副线程处理，但是对于请求还是同步的等待)
     * @return
     */
    @GetMapping("/async/test/callable")
    public Callable<Result<String>> test1() {
        log.info("调用主线程开始");
        Callable<Result<String>> callable = () -> {
            log.info("副线程调用开始");
            Result<String> result = new Result<>();
            result.setCode("200");
            result.setMsg("success");
            result.setStatus(true);
            Thread.sleep(1000);
            log.info("副线程调用返回");

            return result;
        };
        log.info("调用主线程结束");
        return callable;
    }

    @GetMapping("/async/test/deferredResult")
    public DeferredResult<Result<String>> test2() {
        DeferredResult<Result<String>> result = new DeferredResult<>();
        String orderNumber = RandomUtil.randomNumbers(8);

    }
}

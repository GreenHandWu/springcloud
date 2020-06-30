package com.wzm.controller;

import com.wzm.entity.ResultVO;
import com.wzm.entity.Test;
import com.wzm.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class TestController {
    @Resource
    private TestService testService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/system/queryTestById/{id}")
    public ResultVO<Test> getTestById(@PathVariable("id") Long id){
        log.info("查询成功");
        Test test = testService.queryTestById(id);
        if(test != null){
            return new ResultVO<>(200,"查询成功,serverPort: "+serverPort, test);
        }else {
            return new ResultVO<>(500,"查询失败,serverPort: "+serverPort, null);
        }
    }

    @PostMapping("/system/addTest")
    public ResultVO<Test> addTest(@RequestBody Test test){
        log.info("插入数据库");
        int result = testService.addTest(test);
        if(result > 0){
            return new ResultVO<>(200,"插入数据库成功,serverPort: "+serverPort, test);
        }else {
            return new ResultVO<>(500,"插入数据库失败serverPort: "+serverPort, null);
        }
    }

}

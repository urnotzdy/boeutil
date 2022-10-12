package com.example.myutil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class TestContorller {

    //
//    @RequestMapping(value = "/get",method = RequestMethod.GET)
//    public Callable<ResponseMsg<String>> getResult(){
//
//        log.info("接收请求，开始处理...");
//
//        Callable<ResponseMsg<String>> result = (()->{
//            return taskService.getResult();
//        });
//
//        log.info("接收任务线程完成并退出");
//
//        return result;
//    }
    public static void main(String[] args) {
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            ls.add(i + "");
        }
        ls.parallelStream().forEach(s -> {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(s);
                }
        );
    }

}

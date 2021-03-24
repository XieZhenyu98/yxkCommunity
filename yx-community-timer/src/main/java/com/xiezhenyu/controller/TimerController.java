package com.xiezhenyu.controller;

import com.xiezhenyu.manager.QuartzManage;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.TimerService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tim
 * @date 2021/3/24
 */
@RestController
@RequestMapping("/test/aaa")
public class TimerController {
    @Autowired
    TimerService timerService;
    @GetMapping()
    public CommonResult test() throws SchedulerException {
        return timerService.startTimer(1);
    }
}

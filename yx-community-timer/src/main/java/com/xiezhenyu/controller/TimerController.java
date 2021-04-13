package com.xiezhenyu.controller;

import com.xiezhenyu.entity.QuartzJob;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.TimerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tim
 * @date 2021/3/24
 */
@Api("定时任务")
@RestController
@RequestMapping("/timer")
public class TimerController {

    @Autowired
    TimerService timerService;

    @ApiOperation(value = "启动定时任务", notes = "启动定时任务")
    @PostMapping("/start")
    public CommonResult startJob(@ApiParam(value = "定时任务id") @RequestBody QuartzJob quartzJob) {
        System.out.println(quartzJob.getId());
        return timerService.startTimer(quartzJob.getId());
    }

    @ApiOperation(value = "添加定时任务", notes = "启动定时任务")
    @PostMapping("/add")
    public CommonResult addJob(@ApiParam(value = "定时任务实体类") @RequestBody QuartzJob quartzJob) {
        return timerService.addTimer(quartzJob);
    }

    @ApiOperation(value = "定时任务列表", notes = "定时任务列表")
    @GetMapping("/pages/{limit}/{offset}")
    public CommonResult getTimerPages(@ApiParam(value = "第几页") @PathVariable Integer limit,
                                      @ApiParam(value = "偏移量") @PathVariable Integer offset){
        return timerService.getTimerPages(limit,offset);
    }

    @ApiOperation(value = "停止定时任务", notes = "停止定时任务")
    @PutMapping("/stop/{id}")
    public CommonResult stopTimer(@ApiParam(value = "定时任务id") @PathVariable Integer id){
        return timerService.stopTimer(id);
    }

    @ApiOperation(value = "删除定时任务", notes = "删除定时任务")
    @DeleteMapping("/delete/{id}")
    public CommonResult deleteTimer(@ApiParam(value = "定时任务id") @PathVariable Integer id){
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setId(id);
        return timerService.deleteTimer(quartzJob);
    }

    @ApiOperation(value = "暂停定时任务", notes = "暂停定时任务")
    @PostMapping("/pause/{id}")
    public CommonResult pauseTimer(@ApiParam(value = "定时任务id") @PathVariable Integer id){
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setId(id);
        return timerService.pauseTimer(quartzJob);
    }

    @ApiOperation(value = "恢复定时任务", notes = "恢复定时任务")
    @PostMapping("/resume/{id}")
    public CommonResult resumeTimer(@ApiParam(value = "定时任务id") @PathVariable Integer id){
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setId(id);
        return timerService.resumeTimer(quartzJob);
    }
}

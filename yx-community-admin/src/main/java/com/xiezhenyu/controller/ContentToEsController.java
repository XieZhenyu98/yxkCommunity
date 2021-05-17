package com.xiezhenyu.controller;

import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.ContentToEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tim
 * @date 2021/5/17
 */
@RestController
@RequestMapping("/es")
public class ContentToEsController {
    @Autowired
    private ContentToEsService contentToEsService;

    @PostMapping("/contentToEs")
    public CommonResult contentToEs(){
        boolean b = contentToEsService.contentToEs();
        return CommonResult.successCommonResult(b,"插入成功");
    }
}

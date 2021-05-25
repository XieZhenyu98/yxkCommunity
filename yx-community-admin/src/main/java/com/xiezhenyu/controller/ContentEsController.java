package com.xiezhenyu.controller;

import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.ContentEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tim
 * @date 2021/5/17
 */
@RestController
@RequestMapping("/es")
public class ContentEsController {
    @Autowired
    private ContentEsService contentEsService;

    @PostMapping("/contentToEs")
    public CommonResult contentToEs(){
        boolean b = contentEsService.contentToEs();
        return CommonResult.successCommonResult(b,"插入成功");
    }

    @GetMapping("/searchPage")
    public CommonResult searchPage(String keywords, int pageNo, int pageSize) {
        return CommonResult.successCommonResult(contentEsService.searchPage(keywords,pageNo,pageSize),"查询成功");
    }
}

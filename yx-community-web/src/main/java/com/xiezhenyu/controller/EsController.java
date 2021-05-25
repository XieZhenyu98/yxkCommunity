package com.xiezhenyu.controller;

import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.impl.EsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Tim
 * @date 2021/5/25
 */
@Api(tags = "Es")
@RestController
@RequestMapping("/es")
public class EsController {

    @Autowired
    private EsService esService;

    @ApiOperation(value = "从Es获取帖子内容")
    @GetMapping("/content")
    public CommonResult getContentByKeywords(@ApiParam(value = "关键字") String keywords,
                                             @ApiParam(value = "起始页") Integer pageNo,
                                             @ApiParam(value = "每页大小") Integer pageSize) {
        List<Map<String, Object>> contentByKeywords = esService.getContentByKeywords(keywords, pageNo, pageSize);
        return CommonResult.successCommonResult(contentByKeywords,"查询成功");
    }

}

package com.xiezhenyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.common.response.CommonResult;
import com.xiezhenyu.entity.ContentVo;
import com.xiezhenyu.model.ContentDo;
import com.xiezhenyu.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tim
 */
@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private IContentService contentService;

    @PostMapping("/add")
    public CommonResult add(@RequestBody ContentDo contentDo){
        ContentDo result = contentService.add(contentDo);
        return CommonResult.successCommonResult(result,"插入成功");
    }

    @GetMapping("/{id}")
    public CommonResult getContentById(@PathVariable("id") Long id){
        ContentVo content = contentService.getContentById(id);
        return CommonResult.successCommonResult(content,"查询成功");
    }

    @GetMapping("/{limit}/{offset}")
    public CommonResult list(@PathVariable("limit") Integer limit,@PathVariable("offset") Integer offset){
        Page<ContentVo> page = new Page<>(limit,offset);
        ArrayList<ContentVo> contentVos = contentService.selectListVo(limit, offset);
        page.setRecords(contentVos);
        return CommonResult.successCommonResult(page,"查询成功");
    }

    @GetMapping("/{limit}/{offset}/{module_id}")
    public CommonResult listByModuleId(@PathVariable("limit")Integer limit,@PathVariable("offset")Integer offset,@PathVariable("module_id")Long module_id){
        Page<ContentVo> page = contentService.listByModuleId(module_id, limit, offset);
        return CommonResult.successCommonResult(page,"查询成功");
    }

    @GetMapping("/listOfTop")
    public CommonResult listOfTop(){
        List<ContentVo> list = contentService.listOfTop();
        return CommonResult.successCommonResult(list,"查询成功");
    }

    @PutMapping("/update")
    public CommonResult updateContent(@RequestBody ContentDo contentDo){
        ContentDo content = contentService.updateContent(contentDo);
        return CommonResult.successCommonResult(content,"修改成功");
    }

    @DeleteMapping("/delete/{id}")
    public CommonResult deleteContentById(@PathVariable("id")Long id){
        boolean result = contentService.deleteContentById(id);
        if(result){
            return CommonResult.successCommonResult("删除成功");
        }else{
            return CommonResult.errorCommonResult("删除失败");
        }
    }

}

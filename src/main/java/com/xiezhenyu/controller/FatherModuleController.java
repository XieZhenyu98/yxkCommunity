package com.xiezhenyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.common.response.CommonResult;
import com.xiezhenyu.model.FatherModuleDo;
import com.xiezhenyu.service.IFatherModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tim
 */
@RestController
@RequestMapping("/fatherModule")
public class FatherModuleController {

    @Autowired
    private IFatherModuleService fatherModuleService;

    @PostMapping("/add")
    public CommonResult add(@RequestBody FatherModuleDo fatherModuleDo){
        FatherModuleDo fatherModule = fatherModuleService.add(fatherModuleDo);
        return CommonResult.successCommonResult(fatherModule,"添加成功");
    }

    @DeleteMapping("/delete/{id}")
    public CommonResult deleteById(@PathVariable("id") Long id){
        boolean result = fatherModuleService.deleteById(id);
        if(result){
            return CommonResult.successCommonResult("删除成功");
        }else {
            return CommonResult.errorCommonResult("删除失败");
        }
    }

    @PutMapping("/update")
    public CommonResult update(@RequestBody FatherModuleDo fatherModuleDo){
        FatherModuleDo fatherModule = fatherModuleService.update(fatherModuleDo);
        return CommonResult.successCommonResult(fatherModule,"修改成功");
    }

    @GetMapping("/select/{limit}/{offset}")
    public CommonResult selectList(@PathVariable("limit")Integer limit,@PathVariable("offset")Integer offset){
        Page<FatherModuleDo> fatherModuleDoPage = fatherModuleService.selectList(limit, offset);
        return CommonResult.successCommonResult(fatherModuleDoPage,"查询成功");
    }

    @GetMapping("/select/{id}")
    public CommonResult selectById(@PathVariable("id")Long id){
        FatherModuleDo fatherModule = fatherModuleService.selectById(id);
        return CommonResult.successCommonResult(fatherModule,"查询成功");
    }

}

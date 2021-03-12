package com.xiezhenyu.controller;

import com.xiezhenyu.model.UserDo;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author Tim
 * @date 2021/3/11
 */
@Api(tags = "文件")
@RestController()
@RequestMapping("/file")
public class FileController {

    @Autowired
    private IUserService userService;
    private static String userImagePrefix = File.separator+"res"+File.separator+"images"+File.separator+"userImage"+File.separator;

    @PostMapping("/userImageUpload/{userId}")
    public CommonResult userImageUpload(@RequestParam("file") MultipartFile file,@PathVariable("userId") Long userId){
        UserDo user = userService.getUserById(userId);
        String parentPath = (new File(this.getClass().getResource("/").getPath())).toString()+File.separator+"static"+File.separator+"res"+File.separator+"images"+File.separator+"userImage";
        // 判断上传文件是否为空，若为空则返回错误信息
        if(file.isEmpty()){
            return CommonResult.errorCommonResult("上传失败");
        }else{
            // 获取文件原名
            String originalFilename = file.getOriginalFilename()+".png";
            // 获取源文件前缀
            String fileNamePrefix = originalFilename.substring(0,originalFilename.lastIndexOf("."));
            // 获取源文件后缀
            String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 将源文件前缀之后加上时间戳避免重名
            String newFileNamePrefix = fileNamePrefix+(new Date()).getTime();
            // 得到上传后新文件的文件名
            String newFileName = newFileNamePrefix+fileNameSuffix;
            userService.updateUser(user.setImage(userImagePrefix+newFileName));
            // 创建一个新的File对象用于存放上传的文件
            File fileNew = new File(parentPath+File.separator+newFileName);
            try {
                file.transferTo(fileNew);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(parentPath);
        return CommonResult.successCommonResult("上传成功");
    }

}

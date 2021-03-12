package com.xiezhenyu.controller;

import com.xiezhenyu.model.UserDo;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.IUserService;
import com.xiezhenyu.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @ApiOperation(value = "用户头像上传",notes = "用户头像上传,通过token获取用户")
    @PostMapping("/userImageUpload")
    public CommonResult userImageUpload(@ApiParam(value = "上传的文件") @RequestParam("file") MultipartFile file,
                                        @ApiParam(value = "请求") HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String userId = JwtUtils.getTokenInfo(token).getClaim("id").asString();
        UserDo user = userService.getUserById(Long.parseLong(userId));
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
        return CommonResult.successCommonResult(user.getImage(),"上传成功");
    }

}

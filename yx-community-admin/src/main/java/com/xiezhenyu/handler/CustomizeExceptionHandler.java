package com.xiezhenyu.handler;

import com.xiezhenyu.construct.CommonConfig;
import com.xiezhenyu.model.CommonConfigDo;
import com.xiezhenyu.service.ICommonConfigService;
import com.xiezhenyu.utils.dingding.RebootUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Tim
 * @date 2021/6/1
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    @Autowired
    private ICommonConfigService commonConfigService;

    @ExceptionHandler
    public void exceptionHandler(Exception e){
        CommonConfigDo commonConfigAlarmSecret = commonConfigService.getByPrefixAndKey(CommonConfig.DING_PREFIX, CommonConfig.DING_ALARM_REBOOT_SECRET_KEY);
        String secret = commonConfigAlarmSecret.getConfigValue();
        CommonConfigDo commonConfigAlarmWebhook = commonConfigService.getByPrefixAndKey(CommonConfig.DING_PREFIX, CommonConfig.DING_ALARM_REBOOT_WEBHOOK_KEY);
        String webhook = commonConfigAlarmWebhook.getConfigValue();
        String message = "出现异常情况！原因是: \n"+e;
        RebootUtil.sendReboot(secret,webhook,message,false,null);
        e.printStackTrace();
    }
}

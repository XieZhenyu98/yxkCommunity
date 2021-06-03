package com.xiezhenyu.utils.dingding;

import com.alibaba.fastjson.JSON;
import com.xiezhenyu.utils.dingding.text.AtMobiles;
import com.xiezhenyu.utils.dingding.text.ContentModel;
import com.xiezhenyu.utils.dingding.text.TextRebootModel;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tim
 * @date 2021/6/1
 */
public class RebootUtil {

    public static final String WEBHOOK = "https://oapi.dingtalk.com/robot/send?access_token=7a12af28fafb4e75e43485e2f9d14320233eb9445ce62ba67758971dfb2ee732";
    public static final String SECRET = "SEC5a9153185a1b0c8574f668417375cf16bec97a68075357db1b39fe4e9faef72e";

    /**
     * 组装 发送的信息
     * Text版本
     * @param isAt       是否需要 @所有人
     * @param msgContent 要发送信息的主体
     * @param telList    要 @人的电话号码,如果@单独的几个人，就传一个空list，而不是 null
     * @return
     */
    public static String setMessage(boolean isAt, String msgContent, List<String> telList) {

        TextRebootModel model = new TextRebootModel();
        AtMobiles atMobiles = new AtMobiles();
        atMobiles.setIsAtAll(isAt);
        atMobiles.setAtMobiles(telList);

        ContentModel contentModel = new ContentModel();
        contentModel.setContent(msgContent);

        model.setAt(atMobiles);
        model.setText(contentModel);

        String toMsg = JSON.toJSONString(model);

        return toMsg;
    }

    /**
     * post 请求，发送给哪一个机器人
     *
     * @param reboot  机器人的token
     * @param message 发送的消息
     * @return
     */
    public static String sendPost(String reboot, String message) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(reboot);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        String textMsg = message;
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
        String result = null;
        HttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 选择加签方式下的加签方法
     * @param secret 密钥，机器人安全设置页面，加签一栏下面显示的SEC开头的字符串
     * @return
     */
    public static Map<String, String> dingDingSec(String secret) throws Exception {
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        Map<String, String> map = new HashMap();
        map.put("sign", sign);
        map.put("timestamp", timestamp.toString());
        return map;
    }

    /**
     * 加签机器人实现，这里需要注意的是：timestamp和sign需要保持一致
     *
     * @param message 要发送的信息
     * @return
     * @throws Exception
     */
    public static String sendReboot(String secret, String Webhook, String message) throws Exception {
        Map<String,String> map=dingDingSec(secret);
        String sign = map.get("sign");
        String timestamp = map.get("timestamp");
        StringBuffer stringBuffer = new StringBuffer();
        String robotUrl =stringBuffer.append(Webhook).append("&timestamp=").append(timestamp).append("&sign=").append(sign).toString();
        return sendPost(robotUrl, message);
    }

    /**
     * 加签机器人,不需通过setMessage获取message
     *
     * @param secret
     * @param webhook
     * @param message   需要发送消息
     * @param isAt      是否at全体成员
     * @param telList   要 @人的电话号码,如果@单独的几个人，就传一个空list，而不是 null
     */
    public static void sendReboot(String secret, String webhook, String message, boolean isAt, List<String> telList) {
        //钉钉机器人推送
        try {
            String msg = RebootUtil.setMessage(false, message, telList);
            RebootUtil.sendReboot(secret, webhook, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关键字机器人：发送消息中需要有对应的关键字才能发送成功
     * @param message 封装的消息
     * @return
     * @throws Exception
     */
    public static String sendKeyReboot(String webhook, String message) throws Exception {
        return sendPost(webhook, message);
    }

    public static void main(String[] args) {
        sendReboot(SECRET,WEBHOOK,"nihao",false,null);
    }
}

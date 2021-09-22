package test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrFormatter;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.internal.util.Base64;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class DingTest {

    public static String secret = "";
    public static String url = "https://oapi.dingtalk.com/robot/send?access_token={}&timestamp={}&sign={}";
    public static String token = "";
    public static String title = "监控报警";
    public static String contentTemplate = "#### 监控报警\n[{}]({})\n> ###### {} \n";

    @Test
    public void testA() throws Exception {

        Long timestamp = 1630569131819L;
//        Long timestamp = System.currentTimeMillis();
        String sign = sign(timestamp);
        String reqUrl = StrFormatter.format(url, token, timestamp, sign);
        System.out.println(reqUrl);
        if (true) {
            return;
        }
        DingTalkClient client = new DefaultDingTalkClient(reqUrl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");

//        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
//        text.setContent("测试文本消息");
//        request.setText(text);
//        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//        at.setAtMobiles(Arrays.asList("132xxxxxxxx"));
//// isAtAll类型如果不为Boolean，请升级至最新SDK
//        at.setIsAtAll(true);
//        at.setAtUserIds(Arrays.asList("109929", "32099"));
//        request.setAt(at);
//
//        request.setMsgtype("link");
//        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
//        link.setMessageUrl("https://www.dingtalk.com/");
//        link.setPicUrl("");
//        link.setTitle("时代的火车向前开");
//        link.setText("这个即将发布的新版本，创始人xx称它为红树林。而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是红树林");
//        request.setLink(link);
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle(title);
        String content = StrFormatter.format(contentTemplate,"百度","https://www.baidu.com/", DateUtil.formatDate(DateUtil.date()));
        markdown.setText(content);
        request.setMarkdown(markdown);
        OapiRobotSendResponse response = client.execute(request);
    }

    private String sign(Long timestamp) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));

        String stringToSign = timestamp + "\n" + secret;
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        System.out.println(new String(Base64.encodeToByte(signData, true)));
        return URLEncoder.encode(new String(Base64.encodeToByte(signData, true)), "UTF-8");
    }
}

package com.poshist.maBit.utils;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by poshist on 18-1-27.
 */
public class Test {

    public static void main(String[] args) throws java.text.ParseException {
         // 全局请求设置
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        // 创建cookie store的本地实例
        CookieStore cookieStore = new BasicCookieStore();
        // 创建HttpClient上下文
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookieStore);

        // 创建一个HttpClient
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig)
                .setDefaultCookieStore(cookieStore).build();

        CloseableHttpResponse res = null;

        // 创建本地的HTTP内容
        try {
            try {
                            // 构造post数据
                List<NameValuePair> valuePairs = new LinkedList<NameValuePair>();
                valuePairs.add(new BasicNameValuePair("username", "huobitebdc"));
                valuePairs.add(new BasicNameValuePair("password", "jieyue201801"));
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);
                entity.setContentType("application/x-www-form-urlencoded");

                // 创建一个post请求
                HttpPost post = new HttpPost("http://www.btc.top/api/user/sign_in");
                // 注入post数据
                post.setEntity(entity);
                post.setHeader("referer", "http://www.btc.top");
                res = httpClient.execute(post, context);

                // 打印响应信息，查看是否登陆是否成功
                System.out.println("打印响应信息===========");

                res.close();

                System.out.println("登陆成功后,新的Cookie:===============");
                for (Cookie c : context.getCookieStore().getCookies()) {
                    System.out.println(c.getName() + ": " + c.getValue());
                }

                // 构造一个新的get请求，用来测试登录是否成功
                HttpGet newGet = new HttpGet("http://www.zhihu.com/question/following");
                res = httpClient.execute(newGet, context);
                String content = EntityUtils.toString(res.getEntity());
                System.out.println("登陆成功后访问的页面===============");
                System.out.println(content);
                res.close();

            } finally {
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

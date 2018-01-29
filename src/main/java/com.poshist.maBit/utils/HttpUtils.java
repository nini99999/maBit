package com.poshist.maBit.utils;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by poshist on 18-1-26.
 */
public class HttpUtils {
    private static Logger logger=Logger.getLogger(HttpUtils.class);
    private static CloseableHttpClient httpClient =null;
    public  static HttpClientContext context = null;
    public  static CookieStore cookieStore = null;
    public  static RequestConfig requestConfig = null;
    static {
        init();
    }

    private static void init() {
        // 全局请求设置
        requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        // 创建cookie store的本地实例
        cookieStore = new BasicCookieStore();
        // 创建HttpClient上下文
        context = HttpClientContext.create();
        context.setCookieStore(cookieStore);

        // 创建一个HttpClient
        httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig)
                .setDefaultCookieStore(cookieStore).build();

    }
    public static String post(String url, String parameters,String referer) throws IOException {
        HttpPost httpPost = new HttpPost(url);

        if(null!=referer) {
            httpPost.setHeader("referer", referer);
        }
        if(null!=parameters) {
            List<NameValuePair> nvps = toNameValuePairList(parameters);
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
            entity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(entity);
        }
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseJson = httpClient.execute(httpPost,responseHandler);
        logger.info("HttpClient POST请求结果：" + responseJson);
        httpClient.getConnectionManager().closeExpiredConnections();
        httpClient.getConnectionManager().closeIdleConnections(30, TimeUnit.SECONDS);

    return responseJson;

    }

    private static List<NameValuePair> toNameValuePairList(String parameters) {
        if(null==parameters){
            return null;
        }
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        String[] paramList = parameters.split("&");
        for (String parm : paramList) {
            int index = -1;
            for (int i = 0; i < parm.length(); i++) {
                index = parm.indexOf("=");
                break;
            }
            String key = parm.substring(0, index);
            String value = parm.substring(++index, parm.length());
            nvps.add(new BasicNameValuePair(key, value));
        }
        System.out.println(nvps.toString());
        return nvps;
    }

//    public static String post(String url ,List<NameValuePair> params,String referer,String sessionID) {
//    HttpPost post = new HttpPost(url);
//    if(null!=referer) {
//        post.setHeader("referer", referer);
//    }
//
//    ResponseHandler<String> responseHandler = new BasicResponseHandler();
//    String responseJson = null;
//    try {
//        if (params != null) {
//            post.setEntity(new UrlEncodedFormEntity(params));
//        }
//        responseJson = httpClient.execute(post, responseHandler);
//
//        logger.info("HttpClient POST请求结果：" + responseJson);
//    }
//    catch (ClientProtocolException e) {
//        e.printStackTrace();
//        logger.info("HttpClient POST请求异常：" + e.getMessage());
//    }
//    catch (IOException e) {
//        e.printStackTrace();
//    }
//    finally {
//        httpClient.getConnectionManager().closeExpiredConnections();
//        httpClient.getConnectionManager().closeIdleConnections(30, TimeUnit.SECONDS);
//    }
//    return responseJson;
//}
}


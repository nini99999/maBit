package com.poshist.maBit.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.poshist.maBit.utils.HttpUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;

/**
 * Created by poshist on 18-1-26.
 * desc btctop duqu
 */
@Service
@Transactional
public class BtcTop {
    private static String baseUrl="http://www.btc.top";


    public void Login(){


        try {
            HttpUtils.post(baseUrl+"/api/user/sign_in","username=huobitebdc&password=jieyue201801",baseUrl);
            String userInfo= HttpUtils.post(baseUrl+"/api/user/get_dashboard",null,baseUrl);
           String userJson= HttpUtils.post(baseUrl+"/api/user/get_info",null,baseUrl);
           Map usrMap = JSON.parseObject(userJson);
           if(null!= ((Map)usrMap.get("data")).get("sub_users")){
               JSONArray subUserList=(JSONArray)((Map)usrMap.get("data")).get("sub_users");
               for (int i=0;i<subUserList.size();i++){
                   String subUserName=(String)((Map)subUserList.get(i)).get("username");
                   HttpUtils.post(baseUrl+"/api/www/change_to_user","username="+subUserName,baseUrl);
                   userInfo= HttpUtils.post(baseUrl+"/api/user/get_dashboard",null,baseUrl);
               }

           }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void main (String[] args){
        BtcTop bt=new BtcTop();
        bt.Login();
    }
}

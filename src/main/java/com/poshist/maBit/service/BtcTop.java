package com.poshist.maBit.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.poshist.maBit.entity.MbSubUser;
import com.poshist.maBit.entity.MbUserInfo;
import com.poshist.maBit.repository.MbSubUserDao;
import com.poshist.maBit.repository.MbuserInfoDao;
import com.poshist.maBit.utils.HttpUtils;
import com.poshist.maBit.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by poshist on 18-1-26.
 * desc btctop duqu
 */
@Service
@Transactional
public class BtcTop {
    private static String baseUrl="http://www.btc.top";
    private static Long siteId=1l;
@Autowired
private MbSubUserDao mbSubUserDao;
@Autowired
private MbuserInfoDao mbUserInfoDao;

public List<MbUserInfo> getData(String date){
List<MbUserInfo> list=mbUserInfoDao.findByRecTimeBetweenAndMbSubUser_SiteIdOrderByMbSubUserId(TimeUtils.stringToDate(date+" 00:00:00"),TimeUtils.stringToDate(date+" 23:59:59"),1l);
return list;

}


    public void getData() throws IOException {



        HttpUtils.post(baseUrl+"/api/user/sign_in","username=huobitebdc&password=jieyue201801",baseUrl);
           String userInfo= HttpUtils.post(baseUrl+"/api/user/get_dashboard",null,baseUrl);
        saveData(userInfo,"huobitebdc");

           String userJson= HttpUtils.post(baseUrl+"/api/user/get_info",null,baseUrl);
           Map usrMap = JSON.parseObject(userJson);
           if(null!= ((Map)usrMap.get("data")).get("sub_users")){
               JSONArray subUserList=(JSONArray)((Map)usrMap.get("data")).get("sub_users");
               for (int i=0;i<subUserList.size();i++){
                   String subUserName=(String)((Map)subUserList.get(i)).get("username");
                   HttpUtils.post(baseUrl+"/api/www/change_to_user","username="+subUserName,baseUrl);
                   userInfo= HttpUtils.post(baseUrl+"/api/user/get_dashboard",null,baseUrl);
                   saveData(userInfo,subUserName);
               }

           }
    }
    public void saveData(String json,String userName){
        Map um=(Map)JSON.parseObject(json).get("data");
        if(Double.parseDouble(um.get("today_paid").toString())>0||Double.parseDouble(um.get("today_bch_paid").toString())>0){

        MbSubUser su=mbSubUserDao.findMbSubUserByNameAndSiteId(userName,siteId);
        if(null==su){
            su=new MbSubUser();
            su.setName(userName);
            su.setSiteId(siteId);
            su.setStatus("01");
            mbSubUserDao.save(su);
        }

        MbUserInfo ui=new MbUserInfo();
        ui.setMbSubUser(su);
        ui.setPrv(Double.parseDouble(((Map)um.get("speed_in_24h")).get("valid_speed").toString()));
        ui.setBtc(Double.parseDouble(um.get("today_paid").toString()));
        ui.setBch(Double.parseDouble(um.get("today_bch_paid").toString()));
        ui.setRecTime(new Date());
        mbUserInfoDao.save(ui);
        }


    }
}

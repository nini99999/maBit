package com.poshist.maBit.service;

import com.poshist.maBit.entity.MbSite;
import com.poshist.maBit.entity.MbUserInfo;
import com.poshist.maBit.repository.MbSiteDao;
import com.poshist.maBit.utils.HttpUtils;
import com.poshist.maBit.utils.MatchUtils;
import com.poshist.maBit.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by poshist on 18-1-28.
 */
@Component
@EnableScheduling
public class GetData {
@Autowired
private MbSiteDao mbSiteDao;
@Autowired
private BtcTop btctop;
@Autowired
private BtcCom btcCom;

    @Scheduled(cron = "00 00 09 * * ?")
    @Scheduled(cron = "00 30 08 * * ?")
    @Scheduled(cron = "00 30 09 * * ?")
    @Scheduled(initialDelay = 1000,fixedDelay=86400000)
    public void getData(){
        MbSite top=mbSiteDao.findMbSiteByName("btc.top");
        if (!TimeUtils.getNowYYYYMMDD().equals(top.getLastTime())){
            try {
                btctop.getData();
                sendData("btc.top");
            } catch (IOException e) {
                e.printStackTrace();
            }
            top.setLastTime(TimeUtils.getNowYYYYMMDD());
            mbSiteDao.save(top);
        }
        top=mbSiteDao.findMbSiteByName("btc.com");
        if (!TimeUtils.getNowYYYYMMDD().equals(top.getLastTime())){
            try {
                btcCom.getData();
                sendData("btc.com");
            } catch (IOException e) {
                e.printStackTrace();
            }
            top.setLastTime(TimeUtils.getNowYYYYMMDD());
            mbSiteDao.save(top);
        }
    }
    public void sendData(String siteName) throws IOException {

        Double p = 1000000000000000d;
        Double btc=0d;
        Double bch=0d;
        Double btcPrv=0d;
        Double bchPrv=0d;
        //System.out.println( siteName + " " + TimeUtils.getNowYYYYMMDDHHMMSS() + " start");
        sendDataBytelegrm( "parse_mode=Markdown&chat_id=-298395231&text=" + siteName + " " + TimeUtils.getNowYYYYMMDDHHMMSS() + " start");
        List<MbUserInfo> list=null;
        if("btc.top".equals(siteName)) {
            list= btctop.getData(TimeUtils.getNowYYYYMMDD());
        }
     else if("btc.com".equals(siteName)){
            list= btcCom.getData(TimeUtils.getNowYYYYMMDD());
        }
        for (MbUserInfo info : list) {
            String str="";
            if(info.getMbSubUser().getId()==999l){
                str= "用户：BTCCOM汇总;昨日BTC收益:" + MatchUtils.doubleToStr(btc) + ";昨日BTH收益:" + MatchUtils.doubleToStr(bch) + ";24小时BTC算力:" + MatchUtils.doubleToStr((btcPrv / p))+ ";24小时BCH算力:" + MatchUtils.doubleToStr((bchPrv / p))
                +";昨日BTC返佣："+MatchUtils.doubleToStr(info.getBtc())+";昨日BCh返佣："+MatchUtils.doubleToStr(info.getBch())+";昨日BTC总收益："+MatchUtils.doubleToStr(info.getBtc()+btc)+";昨日BCH总收益："+MatchUtils.doubleToStr(info.getBch()+bch);
                if(btcPrv>0){
                    str=str+  ";昨日BTC总收益/P:" + MatchUtils.doubleToStr((info.getBtc()+btc) / (btcPrv / p));
                }else{
                    str=str+  ";昨日BTC收益/P:0";
                }
                if(bchPrv>0){
                    str=str + ";昨日BCH总收益/P:" + MatchUtils.doubleToStr((info.getBch()+bch) / (bchPrv / p)) + ";";
                }else{
                    str=str + ";昨日BCH收益/P:0";
                }

            }else {
         if(info.getPrv()!=0) {
              str= "用户：" + info.getMbSubUser().getName() + ";昨日BTC收益:" + MatchUtils.doubleToStr(info.getBtc()) + ";昨日BTH收益:" + MatchUtils.doubleToStr(info.getBch()) + ";24小时算力:" + MatchUtils.doubleToStr((info.getPrv() / p))
                     + ";昨日BTC收益/P:" + MatchUtils.doubleToStr(info.getBtc() / (info.getPrv() / p)) + ";昨日BCH收益/P:" + MatchUtils.doubleToStr(info.getBch() / (info.getPrv() / p)) + ";";
         if(info.getBch()==0){
             btcPrv=btcPrv+info.getPrv();
         }else {
             bchPrv=bchPrv+info.getPrv();
         }
         btc=btc+info.getBtc();
         bch=bch+info.getBch();
         }else{
             str= "用户：" + info.getMbSubUser().getName() + ";昨日BTC收益:" + MatchUtils.doubleToStr(info.getBtc()) + ";昨日BTH收益:" + MatchUtils.doubleToStr(info.getBch()) + ";24小时算力:0;昨日BTC收益/P:0;昨日BCH收益/P:0;";
         }
            }
            sendDataBytelegrm( "parse_mode=Markdown&chat_id=-298395231&text=" + str);
            //System.out.println( str);
        }
        sendDataBytelegrm( "parse_mode=Markdown&chat_id=-298395231&text=" + siteName + " end");
        //System.out.println( siteName + " end");
    }
    public void sendDataBytelegrm(String text) throws IOException {
        String url = "https://api.telegram.org/bot493436737:AAFVEXH2ueliwkLXIa3LILKTmlEwyWYc7P0/sendMessage";
        //HttpUtils.post(url, text, null);
        System.out.println("tele------------------"+text);
    }
}

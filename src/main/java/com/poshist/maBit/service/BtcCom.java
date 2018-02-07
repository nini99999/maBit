package com.poshist.maBit.service;

import com.alibaba.fastjson.JSON;
import com.poshist.maBit.entity.MbSubUser;
import com.poshist.maBit.entity.MbUserInfo;
import com.poshist.maBit.repository.MbSubUserDao;
import com.poshist.maBit.repository.MbuserInfoDao;
import com.poshist.maBit.utils.HttpUtils;
import com.poshist.maBit.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by poshist on 18-2-1.
 */
@Service
@Transactional
public class BtcCom {
    @Autowired
    private MbSubUserDao mbSubUserDao;
    @Autowired
    private MbuserInfoDao mbuserInfoDao;
    private String baseUrl="https://sz-bccpool.btc.com/v1/account/earn-stats";
    public List<MbUserInfo> getData(String dataStr){
        return mbuserInfoDao.findByRecTimeBetweenAndMbSubUser_SiteIdOrderByMbSubUserId(TimeUtils.stringToDate(dataStr+" 00:00:00"),TimeUtils.stringToDate(dataStr+" 23:59:59"),2l);
    }
    public void getData() throws IOException {
        List<MbSubUser> subList=mbSubUserDao.findBySiteId(2l);
        for (MbSubUser sub:subList){
            MbUserInfo info = new MbUserInfo();
            info.setMbSubUser(sub);
            info.setRecTime(new Date());
            info.setPrv(0d);
            info.setBch(0d);
            info.setBtc(0d);
            if(sub.getId()==999l) {
info.setBtc(getfy("https://btc.com/1FmTzzYCCpvVjatTcJxa2tk9GiuWp3T3kU   ","1FmTzzYCCpvVjatTcJxa2tk9GiuWp3T3kU"));
info.setBch(getfy("https://bch.btc.com/1NAfTShLy3DpV9fQFYkVvRT2u29SUZWizc","1NAfTShLy3DpV9fQFYkVvRT2u29SUZWizc"));
            }else{
                if (StringUtils.isNotEmpty(sub.getBtcDesc())) {
                    String infoJson = HttpUtils.get(baseUrl, sub.getBtcDesc(), null);
                    Map infoMap = (Map) JSON.parseObject(infoJson).get("data");
                    if (null != infoMap.get("earnings_yesterday")) {
                        info.setBtc(Double.parseDouble(infoMap.get("earnings_yesterday").toString()) / 100000000);
                        info.setPrv(Double.parseDouble(((Map) infoMap.get("hashrate_yesterday")).get("size").toString()) * 1000000000000000d);
                        info.setBch(0d);

                    }
                }

                if (StringUtils.isNotEmpty(sub.getBchDesc())) {
                    String infoJson = HttpUtils.get(baseUrl, sub.getBchDesc(), null);
                    Map infoMap = (Map) JSON.parseObject(infoJson).get("data");
                    if (null != infoMap.get("earnings_yesterday")) {
                        info.setBch(Double.parseDouble(infoMap.get("earnings_yesterday").toString()) / 100000000);
                        info.setPrv(Double.parseDouble(((Map) infoMap.get("hashrate_yesterday")).get("size").toString()) * 1000000000000000d);
                        info.setBtc(0d);
                    }
                }
            }
            mbuserInfoDao.save(info);

        }
    }

    public Double getfy(String url,String  address) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements tableElements = document.getElementsByClass("tx-item-summary-timestamp -btc-time-localization").get(0).getElementsByTag("span");
        System.out.println(tableElements.html().substring(0,10));
        if(!TimeUtils.getNowYYYYMMDD().equals(tableElements.html().substring(0,10))){
            return 0d;
        }
        Elements elements=document.getElementsByClass("txio").get(0).getElementsByTag("tr");
        document=Jsoup.parse(elements.html());
        elements=document.getElementsByTag("li");
        for(Element e:elements){
            if(e.html().indexOf(address)>0){
                String str=e.getElementsByClass("txio-amount").html();
                System.out.println( str.substring(0,str.indexOf("<")));
                return Double.parseDouble(str.substring(0,str.indexOf("<")));
            }

        }
        return 0d;
    }

}

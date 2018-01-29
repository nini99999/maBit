package com.poshist.maBit.service;

import com.poshist.maBit.entity.MbSite;
import com.poshist.maBit.repository.MbSiteDao;
import com.poshist.maBit.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;

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

    @Scheduled(cron = "00 00 08 * * ?")
    @Scheduled(cron = "00 30 08 * * ?")
    @Scheduled(cron = "00 30 08 * * ?")
    public void getData(){
        MbSite top=mbSiteDao.findMbSiteByName("btc.top");
        if (!TimeUtils.getNowYYYYMMDD().equals(top.getLastTime())){
            try {
                btctop.getData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            top.setLastTime(TimeUtils.getNowYYYYMMDD());
            mbSiteDao.save(top);
        }
    }
}

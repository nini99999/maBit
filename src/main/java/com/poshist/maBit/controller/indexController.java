package com.poshist.maBit.controller;

import com.poshist.maBit.entity.MbUserInfo;
import com.poshist.maBit.service.BtcCom;
import com.poshist.maBit.service.BtcTop;
import com.poshist.maBit.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by poshist on 18-1-28.
 */
@Controller("/")

public class indexController {
    @Autowired
    private BtcTop btcTop;
    @Autowired
    private BtcCom btcCom;
    @RequestMapping("/")
    public String index(Model model,String dateStr) {
        if (null==dateStr){
            dateStr= TimeUtils.getNowYYYYMMDD();
        }
        List<MbUserInfo> top=btcTop.getData(dateStr);
        List<MbUserInfo> com=btcCom.getData(dateStr);
        model.addAttribute("top",top);
        model.addAttribute("com",com);
        model.addAttribute("dateStr",dateStr);
        return "/index";
    }
}

package com.poshist.maBit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by poshist on 18-1-28.
 */
@Controller("/")

public class indexController {
    @RequestMapping("/")
    public String index(Model model) {
//
//        List<News> expert=newsServcie.getNews("02",22,1,8);
//        List<News> news=newsServcie.getNews("01",22,1,8);
//        List<News> welfare=newsServcie.getNews("03",22,1,7);
//        List<Video> videos=newsServcie.getVideo("01",22,1,8);
//        List<News> banner=newsServcie.getNews("04",22,1,4);
//
//        model.addAttribute("videos",videos);
//        model.addAttribute("expert",expert);
//        model.addAttribute("news",news);
//        model.addAttribute("welfare",welfare);
//        model.addAttribute("banner",banner);
        return "/index";
    }
}

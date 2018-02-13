package com.poshist.maBit.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by poshist on 18-2-7.
 */
public class Test {
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
//    public static void main(String[] agrs) throws IOException {
//        Test t=new Test();
//        t.getfy("https://bch.btc.com/1NAfTShLy3DpV9fQFYkVvRT2u29SUZWizc","1NAfTShLy3DpV9fQFYkVvRT2u29SUZWizc");
//        t.getfy("https://btc.com/1FmTzzYCCpvVjatTcJxa2tk9GiuWp3T3kU   ","1FmTzzYCCpvVjatTcJxa2tk9GiuWp3T3kU");
//    }
}

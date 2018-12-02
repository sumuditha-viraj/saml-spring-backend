package org.viraj.spring.saml.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.viraj.spring.saml.backend.model.Account;
import org.viraj.spring.saml.backend.model.SharePrice;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @RequestMapping("/getAccount")
    public Account account(@RequestParam(value="name") String name){
        return new Account(name);
    }

    @RequestMapping("/getSharePrices")
    public List<SharePrice> sharePrices(){
        List<SharePrice> sharePrices = new ArrayList<>();
        sharePrices.add(new SharePrice("AAPL", 178.58));
        sharePrices.add(new SharePrice("MSFT", 110.89));
        sharePrices.add(new SharePrice("AMZN", 1690.17));
        sharePrices.add(new SharePrice("GOOGL", 1109.65));
        return sharePrices;
    }

}

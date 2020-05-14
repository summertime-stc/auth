package com.example.stest.analysis.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Component
@FeignClient(name = "sso")
public interface OutService {
    @RequestMapping(value="/test",method = RequestMethod.GET)
    public String test();

}

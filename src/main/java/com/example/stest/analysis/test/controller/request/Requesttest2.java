package com.example.stest.analysis.test.controller.request;

import com.example.stest.common.controller.BaseRequest;
import lombok.Data;

public class Requesttest2 extends BaseRequest<Requesttest2.Requesttest2Bean> {

    private static final long serialVersionUID = 1L;

    @Data
    public class Requesttest2Bean {
        private String a;
    }
}
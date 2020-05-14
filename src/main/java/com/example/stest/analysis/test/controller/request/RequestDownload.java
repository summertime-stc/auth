package com.example.stest.analysis.test.controller.request;

import com.example.stest.common.controller.BaseRequest;

import lombok.Data;

/**
 * 添加反馈与分页请求类
 * @author stc
 * @date 2019年6月20日
 */
public class RequestDownload extends BaseRequest<RequestDownload.RequestFeedbackBean4> {
    private static final long serialVersionUID = 1L;

    @Data
    public class RequestFeedbackBean4{
        private String id;
    }
}

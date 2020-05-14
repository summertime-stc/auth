package com.example.stest.analysis.test.controller.response;


import com.example.stest.common.controller.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author zzb
 * @date 2019年3月12日
 */ 
public class Responsetest2 extends BaseResponse<Responsetest2.ResponseResponsetest2Bean> {
	private static final long serialVersionUID = 1L;
	public Responsetest2() {}
	public Responsetest2(Object content) {
		ResponseResponsetest2Bean responseBean = new ResponseResponsetest2Bean(content);
		this.setData(responseBean);
	}

	@Data
    @NoArgsConstructor
    @AllArgsConstructor
	public static class ResponseResponsetest2Bean{
	    private Object content;

	}

}

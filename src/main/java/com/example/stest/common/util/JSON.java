package com.example.stest.common.util;

import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;
import java.util.List;

public class JSON {
	public static String encode(Object obj) {
		return com.alibaba.fastjson.JSON.toJSON(obj).toString();
		
//		if(obj == null || obj.toString().equals("null")) return null;
//		if(obj != null && obj.getClass() == String.class){
//			return obj.toString();
//		}
//		JSONSerializer serializer = new JSONSerializer();
//		serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), Date.class);
//		serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), Timestamp.class);
//		return serializer.deepSerialize(obj);
	}
	public static Object decode(Object str) {
		List<HashMap<String, String>> mapList = com.alibaba.fastjson.JSON.parseObject(str.toString(), new TypeReference<List<HashMap<String, String>>>() {});
		return mapList;
//		String json = str == null ? null : str.toString();
//		if (StringUtil.isNullOrEmpty(json)) return "";
//		JSONDeserializer deserializer = new JSONDeserializer();
//		deserializer.use(String.class, new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"));		
//		Object obj = deserializer.deserialize(json);
//		if(obj != null && obj.getClass() == String.class){
//			return decode(obj.toString());
//		}		
//		return obj;
	}
	
	public static Object decode(String json) {
		List<HashMap<String, String>> mapList = com.alibaba.fastjson.JSON.parseObject(json, new TypeReference<List<HashMap<String, String>>>() {});
		return mapList;
//		if (StringUtil.isNullOrEmpty(json)) return "";
//		JSONDeserializer deserializer = new JSONDeserializer();
//		deserializer.use(String.class, new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"));		
//		Object obj = deserializer.deserialize(json);
//		if(obj != null && obj.getClass() == String.class){
//			return decode(obj.toString());
//		}		
//		return obj;
	}
}


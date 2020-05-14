//package com.example.stest.common.util;
//
//import org.apache.log4j.Logger;
//
//
//public class LOG {
//
//    /**
//     * 构造SERVER端日志记录器
//     */
//    private static Logger SERVER_LOG = Logger.getLogger("SERVER_LOGGER");
//    /**
//     * 构造PORTAL端日志记录器
//     */
//    private static Logger PORTAL_LOG = Logger.getLogger("PORTAL_LOGGER");
//    /**
//     * 构造WTC日志记录器
//     */
//    private static Logger WTC_LOG = Logger.getLogger("WTC_LOGGER");
//
//    /**
//     * 记录SERVER日志
//     *
//     * @param arg0
//     */
//	public static void WRITE_SERVER(String arg0) {
//    	SERVER_LOG.info("医院大数据集成与服务平台："+arg0);
//    }
//
//
//    /**
//     * 记录SERVER日志、异常
//     *
//     * @param arg0
//     * @param arg1
//     */
//    public static void WRITE_SERVER(String arg0, Throwable arg1) {
//    	SERVER_LOG.error("医院大数据集成与服务平台server异常日志 ："+arg0, arg1);
//    }
//
//    /**
//     * 记录PORTAL日志
//     *
//     * @param arg0
//     */
//    public static void WRITE_PORTAL(String arg0) {
//    	PORTAL_LOG.info("医院大数据集成与服务平台:"+arg0);
//    }
//
//    /**
//     * 记录PORTAL日志、异常
//     *
//     * @param arg0
//     * @param arg1
//     */
//    public static void WRITE_PORTAL(String arg0, Throwable arg1) {
//    	PORTAL_LOG.error("医院大数据集成与服务平台portal异常日志"+arg0, arg1);
//    }
//
//    /**
//     * 记录WTC日志
//     *
//     * @param arg0
//     */
//    public static void WRITE_WTC(String arg0) {
//    	WTC_LOG.info("医院大数据集成与服务平台："+arg0);
//    }
//
//    /**
//     * 记录WTC日志、异常
//     *
//     * @param arg0
//     * @param arg1
//     */
//    public static void WRITE_WTC(String arg0, Throwable arg1) {
//    	WTC_LOG.error("医院大数据集成与服务平台："+arg0, arg1);
//    }
//
//}

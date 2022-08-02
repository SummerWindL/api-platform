//package com.platform.api.common.server;
//
//import io.moquette.broker.subscriptions.Topic;
//
///**
// * 发布和收取权限接口
// * @author Advance
// * @date 2022年07月16日 10:51
// * @since V1.0.0
// */
//public interface IAuthorizator {
//    /**
//     * 可以控制某个用户的client，是否具有发布某个主题的权限，目前默认任何Client可以发布任主题
//     * @param topic
//     * @param user
//     * @param client
//     * @return
//     */
//    public boolean canWrite(Topic topic, String user, String client);
//
//    /**
//     * 可以控制某个用户的client，是否具有接收某个主题的权限，目前默认任何Client可以接收任何主题
//     * @param topic
//     * @param user
//     * @param client
//     * @return
//     */
//    public boolean canRead(Topic topic, String user, String client);
//}

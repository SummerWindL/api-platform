package com.platform.api.order;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class OrderServiceTest {
    @Test
    public void orderAsyncTest(){
        OrderService orderService = new OrderService();
        // 异步获取订单详情
        CompletableFuture<OrderDetailDTO> orderDetailFuture = orderService.getOrderDetailAsync(12345L);

        // 处理结果
        orderDetailFuture.thenAccept(orderDetail -> {
            System.out.println(orderDetail);
        }).exceptionally(throwable -> {
            System.err.println("获取订单详情失败: " + throwable.getMessage());
            return null;
        });
        // 主线程等待异步线程执行完成
        orderDetailFuture.join();
    }
}

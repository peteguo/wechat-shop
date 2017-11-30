package com.imooc.service;

import com.imooc.dto.OrderDTO;

/**
 * Created by guofei on 2017/11/21.
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}

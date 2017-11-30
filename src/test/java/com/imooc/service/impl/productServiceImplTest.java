package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.repository.ProductInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by guofei on 2017/11/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class productServiceImplTest {

    @Autowired ProductServiceImpl productService;


    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = productService.findOne("123456");

        Assert.assertNotNull(productInfo);

    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> reslut  = productService.findUpAll();
        Assert.assertNotEquals(0,reslut.size());

    }

    @Test
    public void findAll() throws Exception {
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        Assert.assertNotEquals(0,productInfoPage.getTotalElements());
        //System.out.println(productInfoPage.getTotalElements());
    }

    @Test
    public void save() throws Exception {

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123423");
        productInfo.setProductName("小皮吓");
        productInfo.setProductPrice(new BigDecimal(4.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("好好好吃");
        productInfo.setProductIcon("http://****.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);

        ProductInfo result = productService.save(productInfo);

        Assert.assertNotNull(result);

    }

}
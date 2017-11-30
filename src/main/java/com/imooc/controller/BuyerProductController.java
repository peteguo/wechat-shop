package com.imooc.controller;

import com.imooc.VO.ProductInfoVo;
import com.imooc.VO.ProductVo;
import com.imooc.VO.ResultVO;
import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by guofei on 2017/11/10.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //1.查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2.查询类目（一次性查询）


        /*List<Integer> categoryTypeList = new ArrayList<>();
        //传统方法
        for (ProductInfo productInfo : productInfoList
             ) {
            categoryTypeList.add(productInfo.getCategoryType());
        }*/

        //精简方法(java8,lambda)
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e ->e.getCategoryType())
                .collect(Collectors.toList());


        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据拼装

        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    //productInfoVo.setProductIcon(productInfo.getProductIcon());
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }

            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }


//        ResultVO resultVO = new ResultVO();
//        resultVO.setCode(0);
//        resultVO.setMsg("成功");

//        ProductVo productVo = new ProductVo();
//        resultVO.setData(Arrays.asList(productVo));
//
//        ProductInfoVo productInfoVo = new ProductInfoVo();
//        productVo.setProductInfoVoList(Arrays.asList(productInfoVo));

//        resultVO.setData(productVoList);
//        return resultVO;

           return ResultVOUtil.success(productVoList);

    }

}

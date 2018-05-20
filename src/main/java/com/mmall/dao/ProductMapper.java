package com.mmall.dao;

import com.mmall.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectList();

    List<Product> selectByNameAndProductId(@Param("productName")String productName, @Param("productId") Integer productId);

    List<Product> selectByNameAndCategoryIds(@Param("productName")String productName,@Param("categoryIdList")List<Integer> categoryIdList);


    List<Product> selectIdByCategoryId(@Param(value = "categoryId") Integer categoryId, @Param(value = "num") Integer num);


    //这里一定要用Integer，因为int无法为NULL，考虑到很多商品已经删除的情况。
    Integer selectStockByProductId(Integer id);

    //查找产品销量
    Integer selectSalesVolumeByProductId(Integer id);


    List<Product> selectRecommend(@Param(value = "productIdList") List<Integer> productIdList,@Param(value="orderby")String orderby);

    List<Product> selectRecommendGroupByCategoryId();
}
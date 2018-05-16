package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


/**
 * @author alone95
 * @date 2017/12/2
 */
@Service("iCategoryService")
@Slf4j
public class CategoryServiceImpl implements ICategoryService {



    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 增加品类节点
     * @param categoryName
     * @param parentId
     * @return
     */
    @Override
    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if(parentId == null || StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }

        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);//这个分类是可用的

        int rowCount = categoryMapper.insert(category);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    /**
     * 更新品类
     * @param categoryId
     * @param categoryName
     * @return
     */
    @Override
    public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
        if(categoryId == null || StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("更新品类参数错误");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if (rowCount > 0){
            return ServerResponse.createBySuccess("更新品类名字成功");
        }
        return ServerResponse.createByErrorMessage("更新品类名字失败");

    }

    /**
     * 获取品类子节点(平级)
     * @param categoryId
     * @return
     */
    @Override
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
       List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
       if (CollectionUtils.isEmpty(categoryList)){
            log.info("未找到当前分类的子分类");
       }
       return ServerResponse.createBySuccess(categoryList);
    }

    /**
     * 递归查询本节点id和子节点id
     * @param categoryId
     * @return
     */
    @Override
    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet,categoryId);

        List<Integer> categoryIdList = Lists.newArrayList();
        if (categoryId != null){
            for (Category categoryItem : categorySet){
                categoryIdList.add(categoryItem.getId());
            }
        }
        return  ServerResponse.createBySuccess(categoryIdList);
    }



    /**
     * 递归算法 算出子节点
     * @param categorySet
     * @param categoryId
     * @return
     */
    private Set<Category> findChildCategory(Set<Category>categorySet,Integer categoryId){
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category!=null){
            categorySet.add(category);
        }
        //查找子节点，递归算法要有一个退出的条件来退出
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for(Category categoryItem : categoryList){
            findChildCategory(categorySet,categoryItem.getId());
        }
        return categorySet;
    }
}

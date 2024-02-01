package com.guangjun.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guangjun.reggie.common.R;
import com.guangjun.reggie.entity.Category;
import com.guangjun.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 分类管理
 * @Author: GuangJun
 * @Date: 2024/2/1 22:15
 **/
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * @Description: 菜品分类
     * @Author: GuangJun
     * @Date: 2024/2/1 22:16
     * @Param: [category]
     * @Return: com.guangjun.reggie.common.R<java.lang.String>
     **/
    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("category:{}",category);
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    /**
     * @Description: 分页查询
     * @Author: GuangJun
     * @Date: 2024/2/1 22:17
     * @Param: [page, pageSize]
     * @Return: com.guangjun.reggie.common.R<com.baomidou.mybatisplus.extension.plugins.pagination.Page>
     **/
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){
        //分页构造器
        Page<Category> pageInfo = new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加排序条件，根据sort进行排序
        queryWrapper.orderByAsc(Category::getSort);

        //分页查询
        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * @Description: 根据id删除分类
     * @Author: GuangJun
     * @Date: 2024/2/1 22:17
     * @Param: [id]
     * @Return: com.guangjun.reggie.common.R<java.lang.String>
     **/
    @DeleteMapping
    public R<String> delete(Long id){
        log.info("删除分类，id为：{}",id);

        //categoryService.removeById(id);
        categoryService.remove(id);

        return R.success("分类信息删除成功");
    }

    /**
     * @Description: 根据id修改分类信息
     * @Author: GuangJun
     * @Date: 2024/2/1 22:17
     * @Param: [category]
     * @Return: com.guangjun.reggie.common.R<java.lang.String>
     **/
    @PutMapping
    public R<String> update(@RequestBody Category category){
        log.info("修改分类信息：{}",category);

        categoryService.updateById(category);

        return R.success("修改分类信息成功");
    }
}

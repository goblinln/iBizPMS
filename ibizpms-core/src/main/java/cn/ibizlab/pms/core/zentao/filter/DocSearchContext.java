package cn.ibizlab.pms.core.zentao.filter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.fastjson.annotation.JSONField;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;


import cn.ibizlab.pms.util.filter.QueryWrapperContext;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.ibizlab.pms.core.zentao.domain.Doc;
/**
 * 关系型数据实体[Doc] 查询条件对象
 */
@Slf4j
@Data
public class DocSearchContext extends QueryWrapperContext<Doc> {

	private String n_title_like;//[文档标题]
	public void setN_title_like(String n_title_like) {
        this.n_title_like = n_title_like;
        if(!ObjectUtils.isEmpty(this.n_title_like)){
            this.getSearchCond().like("`title`", n_title_like);
        }
    }
	private String n_type_eq;//[文档类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
        if(!ObjectUtils.isEmpty(this.n_type_eq)){
            this.getSearchCond().eq("`type`", n_type_eq);
        }
    }
	private String n_acl_eq;//[权限]
	public void setN_acl_eq(String n_acl_eq) {
        this.n_acl_eq = n_acl_eq;
        if(!ObjectUtils.isEmpty(this.n_acl_eq)){
            this.getSearchCond().eq("`acl`", n_acl_eq);
        }
    }
	private String n_collector_like;//[收藏者]
	public void setN_collector_like(String n_collector_like) {
        this.n_collector_like = n_collector_like;
        if(!ObjectUtils.isEmpty(this.n_collector_like)){
            this.getSearchCond().like("`collector`", n_collector_like);
        }
    }
	private String n_addedby_eq;//[由谁添加]
	public void setN_addedby_eq(String n_addedby_eq) {
        this.n_addedby_eq = n_addedby_eq;
        if(!ObjectUtils.isEmpty(this.n_addedby_eq)){
            this.getSearchCond().eq("`addedby`", n_addedby_eq);
        }
    }
	private String n_productname_eq;//[所属产品]
	public void setN_productname_eq(String n_productname_eq) {
        this.n_productname_eq = n_productname_eq;
        if(!ObjectUtils.isEmpty(this.n_productname_eq)){
            this.getSearchCond().eq("`productname`", n_productname_eq);
        }
    }
	private String n_productname_like;//[所属产品]
	public void setN_productname_like(String n_productname_like) {
        this.n_productname_like = n_productname_like;
        if(!ObjectUtils.isEmpty(this.n_productname_like)){
            this.getSearchCond().like("`productname`", n_productname_like);
        }
    }
	private String n_projectname_eq;//[所属项目]
	public void setN_projectname_eq(String n_projectname_eq) {
        this.n_projectname_eq = n_projectname_eq;
        if(!ObjectUtils.isEmpty(this.n_projectname_eq)){
            this.getSearchCond().eq("`projectname`", n_projectname_eq);
        }
    }
	private String n_projectname_like;//[所属项目]
	public void setN_projectname_like(String n_projectname_like) {
        this.n_projectname_like = n_projectname_like;
        if(!ObjectUtils.isEmpty(this.n_projectname_like)){
            this.getSearchCond().like("`projectname`", n_projectname_like);
        }
    }
	private String n_libname_eq;//[所属文档库]
	public void setN_libname_eq(String n_libname_eq) {
        this.n_libname_eq = n_libname_eq;
        if(!ObjectUtils.isEmpty(this.n_libname_eq)){
            this.getSearchCond().eq("`libname`", n_libname_eq);
        }
    }
	private String n_libname_like;//[所属文档库]
	public void setN_libname_like(String n_libname_like) {
        this.n_libname_like = n_libname_like;
        if(!ObjectUtils.isEmpty(this.n_libname_like)){
            this.getSearchCond().like("`libname`", n_libname_like);
        }
    }
	private String n_modulename_eq;//[模块分类]
	public void setN_modulename_eq(String n_modulename_eq) {
        this.n_modulename_eq = n_modulename_eq;
        if(!ObjectUtils.isEmpty(this.n_modulename_eq)){
            this.getSearchCond().eq("`modulename`", n_modulename_eq);
        }
    }
	private String n_modulename_like;//[模块分类]
	public void setN_modulename_like(String n_modulename_like) {
        this.n_modulename_like = n_modulename_like;
        if(!ObjectUtils.isEmpty(this.n_modulename_like)){
            this.getSearchCond().like("`modulename`", n_modulename_like);
        }
    }
	private Long n_lib_eq;//[所属文档库]
	public void setN_lib_eq(Long n_lib_eq) {
        this.n_lib_eq = n_lib_eq;
        if(!ObjectUtils.isEmpty(this.n_lib_eq)){
            this.getSearchCond().eq("`lib`", n_lib_eq);
        }
    }
	private Long n_project_eq;//[所属项目]
	public void setN_project_eq(Long n_project_eq) {
        this.n_project_eq = n_project_eq;
        if(!ObjectUtils.isEmpty(this.n_project_eq)){
            this.getSearchCond().eq("`project`", n_project_eq);
        }
    }
	private Long n_product_eq;//[所属产品]
	public void setN_product_eq(Long n_product_eq) {
        this.n_product_eq = n_product_eq;
        if(!ObjectUtils.isEmpty(this.n_product_eq)){
            this.getSearchCond().eq("`product`", n_product_eq);
        }
    }
	private Long n_module_eq;//[所属分类]
	public void setN_module_eq(Long n_module_eq) {
        this.n_module_eq = n_module_eq;
        if(!ObjectUtils.isEmpty(this.n_module_eq)){
            this.getSearchCond().eq("`module`", n_module_eq);
        }
    }

    /**
	 * 启用快速搜索
	 */
    @Override
	public void setQuery(String query)
	{
		 this.query=query;
		 if(!StringUtils.isEmpty(query)){
            this.getSearchCond().and( wrapper ->
                     wrapper.like("`title`", query)
            );
		 }
	}
}




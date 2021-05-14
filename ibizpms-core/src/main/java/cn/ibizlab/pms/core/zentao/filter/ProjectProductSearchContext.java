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
import cn.ibizlab.pms.core.zentao.domain.ProjectProduct;
/**
 * 关系型数据实体[ProjectProduct] 查询条件对象
 */
@Slf4j
@Data
public class ProjectProductSearchContext extends QueryWrapperContext<ProjectProduct> {

                
	private String n_id_eq;//[主键]
	public void setN_id_eq(String n_id_eq) {
        this.n_id_eq = n_id_eq;
    }
                
	private String n_productname_eq;//[产品]
	public void setN_productname_eq(String n_productname_eq) {
        this.n_productname_eq = n_productname_eq;
    }
                
	private String n_productname_like;//[产品]
	public void setN_productname_like(String n_productname_like) {
        this.n_productname_like = n_productname_like;
    }
                
	private String n_projectname_eq;//[项目]
	public void setN_projectname_eq(String n_projectname_eq) {
        this.n_projectname_eq = n_projectname_eq;
    }
                
	private String n_projectname_like;//[项目]
	public void setN_projectname_like(String n_projectname_like) {
        this.n_projectname_like = n_projectname_like;
    }
                
	private String n_planname_eq;//[计划名称]
	public void setN_planname_eq(String n_planname_eq) {
        this.n_planname_eq = n_planname_eq;
    }
                
	private String n_planname_like;//[计划名称]
	public void setN_planname_like(String n_planname_like) {
        this.n_planname_like = n_planname_like;
    }
                
	private Long n_product_eq;//[产品]
	public void setN_product_eq(Long n_product_eq) {
        this.n_product_eq = n_product_eq;
    }
                
	private Long n_plan_eq;//[产品计划]
	public void setN_plan_eq(Long n_plan_eq) {
        this.n_plan_eq = n_plan_eq;
    }
                
	private Long n_branch_eq;//[平台/分支]
	public void setN_branch_eq(Long n_branch_eq) {
        this.n_branch_eq = n_branch_eq;
    }
                
	private Long n_project_eq;//[项目]
	public void setN_project_eq(Long n_project_eq) {
        this.n_project_eq = n_project_eq;
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
                     wrapper.like("t11.`name`", query)
            );
		 }
	}
}




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
import cn.ibizlab.pms.core.zentao.domain.ProductPlan;
/**
 * 关系型数据实体[ProductPlan] 查询条件对象
 */
@Slf4j
@Data
public class ProductPlanSearchContext extends QueryWrapperContext<ProductPlan> {

	private String n_title_like;//[名称]
	public void setN_title_like(String n_title_like) {
        this.n_title_like = n_title_like;
        if(!ObjectUtils.isEmpty(this.n_title_like)){
            this.getSearchCond().like("`title`", n_title_like);
        }
    }
	private Long n_id_eq;//[编号]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
        if(!ObjectUtils.isEmpty(this.n_id_eq)){
            this.getSearchCond().eq("`id`", n_id_eq);
        }
    }
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_begin_gtandeq;//[开始日期]
	public void setN_begin_gtandeq(Timestamp n_begin_gtandeq) {
        this.n_begin_gtandeq = n_begin_gtandeq;
        if(!ObjectUtils.isEmpty(this.n_begin_gtandeq)){
            this.getSearchCond().ge("`begin`", n_begin_gtandeq);
        }
    }
	private String n_statuss_eq;//[状态]
	public void setN_statuss_eq(String n_statuss_eq) {
        this.n_statuss_eq = n_statuss_eq;
        if(!ObjectUtils.isEmpty(this.n_statuss_eq)){
            this.getSearchCond().eq("`statuss`", n_statuss_eq);
        }
    }
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_end_ltandeq;//[结束日期]
	public void setN_end_ltandeq(Timestamp n_end_ltandeq) {
        this.n_end_ltandeq = n_end_ltandeq;
        if(!ObjectUtils.isEmpty(this.n_end_ltandeq)){
            this.getSearchCond().le("`end`", n_end_ltandeq);
        }
    }
	private String n_plantemplet_eq;//[计划模板]
	public void setN_plantemplet_eq(String n_plantemplet_eq) {
        this.n_plantemplet_eq = n_plantemplet_eq;
        if(!ObjectUtils.isEmpty(this.n_plantemplet_eq)){
            this.getSearchCond().eq("`plantemplet`", n_plantemplet_eq);
        }
    }
	private String n_isexpired_eq;//[是否过期]
	public void setN_isexpired_eq(String n_isexpired_eq) {
        this.n_isexpired_eq = n_isexpired_eq;
        if(!ObjectUtils.isEmpty(this.n_isexpired_eq)){
            this.getSearchCond().eq("`isexpired`", n_isexpired_eq);
        }
    }
	private String n_future_eq;//[待定]
	public void setN_future_eq(String n_future_eq) {
        this.n_future_eq = n_future_eq;
        if(!ObjectUtils.isEmpty(this.n_future_eq)){
            this.getSearchCond().eq("`future`", n_future_eq);
        }
    }
	private String n_delta_eq;//[周期]
	public void setN_delta_eq(String n_delta_eq) {
        this.n_delta_eq = n_delta_eq;
        if(!ObjectUtils.isEmpty(this.n_delta_eq)){
            this.getSearchCond().eq("`delta`", n_delta_eq);
        }
    }
	private String n_parentname_eq;//[父计划名称]
	public void setN_parentname_eq(String n_parentname_eq) {
        this.n_parentname_eq = n_parentname_eq;
        if(!ObjectUtils.isEmpty(this.n_parentname_eq)){
            this.getSearchCond().eq("`parentname`", n_parentname_eq);
        }
    }
	private String n_parentname_like;//[父计划名称]
	public void setN_parentname_like(String n_parentname_like) {
        this.n_parentname_like = n_parentname_like;
        if(!ObjectUtils.isEmpty(this.n_parentname_like)){
            this.getSearchCond().like("`parentname`", n_parentname_like);
        }
    }
	private Long n_branch_eq;//[平台/分支]
	public void setN_branch_eq(Long n_branch_eq) {
        this.n_branch_eq = n_branch_eq;
        if(!ObjectUtils.isEmpty(this.n_branch_eq)){
            this.getSearchCond().eq("`branch`", n_branch_eq);
        }
    }
	private Long n_parent_eq;//[父计划]
	public void setN_parent_eq(Long n_parent_eq) {
        this.n_parent_eq = n_parent_eq;
        if(!ObjectUtils.isEmpty(this.n_parent_eq)){
            this.getSearchCond().eq("`parent`", n_parent_eq);
        }
    }
	private Long n_product_eq;//[产品]
	public void setN_product_eq(Long n_product_eq) {
        this.n_product_eq = n_product_eq;
        if(!ObjectUtils.isEmpty(this.n_product_eq)){
            this.getSearchCond().eq("`product`", n_product_eq);
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




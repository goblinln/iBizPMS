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
import cn.ibizlab.pms.core.zentao.domain.Product;
/**
 * 关系型数据实体[Product] 查询条件对象
 */
@Slf4j
@Data
public class ProductSearchContext extends QueryWrapperContext<Product> {

	private Integer n_istop_eq;//[是否置顶]
	public void setN_istop_eq(Integer n_istop_eq) {
        this.n_istop_eq = n_istop_eq;
        if(!ObjectUtils.isEmpty(this.n_istop_eq)){
            this.getSearchCond().eq("`istop`", n_istop_eq);
        }
    }
	private String n_productclass_eq;//[产品分类]
	public void setN_productclass_eq(String n_productclass_eq) {
        this.n_productclass_eq = n_productclass_eq;
        if(!ObjectUtils.isEmpty(this.n_productclass_eq)){
            this.getSearchCond().eq("`productclass`", n_productclass_eq);
        }
    }
	private String n_acl_eq;//[访问控制]
	public void setN_acl_eq(String n_acl_eq) {
        this.n_acl_eq = n_acl_eq;
        if(!ObjectUtils.isEmpty(this.n_acl_eq)){
            this.getSearchCond().eq("`acl`", n_acl_eq);
        }
    }
	private String n_name_eq;//[产品名称]
	public void setN_name_eq(String n_name_eq) {
        this.n_name_eq = n_name_eq;
        if(!ObjectUtils.isEmpty(this.n_name_eq)){
            this.getSearchCond().eq("`name`", n_name_eq);
        }
    }
	private String n_name_like;//[产品名称]
	public void setN_name_like(String n_name_like) {
        this.n_name_like = n_name_like;
        if(!ObjectUtils.isEmpty(this.n_name_like)){
            this.getSearchCond().like("`name`", n_name_like);
        }
    }
	private Long n_id_eq;//[编号]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
        if(!ObjectUtils.isEmpty(this.n_id_eq)){
            this.getSearchCond().eq("`id`", n_id_eq);
        }
    }
	private String n_id_in;//[编号]
	public void setN_id_in(String n_id_in) {
        this.n_id_in = n_id_in;
        if(!ObjectUtils.isEmpty(this.n_id_in)){
			this.getSearchCond().in("`id`",this.n_id_in.split(";"));
        }
    }
	private String n_type_eq;//[产品类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
        if(!ObjectUtils.isEmpty(this.n_type_eq)){
            this.getSearchCond().eq("`type`", n_type_eq);
        }
    }
	private String n_status_eq;//[状态]
	public void setN_status_eq(String n_status_eq) {
        this.n_status_eq = n_status_eq;
        if(!ObjectUtils.isEmpty(this.n_status_eq)){
            this.getSearchCond().eq("`status`", n_status_eq);
        }
    }
	private String n_status_isnotnull;//[状态]
	public void setN_status_isnotnull(String n_status_isnotnull) {
        this.n_status_isnotnull = n_status_isnotnull;
        if(!ObjectUtils.isEmpty(this.n_status_isnotnull)){
			if(this.n_status_isnotnull.equals("1")){
				this.getSearchCond().isNotNull("`status`");
			}
        }
    }
	private String n_linename_eq;//[产品线]
	public void setN_linename_eq(String n_linename_eq) {
        this.n_linename_eq = n_linename_eq;
        if(!ObjectUtils.isEmpty(this.n_linename_eq)){
            this.getSearchCond().eq("`linename`", n_linename_eq);
        }
    }
	private String n_linename_like;//[产品线]
	public void setN_linename_like(String n_linename_like) {
        this.n_linename_like = n_linename_like;
        if(!ObjectUtils.isEmpty(this.n_linename_like)){
            this.getSearchCond().like("`linename`", n_linename_like);
        }
    }
	private Long n_line_eq;//[产品线]
	public void setN_line_eq(Long n_line_eq) {
        this.n_line_eq = n_line_eq;
        if(!ObjectUtils.isEmpty(this.n_line_eq)){
            this.getSearchCond().eq("`line`", n_line_eq);
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
                     wrapper.like("`name`", query)
                        .or().like("`id`", query)
                        .or().like("`code`", query)
            );
		 }
	}
}




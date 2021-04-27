package cn.ibizlab.pms.core.ibiz.filter;

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
import cn.ibizlab.pms.core.ibiz.domain.CaseStats;
/**
 * 关系型数据实体[CaseStats] 查询条件对象
 */
@Slf4j
@Data
public class CaseStatsSearchContext extends QueryWrapperContext<CaseStats> {

	private String n_title_like;//[用例标题]
	public void setN_title_like(String n_title_like) {
        this.n_title_like = n_title_like;
        if(!ObjectUtils.isEmpty(this.n_title_like)){
            this.getSearchCond().like("`title`", n_title_like);
        }
    }
	private Long n_id_eq;//[用例编号]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
        if(!ObjectUtils.isEmpty(this.n_id_eq)){
            this.getSearchCond().eq("`id`", n_id_eq);
        }
    }
	private String n_modulename_eq;//[模块名称]
	public void setN_modulename_eq(String n_modulename_eq) {
        this.n_modulename_eq = n_modulename_eq;
        if(!ObjectUtils.isEmpty(this.n_modulename_eq)){
            this.getSearchCond().eq("`modulename`", n_modulename_eq);
        }
    }
	private String n_modulename_like;//[模块名称]
	public void setN_modulename_like(String n_modulename_like) {
        this.n_modulename_like = n_modulename_like;
        if(!ObjectUtils.isEmpty(this.n_modulename_like)){
            this.getSearchCond().like("`modulename`", n_modulename_like);
        }
    }
	private Long n_product_eq;//[产品]
	public void setN_product_eq(Long n_product_eq) {
        this.n_product_eq = n_product_eq;
        if(!ObjectUtils.isEmpty(this.n_product_eq)){
            this.getSearchCond().eq("`product`", n_product_eq);
        }
    }
	private Long n_module_eq;//[模块]
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




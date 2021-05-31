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
import cn.ibizlab.pms.core.ibiz.domain.ProductSum;
/**
 * 关系型数据实体[ProductSum] 查询条件对象
 */
@Slf4j
@Data
public class ProductSumSearchContext extends QueryWrapperContext<ProductSum> {

                
	private String n_po_eq;//[产品负责人]
	public void setN_po_eq(String n_po_eq) {
        this.n_po_eq = n_po_eq;
    }
                
	private Long n_id_eq;//[主键标识]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
    }
                
	private Long n_plan_eq;//[计划]
	public void setN_plan_eq(Long n_plan_eq) {
        this.n_plan_eq = n_plan_eq;
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
                     wrapper.like("name", query)
            );
		 }
	}
}




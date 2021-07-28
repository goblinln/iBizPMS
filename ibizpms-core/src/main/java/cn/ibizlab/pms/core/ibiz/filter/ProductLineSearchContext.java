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
import cn.ibizlab.pms.core.ibiz.domain.ProductLine;
/**
 * 关系型数据实体[ProductLine] 查询条件对象
 */
@Slf4j
@Data
public class ProductLineSearchContext extends QueryWrapperContext<ProductLine> {

                
	private String n_ibz_productlinename_like;//[产品线名称]
	public void setN_ibz_productlinename_like(String n_ibz_productlinename_like) {
        this.n_ibz_productlinename_like = n_ibz_productlinename_like;
    }
                
	private String n_ibz_productlineid_eq;//[产品线标识]
	public void setN_ibz_productlineid_eq(String n_ibz_productlineid_eq) {
        this.n_ibz_productlineid_eq = n_ibz_productlineid_eq;
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
                     wrapper.like("ibz_productlinename", query)
            );
		 }
	}
}




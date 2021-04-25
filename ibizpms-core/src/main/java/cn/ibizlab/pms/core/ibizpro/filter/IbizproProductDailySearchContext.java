package cn.ibizlab.pms.core.ibizpro.filter;

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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductDaily;
/**
 * 关系型数据实体[IbizproProductDaily] 查询条件对象
 */
@Slf4j
@Data
public class IbizproProductDailySearchContext extends QueryWrapperContext<IbizproProductDaily> {

	private String n_ibizpro_productdailyname_like;//[产品日报名称]
	public void setN_ibizpro_productdailyname_like(String n_ibizpro_productdailyname_like) {
        this.n_ibizpro_productdailyname_like = n_ibizpro_productdailyname_like;
    }
	private Long n_ibizpro_productdailyid_eq;//[产品日报标识]
	public void setN_ibizpro_productdailyid_eq(Long n_ibizpro_productdailyid_eq) {
        this.n_ibizpro_productdailyid_eq = n_ibizpro_productdailyid_eq;
    }
	private String n_po_eq;//[产品负责人]
	public void setN_po_eq(String n_po_eq) {
        this.n_po_eq = n_po_eq;
    }
	private String n_productname_eq;//[产品名称]
	public void setN_productname_eq(String n_productname_eq) {
        this.n_productname_eq = n_productname_eq;
    }
	private String n_productname_like;//[产品名称]
	public void setN_productname_like(String n_productname_like) {
        this.n_productname_like = n_productname_like;
    }
	private Long n_product_eq;//[产品]
	public void setN_product_eq(Long n_product_eq) {
        this.n_product_eq = n_product_eq;
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
                     wrapper.like("t1.`IBIZPRO_PRODUCTDAILYNAME`", query)
            );
		 }
	}
}




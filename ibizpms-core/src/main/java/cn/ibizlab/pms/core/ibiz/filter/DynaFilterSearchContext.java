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
import cn.ibizlab.pms.core.ibiz.domain.DynaFilter;
/**
 * 关系型数据实体[DynaFilter] 查询条件对象
 */
@Slf4j
@Data
public class DynaFilterSearchContext extends QueryWrapperContext<DynaFilter> {

	private String n_dynafilterid_eq;//[动态搜索栏标识]
	public void setN_dynafilterid_eq(String n_dynafilterid_eq) {
        this.n_dynafilterid_eq = n_dynafilterid_eq;
    }
	private String n_dynafiltername_like;//[动态搜索栏名称]
	public void setN_dynafiltername_like(String n_dynafiltername_like) {
        this.n_dynafiltername_like = n_dynafiltername_like;
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
                     wrapper.like("t1.`DYNAFILTERNAME`", query)
            );
		 }
	}
}




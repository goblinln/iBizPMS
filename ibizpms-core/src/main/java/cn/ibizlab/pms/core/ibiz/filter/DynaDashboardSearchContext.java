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
import cn.ibizlab.pms.core.ibiz.domain.DynaDashboard;
/**
 * 关系型数据实体[DynaDashboard] 查询条件对象
 */
@Slf4j
@Data
public class DynaDashboardSearchContext extends QueryWrapperContext<DynaDashboard> {

                
	private String n_dynadashboardid_eq;//[动态数据看板标识]
	public void setN_dynadashboardid_eq(String n_dynadashboardid_eq) {
        this.n_dynadashboardid_eq = n_dynadashboardid_eq;
    }
                
	private String n_dynadashboardname_like;//[动态数据看板名称]
	public void setN_dynadashboardname_like(String n_dynadashboardname_like) {
        this.n_dynadashboardname_like = n_dynadashboardname_like;
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
                     wrapper.like("dynadashboardname", query)
            );
		 }
	}
}




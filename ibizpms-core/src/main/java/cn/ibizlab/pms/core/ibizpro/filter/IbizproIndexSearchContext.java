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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproIndex;
/**
 * 关系型数据实体[IbizproIndex] 查询条件对象
 */
@Slf4j
@Data
public class IbizproIndexSearchContext extends QueryWrapperContext<IbizproIndex> {

                
	private Long n_indexid_eq;//[主键]
	public void setN_indexid_eq(Long n_indexid_eq) {
        this.n_indexid_eq = n_indexid_eq;
    }
                
	private String n_indexname_like;//[标题[需求、任务等]]
	public void setN_indexname_like(String n_indexname_like) {
        this.n_indexname_like = n_indexname_like;
    }
                
	private String n_index_type_eq;//[类型]
	public void setN_index_type_eq(String n_index_type_eq) {
        this.n_index_type_eq = n_index_type_eq;
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
                     wrapper.like("indexname", query)
                        .or().like("indexdesc", query)
            );
		 }
	}
}




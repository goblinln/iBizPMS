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
import cn.ibizlab.pms.core.ibizpro.domain.IBZProSystem;
/**
 * 关系型数据实体[IBZProSystem] 查询条件对象
 */
@Slf4j
@Data
public class IBZProSystemSearchContext extends QueryWrapperContext<IBZProSystem> {

	private String n_ibzpro_systemname_like;//[运行生产系统名称]
	public void setN_ibzpro_systemname_like(String n_ibzpro_systemname_like) {
        this.n_ibzpro_systemname_like = n_ibzpro_systemname_like;
        if(!ObjectUtils.isEmpty(this.n_ibzpro_systemname_like)){
            this.getSearchCond().like("`ibzpro_systemname`", n_ibzpro_systemname_like);
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
                     wrapper.like("`ibzpro_systemname`", query)
            );
		 }
	}
}




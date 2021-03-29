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
import cn.ibizlab.pms.core.ibiz.domain.IbiLogin;
/**
 * 关系型数据实体[IbiLogin] 查询条件对象
 */
@Slf4j
@Data
public class IbiLoginSearchContext extends QueryWrapperContext<IbiLogin> {

	private String n_realname_like;//[真实姓名]
	public void setN_realname_like(String n_realname_like) {
        this.n_realname_like = n_realname_like;
        if(!ObjectUtils.isEmpty(this.n_realname_like)){
            this.getSearchCond().like("`realname`", n_realname_like);
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
                     wrapper.like("`realname`", query)
            );
		 }
	}
}




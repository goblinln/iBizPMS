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
import cn.ibizlab.pms.core.ibiz.domain.UserYearWorkStats;
/**
 * 关系型数据实体[UserYearWorkStats] 查询条件对象
 */
@Slf4j
@Data
public class UserYearWorkStatsSearchContext extends QueryWrapperContext<UserYearWorkStats> {

	private String n_realname_like;//[真实用户名]
	public void setN_realname_like(String n_realname_like) {
        this.n_realname_like = n_realname_like;
    }
	private String n_dept_eq;//[部门编号]
	public void setN_dept_eq(String n_dept_eq) {
        this.n_dept_eq = n_dept_eq;
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
                     wrapper.like("title", query)
            );
		 }
	}
}




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
import cn.ibizlab.pms.core.ibiz.domain.BugStats;
/**
 * 关系型数据实体[BugStats] 查询条件对象
 */
@Slf4j
@Data
public class BugStatsSearchContext extends QueryWrapperContext<BugStats> {

	private String n_title_like;//[名称]
	public void setN_title_like(String n_title_like) {
        this.n_title_like = n_title_like;
        if(!ObjectUtils.isEmpty(this.n_title_like)){
            this.getSearchCond().like("title", n_title_like);
        }
    }
	private String n_openedby_eq;//[由谁创建]
	public void setN_openedby_eq(String n_openedby_eq) {
        this.n_openedby_eq = n_openedby_eq;
        if(!ObjectUtils.isEmpty(this.n_openedby_eq)){
            this.getSearchCond().eq("openedby", n_openedby_eq);
        }
    }
	private Integer n_bugwillnotfix_ltandeq;//[不予解决]
	public void setN_bugwillnotfix_ltandeq(Integer n_bugwillnotfix_ltandeq) {
        this.n_bugwillnotfix_ltandeq = n_bugwillnotfix_ltandeq;
        if(!ObjectUtils.isEmpty(this.n_bugwillnotfix_ltandeq)){
            this.getSearchCond().le("bugwillnotfix", n_bugwillnotfix_ltandeq);
        }
    }
	private Long n_product_eq;//[编号]
	public void setN_product_eq(Long n_product_eq) {
        this.n_product_eq = n_product_eq;
        if(!ObjectUtils.isEmpty(this.n_product_eq)){
            this.getSearchCond().eq("product", n_product_eq);
        }
    }
	private String n_assignedto_eq;//[指派给]
	public void setN_assignedto_eq(String n_assignedto_eq) {
        this.n_assignedto_eq = n_assignedto_eq;
        if(!ObjectUtils.isEmpty(this.n_assignedto_eq)){
            this.getSearchCond().eq("assignedto", n_assignedto_eq);
        }
    }

    /**
	 * 启用快速搜索
	 */
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




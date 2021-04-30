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

	private Integer n_bugwillnotfix_ltandeq;//[不予解决]
	public void setN_bugwillnotfix_ltandeq(Integer n_bugwillnotfix_ltandeq) {
        this.n_bugwillnotfix_ltandeq = n_bugwillnotfix_ltandeq;
    }
	private String n_resolvedby_eq;//[由谁解决]
	public void setN_resolvedby_eq(String n_resolvedby_eq) {
        this.n_resolvedby_eq = n_resolvedby_eq;
    }
	private String n_dept_eq;//[部门]
	public void setN_dept_eq(String n_dept_eq) {
        this.n_dept_eq = n_dept_eq;
    }
	private String n_assignedto_eq;//[指派给]
	public void setN_assignedto_eq(String n_assignedto_eq) {
        this.n_assignedto_eq = n_assignedto_eq;
    }
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_begin_gtandeq;//[开始]
	public void setN_begin_gtandeq(Timestamp n_begin_gtandeq) {
        this.n_begin_gtandeq = n_begin_gtandeq;
    }
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_bugresolveddate_gtandeq;//[bug解决日期]
	public void setN_bugresolveddate_gtandeq(Timestamp n_bugresolveddate_gtandeq) {
        this.n_bugresolveddate_gtandeq = n_bugresolveddate_gtandeq;
    }
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_bugresolveddate_ltandeq;//[bug解决日期]
	public void setN_bugresolveddate_ltandeq(Timestamp n_bugresolveddate_ltandeq) {
        this.n_bugresolveddate_ltandeq = n_bugresolveddate_ltandeq;
    }
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_end_ltandeq;//[结束]
	public void setN_end_ltandeq(Timestamp n_end_ltandeq) {
        this.n_end_ltandeq = n_end_ltandeq;
    }
	private Long n_id_eq;//[标识]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
    }
	private String n_title_like;//[名称]
	public void setN_title_like(String n_title_like) {
        this.n_title_like = n_title_like;
    }
	private String n_openedby_eq;//[由谁创建]
	public void setN_openedby_eq(String n_openedby_eq) {
        this.n_openedby_eq = n_openedby_eq;
    }
	private Long n_product_eq;//[编号]
	public void setN_product_eq(Long n_product_eq) {
        this.n_product_eq = n_product_eq;
    }
	private Long n_project_eq;//[项目编号]
	public void setN_project_eq(Long n_project_eq) {
        this.n_project_eq = n_project_eq;
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




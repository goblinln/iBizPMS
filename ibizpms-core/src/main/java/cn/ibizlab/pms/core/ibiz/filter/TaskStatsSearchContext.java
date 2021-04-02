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
import cn.ibizlab.pms.core.ibiz.domain.TaskStats;
/**
 * 关系型数据实体[TaskStats] 查询条件对象
 */
@Slf4j
@Data
public class TaskStatsSearchContext extends QueryWrapperContext<TaskStats> {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp n_end_ltandeq;//[结束]
	public void setN_end_ltandeq(Timestamp n_end_ltandeq) {
        this.n_end_ltandeq = n_end_ltandeq;
        if(!ObjectUtils.isEmpty(this.n_end_ltandeq)){
            this.getSearchCond().le("`end`", n_end_ltandeq);
        }
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp n_begin_gtandeq;//[属性]
	public void setN_begin_gtandeq(Timestamp n_begin_gtandeq) {
        this.n_begin_gtandeq = n_begin_gtandeq;
        if(!ObjectUtils.isEmpty(this.n_begin_gtandeq)){
            this.getSearchCond().ge("`begin`", n_begin_gtandeq);
        }
    }
	private String n_dept_eq;//[部门]
	public void setN_dept_eq(String n_dept_eq) {
        this.n_dept_eq = n_dept_eq;
        if(!ObjectUtils.isEmpty(this.n_dept_eq)){
            this.getSearchCond().eq("`dept`", n_dept_eq);
        }
    }
	private String n_name_like;//[名称]
	public void setN_name_like(String n_name_like) {
        this.n_name_like = n_name_like;
        if(!ObjectUtils.isEmpty(this.n_name_like)){
            this.getSearchCond().like("`name`", n_name_like);
        }
    }
	private String n_project_eq;//[项目]
	public void setN_project_eq(String n_project_eq) {
        this.n_project_eq = n_project_eq;
        if(!ObjectUtils.isEmpty(this.n_project_eq)){
            this.getSearchCond().eq("`project`", n_project_eq);
        }
    }
	private String n_finishedby_eq;//[完成者]
	public void setN_finishedby_eq(String n_finishedby_eq) {
        this.n_finishedby_eq = n_finishedby_eq;
        if(!ObjectUtils.isEmpty(this.n_finishedby_eq)){
            this.getSearchCond().eq("`finishedby`", n_finishedby_eq);
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
                     wrapper.like("`name`", query)
            );
		 }
	}
}




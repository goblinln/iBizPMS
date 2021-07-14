package cn.ibizlab.pms.core.zentao.filter;

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
import cn.ibizlab.pms.core.zentao.domain.TaskEstimate;
/**
 * 关系型数据实体[TaskEstimate] 查询条件对象
 */
@Slf4j
@Data
public class TaskEstimateSearchContext extends QueryWrapperContext<TaskEstimate> {

	private String n_year_eq;//[年]
	public void setN_year_eq(String n_year_eq) {
        this.n_year_eq = n_year_eq;
        if(!ObjectUtils.isEmpty(this.n_year_eq)){
            this.getSearchCond().eq("`year`", n_year_eq);
        }
    }
	private String n_account_eq;//[用户]
	public void setN_account_eq(String n_account_eq) {
        this.n_account_eq = n_account_eq;
        if(!ObjectUtils.isEmpty(this.n_account_eq)){
            this.getSearchCond().eq("`account`", n_account_eq);
        }
    }
	private Long n_id_like;//[编号]
	public void setN_id_like(Long n_id_like) {
        this.n_id_like = n_id_like;
        if(!ObjectUtils.isEmpty(this.n_id_like)){
            this.getSearchCond().like("`id`", n_id_like);
        }
    }
	private Long n_id_eq;//[编号]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
        if(!ObjectUtils.isEmpty(this.n_id_eq)){
            this.getSearchCond().eq("`id`", n_id_eq);
        }
    }
	private String n_evaluationstatus_eq;//[评估状态]
	public void setN_evaluationstatus_eq(String n_evaluationstatus_eq) {
        this.n_evaluationstatus_eq = n_evaluationstatus_eq;
        if(!ObjectUtils.isEmpty(this.n_evaluationstatus_eq)){
            this.getSearchCond().eq("`evaluationstatus`", n_evaluationstatus_eq);
        }
    }
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_date_gtandeq;//[日期]
	public void setN_date_gtandeq(Timestamp n_date_gtandeq) {
        this.n_date_gtandeq = n_date_gtandeq;
        if(!ObjectUtils.isEmpty(this.n_date_gtandeq)){
            this.getSearchCond().ge("`date`", n_date_gtandeq);
        }
    }
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd")
	private Timestamp n_date_ltandeq;//[日期]
	public void setN_date_ltandeq(Timestamp n_date_ltandeq) {
        this.n_date_ltandeq = n_date_ltandeq;
        if(!ObjectUtils.isEmpty(this.n_date_ltandeq)){
            this.getSearchCond().le("`date`", n_date_ltandeq);
        }
    }
	private String n_month_eq;//[月]
	public void setN_month_eq(String n_month_eq) {
        this.n_month_eq = n_month_eq;
        if(!ObjectUtils.isEmpty(this.n_month_eq)){
            this.getSearchCond().eq("`month`", n_month_eq);
        }
    }
	private String n_taskname_eq;//[任务名称]
	public void setN_taskname_eq(String n_taskname_eq) {
        this.n_taskname_eq = n_taskname_eq;
        if(!ObjectUtils.isEmpty(this.n_taskname_eq)){
            this.getSearchCond().eq("`taskname`", n_taskname_eq);
        }
    }
	private String n_taskname_like;//[任务名称]
	public void setN_taskname_like(String n_taskname_like) {
        this.n_taskname_like = n_taskname_like;
        if(!ObjectUtils.isEmpty(this.n_taskname_like)){
            this.getSearchCond().like("`taskname`", n_taskname_like);
        }
    }
	private Long n_project_eq;//[项目]
	public void setN_project_eq(Long n_project_eq) {
        this.n_project_eq = n_project_eq;
        if(!ObjectUtils.isEmpty(this.n_project_eq)){
            this.getSearchCond().eq("`project`", n_project_eq);
        }
    }
	private Long n_task_eq;//[任务]
	public void setN_task_eq(Long n_task_eq) {
        this.n_task_eq = n_task_eq;
        if(!ObjectUtils.isEmpty(this.n_task_eq)){
            this.getSearchCond().eq("`task`", n_task_eq);
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
                     wrapper.like("`id`", query)
            );
		 }
	}
}




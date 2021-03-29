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
	private Long n_id_like;//[编号]
	public void setN_id_like(Long n_id_like) {
        this.n_id_like = n_id_like;
        if(!ObjectUtils.isEmpty(this.n_id_like)){
            this.getSearchCond().like("`id`", n_id_like);
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



